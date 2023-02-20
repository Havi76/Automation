package pages.menu;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.actionsMenu.ActionsMenuBL;
import pages.uielement.Solider;
import pages.uielement.Subunit;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static framework.configuration.ScrollBehaviour.smooth;

public class MenuBL {
    private final MenuPage page = Selenide.page(MenuPage.class);

    public MenuBL clickOnSearch() {
        page.searchButton().click();
        return this;
    }

    public ActionsMenuBL clickOnActionsMenu() {
        page.actionButton().click();
        return new ActionsMenuBL();
    }

    public MenuBL fillTheSearch(String name) {
        page.searchField().val(name); //val is faster than sendkeys
        return this;
    }

    public Subunit findSubunit(String subunit) {
        page.unitTitle().shouldHave(text("הגדוד שלי"));
        page.subunitList().shouldHave(sizeGreaterThan(0));
        SelenideElement tempSubunit = page.subunitList().stream()
                .filter(selenideElement -> selenideElement.text().contains(subunit)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("לא נמצאה המסגרת המבוקשת"));
        tempSubunit.scrollIntoView(smooth);
        return new Subunit(tempSubunit);

    }

    public Solider findSolider(String soliderName) {
        page.solidersCards().shouldHave(sizeGreaterThan(0));
        return new Solider(page.solidersCards().find(text(soliderName)));
    }
}