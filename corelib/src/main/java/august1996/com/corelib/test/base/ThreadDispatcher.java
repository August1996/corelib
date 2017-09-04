package august1996.com.corelib.test.base;

import java.util.ArrayList;


public interface ThreadDispatcher {
    <T> void dispatcher(ArrayList<T> value, OnMockedCallback<T> callback);
}