package pages.commendNotes;

import com.codeborne.selenide.Selenide;
import framework.elements.widgets.TextField;
import pages.soliderDetails.SoliderDetailsBL;
import pages.datePicker.DatePickerBL;

import static com.codeborne.selenide.Condition.*;

public class AddCommanderNoteBL {
    private final AddCommanderNotePage page =
            Selenide.page(AddCommanderNotePage.class);

    public AddCommanderNoteBL writeNote(String note) {
        page.addNoteTitle().shouldHave(exactText("הערת מפקד"));
        page.textInput().val(note);
        return this;
    }

    public AddCommanderNoteBL writeNegativeNote(String maxCharsNote){
        page.addNoteTitle().shouldHave(exactText("הערת מפקד"));
        page.textInput().val(maxCharsNote);
        page.commanderNoteErrorMessage().shouldHave(text("עברת את מגבלת התווים המותרת"));
        return this;
    }

    public AddCommanderNoteBL checkSaveDisabled() {
        page.saveButton().shouldBe(disabled);
        return this;
    }

    public AddCommanderNoteBL clearCommanderNote() {
        TextField.clear(page.textInput());
        return this;
    }

    public DatePickerBL openCalendar(){
        page.datePickerButton().click();
        return new DatePickerBL();
    }

    public SoliderDetailsBL saveNote() {
        page.saveButton().shouldBe(enabled).click();
        return new SoliderDetailsBL();
    }

    public SoliderDetailsBL cancelButtonClickTextEmpty(){
        page.cancelButton().click();
        return new SoliderDetailsBL();
    }

    public AddCommanderNoteBL cancelButtonClickTextFilled(){
        page.cancelButton().click();
        return this;
    }

    public AddCommanderNoteBL cancelAndClickNo(String note){
        page.cancelButton().click();
        page.exitWithoutSaveNoButton().click();
        page.textInput().shouldHave(text(note));
        page.datePickerSvg().should(attribute("fill", "#5271FF"));
        return new AddCommanderNoteBL();
    }

    public SoliderDetailsBL cancelAndClickYes(){
        page.cancelButton().click();
        page.exitWithoutSaveYesButton().click();
        return new SoliderDetailsBL();
    }
}
