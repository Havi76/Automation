package pages.soliderDetails;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import pages.commendNotes.AddCommanderNoteBL;
import pages.deleteNote.DeleteNoteBL;
import pages.newDistributionMessage.NewDistributionMessageBL;
import pages.newInterview.NewInterviewBL;

import static com.codeborne.selenide.Condition.text;
import static framwork.configuration.ScrollBehaviour.smooth;

public class SoliderDetailsBL {
    private final SoliderDetailsPage page = Selenide.page(SoliderDetailsPage.class);

    public AddCommanderNoteBL clickOnNewCommanderNote() {
        page.addCommanderNote().click();
        return new AddCommanderNoteBL();
    }

    public SoliderDetailsBL checkNoteSaved(String note){
        page.notesListWhenClosed().shouldBe(CollectionCondition.sizeGreaterThan(0));
        page.notesListWhenClosed().get(0).shouldBe(text(note));
        //בדיקה מספיקה אך יוצאת מנקודת
        // הנחה שההערה שנשמרת היא האחרונה
        return this;
    }

    public SoliderDetailsBL isNoteFound(String note){
        page.notesListWhenClosed().shouldBe
                (CollectionCondition.sizeGreaterThan(0));
        if (page.moreNotesSign().exists()){
            page.moreNotesSign().click();
            page.notesListWhenOpen().filter(text(note))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
            page.exitButton().click();
        }
        else {
            page.notesListWhenClosed().filter(text(note))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
        }
        return this;
    }

    public DeleteNoteBL deleteLastNote(String action){
        page.moreOptions().click();
        page.deleteButton().click();
        return new DeleteNoteBL();
    }

    public NewDistributionMessageBL openLastDistributionMessageUpdate(){
        page.updatesCards().find(text("הודעת תפוצה")).scrollIntoView(smooth);
        page.updatesCards().find(text("הודעת תפוצה")).click();
        page.distributionMessageUpdates().shouldHave(CollectionCondition.sizeGreaterThan(0));
        page.distributionMessageUpdates().get(0).click();
        return new NewDistributionMessageBL();
    }

    public NewDistributionMessageBL openDistributionMessageUpdate(String name){
        Selenide.refresh();
        page.updatesCards().find(text("הודעת תפוצה")).scrollIntoView(smooth);
        page.updatesCards().find(text("הודעת תפוצה")).click();
        page.distributionMessageUpdates().shouldHave(CollectionCondition.sizeGreaterThan(0));
        page.distributionMessageUpdates().find(text(name)).click();
        return new NewDistributionMessageBL();
    }

    public SoliderDetailsBL pressOnHavadAndInterviews() {
        page.havadsAndInterviewsButton().click();
        return this;
    }

    public NewInterviewBL pressOnNewInterview() {
        page.addNewInterviewButton().click();
        return new NewInterviewBL();
    }
}
