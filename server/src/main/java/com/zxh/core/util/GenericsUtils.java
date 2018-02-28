package com.zxh.core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsUtils {
    private GenericsUtils() {
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends
     * GenricManager<Book>
     * 
     * @param clazz
     *            The class to introspect
     * @return the first generic declaration, or <code>Object.class</code> if
     *         cannot be determined
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型. 如public BookManager extends
     * GenricManager<Book>
     * 
     * @param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic declaration,start from 0.
     * @return the index generic declaration, or <code>Object.class</code> if
     *         cannot be determined
     */
    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {

        Type genType = clazz.getGenericSuperclass();
        /* 判断是否继承自此类 */
        if (!(genType instanceof ParameterizedType)) {
        	System.out.println("a");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        System.out.println(params.length);
        /* 判断传的参数：index 是否合法 */
        if (index >= params.length || index < 0) {
        	System.out.println("ab");
            return Object.class;
        }
        /* 判断是否是真正的类型，因为可能是 泛化类型 T */
        if (!(params[index] instanceof Class<?>)) {
        	System.out.println("abc");
            return Object.class;
        }
        return (Class<?>) params[index];
    }
}
