package cpm.support;


import org.joda.time.Duration;
import org.joda.time.Seconds;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hemantojha on 24/07/16.
 */
public class WebDriverWait extends FluentWait<WebDriver>
{

    //region WebDriverWait - Variables Declaration and Initialization Section.

    private static final Logger logger = LoggerFactory.getLogger( WebDriverWait.class );

    public final static long DEFAULT_SLEEP_TIMEOUT = 500;

    //endregion


    //region WebDriverWait - Constructor Methods Section

    /**
     * @param driver The WebDriver instance to pass to the expected conditions
     * @param timeOutInSeconds The timeout in seconds when an expectation is
     * @param sleepTimeOut The timeout used whilst sleeping. Defaults to 500ms called.
     */
    public WebDriverWait(WebDriver driver, Seconds timeOutInSeconds, Duration sleepTimeOut )
    {
        super( driver );

        withTimeout( timeOutInSeconds );
        pollingEvery( sleepTimeOut );
        ignoring( NotFoundException.class );
    }

    public WebDriverWait( WebDriver driver )
    {
        super( driver );
        ignoring( NotFoundException.class );
    }


    //endregion


    //region WebDriverWait - Protected Methods Section

    @Override
    protected RuntimeException timeoutException( String message, Throwable lastException )
    {
        TimeoutException ex = new TimeoutException( message, lastException );
        ex.addInfo( WebDriverException.DRIVER_INFO, input.getClass().getName() );
        if ( input instanceof RemoteWebDriver)
        {
            RemoteWebDriver remote = ( RemoteWebDriver ) input;
            if ( remote.getSessionId() != null )
            {
                ex.addInfo( WebDriverException.SESSION_ID, remote.getSessionId().toString() );
            }
            if ( remote.getCapabilities() != null )
            {
                ex.addInfo( "Capabilities", remote.getCapabilities().toString() );
            }
        }
        throw ex;
    }

    //endregion

}
