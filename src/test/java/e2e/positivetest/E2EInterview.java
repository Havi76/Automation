package e2e.positivetest;

import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EInterview {
    String subunitName = "פלוגה א";
    String soliderName = "עמיחי מרנס";
    String date = "7.2.23";
    String userName = "ינוב סגל";

    @Test(description = "Checking the positive process of adding interview")
    void addInterview() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click()
                .pressOnHavadAndInterviews()
                .pressOnNewInterview()
                .chooseRandomTopic()
                .fillInterview()
                .saveInterview();
    }

    @Test(description = "Ensure interview has been added", dependsOnMethods = "addInterview")
    void ensureInterview() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click()
                .pressOnHavadAndInterviews()
                .isInterviewFound(date, userName);
    }
}
