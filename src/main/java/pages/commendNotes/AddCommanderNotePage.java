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

    @Find(css = ".DatePickerInput_calendarIcon__2E49_ svg")
    private SelenideElement datePickerSvg;

    @Find(className = "Notes_ModalText__3MnLp")
    private SelenideElement commanderNoteErrorMessage;

    @Find(className = "Notes_whiteButton___IEgE")
    private SelenideElement cancelButton;

    @Find(className = "Notification_whiteButton__d2POB")
    private SelenideElement exitWithoutSaveNoButton;

    @Find(className = "Notification_blueButton__q0TEz")
    private SelenideElement exitWithoutSaveYesButton;

}
