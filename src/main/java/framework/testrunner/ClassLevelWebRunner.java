package framework.testrunner;

import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.webdriver.DriverFactory;
import framework.util.AuthorizationManager;
import framework.DriverProviderWeb;
import framework.util.MonitoringMail;
import framework.util.TestConfig;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.runner.BaseTestRunner;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.internal.BaseTestMethod;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassLevelWebRunner implements IInvokedMethodListener, IClassListener, ISuiteListener, ITestListener {
    static String massageBody;



    @Override
    public void onBeforeClass(ITestClass testClass) {
        Configuration.timeout = 20000;
        Configuration.browser = DriverProviderWeb.class.getCanonicalName();
        Configuration.holdBrowserOpen = true;
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "test-result/reports";

        Selenide.open("https://kodkod-frontend.azurewebsites.net/");
        AuthorizationManager.authorizeAndRefresh();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .includeSelenideSteps(false)
                .savePageSource(false)
                .screenshots(true));
    }

    public void onAfterClass(ITestClass testClass) {
        WebDriver driver = WebDriverRunner.getWebDriver();

        if (driver != null) {
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

    private static String getTestMethodName(ITestResult iTestRusult) {
        return iTestRusult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment
    private void takeScreenshot(WebDriver webDriver) throws IOException {
        File screenshotAs = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
    }


    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message) {
        return message;
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult iTestResult){
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + "failed");
        WebDriver driver = WebDriverRunner.getWebDriver();
        System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
        takeScreenshot(driver);
        saveTextLog(getTestMethodName(iTestResult) + "failed and screenshot taken!");
    }
}
