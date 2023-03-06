package pages.soliderDetails;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.attendanceInfo.AttendanceInfoBL;
import pages.commendNotes.AddCommanderNoteBL;
import pages.deleteNote.DeleteNoteBL;
import pages.newDistributionMessage.NewDistributionMessageBL;
import pages.newInterview.NewInterviewBL;
import static com.codeborne.selenide.Condition.*;
import static framework.configuration.ScrollBehaviour.smooth;
import static pages.newInterview.NewInterviewBL.interviewFoundFlag;
import static pages.uielement.RamzorGraph.chosenRecommendationValue;

public class SoliderDetailsBL {
    private final SoliderDetailsPage page = Selenide.page(SoliderDetailsPage.class);
    static boolean showingMore = false;
    public static Boolean noteFoundFlag = false;

    @Step
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

    public SoliderDetailsBL isNoteFound(String note, Boolean dontFindFlag){
        page.notesListWhenClosed().shouldBe
                (CollectionCondition.sizeGreaterThan(0));
        if (page.moreNotesSign().exists()){
            page.moreNotesSign().click();
            if (!page.notesListWhenOpen().filter(text(note)).isEmpty()){
                    noteFoundFlag = true;
                    return this;
            }
            page.exitButton().click();
        }
        else {
            if (!page.notesListWhenClosed().filter(text(note)).isEmpty()){
                noteFoundFlag = true;
                return this;
            }
        }
        assert dontFindFlag || noteFoundFlag;
        return this;
    }

    public DeleteNoteBL deleteNote(String note){
        page.moreOptions().get(2).click();
        page.deleteButton().click();
        return new DeleteNoteBL();
    }

    public SoliderDetailsBL exitClick(){
        page.exitButton().click();
        return new SoliderDetailsBL();
    }

    public NewDistributionMessageBL openLastDistributionMessageUpdate(){
        page.updatesCards().find(text("הודעת תפוצה")).scrollIntoView(smooth);
        page.updatesCards().find(text("הודעת תפוצה")).click();
        page.distributionMessageUpdates().shouldHave(CollectionCondition.sizeGreaterThan(0));
        page.distributionMessageUpdates().get(0).click();
        return new NewDistributionMessageBL();
    }

    public NewDistributionMessageBL openDistributionMessageUpdate(String name){
        page.updatesCards().find(text("הודעת תפוצה")).should(visible, enabled).scrollIntoView(smooth);
        page.updatesCards().find(text("הודעת תפוצה")).click();
        page.distributionMessageUpdates().shouldHave(CollectionCondition.sizeGreaterThan(0));
        page.distributionMessageUpdates().find(text(name)).click();
        return new NewDistributionMessageBL();
    }

    public SoliderDetailsBL closeDistributionMessageUpdate(){
        page.updatesCards().find(text("הודעת תפוצה")).should(visible, enabled).scrollIntoView(smooth);
        page.updatesCards().find(text("הודעת תפוצה")).click();
        return this;
    }

    public SoliderDetailsBL pressOnHavadAndInterviews() {
        page.havadsAndInterviewsButton().click();
        return this;
    }

    public NewInterviewBL pressOnNewInterview() {
        page.addNewInterviewButton().click();
        return new NewInterviewBL();
    }

    public SoliderDetailsBL isInterviewFound(String date, String userName, Boolean dontFindFlag) {
        ElementsCollection relevantInterviewsCollection;
        if (page.showmoreButton().exists()){
            page.showmoreButton().click();
            showingMore = true;
            relevantInterviewsCollection = page.interviewsCollectiononPopUp().shouldHave(CollectionCondition.sizeGreaterThan(0))
                    .filter(text(date)).filter(text(userName));
        }
        else
            relevantInterviewsCollection = page.interviewsCollectiononSoliderDetailsPage().shouldHave(CollectionCondition.sizeGreaterThan(0))
                    .filter(text(date)).filter(text(userName));
        for (SelenideElement interview : relevantInterviewsCollection.shouldHave(CollectionCondition.sizeGreaterThan(0))) {
            interview.shouldBe(enabled, visible).click();
            new NewInterviewBL()
                    .checkInterviewContent()
                    .exitExistingInterview();
            if (interviewFoundFlag)
                return this;
        }
        assert dontFindFlag || interviewFoundFlag;
        return this;
    }

    public void closeShowingMoreIfOpen() {
        if (showingMore)
            page.closeShowingMoreButton().click();
        showingMore = false;
    }

    public AttendanceInfoBL clickAttendanceInfo(){
        String attendanceInfoValue = page.attendanceInfoValue().text();
        page.attendanceInfoValue().click();
        return new AttendanceInfoBL(attendanceInfoValue, page.isCommanderGrade().exists());
    }

    public SoliderDetailsBL ensureAttendanceInfo(){
        page.attendanceInfoValue().shouldHave(text(chosenRecommendationValue));
        return this;
    }
}
