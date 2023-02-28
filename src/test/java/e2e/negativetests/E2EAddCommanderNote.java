package e2e.negativetests;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import framework.ReadExcel;
import framework.testrunner.ClassLevelWebRunner;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static e2e.Consts.*;

import java.util.logging.Level;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@Test(groups = {"E2E negative test"}, suiteName = "E2E")
@Listeners({ClassLevelWebRunner.class, UniversalVideoListener.class})
public class E2EAddCommanderNote {
    private static final Logger logger = LogManager.getLogger(E2EAddCommanderNote.class);

    @Feature("הערות מפקד")
    @Story("הזנת הערה לחייל")
    @Severity(CRITICAL)
    @Test(description = "Checking the limitations of adding commander note",
            dataProvider = "CommanderNoteData", dataProviderClass = ReadExcel.class)
    @Video(name = "E2E_negative_test_of_add_commander_note")
    void addCommanderNote(String commanderNote) {
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.info("Info Message!");
        logger.warn("Warn Message!");
        logger.error("Error Message!");
        logger.fatal("Fatal Message!");
        logger.info("Test started");
        new MenuBL()
                .findSubunit(SUBUNIT_NAME)
                .click()
                .findSolider(SOLIDER_NAME)
                .click()
                .clickOnNewCommanderNote()
                .checkSaveDisabled()
                .writeNegativeNote(commanderNote)
                .clearCommanderNote()
                .openCalendar()
                .checkDatesLimitations()
                .chooseRandomDate()
                .checkSaveDisabled();
        logger.getLevel();
        logger.info("Test Finished");
    }

}
