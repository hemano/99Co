package steps;

import utils.listeners.WebDriverListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hemantojha on 03/08/16.
 */
public class TBD {

    private WebDriver driver;

    @Test
    public void test(){
        DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        System.out.println("dateFormat.format(date).toString() = " + dateFormat.format(date).toString());


    }

    @BeforeTest(enabled = false)
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test(enabled = false)
    public void Test01() throws Exception {

        EventFiringWebDriver event = new EventFiringWebDriver(driver);
        WebDriverListeners eventListener = new WebDriverListeners();
        event.register(eventListener);

        // beforeNavigateTo & afterNavigateTo
        event.get("http://www.google.com");

        // beforeNavigateBack & afterNavigateBack
        event.get("http://www.google.com");
        event.get("http://www.bing.com");
        event.navigate().back();

        // beforeNavigateForward & afterNavigateForward
        event.get("http://www.google.com");
        event.get("http://www.bing.com");
        event.navigate().back();
        event.navigate().forward();

        // onException
        event.get("http://.........");
        event.findElement(By.id("Wrong Value"));

        // beforeFindBy & afterFindBy
        event.get("http://.........");
        event.findElement(By.id("Value"));

        // beforeClickOn & afterClickOn
        event.get("http://.........");
        event.findElement(By.id("Value")).click();

        // beforeChangeValueOf & afterChangeValueOf
        event.get("http://.........");
        event.findElement(By.id("Value")).clear();
        event.findElement(By.id("Value")).sendKeys("Seleniumworks");
    }
}

