package objects;

import cpm.model.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by hemantojha on 25/07/16.
 */
public class TransactionRowObject extends BasePageObject{

    private WebElement row;

    public TransactionRowObject(int rowNumber){
        WebElement table = getWrappedDriver().findElement(By.cssSelector("#paginate"));
        row = table.findElement(By.cssSelector(String.format("tbody tr:nth-child(%d)",rowNumber)));
    }


    public String getTransactionId(){
        return row.findElement(By.cssSelector(String.format("td:nth-child(%d)",4))).getText();
    }


    public String getMobile(){
        return row.findElement(By.cssSelector(String.format("td:nth-child(%d)",9))).getText();
    }


    public String getEmail(){
        return row.findElement(By.cssSelector(String.format("td:nth-child(%d)",10))).getText();
    }

}
