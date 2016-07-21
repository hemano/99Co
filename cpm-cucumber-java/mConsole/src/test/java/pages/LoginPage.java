package pages;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by hemantojha on 21/07/16.
 */
public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
//        PageFactory.initElements(DriverFactory.getDriver(), this);
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.ID, using = "userName")
    private WebElement userNameLocator;

    @FindBy(how = How.ID, using = "inputPassword")
    private WebElement inputPasswordLocator;

    @FindBy(how = How.ID, using = "login")
    private WebElement loginButtonLocator;


    public LoginPage enterUserName(String userName){
        userNameLocator.sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String pwd){
        inputPasswordLocator.sendKeys(pwd);
        return this;
    }

    public void andLogin(){
        loginButtonLocator.click();
    }

}
