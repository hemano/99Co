package utils.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import utils.WebDriverThread;
import utils.listeners.WebDriverListeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hemantojha on 21/07/16.
 */
public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());

    private static ThreadLocal<WebDriverThread> driverThread;

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

    public static WebDriver getDriver() {
        try {
//            return driverThread.get().getDriver();
            EventFiringWebDriver event = new EventFiringWebDriver(driverThread.get().getDriver());
            WebDriverListeners eventListener = new WebDriverListeners();
            event.register(eventListener);
            return event;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeDriverObjects() throws Exception{
        for(WebDriverThread webDriverThread: webDriverThreadPool){
//            getDriver().manage().deleteAllCookies();
            webDriverThread.quitDriver();
        }
    }

    @Step("{0}")
    public void logToReport(String message) {
        logger.info(message); //or System.out.println(message);
        logger.debug(message); //or System.out.println(message);
    }

}
