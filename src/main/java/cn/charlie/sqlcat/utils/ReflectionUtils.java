package cn.charlie.sqlcat.utils;

import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author charlie
 * @date 4/10/2023 10:46 AM
 **/
public class ReflectionUtils {
    public static Object getFieldValue(Object obj, String fieldName) {
        Field var2 = getAccessibleField(obj, fieldName);
        if (var2 == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        } else {
            Object var3 = null;

            try {
                var3 = var2.get(obj);
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            }

            return var3;
        }
    }

    public static Field getAccessibleField(Object obj, String fieldName) {
        Validate.notNull(obj, "object can't be null");
        Validate.notBlank(fieldName, "fieldName can't be blank");
        Class var2 = obj.getClass();

        while (var2 != Object.class) {
            try {
                Field var3 = var2.getDeclaredField(fieldName);
                makeAccessible(var3);
                return var3;
            } catch (NoSuchFieldException var4) {
                var2 = var2.getSuperclass();
            }
        }

        return null;
    }

    public static void makeAccessible(Field field) {
        if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
            field.setAccessible(true);
        }
    }
}
