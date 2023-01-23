package e2e.positivetest;

import com.codeborne.selenide.Selenide;
import framwork.general.DataFaker;
import framwork.testrunner.ClassLevelWebRunner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.menu.MenuBL;
import pages.soliderDetails.SoliderDetailsPage;

@Test(groups = {"E2E positive test"}, suiteName = "E2E")
@Listeners(ClassLevelWebRunner.class)
public class E2EAddCommanderNote {

    @Test
    void AddCommanderNote() {
        final SoliderDetailsPage page = Selenide.page(SoliderDetailsPage.class);
        DataFaker dataFaker = new DataFaker();

        String action = "מחק";
        String note = dataFaker.gameOfThrones(32);

        new MenuBL()
                .findSubunit("פלוגה א")
                .click()
                .findSolider("עמיחי מרנס")
                .click()
                .clickOnNewCommanderNote()
                .writeNote(note)
                .scheduleNote()
                .chooseRandomDate()
                .saveNote()
                .checkNoteSaved(note)
                .isNoteFound(note)
                .deleteLastNote(action)
                .approveDelete();







    }
}
