package cpm.support;

import com.google.common.base.*;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Seconds;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.misc.Sleeper;
import java.util.Collection;
import java.util.List;

/**
 * Created by hemantojha on 24/07/16.
 */
public class FluentWait<T> implements Wait<T>
{

    //region FluentWait - Variables Declaration and Initialization Section.

    private static final Logger logger = LoggerFactory.getLogger( FluentWait.class );

    /**
     * Time represents a duration of time measured in milliseconds.
     * The duration is often obtained from an interval.
     */
    public static final Duration FIVE_HUNDRED_MILLIS = new Duration( 500 );

    // ==========================  END OF STATIC MEMBERS ========================================================================

    /**
     * The input value to pass to the evaluated conditions.
     */
    protected final T input;

    // ==========================  END OF FINAL MEMBERS ==========================================================================

    private Seconds timeout = Seconds.ONE;

    private Duration interval = FIVE_HUNDRED_MILLIS;

    private List<Class<? extends Throwable>> ignoredExceptions = Lists.newLinkedList();

    private Supplier<String> messageSupplier = new Supplier<String>()
    {
        @Override
        public String get()
        {
            return null;
        }
    };

    //endregion


    //region FluentWait - Constructor Methods Section

    /**
     * @param input The input value to pass to the evaluated conditions.
     */
    public FluentWait( T input )
    {
        this.input = Preconditions.checkNotNull( input );
    }

    //endregion


    //region FluentWait - Public Methods Section

    /**
     * Sets how long to wait for the evaluated condition to be true. The default timeout is {@link #FIVE_HUNDRED_MILLIS}.
     *
     * @param timeout The timeout duration.
     *
     * @return A self reference.
     */
    public FluentWait<T> withTimeout( Seconds timeout )
    {
        this.timeout = timeout;
        return this;
    }

    /**
     * Sets the message to be displayed when time expires.
     *
     * @param message to be appended to default.
     *
     * @return A self reference.
     */
    public FluentWait<T> withMessage( final String message )
    {
        this.messageSupplier = new Supplier<String>()
        {
            @Override
            public String get()
            {
                return message;
            }
        };
        return this;
    }

    /**
     * Sets the message to be evaluated and displayed when time expires.
     *
     * @param messageSupplier to be evaluated on failure and appended to default.
     *
     * @return A self reference.
     */
    public FluentWait<T> withMessage( Supplier<String> messageSupplier )
    {
        this.messageSupplier = messageSupplier;
        return this;
    }

    /**
     * Sets how often the condition should be evaluated.
     * <p>
     * In reality, the interval may be greater as the cost of actually evaluating a condition function
     * is not factored in. The default polling interval is {@link #FIVE_HUNDRED_MILLIS}.
     *
     * @param duration The timeout duration.
     *
     * @return A self reference.
     */
    public FluentWait<T> pollingEvery( Duration duration )
    {
        this.interval = duration;
        return this;
    }

    /**
     * Configures this instance to ignore specific types of exceptions while waiting for a condition.
     * Any exceptions not white-listed will be allowed to propagate, terminating the wait.
     *
     * @param types The types of exceptions to ignore.
     * @param <K> an Exception that extends Throwable
     *
     * @return A self reference.
     */
    public <K extends Throwable> FluentWait<T> ignoreAll( Collection<Class<? extends K>> types )
    {
        ignoredExceptions.addAll( types );
        return this;
    }

    /**
     * @param exceptionType exception to ignore
     *
     * @return a self reference
     *
     * @see #ignoreAll(Collection)
     */
    public FluentWait<T> ignoring( Class<? extends Throwable> exceptionType )
    {
        return this.ignoreAll( ImmutableList.<Class<? extends Throwable>>of( exceptionType ) );
    }

    /**
     * @param firstType exception to ignore
     * @param secondType another exception to ignore
     *
     * @return a self reference
     *
     * @see #ignoreAll(Collection)
     */
    public FluentWait<T> ignoring( Class<? extends Throwable> firstType, Class<? extends Throwable> secondType )
    {
        return this.ignoreAll( ImmutableList.of( firstType, secondType ) );
    }

    /**
     * Repeatedly applies this instance's input value to the given predicate until the timeout expires
     * or the predicate evaluates to true.
     *
     * @param isTrue The predicate to wait on.
     *
     * @throws TimeoutException If the timeout expires.
     */
    public FluentWait<WebElement> until(final Predicate<T> isTrue )
    {
        until( new Function<T, Boolean>()
        {
            public Boolean apply( T input )
            {
                return isTrue.apply( input );
            }

            public String toString()
            {
                return isTrue.toString();
            }
        } );
        return null;
    }

    /**
     * Repeatedly applies this instance's input value to the given function until one of the following occurs:
     * <ol>
     *     <li>the function returns neither null nor false</li>
     *     <li>the function throws an un-ignored exception</li>
     *     <li>the timeout expires<li>
     *     <li>the current thread is interrupted</li>
     * </ol>
     *
     * @param isTrue the parameter to pass to the
     * @param <V> The function's expected return type.
     *
     * @return The functions' return value if the function returned something different
     *         from null or false before the timeout expired.
     *
     * @throws TimeoutException If the timeout expires.
     */
    public <V> V until( Function<? super T, V> isTrue )
    {
        long end = DateTimeUtils.currentTimeMillis() + timeout.toStandardDuration().getMillis();
        Throwable lastException = null;
        while ( true )
        {
            try
            {
                V value = isTrue.apply( input );
                if ( value != null && Boolean.class.equals( value.getClass() ) )
                {
                    if ( Boolean.TRUE.equals( value ) ) return value;
                }
                else if ( value != null )
                {
                    return value;
                }
            }
            catch ( Throwable e )
            {
                lastException = propagateIfNotIgnored( e );
            }

            // Check the timeout after evaluating the function to ensure conditions with a zero timeout can succeed.
            if ( ! ( System.currentTimeMillis() < end ) )
            {
                String message = messageSupplier != null ? messageSupplier.get() : null;
                String toAppend = message == null ? " waiting for " + isTrue.toString() : ": " + message;
                String timeoutMessage = String.format( "Timed out after %d seconds%s", timeout.getSeconds(), toAppend );

                throw timeoutException( timeoutMessage, lastException );
            }

            Sleeper.pauseFor( interval );
        }
    }

    //endregion


    //region FluentWait - Protected Methods Section

    /**
     * Throws a timeout exception. This method may be overridden to throw an exception that is
     * idiomatic for a particular test infrastructure, such as an AssertionError in JUnit4.
     *
     * @param message The timeout message.
     * @param lastException The last exception to be thrown and subsequently suppressed while waiting on a function.
     *
     * @return Nothing will ever be returned; this return type is only specified as a convenience.
     */
    protected RuntimeException timeoutException( String message, Throwable lastException )
    {
        throw new TimeoutException( message, lastException );
    }

    //endregion


    //region FluentWait - Private Function Section

    private Throwable propagateIfNotIgnored( Throwable e )
    {
        for ( Class<? extends Throwable> ignoredException : ignoredExceptions )
        {
            if ( ignoredException.isInstance( e ) )
            {
                return e;
            }
        }
        throw Throwables.propagate( e );
    }

    //endregion
}
