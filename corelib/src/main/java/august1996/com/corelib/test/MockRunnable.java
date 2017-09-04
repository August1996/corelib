package august1996.com.corelib.test;

import java.util.ArrayList;

import august1996.com.corelib.test.base.OnMockedCallback;
import august1996.com.corelib.test.base.ThreadDispatcher;


public class MockRunnable<T> implements Runnable {

    private Class<T> clazz;
    private int size;
    private int delay;
    private OnMockedCallback<T> callback;
    private Class<? extends ThreadDispatcher> threadDispatch;

    public MockRunnable(Class<T> clazz, int size, int delay, Class<? extends ThreadDispatcher> threadDispatch,
                        OnMockedCallback<T> callback) {
        super();
        this.delay = delay;
        this.clazz = clazz;
        this.callback = callback;
        this.size = size;
        this.threadDispatch = threadDispatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            ArrayList<T> listEntity = Factory.getListEntity(clazz, size);
            DispatcherHolder.get(threadDispatch).dispatcher(listEntity, callback);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}