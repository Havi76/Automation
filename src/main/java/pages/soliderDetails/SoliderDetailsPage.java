package pages.soliderDetails;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class SoliderDetailsPage {
    @Find(className = "Notes_add__3OGS0")
    private SelenideElement addCommanderNote;

    @Find(css = ".Notes_body__ZbSnt .Notes_note__WyTNF")
    private ElementsCollection notesListWhenClosed;

    @Find(className = "Notes_More__19olI")
    private SelenideElement moreNotesSign;

    @Find(className = "Notes_fullViewNote__3meGg")
    private ElementsCollection notesListWhenOpen;

    @Find(css = ".Notes_bodyIcons__DcuhI svg")
    private SelenideElement moreOptions;

    @Find(text = "מחק")
    private SelenideElement deleteButton;

    @Find(className = "Notes_titleLeft__2PKyb")
    private SelenideElement exitButton;

    @Find(className = "FanRequest_textGrid__3eouT")
    private ElementsCollection updatesCards;

    @Find(className = "RequestWindow_BDayMsg__1ese2")
    private ElementsCollection distributionMessageUpdates;

    @Find(text = "חוו\"דים וריאיונות")
    private SelenideElement havadsAndInterviewsButton;

    @Find(className = "Interviews_addIcon__2vk43")
    private SelenideElement addNewInterviewButton;

    @Find(className = "ShowMore_extraRow__YwXyC")
    private SelenideElement showmoreButton;

    @Find(css = ".Interviews_allInterviewsContainer__1LmGl .Interview_interview__3HvWr")
    private ElementsCollection interviewsCollectiononPopUp;

    @Find (css = ".Interviews_interviewsContainer__VSAsX .Interview_interview__3HvWr")
    private ElementsCollection interviewsCollectiononSoliderDetailsPage;

    @Find(className = "Interview_interviewDate__3-tYQ")
    private SelenideElement interviewDate;


}
