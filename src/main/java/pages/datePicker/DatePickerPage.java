package pages.datePicker;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class DatePickerPage {
    @Find(css =
            ".DatePickerCalendar_dayCell__2LmVF:not(.DatePickerCalendar_disabledCell__3F9L-)")
    private ElementsCollection enabledDays;

    @Find(className = "DatePickerCalendar_dayCell__2LmVF")
    private ElementsCollection allDays;

    @Find(css = ".DatePickerCalendar_dayCell__2LmVF.DatePickerCalendar_disabledCell__3F9L- .DatePickerCalendar_dayCell_today__RS0hv")
    private SelenideElement todayCell;
}
