package pages.menu;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framework.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class MenuPage {
    @Find(className = "UnitRect_header__23_TK")
    private SelenideElement unitTitle;

    @Find(className = "UnitRect_parenthesis__4aO0b")
    private SelenideElement unitParenthesis;

    @Find(className = "UnitRect_newActionIcon__1ni2B")
    private SelenideElement actionButton;

    @Find(className = "functionalRect_labelSelects__1K1VN")
    private SelenideElement labelSelecetsButton;

    @Find(className = "functionalRect_OnOff__ItL10")
    private SelenideElement onOffButton;

    @Find(className = "functionalRect_Filter__3B9gc")
    private SelenideElement filterButton;

    @Find(className = "functionalRect_Search__v4369")
    private SelenideElement searchButton;

    @Find(id = "searchInputTreeId")
    private SelenideElement searchField;

    @Find(className = "functionalRect_functionalItem__7LfMm")
    private SelenideElement orderByButton;

    @Find(id = "מסגרת")
    private SelenideElement subUnitOrderButton;

    @Find(id = "א ב")
    private SelenideElement abcOrderButton;

    @Find(className = "ShowByWindow_expandedTitle__1tAVc div:nth-child(1)")
    private SelenideElement firstNameOrderButton;

    @Find(css = ".ShowByWindow_expandedTitle__1tAVc div:nth-child(2)")
    private SelenideElement lastNameOrderButton;

    @Find(id = "אוכלוסיה")
    private SelenideElement populationOrderButton;

    @Find(id = "תפקידים")
    private SelenideElement rolesOrderButton;

    @Find(className = "SegelRect_segelRectangle__ZM0KX")
    private SelenideElement segelCard;

    @Find(className = "frameworkRect_PlugaRectangleDesktop__kleu6")
    private ElementsCollection subunitList;

    @Find(className = "SoldierRect_SoldierWind__3BRIq")
    private ElementsCollection solidersCards;

//    @Find(css = "LandingRight_desktopScrollContent__1NetZ div:nth-child()")
//    private SelenideElement ;


}
