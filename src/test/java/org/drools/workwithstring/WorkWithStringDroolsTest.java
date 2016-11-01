package org.drools.workwithstring;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.ConfigurationManager;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionBaseConfigurationOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.osgi.framework.Constants;

import java.io.File;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;
import static org.ops4j.pax.tinybundles.core.TinyBundles.bundle;


/**
 * Created by lterifaj on 10/25/16.
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class WorkWithStringDroolsTest {

    private static final String BXMS_FEATURE_GROUP_ID = "org.drools";
    public static final String BXMS_FEATURE_ARTIFACT_ID = "drools-karaf-features";

    private static final String FUSE_61_62_CLASSIFIER = "features";
    private static final String FUSE_63_CLASSIFIER = "features-fuse-6_3";

    private static final String DROOLS_MODULE_FEATURE_NAME = "drools-module";

    private KieServices kieServices;

    private KieSession kieSession1;
    private KieSession kieSession2;
    private KieSession kieSession3;


    @Configuration
    public static Option[] configure() {
        KarafDistributionBaseConfigurationOption karafConfiguration = karafDistributionConfiguration();
        karafConfiguration
                //.frameworkUrl(CoreOptions.maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("zip").version("3.0.0"))
                .frameworkUrl("file:///home/lterifaj/tools/jboss-fuse-karaf-6.3.0.redhat-187.zip")
                .karafVersion("3.0.0")
                .name("Apache Karaf")
                .useDeployFolder(false)
                .unpackDirectory(new File("target/paxexam/unpack/"));


        return CoreOptions.options(
                karafConfiguration,
                //logLevel(LogLevelOption.LogLevel.INFO),
                junitBundles(),
                features(maven().groupId(BXMS_FEATURE_GROUP_ID).artifactId(BXMS_FEATURE_ARTIFACT_ID)
                                .versionAsInProject().type("xml").classifier(FUSE_63_CLASSIFIER),
                        DROOLS_MODULE_FEATURE_NAME),
                wrappedBundle(mavenBundle().groupId("org.assertj").artifactId("assertj-core").versionAsInProject()),
                streamBundle(bundle()
                        .add(TempString.class)
                        .add(WorkWithString.class)
                        .set(Constants.IMPORT_PACKAGE, "*")
                        .set(Constants.EXPORT_PACKAGE, "org.drools.workwithstring")
                        .set(Constants.BUNDLE_SYMBOLICNAME, "Test-Domain")
                        //.set(Constants.DYNAMICIMPORT_PACKAGE, "*")
                        .build()).start()
        );
    }

    /*
    public static String karafVersion() {
        ConfigurationManager cm = new ConfigurationManager();
        String karafVersion = cm.getProperty("pax.exam.karaf.version", "3.0.0");
        return karafVersion;
    }
    */



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
