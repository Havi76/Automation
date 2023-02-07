package e2e.positivetest;

import framwork.general.DataFaker;
import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;

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

    @Test(description = "Ensure commander note has been added", dependsOnMethods = "addCommanderNote")
    void ensureCommanderNote() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click()
                .isNoteFound(note);
    }

    @Test(description = "Deleting commander note", dependsOnMethods = "addCommanderNote")
    void deleteCommanderNote() {
        new MenuBL()
                .findSubunit(subunitName)
                .click()
                .findSolider(soliderName)
                .click()
                .deleteNote(note)
                .approveDelete();
    }







}
