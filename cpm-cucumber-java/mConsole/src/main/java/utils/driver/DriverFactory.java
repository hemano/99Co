package utils.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import utils.WebDriverThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hemantojha on 21/07/16.
 */
public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    //    protected static WebDriver driver;
    private static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());

    private static ThreadLocal<WebDriverThread> driverThread;


//    private static WebDriverThread driverThread;
//    public driver.DriverFactory() {
//        initialize();
//    }

//    @Before
    public static void instantiateDriverObject() {

        driverThread = new ThreadLocal<WebDriverThread>() {

            @Override
            public WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

//    private void initialize() {
//        if (driverThread == null)
//            createNewDriverInstance();
//    }

//    private void createNewDriverInstance() {
//
//        String browser = new PropertyReader().readProperty("browser");
//        if(browser.equals("firefox")){
//            driver = new FirefoxDriver();
//        }else{
//            try {
//                throw new Exception("Brower property is not correct : " + browser);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        WebDriverThread webDriverThread = new WebDriverThread();
//
//    }

//    public static WebDriver getDriver() throws Exception {
//        return driverThread.getDriver();
////        return driver;
//    }
//
//    public void destroyDriver(){
//        driverThread.quitDriver();
//    }

    public static WebDriver getDriver() {
        try {
            return driverThread.get().getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    @AfterMethod
//    public static void quitDriver() throws Exception{
//        getDriver().manage().deleteAllCookies();
//    }

//    @After
    public static void closeDriverObjects() throws Exception{
        for(WebDriverThread webDriverThread: webDriverThreadPool){
            getDriver().manage().deleteAllCookies();
            webDriverThread.quitDriver();
        }
    }

//    @AfterMethod
//    public static void quitDriver() throws Exception {
//        getDriver().manage().deleteAllCookies();
//    }
//
//    @AfterSuite
//    public static void closeDriverObjects() {
//        for (WebDriverThread webDriverThread : webDriverThreadPool) {
//            webDriverThread.quitDriver();
//        }
//    }

//    @Attachment()
//    protected String addLog(String logs) {
//        return logs;
//    }

    @Step("{0}")
    public void logToReport(String message) {
        logger.info(message); //or System.out.println(message);
        logger.debug(message); //or System.out.println(message);
    }


}
