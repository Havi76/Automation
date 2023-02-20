package framework;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;

public class DriverProviderWeb implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("browserVersion", "101.0");
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        try {
//            RemoteWebDriver driver = new RemoteWebDriver(
//                    URI.create("http://192.168.208.1:4444/wd/hub").toURL(),
//                    capabilities
//            );
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions().addArguments("--Start-fullscreen")
                    .setExperimentalOption("excludeSwitches",new String[]{"enable-automation"});
            return new ChromeDriver(options);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }
}