package august1996.com.corelib.tool;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 判断文件类型
 * MediaScanner helper class.
 */
public class MediaFile {
    public static String sFileExtensions;

    // Image file types  
    public static final int FILE_TYPE_JPEG = 31;
    public static final int FILE_TYPE_GIF = 32;
    public static final int FILE_TYPE_PNG = 33;
    public static final int FILE_TYPE_BMP = 34;
    public static final int FILE_TYPE_WBMP = 35;
    private static final int FIRST_IMAGE_FILE_TYPE = FILE_TYPE_JPEG;
    private static final int LAST_IMAGE_FILE_TYPE = FILE_TYPE_WBMP;


    //静态内部类  
    private static class MediaFileType {

        int fileType;
        String mimeType;

        MediaFileType(int fileType, String mimeType) {
            this.fileType = fileType;
            this.mimeType = mimeType;
        }
    }

    private static HashMap<String, MediaFileType> sFileTypeMap
            = new HashMap<String, MediaFileType>();
    private static HashMap<String, Integer> sMimeTypeMap
            = new HashMap<String, Integer>();

    static void addFileType(String extension, int fileType, String mimeType) {
        sFileTypeMap.put(extension, new MediaFileType(fileType, mimeType));
        sMimeTypeMap.put(mimeType, new Integer(fileType));
    }

    static {
        addFileType("JPG", FILE_TYPE_JPEG, "image/jpeg");
        addFileType("JPEG", FILE_TYPE_JPEG, "image/jpeg");
        addFileType("GIF", FILE_TYPE_GIF, "image/gif");
        addFileType("PNG", FILE_TYPE_PNG, "image/png");
        addFileType("BMP", FILE_TYPE_BMP, "image/x-ms-bmp");
        addFileType("WBMP", FILE_TYPE_WBMP, "image/vnd.wap.wbmp");

        // compute file extensions list for native Media Scanner  
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = sFileTypeMap.keySet().iterator();

        while (iterator.hasNext()) {
            if (builder.length() > 0) {
                builder.append(',');
            }
            builder.append(iterator.next());
        }
        sFileExtensions = builder.toString();
    }


    public static boolean isImageFileType(int fileType) {
        return (fileType >= FIRST_IMAGE_FILE_TYPE &&
                fileType <= LAST_IMAGE_FILE_TYPE);
    }


    public static MediaFileType getFileType(String path) {
        int lastDot = path.lastIndexOf(".");
        if (lastDot < 0)
            return null;
        return sFileTypeMap.get(path.substring(lastDot + 1).toUpperCase());
    }

    //根据视频图片文件路径判断文件类型
    public static boolean isImageFileType(String path) {  //自己增加
        MediaFileType type = getFileType(path);
        if (null != type) {
            return isImageFileType(type.fileType);
        }
        return false;
    }

    //根据mime类型查看文件类型  
    public static int getFileTypeForMimeType(String mimeType) {
        Integer value = sMimeTypeMap.get(mimeType);
        return (value == null ? 0 : value);
    }

}  