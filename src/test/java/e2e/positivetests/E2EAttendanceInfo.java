package e2e.positivetests;

import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static e2e.Consts.*;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EAttendanceInfo {

    @Feature("מדד רמזור")
    @Story("בדיקת מדד רמזור")
    @Severity(CRITICAL)
    @Test(description = "Checking Attendance Info")
    void checkAttendanceInfo(){
        new MenuBL()
                .findSubunit(SUBUNIT_NAME)
                .click()
                .findSolider(SOLIDER_NAME)
                .click()
                .clickAttendanceInfo()
                .findRamzorGraph()
                .checkSystemRecommendation()
                .chooseRandomRecommendation()
                .chooseRandomReason()
                .saveAttendanceInfo()
                .exitApproval()
                .ensureAttendanceInfo();
    }
}
