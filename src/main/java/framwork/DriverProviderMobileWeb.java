package framwork;

import com.codeborne.selenide.WebDriverProvider;
import framwork.configuration.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class DriverProviderMobileWeb implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().setup();

        Map<String, String> experimentalOptions = new HashMap<>() {{
            put("deviceName", Config.driverDeviceName);
        }};

        ChromeOptions options = new ChromeOptions().setExperimentalOption("mobileEmulation", experimentalOptions)
                                                   .setExperimentalOption("excludeSwitches", new String[]{"enable-automation"})
                                                   .setHeadless(Config.driverHeadless);

        return new ChromeDriver(options);
    }
}