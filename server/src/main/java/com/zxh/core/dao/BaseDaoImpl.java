package com.zxh.core.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.zxh.core.annotation.Ignore;
import com.zxh.core.annotation.PrimaryKey;
import com.zxh.core.annotation.Table;
import com.zxh.core.annotation.TableColumn;
import com.zxh.core.util.GenericsUtils;
import com.zxh.core.util.ReflectionUtils;
import com.zxh.core.util.SQLGenerator;

/**
 * 基本Dao
 * @author zxh
 * 2018年2月27日
 * @param <T>
 * @param <PK>
 */
@Repository
public class BaseDaoImpl<T extends Serializable,PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T,PK>{
	
	private Class<T>  entityClass;
	//主键的序列,一般批量操作，返回值
	String seq;
	//实体类主键名称
    String pkName;
    //实体类ID字段名称
    String idName;
	//字段名，如果未标记列名则取字段名,(列名和字段名有时不一样)
    String fieldName;
    //列名
    String columnName;
    String tableName;
    SQLGenerator<T> sqlGenerator;
    
    /**
     * 作cache 结构{T类的镜像,{数据库列名,实体字段名}}
     */
    private static final Map<Class<?>, Map<String, String>>    classFieldMap    = new HashMap<Class<?>, Map<String, String>>();
    
    Map<String, String> columnFieldNamesMap;   
   // Map<String,Object> excelMap;
    
    @SuppressWarnings(value = "unchecked")
	public  BaseDaoImpl() {
		
		this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());
		columnFieldNamesMap = classFieldMap.get(entityClass);
        if (null == columnFieldNamesMap) {
        	columnFieldNamesMap = new LinkedHashMap<String, String>();
            classFieldMap.put(entityClass, columnFieldNamesMap);
        }
        
		Field[] file = entityClass.getDeclaredFields();
	        
		for(Field field : file){
			if (field.isAnnotationPresent(Ignore.class)) {
                continue;
            }
			fieldName = field.getName();
			TableColumn tableColumn = field.getAnnotation(TableColumn.class);
		    if (tableColumn!=null) {
                columnName = tableColumn.value();
            } else {
                columnName = null;
            }
			  
			columnName = (StringUtils.isEmpty(columnName) ? fieldName : columnName);
			if(field.isAnnotationPresent(PrimaryKey.class)){
				idName = fieldName;
				pkName = columnName;
				PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
				if(null!=primaryKey.value()){
					pkName = primaryKey.value();
					columnName = primaryKey.value();
				}
			}
			columnFieldNamesMap.put(columnName, fieldName);  
			  
		}
	
		Table table = this.entityClass.getAnnotation(Table.class);
		if(table!=null){
			tableName = table.value();
		}
		
		sqlGenerator = new SQLGenerator<T>(columnFieldNamesMap,tableName,pkName);
	}
    @Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }
	
	@Override
	public int insert(T t) {
		String sql_insert = sqlGenerator.sql_insert(t);
		System.out.println(sql_insert);
		System.out.println(entityClass.getName());
		return getSqlSession().insert("base.create",
				sql_insert );
	}
	
	@Override
	public int delete(PK id) {
		return getSqlSession().insert("base.delete",
                sqlGenerator.sql_deleteById(id));
	}
	
	@Override
	public int update(T t) {
		return getSqlSession().update("base.modify",
                sqlGenerator.sql_update(t, columnFieldNamesMap));
	}
	
	@Override
	public List<T> findAll() {
		List<Map<String, Object>> resultMapList = getSqlSession()
                .selectList("base.find", sqlGenerator.sql_findAll());
        List<T> tList = new ArrayList<T>(resultMapList.size());
        for (Map<String, Object> resultMap : resultMapList) {
            T t = handleResult(resultMap, this.entityClass);
            tList.add(t);
        }
        return tList;
	}
	
	@Override
	public T findOneById(PK id) {
		
		Map<String, Object> resultMap = getSqlSession().selectOne(
                "base.findOneById", sqlGenerator.sql_findOneById(id));
         
        return handleResult(resultMap, this.entityClass);
	}	
	
	@Override
	public int findAllCount() {
		return getSqlSession().selectOne("base.findAllCount",sqlGenerator.sql_findAllCount());
	}
	
	private T handleResult(Map<String, Object> resultMap, Class<T> tClazz) {
	        T t = null;
	        try {
	            t = tClazz.newInstance();
	        } catch (InstantiationException e) {
	            logger.error("封装查询结果时，实例化对象(" + this.entityClass + ")时，出现异常!"
	                    + e.getMessage());
	        } catch (IllegalAccessException e) {
	            logger.error("封装查询结果时，实例化对象(" + this.entityClass + ")时，出现异常!"
	                    + e.getMessage());
	        }
	        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
	            String key = entry.getKey();
	            key = columnFieldNamesMap.get(key);
	            Object val = entry.getValue();
	            ReflectionUtils.invokeSetterMethod(t, key, val);
	        }
	        return t;
	    }	
	
	/**
	 * 上传excel方法
	 * path路径
	 * startNum  excel从哪行开始
	 */
/*	public boolean addExcelUpload(CommonsMultipartFile file,String path,int startNum) throws IllegalStateException, IOException, BiffException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IntrospectionException{
		
		File f = new File(path);
		file.transferTo(f);
		
		Workbook wb = null;
		wb = Workbook.getWorkbook(f);
		
		Sheet sheet = wb.getSheet(0);
		int rowNum = sheet.getRows();
		
		Field[] fields = this.entityClass.getDeclaredFields();
		
		Map<Integer,Field> map = new HashMap<Integer,Field>();
		
		for(Field field:fields){
			
			Excel excel = field.getAnnotation(Excel.class);
			if(excel!=null){
				map.put(excel.cellNum(),field);
			}
		}
		
		for(int i =startNum;i<rowNum;i++){
			
			Cell[] cell = sheet.getRow(i);
			T t = entityClass.newInstance();
			
		    for(Map.Entry<Integer, Field> entry : map.entrySet()) {
		    	Field fe = entry.getValue();
				PropertyDescriptor pd = new PropertyDescriptor(fe.getName().toString(),entityClass);
				
				Type type = fe.getGenericType();
				Method m = pd.getWriteMethod();
				
				if(type==String.class){
				    m.invoke(t,cell[entry.getKey()].getContents());
				}
				else if(type==Integer.class){
				    m.invoke(t,Integer.valueOf(cell[entry.getKey()].getContents()));
				}
		        
		       
		    	 
		    }
		    add(t);
		    
		}
		
		return true;
	}
	*/
	
}
