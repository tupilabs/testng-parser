package com.tupilabs.testng.parser;

/* package */ enum Statuses {

    PASS("pass"), FAIL("fail"), SKIP("skip");
    
    private String value;
    
    Statuses(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return getValue();
    }
    
    static Statuses get(String value) {
        if ("pass".equalsIgnoreCase(value)) {
            return PASS;
        } else if("fail".equalsIgnoreCase(value)) {
            return FAIL;
        } else if("skip".equalsIgnoreCase(value)) {
            return SKIP;
        }
        return null;
    }
    
}
