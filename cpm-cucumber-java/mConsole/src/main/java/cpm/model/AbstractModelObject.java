package cpm.model;

import com.google.common.base.Preconditions;
import cpm.events.EventFiringSynchronization;
import cpm.interfaces.Synchronization;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hamcrest.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.internal.Attributes;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Created by hemantojha on 24/07/16.
 */
public abstract class AbstractModelObject
{

    //region AbstractPageObject - Variables Declaration and Initialization Section.

    private static final Logger logger = LoggerFactory.getLogger( AbstractModelObject.class );

    // ==========================  END OF STATIC MEMBERS ========================================================================

    /** the session web driver */
    WebDriver driver;


    protected Synchronization sync;


    // ==========================  HELPER MEMBERS ========================================================================

    //endregion


    //region AbstractPageObject - Constructor Methods Section
    protected AbstractModelObject( final WebDriver driver, final String logicalName )
    {

        this.driver = getWrappedDriver();
        this.sync = new EventFiringSynchronization(getWrappedDriver());

    }

    //endregion


    //region AbstractPageObject - Public Methods Section

    public final WebDriver getWrappedDriver()
    {
        return driver;
    }

    public URL getURL()
    {
        try
        {
            return new URL( getCurrentUrl() );
        }
        catch ( MalformedURLException e )
        {
            throw new IllegalArgumentException( e );
        }
    }
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }

    //endregion


    //region AbstractPageObject - Protected Methods Section

    /**
     * Assertion and validates static fields.
     */
    protected abstract void validateInitialState();

    /**
     * initializes the page by initializing static objects
     * and allocate often used members, not used as lazy init
     */
    protected abstract void initElements();


    //endregion
}
