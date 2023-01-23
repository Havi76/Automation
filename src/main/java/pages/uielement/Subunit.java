package pages.uielement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.RootLocator;
import framwork.annotations.locators.Find;
import framwork.elements.UIElement;
import pages.menu.MenuBL;
import pages.menu.MenuPage;


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
