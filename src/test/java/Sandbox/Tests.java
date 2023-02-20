package Sandbox;

import framework.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;

@Test(groups = {"E2E"})
@Listeners(ClassLevelWebRunner.class)
public class Tests {
    String subunitName = "פלוגה א";
    String soliderName = "עמיחי מרנס";
    String date = "7.2.23";
    String userName = "ינוב סגל";
    String interviewerId = "212753743";

    @Test
    void e2eTest() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click()
                .pressOnHavadAndInterviews()
                .pressOnNewInterview();
//                .checkNewInterviewLimitations();

    }
}
