package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hemantojha on 21/07/16.
 */
public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger( DriverFactory.class );

    protected static WebDriver driver;

    public DriverFactory() {
        initialize();
    }

    private void initialize() {
        if(driver==null)
            createNewDriverInstance();
    }

    private void createNewDriverInstance() {

        String browser = new PropertyReader().readProperty("browser");
        if(browser.equals("firefox")){
            driver = new FirefoxDriver();
        }else{
            try {
                throw new Exception("Brower property is not correct : " + browser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public WebDriver getDriver(){
        return driver;
    }

    public void destroyDriver(){
        driver.quit();
        driver=null;
    }

}
