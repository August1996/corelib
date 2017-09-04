package august1996.com.corelib.store;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by August on 2016/11/27.
 * SharePreference工具类封装
 */

public class SPUtil {

    private static final String LIST_TAG = ".LIST";
    private static SharedPreferences sharedPreferences;
    private static Gson gson;

    /**
     * 使用之前初始化, 可在Application中调用
     *
     * @param context 请传入ApplicationContext避免内存泄漏
     */
    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_MULTI_PROCESS);
        gson = new Gson();
    }

    private static void checkInit() {
        if (sharedPreferences == null || gson == null) {
            throw new IllegalStateException("Please call init(context) first.");
        }
    }

    /**
     * 保存对象数据至SharedPreferences, key默认为类名, 如
     * <pre>
     * PreferencesHelper.saveData(saveUser);
     * </pre>
     *
     * @param data 不带泛型的任意数据类型实例
     */
    public static <T> void saveData(T data) {
        saveData(data.getClass().getName(), data);
    }

    /**
     * 根据key保存对象数据至SharedPreferences, 如
     * <pre>
     * PreferencesHelper.saveData(key, saveUser);
     * </pre>
     *
     * @param data 不带泛型的任意数据类型实例
     */
    public static <T> void saveData(String key, T data) {
        checkInit();
        String json = null;
        if (data == null) {
            json = "";
        } else {
            json = gson.toJson(data);
        }

        sharedPreferences.edit().putString(key, json).commit();
    }

    /**
     * 保存List集合数据至SharedPreferences, 请确保List至少含有一个元素, 如
     * <pre>
     * PreferencesHelper.saveData(users);
     * </pre>
     *
     * @param data List类型实例
     */
    public static <T> void saveData(List<T> data) {
        checkInit();
        if (data == null || data.size() <= 0)
            throw new IllegalStateException(
                    "List should not be null or at least contains one element.");
        Class returnType = data.get(0).getClass();
        sharedPreferences.edit().putString(returnType.getName() + LIST_TAG,
                gson.toJson(data)).commit();
    }

    /**
     * 将数据从SharedPreferences中取出, key默认为类名, 如
     * <pre>
     * User user = PreferencesHelper.getData(key, User.class)
     * </pre>
     */
    public static <T> T getData(Class<T> clz) {
        return getData(clz.getName(), clz);
    }

    /**
     * 根据key将数据从SharedPreferences中取出, 如
     * <pre>
     * User user = PreferencesHelper.getData(User.class)
     * </pre>
     */
    public static <T> T getData(String key, Class<T> clz) {
        checkInit();
        String json = sharedPreferences.getString(key, "");
        return gson.fromJson(json, clz);
    }

    /**
     * 将数据从SharedPreferences中取出, 如
     * <pre>List<User> users = PreferencesHelper.getData(List.class, User.class)</pre>
     */
    public static <T> List<T> getData(Class<List> clz, Class<T> gClz) {
        checkInit();
        String json = sharedPreferences.getString(gClz.getName() + LIST_TAG, "");
        return gson.fromJson(json, ParameterizedTypeImpl.get(clz, gClz));
    }

    /**
     * 简易字符串保存, 仅支持字符串
     */
    public static void saveData(String key, String data) {
        sharedPreferences.edit().putString(key, data).commit();
    }

    /**
     * 简易字符串获取, 仅支持字符串
     */
    public static String getData(String key) {
        return sharedPreferences.getString(key, "");
    }

    /**
     * 删除保存的对象
     */
    public static void remove(String key) {
        sharedPreferences.edit().remove(key).commit();
    }

    /**
     * 删除保存的对象
     */
    public static void remove(Class clz) {
        remove(clz.getName());
    }

    /**
     * 删除保存的数组
     */
    public static void removeList(Class clz) {
        sharedPreferences.edit().remove(clz.getName() + LIST_TAG).commit();
    }

    public static void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }


    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).commit();
    }


    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).commit();
    }


    public static long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).commit();
    }


    public static float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }


}