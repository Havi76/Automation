package Sandbox;

import e2e.positivetests.E2EAddCommanderNote;
import e2e.positivetests.E2EInterview;
import framework.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;

@Test(groups = {"E2E"})
@Listeners(ClassLevelWebRunner.class)
public class Tests {
    String subunitName = "פלוגה א";
    String soliderName = "עמיחי מרנס";
    String date = "21.2.23";
    String userName = "ינוב סגל";
    String interviewerId = "212753743";

    @Test
    void e2eTest() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click();
        new SoliderDetailsBL()
                .clickOnNewCommanderNote()
                .cancelButtonClickTextEmpty()
                .clickOnNewCommanderNote()
                .writeNote(E2EAddCommanderNote.note)
                .openCalendar()
                .chooseRandomDate()
                .cancelAndClickNo(E2EAddCommanderNote.note)
                .cancelAndClickYes()
                .isNoteFound(E2EAddCommanderNote.note, true);
    }

}
