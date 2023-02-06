package e2e.positivetest;

import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.newInterview.NewInterviewBL;
import pages.soliderDetails.SoliderDetailsBL;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EAddInterview {

    @Test
    void AddInterview() {
        new MenuBL()
                .findSubunit("פלוגה א")
                .click()
                .findSolider("עמיחי מרנס")
                .click()
//                .pressOnHavadAndInterviews()
//                .pressOnNewInterview()
//                .chooseRandomTopic()
//                .fillInterview()
                .pressOnHavadAndInterviews()
                .isInterviewFound("5.2.23","ינוב סגל");

    }
}
