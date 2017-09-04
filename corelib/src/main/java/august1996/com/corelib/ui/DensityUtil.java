package august1996.com.corelib.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * UI单位转换的工具类
 *
 * @author August1996
 */
public class DensityUtil {


    private static Context context;
    private static int screenWidth = -1;
    private static int screenHeight = -1;


    private DensityUtil() {
    }

    public static void init(Context ctx) {
        context = ctx.getApplicationContext();
        initScreenSize();
    }

    private static void initScreenSize() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
    }

    private static void checkInit() {
        if (context == null) {
            throw new IllegalStateException("工具类必须初始化");
        }
    }


    public static int dp2px(float dpVal) {
        checkInit();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(float spVal) {
        checkInit();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(float pxVal) {
        checkInit();
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    public static float px2sp(float pxVal) {
        checkInit();
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static float getScreenWidth() {
        checkInit();
        return screenWidth;
    }

    public static float getScreenHeight() {
        checkInit();
        return screenHeight;
    }


}
