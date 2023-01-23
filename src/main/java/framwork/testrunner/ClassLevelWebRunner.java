package framwork.testrunner;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import framwork.util.AuthorizationManager;
import framwork.DriverProviderWeb;
import framwork.util.MonitoringMail;
import framwork.util.TestConfig;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClassLevelWebRunner implements IInvokedMethodListener, IClassListener, ISuiteListener {
    static String massageBody;

    @Override
    public void onBeforeClass(ITestClass testClass) {
        Configuration.timeout = 20000;
        Configuration.browser = DriverProviderWeb.class.getCanonicalName();
        Configuration.holdBrowserOpen = true;
        Configuration.startMaximized = true;

        Selenide.open("https://kodkod-frontend.azurewebsites.net/");
        AuthorizationManager.authorizeAndRefresh();
    }

    public void onAfterClass(ITestClass testClass) {
        WebDriver driver = WebDriverRunner.getWebDriver();

        if (driver != null)
        {
            driver.quit();
        }
    }

    @SneakyThrows
    public void onFinish(ISuite iSuite) {
        MonitoringMail monitoringMail = new MonitoringMail();

        try {
            massageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/kodkod%20automation/allure";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, massageBody);
    }
}
