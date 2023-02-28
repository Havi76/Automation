package e2e.positivetests;

import framework.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import static e2e.Consts.*;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2ESearchSolider {

    @Test()
    void searchSolider() {
        new MenuBL().clickOnSearch()
                .fillTheSearch(SOLIDER_PERSONAL_NUMBER)
                .clickOnSearch();
    }
}

