package pages.attendanceInfo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class AttendanceInfoPage {

    @Find(className = "RamzorGraph_wrapper__2uabD")
    private ElementsCollection ramzorGraphsWrappers;

    @Find(className = "Ramzor_dropdown__cEGzz")
    private SelenideElement dropDownList;

    @Find(className = "CostumeMenu_item__2mSlv")
    private ElementsCollection dropDownListOptions;

    @Find(className = "Ramzor_saveButton__GyZsV")
    private SelenideElement saveButton;

    @Find(className = "Notification_textTitle__29Bzu")
    private SelenideElement approvalText;

    @Find(className = "Notification_XIcon__1EBBv")
    private SelenideElement approvalExitButton;
}
