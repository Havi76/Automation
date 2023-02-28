package e2e.negativetests;

import framework.ReadExcel;
import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import static e2e.Consts.*;

import static io.qameta.allure.SeverityLevel.CRITICAL;


@Test(groups = {"E2E negative test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EAddInterview {

    @Feature("ראיונות אישיים")
    @Story("הזנת ראיון אישי לחייל")
    @Severity(CRITICAL)
    @Test(description = "Checking the limitations of adding interview", dataProvider = "InterviewData"
            , dataProviderClass = ReadExcel.class)
    void addInterview(String interviewContent, String interviewConclusion) {
        new MenuBL()
                .findSubunit(SUBUNIT_NAME)
                .click()
                .findSolider(SOLIDER_NAME)
                .click()
                .pressOnHavadAndInterviews()
                .pressOnNewInterview()
                .negativeContentAndConclusion(interviewContent, interviewConclusion)
                .exitNewInterview()
                .pressOnNewInterview()
                .negativeSimpleTopicsAndContentOrConclusion(interviewContent, interviewConclusion)
                .exitNewInterview()
                .pressOnNewInterview()
                .negativeComplicatedWithoutRecommendation(interviewContent, interviewConclusion)
                .exitNewInterview()
                .pressOnNewInterview()
                .negativeComplicatedWithoutResponse(interviewContent, interviewConclusion)
                .exitNewInterview()
                .pressOnNewInterview()
                .negativeComplicatedWithoutContentOrConclusion(interviewContent, interviewConclusion)
                .exitNewInterview();
        new MenuBL().returnToMainPage();
    }
}
