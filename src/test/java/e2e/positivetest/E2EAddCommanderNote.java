package e2e.positivetest;

import framwork.general.DataFaker;
import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsBL;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EAddCommanderNote {
    String subunitName = "פלוגה א";
    String soliderName = "עמיחי מרנס";
    String note = new DataFaker().gameOfThrones(32);

    @Test(description = "Checking the positive process of adding commander note")
    void addCommanderNote() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click()
                .clickOnNewCommanderNote()
                .writeNote(note)
                .scheduleNote()
                .chooseRandomDate()
                .saveNote();
    }

    @Test(description = "Ensure commander note has been added", dependsOnMethods = "addCommanderNote", priority = 1)
    void ensureCommanderNote() {
        new SoliderDetailsBL()
                .isNoteFound(note);
    }

    @Test(description = "Deleting commander note", dependsOnMethods = "addCommanderNote", priority = 2)
    void deleteCommanderNote() {
        new SoliderDetailsBL()
                .deleteNote(note)
                .approveDelete();
    }







}
