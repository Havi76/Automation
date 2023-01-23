package pages.chooseParticipates;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import framwork.annotations.locators.Find;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ChooseParticiptesPage {
    @Find(className = "ActionsTreeFramework_singleCheckbox__2ZLt4")
    private ElementsCollection subunitCheckboxCollection;

    @Find(className = "ActionTreeFunctional_searchInput__1Vbbe")
    private SelenideElement searchInput;

    @Find(className = "ActionTreeSoldier_singleCheckbox__2gN-V")
    private SelenideElement soliderCheckbox;

    @Find(className = "GenericButton_Button__2PbBa")
    private SelenideElement chooseButton;
}
