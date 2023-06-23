package com.xyp.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    /**
     *
     * 把Map中的值注入到bean对象中
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map value,T bean){
        try {
            BeanUtils.populate(bean,value);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;

    }

    /**
     * 将字符串转成int类型
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}
