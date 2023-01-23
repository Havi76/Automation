package pages.commendNotes;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import pages.soliderDetails.SoliderDetailsBL;
import pages.datePicker.DatePickerBL;

import java.util.Random;

public class AddCommanderNoteBL {
    private final AddCommanderNotePage page =
            Selenide.page(AddCommanderNotePage.class);

    public AddCommanderNoteBL writeNote(String note) {
        page.addNoteTitle().shouldHave(Condition.exactText("הערת מפקד"));
        page.textInput().val(note);
        return this;
    }

    public DatePickerBL scheduleNote(){
        page.datePickerButton().click();
        return new DatePickerBL();
    }

    public SoliderDetailsBL saveNote() {
        page.saveButton().shouldBe(Condition.enabled).click();
        return new SoliderDetailsBL();
    }
}
