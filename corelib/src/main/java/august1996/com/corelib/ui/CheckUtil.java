package august1996.com.corelib.ui;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by August on 2016/11/27.
 * 检测工具类
 */

public class CheckUtil {

    public static boolean checkEmptyEditText(EditText... ets) {
        for (EditText et : ets) {
            if (et == null) {
                return true;
            }
            if (TextUtils.isEmpty(et.getText().toString())) {
                return true;
            }
        }
        return false;
    }
}
