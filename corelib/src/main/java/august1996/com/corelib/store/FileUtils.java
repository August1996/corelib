package august1996.com.corelib.store;


import java.io.File;

/**
 * Created by August on 2017/1/5.
 */

public class FileUtils {

    public static boolean createDir(File dir) {

        if (dir == null) {
            return false;
        }

        if (!dir.exists() || !dir.isDirectory()) {
            if (dir.exists()) {
                dir.delete();
            }
            return dir.mkdirs();
        }

        return false;
    }

    public static String getSuffix(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }

}
