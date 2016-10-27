package org.drools.workwithstring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.ConfigurationManager;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionBaseConfigurationOption;
import org.ops4j.pax.exam.options.DefaultCompositeOption;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

/**
 * Created by lterifaj on 10/5/16.
 */
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class ExampleTest {

    //public static String karafVersion = "2.4.0";

    //private static Logger LOG = LoggerFactory.getLogger(ExampleTest.class);

    @Test
    public void testSum() throws Exception {
        int a = 2;
        int b = 4;
        assertEquals(a + b, 6);
    }

    @Configuration
    public static Option[] configure() {
        KarafDistributionBaseConfigurationOption karafConfiguration = karafDistributionConfiguration();
        karafConfiguration
                .frameworkUrl(CoreOptions.maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("zip").version("3.0.0"))
                .karafVersion("3.0.0")
                .name("Apache Karaf")
                .useDeployFolder(false)
                .unpackDirectory(new File("target/paxexam/unpack/"));

        return CoreOptions.options(
                karafConfiguration,
                junitBundles()
        );
    }

    public static String karafVersion() {
        ConfigurationManager cm = new ConfigurationManager();
        String karafVersion = cm.getProperty("pax.exam.karaf.version", "3.0.0");
        return karafVersion;
    }

}
