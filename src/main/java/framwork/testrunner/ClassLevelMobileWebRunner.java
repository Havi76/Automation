package framwork.testrunner;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import framwork.util.AuthorizationManager;
import org.openqa.selenium.WebDriver;
import org.testng.*;


public class ClassLevelMobileWebRunner implements IInvokedMethodListener, IClassListener {

    @Override
    public void onBeforeClass(ITestClass testClass) {
        Configuration.timeout = 20000;
//        Configuration.browser = DriverProviderMobileWeb.class.getCanonicalName();
        System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        Configuration.holdBrowserOpen = true;
        Configuration.startMaximized = true;

        Selenide.open("https://kodkod-frontend.azurewebsites.net/");
        AuthorizationManager.authorizeAndRefresh();

    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        WebDriver driver = WebDriverRunner.getWebDriver();

        if (driver != null) {
          driver.quit();
        }
    }
}