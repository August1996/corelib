package august1996.com.corelib.store;

import android.text.TextUtils;

/**
 * Created by august on 2017/7/30.
 */

public class TokenStore {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_PHONE = "phone";
    private static volatile String token;
    private static volatile String phone;

    public static void setToken(String token) {
        TokenStore.token = token;
        if (TextUtils.isEmpty(token)) {
            SPUtil.remove(KEY_TOKEN);
        } else {
            SPUtil.saveData(KEY_TOKEN, token);
        }
    }

    public static String getToken() {
        if (TextUtils.isEmpty(token)) {
            token = SPUtil.getData(KEY_TOKEN);
        }
        return token;
    }

    public static void setPhone(String phone) {
        TokenStore.phone = phone;
        if (TextUtils.isEmpty(phone)) {
            SPUtil.remove(KEY_PHONE);
        } else {
            SPUtil.saveData(KEY_PHONE, phone);
        }
    }

    public static String getPhone() {
        if (TextUtils.isEmpty(phone)) {
            phone = SPUtil.getData(KEY_PHONE);
        }
        return phone;
    }

    public static boolean valid() {
        return !TextUtils.isEmpty(getToken()) && !TextUtils.isEmpty(getPhone());
    }


    public static void clear() {
        setPhone(null);
        setToken(null);
    }

}
