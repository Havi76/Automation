package pages.topMenu;

import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class TopMenuPage {
    @Find(className = "TopMenu_userName__e4YTS")
    private SelenideElement userName;
}
