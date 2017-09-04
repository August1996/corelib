package august1996.com.corelib.network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author August
 *         网络状态工具类
 */
public class NetworkUtils {


    private static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static Context getContext() {
        if (mContext == null) {
            throw new IllegalStateException("工具类必须初始化");
        }
        return mContext;
    }

    /**
     * 判断是不是wifi网络状态
     *
     * @return
     */
    public static boolean isWifi() {
        return "2".equals(getNetType(getContext())[0]);
    }

    /**
     * 判断是不是2/3G网络状态
     *
     * @return
     */
    public static boolean isMobile() {
        return "1".equals(getNetType(getContext())[0]);
    }

    /**
     * 网络是否可用
     *
     * @return
     */
    public static boolean isNetAvailable() {
        if ("1".equals(getNetType(getContext())[0]) || "2".equals(getNetType(getContext())[0])) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前网络状态 返回2代表wifi,1代表2G/3G
     *
     * @param paramContext
     * @return
     */
    public static String[] getNetType(Context paramContext) {
        String[] arrayOfString = {"Unknown", "Unknown"};
        PackageManager localPackageManager = paramContext.getPackageManager();
        if (localPackageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0) {
            arrayOfString[0] = "Unknown";
            return arrayOfString;
        }

        ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService("connectivity");
        if (localConnectivityManager == null) {
            arrayOfString[0] = "Unknown";
            return arrayOfString;
        }

        NetworkInfo localNetworkInfo1 = localConnectivityManager.getNetworkInfo(1);
        if (localNetworkInfo1 != null && localNetworkInfo1.getState() == NetworkInfo.State.CONNECTED) {
            arrayOfString[0] = "2";
            return arrayOfString;
        }

        NetworkInfo localNetworkInfo2 = localConnectivityManager.getNetworkInfo(0);
        if (localNetworkInfo2 != null && localNetworkInfo2.getState() == NetworkInfo.State.CONNECTED) {
            arrayOfString[0] = "1";
            arrayOfString[1] = localNetworkInfo2.getSubtypeName();
            return arrayOfString;
        }

        return arrayOfString;
    }
}