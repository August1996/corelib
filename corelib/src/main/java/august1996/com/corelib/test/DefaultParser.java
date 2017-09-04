package august1996.com.corelib.test;


import august1996.com.corelib.test.base.TypeParser;

public class DefaultParser implements TypeParser {

    @Override
    public Object parser(String type) {
        if (DefaultType.NOW_TIMESTAMP.equals(type)) {
            return System.currentTimeMillis();
        }
        return null;
    }
}