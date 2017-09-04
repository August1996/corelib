package august1996.com.corelib.test;

import java.util.ArrayList;

import august1996.com.corelib.test.base.OnMockedCallback;
import august1996.com.corelib.test.base.ThreadDispatcher;


public class DefaultDispatcher implements ThreadDispatcher {

    @Override
    public <T> void dispatcher(ArrayList<T> value, OnMockedCallback<T> callback) {
        if (callback != null) {
            callback.onMocked(value);
        }
    }

}