package pages.commendNotes;

import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class AddCommanderNotePage {
    @Find(className = "Notes_textCss__3lqS2")
    private SelenideElement textInput;

    @Find(className = "Notes_blueButton__14i8f")
    private SelenideElement saveButton;

    @Find(className = "Notes_modalTitleTxt__2MLVu")
    private SelenideElement addNoteTitle;

    @Find(className = "DatePickerInput_calendarIcon__2E49_")
    private SelenideElement datePickerButton;
}
