package pages.datePicker;

import com.codeborne.selenide.ElementsCollection;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class DatePickerPage {
    @Find(css =
            ".DatePickerCalendar_dayCell__2LmVF:not(.DatePickerCalendar_disabledCell__3F9L-, .DatePickerCalendar_otherMonthCell__n50Eo)")
    private ElementsCollection enabledDates;
}
