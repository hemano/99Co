package utils.listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hemantojha on 03/08/16.
 */
public class WebDriverListeners extends AbstractWebDriverEventListener {
    private By lastFindBy;
    private WebElement lastElement;
    private String originalValue;

    private static final Logger logger = LoggerFactory.getLogger(WebDriverListeners.class);

    /*
     *  URL NAVIGATION | NAVIGATE() & GET()
     */
    // Prints the URL before Navigating to specific URL "get("http://www.google.com");"
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Before Navigating To : " + url + ", my url was: "
                + driver.getCurrentUrl());
//        System.out.println("Before Navigating To : " + url + ", my url was: "
//                + driver.getCurrentUrl());
    }

    // Prints the current URL after Navigating to specific URL "get("http://www.google.com");"
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
//        System.out.println("After Navigating To: " + url + ", my url is: "
//                + driver.getCurrentUrl());
        logger.info("After Navigating To: " + url + ", my url is: "
                + driver.getCurrentUrl());
    }

    // Prints the URL before Navigating back "navigate().back()"
    @Override
    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Before Navigating Back. I was at "
                + driver.getCurrentUrl());
    }

    // Prints the current URL after Navigating back "navigate().back()"
    @Override
    public void afterNavigateBack(WebDriver driver) {
        System.out.println("After Navigating Back. I'm at "
                + driver.getCurrentUrl());
    }

    // Prints the URL before Navigating forward "navigate().forward()"
    @Override
    public void beforeNavigateForward(WebDriver driver) {
        System.out.println("Before Navigating Forward. I was at "
                + driver.getCurrentUrl());
    }

    // Prints the current URL after Navigating forward "navigate().forward()"
    @Override
    public void afterNavigateForward(WebDriver driver) {
        System.out.println("After Navigating Forward. I'm at "
                + driver.getCurrentUrl());
    }


    /*
     * ON EXCEPTION | SCREENSHOT, THROWING ERROR
     */
    // Takes screenshot on any Exception thrown during test execution
    @Override
    public void onException(Throwable throwable, WebDriver webdriver) {
        System.out.println("Caught Exception");
        // TODO: 04/08/16 We want to add the screenshot when exception is thrown
        CustomAllureListener customAllureListener = new CustomAllureListener();
        customAllureListener.attachFailed();

        File scrFile = ((TakesScreenshot) webdriver)
                .getScreenshotAs(OutputType.FILE);
        try {
            String screenshotDir = System.getProperty("screenShotDirectory");

            DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            Date date = new Date();
            String timeStamp = dateFormat.format(date).toString();

            org.apache.commons.io.FileUtils.copyFile(scrFile, new File(
                    screenshotDir + "/" + timeStamp + ".jpg"));
        } catch (Exception e) {
            System.out.println("Unable to Save");
            e.printStackTrace();

        }
    }


    /*
     * FINDING ELEMENTS | FINDELEMENT() & FINDELEMENTS()
     */
    // Called before finding Element(s)
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
//        System.out.println("Trying to find: '" + lastFindBy + "'.");
        logger.info("Trying to find: '" + lastFindBy + "'.");
//        System.out.println("Trying to find: " + by.toString()); // This is optional and an alternate way
        logger.info("Trying to find: " + by.toString());
    }

    // Called after finding Element(s)
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
//        System.out.println("Found: '" + lastFindBy + "'.");
//        System.out.println("Found: " + by.toString() + "'."); // This is optional and an alternate way
        logger.info("Found: '" + lastFindBy + "'.");
        logger.info("Found: " + by.toString() + "'.");
    }


    /*
     * CLICK | CLICK()
     */
    // Called before clicking an Element
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Trying to click: '" + element + "'");
        logger.info("Trying to click: '" + element + "'");
        // Highlight Elements before clicking
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: black; border: 3px solid black;");
        }
    }

    // Called after clicking an Element
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Clicked Element with: '" + element + "'");
        logger.info("Clicked Element with: '" + element + "'");
    }


    /*
     * CHANGING VALUES | CLEAR() & SENDKEYS()
     */
    // Before Changing values
    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        lastElement = element;
        originalValue = element.getText();

        // What if the element is not visible anymore?
        if (originalValue.isEmpty()) {
            originalValue = element.getAttribute("value");
        }
    }

    // After Changing values
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        lastElement = element;
        String changedValue = "";
        try {
            changedValue = element.getText();
        } catch (StaleElementReferenceException e) {
            System.out
                    .println("Could not log change of element, because of a stale"
                            + " element reference exception.");
            return;
        }
        // What if the element is not visible anymore?
        if (changedValue.isEmpty()) {
            changedValue = element.getAttribute("value");
        }

        System.out.println("Changing value in element found " + lastElement
                + " from '" + originalValue + "' to '" + changedValue + "'");
    }


    /*
     * SCRIPT - this section will be modified ASAP
     */
    // Called before RemoteWebDriver.executeScript(java.lang.String, java.lang.Object[])
    @Override
    public void beforeScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub
    }

    // Called before RemoteWebDriver.executeScript(java.lang.String, java.lang.Object[])
    @Override
    public void afterScript(String script, WebDriver driver) {
        // TODO Auto-generated method stub
    }

}