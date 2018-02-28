package com.zxh.core.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zxh.core.entity.Pair;

/**
 *  
 * @author zxh
 * 2018年2月27日
 * @param <T>
 */
public class SQLGenerator<T> {
	     
		private static final Log logger = LogFactory.getLog(SQLGenerator.class);
	    private Map<String, String>    columnFieldNames;
	    private String        tableName;
	    private String        columnsStr;
	    private String        pkName;
	     
	    public SQLGenerator(Map<String, String> columnFieldNames, String tableName, String pkName) {
	        super();
	        this.columnFieldNames = columnFieldNames;
	        this.tableName = tableName;
	        this.pkName = pkName;
	        this.columnsStr = StringUtils.join(columnFieldNames.keySet(), ",");
	    }
	     
	    /**
	     * 生成新增的SQL
	     * @param t
	     * @param currentColumnFieldNames
	     * @return
	     */
	    public String sql_insert(T t) {
	        Pair<LinkedList<String>, LinkedList<Object>> pair = obtainFieldValues(t);        
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("INSERT INTO ").append(tableName).append("(")
	                .append(StringUtils.join(pair.key, ",")).append(") values (")
	                .append(StringUtils.join(pair.value, ",")).append(")");
	        String sql = sql_build.toString();
	        return sql;
	    }
	     
	    /**
	     * 提供给生成新增SQL 使用
	     * @param t
	     * @param currentColumnFieldNames
	     * @return
	     */
	    private Pair<LinkedList<String>, LinkedList<Object>> obtainFieldValues(T t) {
	    	Pair<LinkedList<String>, LinkedList<Object>> pair = new Pair<LinkedList<String>,LinkedList<Object>>(new LinkedList<String>(),new LinkedList<Object>());
	        for (Entry<String,String> entry : columnFieldNames.entrySet()) {
	            Object value = ReflectionUtils.obtainFieldValue(t,entry.getValue());
	            value = handleValue(value);
	            if(null!=value){
	            	pair.key.add(entry.getKey());
	            	pair.value.add(value);
	            }
	        }
	        return pair;
	    }
	     
	    /**
	     * 处理value
	     * @param value
	     */
	    private Object handleValue(Object value) {
	        if (value instanceof String) {
	            value = "'" + value + "'";
	        } else if (value instanceof Date) {
	            Date date = (Date) value;
	            value = "date_format('" + date+ "','YYYY-MM-DD HH24:MI:SS.FF3')";
	        } else if (value instanceof Boolean) {
	            Boolean v = (Boolean) value;
	            value = v ? 1 : 0;
	        }else if(null == value || StringUtils.isBlank(value.toString())){
	            return null;
	        }else{
	        	value = "'"+value.toString()+"'";
	        }
	        return value;
	    }
	     
	    /**
	     * 生成根据ID删除的SQL
	     * 
	     * @param id
	     * @return
	     */
	    public <PK> String sql_deleteById(PK id) {
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("DELETE FROM ").append(this.tableName)
	                .append(" WHERE ").append(pkName).append(" = ").append(id);
	        String sql = sql_build.toString();
	        return sql;
	    }
	     
	    public String sql_deleteAll() {
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("DELETE FROM ").append(this.tableName);
	        String sql = sql_build.toString();
	        //logger.debug("生成的SQL为: " + sql);
	        return sql;
	    }
	     
	    /**
	     * 生成更新的SQL
	     * 
	     * @param t
	     * @param currentColumnFieldNames
	     * @return
	     */
	    public String sql_update(T t, Map<String, String> currentColumnFieldNames) {
	        List<String> values = obtainColumnVals(t, currentColumnFieldNames);
	        Object id = ReflectionUtils.obtainFieldValue(t,
	                currentColumnFieldNames.get(pkName));
	        id = handleValue(id);
	         
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("UPDATE ").append(tableName).append(" SET ")
	                .append(StringUtils.join(values, ",")).append(" WHERE ")
	                .append(pkName).append(" = ").append(id);
	         
	        String sql = sql_build.toString();
	        return sql;
	    }
	     
	    /**
	     * 提供给生成更新SQL使用
	     * 
	     * @param t
	     * @param currentColumnFieldNames
	     * @return
	     */
	    private List<String> obtainColumnVals(T t,
	            Map<String, String> currentColumnFieldNames) {
	        List<String> colVals = new LinkedList<String>();
	        for (String column : currentColumnFieldNames.keySet()) {
	            Object value = ReflectionUtils.obtainFieldValue(t,
	                    currentColumnFieldNames.get(column));
	            if (value != null && !StringUtils.equalsIgnoreCase(column, pkName)) {
	                colVals.add(column + "=" + handleValue(value));
	            }
	        }
	        return colVals;
	    }
	     
	    /**
	     * 生成根据ID查询的SQL
	     * 
	     * @param id
	     * @return
	     */
	    public <PK> String sql_findOneById(PK id) {
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("SELECT ").append(columnsStr).append(" FROM ")
	                .append(this.tableName)
	                .append(" WHERE  " + pkName + " = " + id+" limit 1");
	         
	        String sql = sql_build.toString();
	         
	         
	        return sql;
	         
	    }
	     
	    /**
	     * 生成查询所有的SQL
	     * 
	     * @return
	     */
	    public String sql_findAll() {
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("SELECT ").append(columnsStr).append(" FROM ")
	                .append(this.tableName);
	        String sql = sql_build.toString();
	         
	         
	        return sql;
	    }
	     
	    /**
	     * 生成查询数量的SQL
	     * 
	     * @return
	     */
	    public String sql_findAllCount() {
	        StringBuilder sql_build = new StringBuilder();
	        sql_build.append("SELECT COUNT(1) ").append(" FROM ")
	                .append(this.tableName);
	        String sql = sql_build.toString();
	         
	         
	        return sql;
	    }
	    
}
