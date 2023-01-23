package pages.topMenu;

import com.codeborne.selenide.Selenide;
import pages.soliderDetails.SoliderDetailsPage;

public class TopMenuBL {
    private final TopMenuPage page = Selenide.page(TopMenuPage.class);

    public String getName() {
        return page.userName().text();
    }
}
