package pages;

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
public class LoginPage extends BasePageObject{


    public LoginPage() throws Exception{
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }


    @FindBy(how = How.ID, using = "userName")
    private WebElement userNameLocator;

    @FindBy(how = How.ID, using = "inputPassword")
    private WebElement inputPasswordLocator;

    @FindBy(how = How.ID, using = "login")
    private WebElement loginButtonLocator;


    public LoginPage enterUserName(String userName){
        logToReport(String.format("User is entering user name {%s}",userName));
        userNameLocator.sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String pwd){
        logToReport(String.format("User is entering password {%s}",pwd));
        inputPasswordLocator.sendKeys(pwd);
        return this;
    }

    public void andLogin(){
        logToReport(String.format("User clicks on Login Button"));
        loginButtonLocator.click();
        WebElement element = sync.wait10().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#manageTransactionBtn")));
        sync.wait2().until(ExpectedConditions.visibilityOf(element));
    }

    public void UserLogsInWithUserNameAndPassword(){
        String userName = new PropertyReader().readProperty("userName");
        String password = new PropertyReader().readProperty("password");
        this.enterUserName(userName).enterPassword(password).andLogin();
    }

}
