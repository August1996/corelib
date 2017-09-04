package august1996.com.corelib.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;

import august1996.com.corelib.ui.CircleImageView;


/**
 * Created by august on 2017/4/9.
 */

public class ImageLoader {

    private static final ImageLoader sInstance = new ImageLoader();

    private WeakReference<Context> wContext;

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        wContext = new WeakReference<Context>(context.getApplicationContext());
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .init(config);
    }


    public void displayImage(String data, ImageView target, int placeHolder) {
        if (wContext.get() == null) {
            return;
        }
        if (data == null) {
            return;
        }
        target.setImageResource(0);
        if (data.startsWith("/")) {
            data = "file://" + data;
        }
        if (!(target instanceof CircleImageView)) {
            target.setBackgroundColor(Color.GRAY);
        }

        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .displayImage(data, target);

    }


    public void displayImage(String data, ImageView target) {
        displayImage(data, target, 0);
    }


    public void pause() {
        if (wContext.get() == null) {
            return;
        }
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .pause();
    }

    public void resume() {
        if (wContext.get() == null) {
            return;
        }
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .resume();
    }

    public void clearDiskCache() {
        if (wContext.get() == null) {
            return;
        }
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .clearDiskCache();
    }

    public void clearMemCache() {
        if (wContext.get() == null) {
            return;
        }
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .clearMemoryCache();
        System.gc();
    }

    public void onTrimMemory(int level) {
        if (wContext.get() == null) {
            return;
        }
    }

    public File getCacheDir() {
        return com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .getDiskCache()
                .getDirectory();
    }

    public File getCahceFileName(String url) {
        return new File(getCacheDir(), MD5Util.toMD5(url));
    }


    public interface CacheImageListener {
        void onImageCache(File file);
    }

    public void cacheImage(final String url, final CacheImageListener listener) {
        if (wContext.get() == null) {
            return;
        }

        final File imageFile = getCahceFileName(url);
        if (imageFile.exists()) {
            if (listener != null) {
                listener.onImageCache(imageFile);
            }
            return;
        }

        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .loadImage(url, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        try {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(imageFile));
                            listener.onImageCache(imageFile);
                        } catch (Exception e) {
                            onLoadingFailed(s, view, new FailReason(FailReason.FailType.IO_ERROR, e));
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
    }
}
