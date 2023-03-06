package pages.uielement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import framework.annotations.RootLocator;
import framework.annotations.locators.Find;
import framework.elements.UIElement;
import io.qameta.allure.Step;
import pages.menu.MenuBL;


@RootLocator(".frameworkRect_PlugaRectangleDesktop__kleu6")
public class Subunit extends UIElement {
    @Find(css = ".frameworkRect_header__1EUuJ :nth-child(1)")
    private ElementsCollection subunits;

    public Subunit(SelenideElement self) {
        super(self);
    }

    public MenuBL click() {
        getSelf().click();
        Selenide.sleep(4000);
        return new MenuBL();
    }
}
