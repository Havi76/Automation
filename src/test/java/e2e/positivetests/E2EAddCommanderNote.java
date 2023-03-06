package e2e.positivetests;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.annotations.Video;
import framework.general.DataFaker;
import framework.testrunner.ClassLevelWebRunner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;

import java.util.Date;

import static e2e.Consts.*;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners({ClassLevelWebRunner.class, UniversalVideoListener.class})
public class E2EAddCommanderNote {
    public static Logger log = Logger.getLogger(E2EAddCommanderNote.class.getName());
    Date date = new Date();
    public static String note = new DataFaker().gameOfThrones(32);

    @Test(description = "Checking the positive process of adding commander note")
    @Video(name = "E2E_test_of_add_commander_note")
    void addCommanderNote() {
        System.setProperty("current.date", date.toString().replace(":", "_").replace(" ", "_"));
        PropertyConfigurator.configure("./src/test/resources/log4j.properties");
        log.info("The test of add commander note started");
        new MenuBL()
                .findSubunit(SUBUNIT_NAME)
                .click()
                .findSolider(SOLIDER_NAME)
                .click()
                .clickOnNewCommanderNote()
                .writeNote(note)
                .openCalendar()
                .chooseRandomDate()
                .saveNote();
        log.info("The test of add commander note finished");
    }

    @Test(description = "Ensure commander note has been added", dependsOnMethods = "addCommanderNote", priority = 1)
    @Video(name = "E2E_test_of_ensure_add_commander_note")
    void ensureCommanderNote() {
        new SoliderDetailsBL()
                .isNoteFound(note, false);
    }

    @Test(description = "Deleting commander note", dependsOnMethods = "addCommanderNote", priority = 2)
    void deleteCommanderNote() {
        new SoliderDetailsBL()
                .deleteNote(note)
                .approveDelete()
                .exitClick();
    }

    @Test(description = "Checking cancel button", priority = 3)
    void cancelCommanderNote(){
        new SoliderDetailsBL()
                .clickOnNewCommanderNote()
                .cancelButtonClickTextEmpty()
                .clickOnNewCommanderNote()
                .writeNote(note)
                .openCalendar()
                .chooseRandomDate()
                .cancelAndClickNo(note)
                .cancelAndClickYes()
                .isNoteFound(note, true);
    }
}
