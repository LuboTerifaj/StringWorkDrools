package org.drools.workwithstring;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;

import static org.junit.Assert.assertEquals;


/**
 * Created by lterifaj on 10/25/16.
 */
public class WorkWithStringDroolsTest {

    private KieServices kieServices;

    private KieSession kieSession1;
    private KieSession kieSession2;
    private KieSession kieSession3;


    @Before
    public void setUp() throws Exception {
        this.kieServices = KieServices.Factory.get();
        final KieContainer kieContainer = this.kieServices.newKieClasspathContainer(this.getClass().getClassLoader());

        this.kieSession1 = kieContainer.newKieSession("LowerCaseStringKS");
        Assertions.assertThat(this.kieSession1).as("KieSession can not be null.").isNotNull();

        this.kieSession2 = kieContainer.newKieSession("ReverseStringKS");
        Assertions.assertThat(this.kieSession2).as("KieSession can not be null.").isNotNull();

        this.kieSession3 = kieContainer.newKieSession("UpperCaseStringKS");
        Assertions.assertThat(this.kieSession3).as("KieSession can not be null.").isNotNull();
    }

    @After
    public void cleanup() {
        if (this.kieSession1 != null) {
            this.kieSession1.dispose();
        }
        if (this.kieSession2 != null) {
            this.kieSession2.dispose();
        }
        if (this.kieSession3 != null) {
            this.kieSession3.dispose();
        }
    }

    @Test
    public void testBasicInsertToKieSession() {
        TempString str = new TempString("Hello man");
        this.kieSession1.insert(str);
        this.kieSession1.fireAllRules();

        Collection<?> objects = this.kieSession1.getObjects();
        Assertions.assertThat(objects.size()).isEqualTo(1);
        Assertions.assertThat(objects.iterator().next()).isEqualTo(str);
        Assertions.assertThat(str.equals(objects.iterator().next())).isTrue();
        Assertions.assertThat(objects.iterator().next().equals(str)).isTrue();
    }

    @Test
    public void testFireRulesLowerCaseString() {
        TempString str = new TempString("Hello man");
        this.kieSession1.insert(str);
        this.kieSession1.fireAllRules();

        assertEquals("hello man", str.getStr());
    }

    @Test
    public void testFireRulesReverseString() {
        TempString str = new TempString("Hello man");
        this.kieSession2.insert(str);
        this.kieSession2.fireAllRules();

        assertEquals("nam olleH", str.getStr());
    }

    @Test
    public void testFireRulesUpperCaseString() {
        TempString str = new TempString("Hello man");
        this.kieSession3.insert(str);
        this.kieSession3.fireAllRules();

        assertEquals("HELLO MAN", str.getStr());
    }

}
