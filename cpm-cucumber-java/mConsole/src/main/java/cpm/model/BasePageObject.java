package cpm.model;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import cpm.events.EventFiringSynchronization;
import cpm.interfaces.Synchronization;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hamcrest.Matcher;
import org.joda.time.Seconds;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.DescriptionType;
import utils.driver.DriverFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Created by hemantojha on 24/07/16.
 */
public class BasePageObject
{

    //region AbstractPageObject - Variables Declaration and Initialization Section.

    private static final Logger logger = LoggerFactory.getLogger( BasePageObject.class );

    // ==========================  END OF STATIC MEMBERS ========================================================================

    //endregion

    protected Synchronization sync;

    //region AbstractPageObject - Constructor Methods Section
    protected BasePageObject() {
        this.sync = new EventFiringSynchronization(getWrappedDriver());

    }

    //endregion

    public final WebDriver getWrappedDriver() {
        return DriverFactory.getDriver();
    }

    //region AbstractPageObject - Public Methods Section

    public final Set<Cookie> getCookies()
    {
        return getWrappedDriver().manage().getCookies();
    }


//    public final void scrollTop( final ScrollSpeedMode mode )
//    {
//        WebDriverUtils.scrollTop( getWrappedDriver(), mode );
//    }
//
//    @Override
//    public final void scrollByLines( final int lines )
//    {
//        WebDriverUtils.scrollByLines( getWrappedDriver(), lines );
//    }
//
//    @Override
//    public final void scrollByPages( final int pages )
//    {
//        WebDriverUtils.scrollByPages( getWrappedDriver(), pages );
//    }
//
//    public final String getTitle()
//    {
//        return getWrappedDriver().getTitle();
//    }
//
//    public final String getWindowHandle()
//    {
//        return windowHandle;
//    }
//
//    public final void switchToThisWindow()
//    {
//        getWrappedDriver().switchTo().window( windowHandle );
//    }

    public Set<String> getWindowHandles()
    {
        return getWrappedDriver().getWindowHandles();
    }

    //endregion


    //region AbstractPageObject - Protected Methods Section

    @Attachment()
    protected String addLog(String logs) {
        return logs;
    }

    @Step("{0}")
    public void logToReport(String message) {
        logger.info(message); //or System.out.println(message);
        logger.debug(message); //or System.out.println(message);
    }

    //endregion

}