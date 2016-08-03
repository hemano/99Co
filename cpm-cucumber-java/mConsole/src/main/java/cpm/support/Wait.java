package cpm.support;

import com.google.common.base.Function;

/**
 * Created by hemantojha on 24/07/16.
 */
public interface Wait<F>
{
    /**
     * Implementations should wait until the condition evaluates to a value that is neither null nor
     * false. Because of this contract, the return type must not be Void.
     *
     * <p>
     * If the condition does not become true within a certain time (as defined by the implementing
     * class), this method will throw a non-specified {@link Throwable}. This is so that an
     * implementor may throw whatever is idiomatic for a given test infrastructure (e.g. JUnit4 would
     * throw {@link AssertionError}.
     *
     * @param <T> the return type of the method, which must not be Void
     *
     * @param isTrue the parameter to pass to the
     *
     * @return value from the isTrue condition
     */
    <T> T until( Function<? super F, T> isTrue );
}