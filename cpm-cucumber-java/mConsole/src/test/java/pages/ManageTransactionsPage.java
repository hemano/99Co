package pages;

import com.google.common.base.Optional;
import cpm.model.BasePageObject;
import objects.TransactionRowObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.driver.DriverFactory;
import utils.driver.WebDriverUtils;

/**
 * Created by hemantojha on 25/07/16.
 */
public class ManageTransactionsPage extends BasePageObject {


    @FindBy(how = How.ID, using = "quickSearchByName")
    private WebElement searchTransactionsInputLocator;


    By searchTransactionOptionsByLocator = By.cssSelector(".intuitive-search-ul");
    By transactionTableByLocator = By.cssSelector("#paginate");


    public void enterValueInSearchTransaction(String searchKey){
        logToReport(String.format("Searching with value {%s}",searchKey));
        searchTransactionsInputLocator.click();
        searchTransactionsInputLocator.clear();
        searchTransactionsInputLocator.sendKeys(searchKey);
    }

    public void clickOnTheOption(WebElement optionElement){
        logToReport("Clicking on the Option");
        optionElement.click();

        WebElement table = sync.wait10().until(ExpectedConditions.presenceOfElementLocated(transactionTableByLocator));
        sync.wait2().until(ExpectedConditions.visibilityOf(table));
    }

    public Optional<WebElement> doesSearchOptionExists(){
        return WebDriverUtils.elementExists(getWrappedDriver(),searchTransactionOptionsByLocator,2);
    }

    public String getMobileNumberFirstRow(){
        return new TransactionRowObject(1).getMobile();
    }


    public String getEmailFirstRow(){
        return new TransactionRowObject(1).getEmail();
    }

    public ManageTransactionsPage() throws Exception {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    public String getURL(){
        return getWrappedDriver().getCurrentUrl();
    }
}
