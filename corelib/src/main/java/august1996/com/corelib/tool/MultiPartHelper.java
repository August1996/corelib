package august1996.com.corelib.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by August on 2017/2/10.
 * 文件上传帮助类
 */

public class MultiPartHelper {

    /**
     * 构造字段信息参数
     *
     * @param content
     * @return
     */
    public static RequestBody create(String content) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), content);
    }


    /**
     * 构造单个文件上传
     *
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part create(String key, File file) {
        return MultipartBody.Part.createFormData(key, file.getName(), create(file));
    }

    public static RequestBody create(File file) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    public static MultipartBody.Part create(String key, String filePath) {
        return create(key, new File(filePath));
    }

    /**
     * 构造多个文件上传参数
     *
     * @param fileMap
     * @return
     */

    public static HashMap<String, RequestBody> create(HashMap<String, String> fileMap) {
        HashMap<String, RequestBody> map = new HashMap<>();
        for (Map.Entry<String, String> entry : fileMap.entrySet()) {
            File file = new File(entry.getValue());
            map.put(entry.getKey(), create(file));
        }
        return map;
    }

    public static List<MultipartBody.Part> create(List<String> fileList) {
        List<MultipartBody.Part> partList = new ArrayList<>();
        for (String filename : fileList) {
            File file = new File(filename);
            RequestBody imageBody = create(file);
            partList.add(MultipartBody.Part.createFormData(file.getName(), file.getName(), imageBody));
        }
        return partList;
    }

}
