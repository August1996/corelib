package august1996.com.corelib.network;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import august1996.com.corelib.store.TokenStore;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by August on 2016/11/27.
 * Retrofit的二次封装
 */

public class RetrofitFactory {

    private static Map<String, Retrofit> instances = new HashMap<>();
    private static Retrofit defaultInstance;
    private static String defaultURL;
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    if (TextUtils.isEmpty(TokenStore.getToken())) {
                        return chain.proceed(chain.request());
                    } else {
                        String token = "Bearer " + TokenStore.getToken();
                        return chain.proceed(chain.request().newBuilder()
                                .addHeader("Authorization", token).build());
                    }
                }
            })
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Gson gson = new Gson();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("\n\n")
                            .append("request url---->")
                            .append(chain.request().url().toString())
                            .append("\n")
                            .append("request headers---->")
                            .append(chain.request().headers().toString())
                            .append("\n")
                            .append("request body---->" + gson.toJson(chain.request().body()))
                            .append("\n\n");
                    Log.i("CDWC_REQUEST", stringBuffer.toString());
                    return chain.proceed(chain.request());
                }
            })
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .build();

    /**
     * 如果需要调用getDefaultInstance，就必须使用该方法
     *
     * @param url
     */
    public static void init(String url) {
        defaultURL = url;
    }

    /**
     * 检查defaultURL是否被初始化
     */
    private static void checkInit() {
        if (defaultURL == null) {
            throw new IllegalStateException("使用默认URL必须调用Init初始化");
        }

    }

    private RetrofitFactory() {
    }

    /**
     * 获取defaultURL对应的的Retrofit实例
     *
     * @return
     */
    public static Retrofit getDefaultInstance() {
        checkInit();
        defaultInstance = getInstance(defaultURL);
        return defaultInstance;
    }

    /**
     * 根据URL获取Retrofit实例
     *
     * @param url
     * @return
     */
    public static Retrofit getInstance(String url) {
        Retrofit instance = instances.get(url);
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                instance = new Retrofit.Builder()
                        .baseUrl(url)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                instances.put(url, instance);
            }
        }
        return instance;
    }

    public static String getDefaultURL() {
        return defaultURL;
    }


}
