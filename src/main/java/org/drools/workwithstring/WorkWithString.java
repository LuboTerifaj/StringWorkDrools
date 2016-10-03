package org.drools.workwithstring;

/**
 * Created by lterifaj on 9/27/16.
 */
public class WorkWithString {

    private String str = "";

    public WorkWithString() {
    }

    public WorkWithString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        this.str = str;
    }

    public void setStr(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public String reverseString(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        return new StringBuilder(str).reverse().toString();
    }

    public String reverseString() {
        return new StringBuilder(this.str).reverse().toString();
    }

    public String toUpperCase(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        return str.toUpperCase();
    }

    public String toUpperCase() {
        return this.str.toUpperCase();
    }

    public String toLowerCase(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Str cannot be null.");
        }
        return str.toLowerCase();
    }

    public String toLowerCase() {
        return this.str.toLowerCase();
    }
}
