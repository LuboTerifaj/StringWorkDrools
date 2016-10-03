import org.drools.workwithstring.WorkWithString;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lterifaj on 9/27/16.
 */
public class Main {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<String>();

        strings.add("Hello man");
        strings.add("123456789");
        strings.add("asdbvi GJVIDPS");
        strings.add("DkveoNM   pkF");
        strings.add("Hey!");

        System.out.println("Original strings: ");
        for (String item : strings ) {
            System.out.println(item);
        }
        System.out.println("---------------------------------");

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kc.newKieSession( "ReverseStringKS");

        System.out.println("Reverse strings: ");
        for (String item : strings ) {
            kieSession.insert(item);
        }
        kieSession.fireAllRules();

        System.out.println("---------------------------------");

        kieSession = kc.newKieSession("LowerCaseStringKS");

        System.out.println("LowerCase strings: ");
        for (String item : strings ) {
            kieSession.insert(item);
        }
        kieSession.fireAllRules();

        System.out.println("---------------------------------");

        kieSession = kc.newKieSession("UpperCaseStringKS");

        System.out.println("UpperCase strings: ");
        for (String item : strings ) {
            kieSession.insert(item);
        }
        kieSession.fireAllRules();

    }
}
