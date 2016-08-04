package pages.ios;

import cpm.model.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyReader;
import utils.driver.DriverFactory;


/**
 * Created by hemantojha on 21/07/16.
 */
public class HomePage extends BasePageObject{


    public HomePage() throws Exception{
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }


    @FindBy(how = How.XPATH, using = "//*[@name='English']")
    private WebElement chooseEnglishLanguageLocator;

    @FindBy(how = How.XPATH, using = "//*[@name='My Profile']/UIAButton[1]")
    private WebElement myProfileLocator;


    public HomePage selectEnglishLanguage(){
        logToReport("Choosing the English language");
        chooseEnglishLanguageLocator.click();
        return this;
    }

    public HomePage selectMyProfile(){
        logToReport("Select My Profile");
        myProfileLocator.click();
        return this;
    }

}
