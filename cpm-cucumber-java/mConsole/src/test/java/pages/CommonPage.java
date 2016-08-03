package pages;

import cpm.model.BasePageObject;
import org.apache.commons.logging.Log;
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
public class CommonPage extends BasePageObject{


    public CommonPage() throws Exception{
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }


    @FindBy(how = How.ID, using = "displayName")
    private WebElement displayNameLocator;

    @FindBy(how = How.CSS, using = ".user.dropdown-menu a[href='/app/logout']")
    private WebElement logoutLocator;


    public LoginPage logout() throws Exception{

        displayNameLocator.click();
        sync.wait2().until(ExpectedConditions.visibilityOf(logoutLocator));
        logoutLocator.click();
        return new LoginPage();
    }

}
