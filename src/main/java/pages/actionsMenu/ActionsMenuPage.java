package pages.actionsMenu;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ActionsMenuPage {
    @Find(className = "ActionsMenu_modalTitle__3fGJp")
    private SelenideElement actionsMenuHeader;

    @Find(text = "זימון למילואים")
    private SelenideElement recruitmentButton;

    @Find(text = "תנועה בתוך המסגרת")
    private SelenideElement moveInSubunitButton;

    @Find(text = "שינוי תפקיד לחייל")
    private SelenideElement changeRoleButton;

    @Find(text = "הגדרת תפקיד")
    private SelenideElement defineRoleButton;

    @Find(className = "ActionsMenu_searchInput__3XyLB")
    private SelenideElement actionSearchBar;

    @Find(className = "ActionsMenu_actionsGroupName__1cm6x")
    private ElementsCollection actionsTitles;

    @Find(className = "ActionsMenu_actionBlock__3RCxb")
    private ElementsCollection actions;
}
