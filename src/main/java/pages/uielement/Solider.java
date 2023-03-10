package pages.uielement;

import com.codeborne.selenide.SelenideElement;
import framework.annotations.RootLocator;
import framework.annotations.locators.Find;
import framework.elements.UIElement;
import pages.soliderDetails.SoliderDetailsBL;

@RootLocator(".SoldierRect_SoldierWind__3BRIq")
public class Solider extends UIElement {
    @Find(css =".SoldierRect_FullNameText__2V8fK div")
    private SelenideElement name;

    public Solider(SelenideElement self){
        super(self);
    }

    public SoliderDetailsBL click(){
        getSelf().click();
        return new SoliderDetailsBL();
    }
}
