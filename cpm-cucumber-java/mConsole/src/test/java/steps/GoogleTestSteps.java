package steps;

import cucumber.api.java.en.Given;
import domain.UserDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverFactory;
import utils.PropertyReader;

import java.util.List;

/**
 * Created by hemantojha on 21/07/16.
 */
public class GoogleTestSteps extends DriverFactory{

    private static final Logger logger = LoggerFactory.getLogger( GoogleTestSteps.class );


    @Given("^User finds the title relevant to search keyword$")
    public void User_finds_the_title_relevant_to_search_keyword(List<UserDetails> users) throws Throwable {

        UserDetails userDetails = users.get(0);
        System.out.println("userDetails = " + userDetails.searchKeyword);
        System.out.println("userDetails = " + userDetails.firstName);

        userDetails = users.get(1);
        System.out.println("userDetails = " + userDetails.searchKeyword);
        System.out.println("userDetails = " + userDetails.firstName);


        try {
            driver.get("http://www.google.com");
            driver.manage().window().maximize();

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Milk");
            searchBox.submit();

            By rso = By.cssSelector("#rso");
            WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(rso));
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));

            String title = driver.getTitle();

            System.out.println("title = " + title);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
        }




    }


}

