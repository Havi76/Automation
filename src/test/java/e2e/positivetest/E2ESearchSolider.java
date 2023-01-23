package e2e.positivetest;

import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.menu.MenuPage;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2ESearchSolider {
    @Test
    void searchSolider() {
        new MenuBL().clickOnSearch()
                .fillTheSearch("8778535");

    }
}

//https://kodkod-frontend.azurewebsites.net/
