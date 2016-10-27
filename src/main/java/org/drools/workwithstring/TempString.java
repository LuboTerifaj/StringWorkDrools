package org.drools.workwithstring;

/**
 * Created by lterifaj on 10/25/16.
 */
public class TempString {

    private String str;

    public TempString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        this.str = str;
    }
}
