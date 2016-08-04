package steps.iOSAppSteps;

import com.google.common.base.Optional;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import utils.listeners.WebDriverListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by hemantojha on 01/08/16.
 */
public class MBEApp {

    AppiumDriver driver;
    EventFiringWebDriver wd;
    WebDriverListeners eventListener;

    @Test
    public void testMobile() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.0");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "9.2");
        capabilities.setCapability("deviceName", "iPhone 6s");
        capabilities.setCapability("app", "/Users/hemantojha/Documents/Cellpoint/mbeapp_3_12/MBE.app");
        capabilities.setCapability("autoAcceptAlerts", true);

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wd = new EventFiringWebDriver(driver);
        eventListener = new WebDriverListeners();
        wd.register(eventListener);


        wd.findElement(By.xpath("//*[@name='English']")).click();

        wd.findElement(By.xpath("//*[@name='My Profile']/UIAButton[1]")).click();

        WebElement firstName = wd.findElement(By.xpath("//UIATextField[1]"));
        firstName.sendKeys("Hemantt");
        firstName.sendKeys(Keys.BACK_SPACE);

        WebElement lastName = wd.findElement(By.xpath("//UIATextField[2]"));
        lastName.sendKeys("Ojhaa");
        lastName.sendKeys(Keys.BACK_SPACE);

        wd.findElement(By.xpath("//UIATextField[3]")).click();
        wd.findElement(By.xpath("//UIAPicker[1]/UIAPickerWheel[1]")).sendKeys("USA");


        wd.findElement(By.xpath("//UIATextField[4]"))
                .sendKeys("1234567890");

        wd.findElement(By.xpath("//UIATextField[5]"))
                .sendKeys("agh@ccc.com");

        //hide the keyboard

        if(ifKeyboard().isPresent()){
            wd.findElement(By.xpath("//*[@name='Return']")).click();
        }

        wd.findElement(By.xpath("//*[@name='Save & Continue']")).click();


        //Add-ons


        wd.findElement(By.xpath("//*[@name='Add-ons']")).click();

        wd.findElement(By.xpath("//UIATableView[1]//UIAStaticText[@name='Configuration']")).click();

        wd.findElement(By.xpath("//*[@name='Save']")).click();

        wd.navigate().back();

        //Search for flight

        wd.findElement(By.xpath("//*[@name='Flight Search']/UIAButton[1]")).click();


        wd.findElement(By.xpath("//UIAButton[@name='ONE WAY']")).click();

        wd.findElement(By.xpath("//UIAButton[@name='SEARCH FLIGHT']")).click();

        wd.findElement(By.xpath("//UIATableCell[1]")).click();

        wd.findElement(By.xpath("//UIAButton[@name='REVIEW AND PURCHASE']")).click();

        wd.findElement(By.xpath("//UIAButton[@name='PROCEED TO PAYMENT']")).click();

        //Select visa checkout
        wd.findElement(By.xpath("//UIAButton[@name='card 16']")).click();

        //VCO WebView
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wd.findElement(By.xpath("//UIATextField[@name='Username']")).sendKeys("tejashree@cellpointmobile.com");

        wd.findElement(By.xpath("//UIASecureTextField[@name='Password']")).sendKeys("Abcd@123");

        wd.findElement(By.xpath("//UIAButton[@name='Sign In']")).click();

        wd.findElement(By.xpath("//UIAButton[@name='Continue']")).click();

        wd.findElement(By.xpath("//UIATextField[1]")).sendKeys("1005");

        //hide the keyboard
        if(ifKeyboard().isPresent()){
            wd.findElement(By.xpath("//*[@name='Return']")).click();
        }

        wd.findElement(By.xpath("//UIAButton[@name='CONTINUE']")).click();

        wd.findElement(By.xpath("//UIAButton[@name='OK'][1]")).click();


        wd.quit();
    }

    public Optional<WebElement> ifKeyboard(){

        Optional<WebElement> optional = Optional.of(wd.findElement(By.xpath("//UIAKeyboard[1]")));
        return optional;
    }

}
