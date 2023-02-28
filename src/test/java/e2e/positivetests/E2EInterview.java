package e2e.positivetests;

import database.InterviewsDAL;
import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;

import static e2e.Consts.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EInterview {
    private static Logger logger = LogManager.getLogger(E2EInterview.class.getName());

    @Feature("ראיונות אישיים")
    @Story("הזנת ראיון אישי לחייל")
    @Severity(CRITICAL)
    @Test(description = "Checking the positive process of adding interview")
    void addInterview() {
        logger.info("Test started");
        new MenuBL()
                .findSubunit(SUBUNIT_NAME)
                .click()
                .findSolider(SOLIDER_NAME)
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
                .isInterviewFound(FORMATTED_DATE, USER_NAME, false);
        new SoliderDetailsBL().closeShowingMoreIfOpen();

    }

    @Test(description = "Deleting interview from DB", dependsOnMethods = "addInterview", priority = 1)
    void deleteInterview() {
        InterviewsDAL.instance().deleteLastInterviewByInterviewer(INTERVIEWER_ID);
    }

    @Test(description = "Canceling w/o saving interview", priority = 2, suiteName = "GUI")
    void cancelInterview() {
        new SoliderDetailsBL()
                .pressOnHavadAndInterviews()
                .pressOnNewInterview()
                .chooseRandomTopic()
                .fillInterview()
                .exitNewInterview()
                .pressOnHavadAndInterviews()
                .isInterviewFound(FORMATTED_DATE, USER_NAME, true);
        new SoliderDetailsBL().closeShowingMoreIfOpen();
    }

    @Test(description = "Canceling interview then regretting", priority = 3, suiteName = "GUI")
    void cancelInterviewThenRegret() {
        new SoliderDetailsBL()
                .pressOnHavadAndInterviews()
                .pressOnNewInterview()
                .chooseRandomTopic()
                .fillInterview()
                .exitNewInterviewThenRegret()
                .checkTopic()
                .checkInterviewContent()
                .checkInterviewConclusion();
        new SoliderDetailsBL().closeShowingMoreIfOpen();
    }



}

