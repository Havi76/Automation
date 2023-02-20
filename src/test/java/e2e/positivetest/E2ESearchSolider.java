package e2e.positivetest;

import framework.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2ESearchSolider {
    String personalNumber = "8778535";

    @Test()
    void searchSolider() {
        new MenuBL().clickOnSearch()
                .fillTheSearch(personalNumber)
                .clickOnSearch();
    }
}

