package org.apache.commons.logging.impl;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link Log org.apache.commons.logging.Log} interface which
 * delegates all processing to a wrapped {@link Logger org.slf4j.Logger}
 * instance.
 * <p>
 * JCL's FATAL level is mapped to ERROR. All other levels map one to one.
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public class SLF4JLog implements Log, Serializable
{

    private static final long serialVersionUID = 680728617011167209L;

    // used to store this logger's name to recreate it after serialization
    protected String name;

    // in both Log4jLogger and Jdk14Logger classes in the original JCL, the
    // logger instance is transient
    private transient Logger logger;


    SLF4JLog(Logger logger)
    {
        this.logger = logger;
        this.name = logger.getName();
    }


    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    @Override
    public boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }


    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    @Override
    public boolean isErrorEnabled()
    {
        return logger.isErrorEnabled();
    }


    /**
     * Delegates to the <code>isErrorEnabled<code> method of the wrapped 
     * <code>org.slf4j.Logger</code> instance.
     */
    @Override
    public boolean isFatalEnabled()
    {
        return logger.isErrorEnabled();
    }


    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    @Override
    public boolean isInfoEnabled()
    {
        return logger.isInfoEnabled();
    }


    /**
     * Delegates to the <code>isDebugEnabled<code> method of the wrapped 
     * <code>org.slf4j.Logger</code> instance.
     */
    @Override
    public boolean isTraceEnabled()
    {
        return logger.isTraceEnabled();
    }


    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    @Override
    public boolean isWarnEnabled()
    {
        return logger.isWarnEnabled();
    }


    /**
     * Converts the input parameter to String and then delegates to the debug
     * method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     */
    @Override
    public void trace(Object message)
    {
        logger.trace(String.valueOf(message));
    }


    /**
     * Converts the first input parameter to String and then delegates to the
     * debug method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     * @param t
     *            the exception to log
     */
    @Override
    public void trace(Object message, Throwable t)
    {
        logger.trace(String.valueOf(message), t);
    }


    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     */
    @Override
    public void debug(Object message)
    {
        logger.debug(String.valueOf(message));
    }


    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     * @param t
     *            the exception to log
     */
    @Override
    public void debug(Object message, Throwable t)
    {
        logger.debug(String.valueOf(message), t);
    }


    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     */
    @Override
    public void info(Object message)
    {
        logger.info(String.valueOf(message));
    }


    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     * @param t
     *            the exception to log
     */
    @Override
    public void info(Object message, Throwable t)
    {
        logger.info(String.valueOf(message), t);
    }


    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     */
    @Override
    public void warn(Object message)
    {
        logger.warn(String.valueOf(message));
    }


    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     * @param t
     *            the exception to log
     */
    @Override
    public void warn(Object message, Throwable t)
    {
        logger.warn(String.valueOf(message), t);
    }


    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     */
    @Override
    public void error(Object message)
    {
        logger.error(String.valueOf(message));
    }


    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     * @param t
     *            the exception to log
     */
    @Override
    public void error(Object message, Throwable t)
    {
        logger.error(String.valueOf(message), t);
    }


    /**
     * Converts the input parameter to String and then delegates to the error
     * method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     */
    @Override
    public void fatal(Object message)
    {
        logger.error(String.valueOf(message));
    }


    /**
     * Converts the first input parameter to String and then delegates to the
     * error method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *            the message to log. Converted to {@link String}
     * @param t
     *            the exception to log
     */
    @Override
    public void fatal(Object message, Throwable t)
    {
        logger.error(String.valueOf(message), t);
    }


    /**
     * Replace this instance with a homonymous (same name) logger returned by
     * LoggerFactory. Note that this method is only called during
     * deserialization.
     * 
     * @return logger with same name as returned by LoggerFactory
     * @throws ObjectStreamException
     */
    protected Object readResolve() throws ObjectStreamException
    {
        Logger logger = LoggerFactory.getLogger(this.name);
        return new SLF4JLog(logger);
    }
}
