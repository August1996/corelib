package august1996.com.corelib.test;


import august1996.com.corelib.test.base.TypeParser;

public class HolderParser implements TypeParser {

    @Override
    public Object parser(String type) {
        return ValueHolder.get(type);
    }

}