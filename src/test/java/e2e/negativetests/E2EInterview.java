package e2e.negativetests;

import framework.ReadExcel;
import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;

import static io.qameta.allure.SeverityLevel.CRITICAL;


@Test(groups = {"E2E negative test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EInterview {
    String subunitName = "פלוגה א";
    String soliderName = "עמיחי מרנס";
    String date = "7.2.23";
    String userName = "ינוב סגל";
    String interviewerId = "212753743";
    String five = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    String two =  "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    @Feature("ראיונות אישיים")
    @Story("הזנת ראיון אישי לחייל")
    @Severity(CRITICAL)
    @Test(description = "Checking the limitations of adding interview",
            dataProvider = "InterviewData", dataProviderClass = ReadExcel.class)
    void addInterview(String interviewContent, String interviewConclusion) {
//        interviewContent = five;
//        interviewConclusion = two;
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
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
    }
}
