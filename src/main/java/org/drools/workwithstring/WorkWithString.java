package org.drools.workwithstring;

/**
 * Created by lterifaj on 9/27/16.
 */
public class WorkWithString {

    private String str = "";

    public WorkWithString(String str) {
        this.str = str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public String reverseString(String str) {
        if (str == null) {
            throw new NullPointerException("Str cannot be null.");
        }
        return new StringBuilder(str).reverse().toString();
    }

    public String reverseString() {
        if (this.str == null) {
            throw new NullPointerException("Str cannot be null.");
        }
        return new StringBuilder(this.str).reverse().toString();
    }

    public String toUpperCase(String str) {
        if (str == null) {
            throw new NullPointerException("Str cannot be null.");
        }
        return str.toUpperCase();
    }

    public String toUpperCase() {
        if (this.str == null) {
            throw new NullPointerException("Str cannot be null.");
        }
        return this.str.toUpperCase();
    }

    public String toLowerCase(String str) {
        if (str == null) {
            throw new NullPointerException("Str cannot be null.");
        }
        return str.toLowerCase();
    }

    public String toLowerCase() {
        if (this.str == null) {
            throw new NullPointerException("Str cannot be null.");
        }
        return this.str.toLowerCase();
    }
}
