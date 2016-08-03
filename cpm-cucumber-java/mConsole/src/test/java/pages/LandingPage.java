package pages;

import cpm.model.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.driver.DriverFactory;


/**
 * Created by hemantojha on 21/07/16.
 */
public class LandingPage extends BasePageObject{

    public LandingPage() throws Exception{
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(how = How.CSS, using = "#manageTransactionBtn")
    private WebElement manageTransactionButtonLocator;

    public void clickOnManageTransaction(){
        manageTransactionButtonLocator.click();
        sync.wait10().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#paginate")));
    }



}
