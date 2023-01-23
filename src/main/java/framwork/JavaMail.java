package framwork;

import framwork.util.MonitoringMail;
import framwork.util.TestConfig;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class JavaMail {
    static String massageBody;

    @Test
    void sendMail() throws MessagingException {
        MonitoringMail monitoringMail = new MonitoringMail();

        try {
            massageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/kodkod%20automation/allure";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, massageBody);
    }
}