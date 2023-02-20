package e2e.positivetest;

import database.InterviewsDAL;
import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EInterview {
    String subunitName = "פלוגה א";
    String soliderName = "עמיחי מרנס";
    String date = "7.2.23";
    String userName = "ינוב סגל";
    String interviewerId = "212753743";

    @Feature("ראיונות אישיים")
    @Story("הזנת ראיון אישי לחייל")
    @Severity(CRITICAL)
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

    @Test(description = "Ensuring interview has been added", dependsOnMethods = "addInterview")
    void ensureInterview() {
        new SoliderDetailsBL()
                .pressOnHavadAndInterviews()
                .isInterviewFound(date, userName);
        new SoliderDetailsBL().closeShowingMoreIfOpen();

    }

    @Test(description = "Deleting interview from DB", dependsOnMethods = "addInterview", priority = 1)
    void deleteInterview() {
        InterviewsDAL.instance().deleteLastInterviewByInterviewer(interviewerId);
    }
}
