package august1996.com.corelib.ui.permission;

import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class PermissionsChecker {


    // 判断权限集合
    public static boolean lacksPermissions(Application context, String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(context, permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private static boolean lacksPermission(Application context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) ==
                PackageManager.PERMISSION_DENIED;
    }
}