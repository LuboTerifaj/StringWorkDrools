package org.drools.workwithstring;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lterifaj on 10/3/16.
 */
public class WorkWithStringTest {

    private WorkWithString wws;

    private static List<String> strings = new ArrayList<String>();

    private static List<String> reverseStrings = new ArrayList<String>();
    private static List<String> upperCaseStrings = new ArrayList<String>();
    private static List<String> lowerCaseStrings = new ArrayList<String>();

    @BeforeClass
    public static void setUpOnce() {
        strings.add("Hello man");
        strings.add("123456789");
        strings.add("asdbvi GJVIDPS");
        strings.add("DkveoNM   pkF");
        strings.add(" Hey!");

        reverseStrings.add("nam olleH");
        reverseStrings.add("987654321");
        reverseStrings.add("SPDIVJG ivbdsa");
        reverseStrings.add("Fkp   MNoevkD");
        reverseStrings.add("!yeH ");

        upperCaseStrings.add("HELLO MAN");
        upperCaseStrings.add("123456789");
        upperCaseStrings.add("ASDBVI GJVIDPS");
        upperCaseStrings.add("DKVEONM   PKF");
        upperCaseStrings.add(" HEY!");

        lowerCaseStrings.add("hello man");
        lowerCaseStrings.add("123456789");
        lowerCaseStrings.add("asdbvi gjvidps");
        lowerCaseStrings.add("dkveonm   pkf");
        lowerCaseStrings.add(" hey!");
    }

    @Before
    public void setUp() throws Exception {
        wws = new WorkWithString();
    }


    @Test
    public void testConstructorIllegalArgument() throws Exception {
        try {
            new WorkWithString(null);
            fail("Exception was expected for null input.");
        } catch (IllegalArgumentException ex) {

        }
    }

    @Test
    public void testSetStrWithNull() throws Exception {
        try {
            wws.setStr(null);
            fail("Method does not throw IllegalArgumentException");
        } catch (IllegalArgumentException ex) {

        }

    }


    @Test
    public void testGetStr() throws Exception {
        for (int i = 0; i < strings.size(); i++) {
            assertEquals(strings.get(i), new WorkWithString(strings.get(i)).getStr());

            wws = new WorkWithString();
            wws.setStr(strings.get(i));

            assertEquals(strings.get(i), wws.getStr());
        }
    }


    @Test
    public void testReverseString() throws Exception {
        for (int i = 0; i < strings.size(); i++) {

            assertEquals(reverseStrings.get(i), new WorkWithString(strings.get(i)).reverseString());

            wws = new WorkWithString();
            wws.setStr(strings.get(i));

            assertEquals(reverseStrings.get(i), wws.reverseString());
        }
    }

    @Test
    public void testToUpperCase() throws Exception {
        for (int i = 0; i < strings.size(); i++) {
            assertEquals(upperCaseStrings.get(i), new WorkWithString(strings.get(i)).toUpperCase());

            wws = new WorkWithString();
            wws.setStr(strings.get(i));

            assertEquals(upperCaseStrings.get(i), wws.toUpperCase());
        }
    }

    @Test
    public void testToLowerCase() throws Exception {
        for (int i = 0; i < strings.size(); i++) {
            assertEquals(lowerCaseStrings.get(i), new WorkWithString(strings.get(i)).toLowerCase());

            wws = new WorkWithString();
            wws.setStr(strings.get(i));

            assertEquals(lowerCaseStrings.get(i), wws.toLowerCase());
        }
    }

}