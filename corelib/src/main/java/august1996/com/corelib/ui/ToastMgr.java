package august1996.com.corelib.ui;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by August on 2016/11/27.
 */

public class ToastMgr {


    private static Context context;


    private ToastMgr() {

    }

    /**
     * 初始化方法，最好在Application里初始化
     *
     * @param ctx
     */
    public static void init(Context ctx) {
        context = ctx.getApplicationContext();
    }

    /**
     * 检测是否初始化
     */
    private static void checkInit() {
        if (context == null) {
            throw new IllegalStateException("使用前请调用init方法");
        }
    }


    /**
     * 非线程安全显示短时间的Toast
     *
     * @param charSequence
     */
    public static void showShort(CharSequence charSequence) {
        showToast(charSequence, Toast.LENGTH_SHORT);
    }

    /**
     * 非线程安全显示长时间的Toast
     *
     * @param charSequence
     */
    public static void showLong(CharSequence charSequence) {
        showToast(charSequence, Toast.LENGTH_LONG);
    }


    /**
     * 非线程安全显示Toast
     *
     * @param charSequence
     * @param time
     */
    public static void showToast(CharSequence charSequence, int time) {
        checkInit();
        Toast.makeText(context, charSequence, time).show();
    }


}
