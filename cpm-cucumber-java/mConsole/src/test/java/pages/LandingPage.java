package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hemantojha on 21/07/16.
 */
public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver){
//        PageFactory.initElements(DriverFactory.getDriver(), this);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "#manageTransactionBtn")
    private WebElement manageTransactionButtonLocator;

    public void clickOnManageTransaction(){
        manageTransactionButtonLocator.click();
    }


}
