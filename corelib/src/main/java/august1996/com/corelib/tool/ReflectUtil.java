package august1996.com.corelib.tool;

import java.lang.reflect.Field;

/**
 * Created by august on 2017/4/8.
 */

public class ReflectUtil {


    public static Object getField(Object object, String fieldName) {
        Class<?> aClass = object.getClass();
        try {
            Field field = aClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean setFiled(Object object, String fieldName, Object fieldObject) {
        Class<?> aClass = object.getClass();
        try {
            Field field = aClass.getDeclaredField(fieldName);
            if (field == null) {
                return false;
            }

            field.setAccessible(true);
            field.set(object, fieldObject);

        } catch (Exception e) {
            return false;
        }
        return true;

    }

}
