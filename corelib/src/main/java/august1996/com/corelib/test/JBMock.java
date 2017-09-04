package august1996.com.corelib.test;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import august1996.com.corelib.test.base.OnMockedCallback;
import august1996.com.corelib.test.base.ThreadDispatcher;


public class JBMock {
    private static final JBMock sInstance = new JBMock();

    public static JBMock getInstance() {
        return sInstance;
    }

    private JBMock() {
        mExecutors = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private ExecutorService mExecutors;

    public <T> ArrayList<T> syncGet(Class<T> cls, int delay, int size) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Factory.getListEntity(cls, size);
    }


    public <T> void asyncGet(Class<T> cls, int delay, int size, Class<? extends ThreadDispatcher> threadDispatcherCls,
                             OnMockedCallback<T> callback) {
        mExecutors.submit(new MockRunnable<T>(cls, size, delay, threadDispatcherCls, callback));
    }


}