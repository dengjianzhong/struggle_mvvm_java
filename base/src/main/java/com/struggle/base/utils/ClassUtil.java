package com.struggle.base.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author 邓建忠
 * @CreateTime 2021/8/8 22:30
 * @Description TODO
 */
public class ClassUtil {

    /**
     * 根据超类泛型生成class对象
     *
     * @param object
     * @param index
     * @return
     */
    public static Type getParentGeneric(Object object, int index) {
        Class<?> currentClass = object.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) currentClass.getGenericSuperclass();
        return parameterizedType.getActualTypeArguments()[index];
    }
}
