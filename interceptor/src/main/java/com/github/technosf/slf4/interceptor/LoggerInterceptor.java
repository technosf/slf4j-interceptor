/*
 * Copyright 2016 technosf [https://github.com/technosf]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.technosf.slf4.interceptor;

import static com.github.technosf.slf4.interceptor.LogLevel.DEBUG;
import static com.github.technosf.slf4.interceptor.LogLevel.ERROR;
import static com.github.technosf.slf4.interceptor.LogLevel.INFO;
import static com.github.technosf.slf4.interceptor.LogLevel.TRACE;
import static com.github.technosf.slf4.interceptor.LogLevel.WARN;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.Marker;

import com.github.technosf.slf4.interceptor.base.AbstractInterceptor;

/**
 * An {@code Interceptor} that implements {@code Logger}, wrapping an actual
 * logger and intercepting the flow to it.
 * <p>
 * This is the class used by drop-in logging implementation replacements.
 * 
 * @author technosf
 * @since 0.0.1
 * @version 0.0.1
 */
public final class LoggerInterceptor
        extends AbstractInterceptor
        implements Logger, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 20160517122135L;

    /**
     * Underlying logger
     */
    private transient Logger slf4jLogger;


    /**
     * Creates the interceptor
     * 
     * @param slf4jLogger
     *            the logger to wrap
     */
    public LoggerInterceptor(Logger slf4jLogger)
    {
        this.slf4jLogger = slf4jLogger;
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#getName()
     */
    @Override
    public String getName()
    {
        return slf4jLogger.getName();
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled()
    {
        return slf4jLogger.isTraceEnabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(String msg)
    {
        intercept(TRACE, slf4jLogger, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
     */
    @Override
    public void trace(String format, Object arg)
    {
        intercept(TRACE, slf4jLogger, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void trace(String format, Object arg1, Object arg2)
    {
        intercept(TRACE, slf4jLogger, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
     */
    @Override
    public void trace(String format, Object... arguments)
    {
        intercept(TRACE, slf4jLogger, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void trace(String msg, Throwable t)
    {
        intercept(TRACE, slf4jLogger, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isTraceEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isTraceEnabled(Marker marker)
    {
        return slf4jLogger.isTraceEnabled(marker);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void trace(Marker marker, String msg)
    {
        intercept(TRACE, slf4jLogger, marker, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void trace(Marker marker, String format, Object arg)
    {
        intercept(TRACE, slf4jLogger, marker, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2)
    {
        intercept(TRACE, slf4jLogger, marker, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void trace(Marker marker, String format, Object... argArray)
    {
        intercept(TRACE, slf4jLogger, marker, format, argArray);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#trace(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void trace(Marker marker, String msg, Throwable t)
    {
        intercept(TRACE, slf4jLogger, marker, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled()
    {
        return slf4jLogger.isDebugEnabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg)
    {
        intercept(DEBUG, slf4jLogger, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
     */
    @Override
    public void debug(String format, Object arg)
    {
        intercept(DEBUG, slf4jLogger, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void debug(String format, Object arg1, Object arg2)
    {
        intercept(DEBUG, slf4jLogger, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
     */
    @Override
    public void debug(String format, Object... arguments)
    {
        intercept(DEBUG, slf4jLogger, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void debug(String msg, Throwable t)
    {
        intercept(DEBUG, slf4jLogger, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isDebugEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isDebugEnabled(Marker marker)
    {
        return slf4jLogger.isDebugEnabled(marker);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void debug(Marker marker, String msg)
    {
        intercept(DEBUG, slf4jLogger, marker, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void debug(Marker marker, String format, Object arg)
    {
        intercept(DEBUG, slf4jLogger, marker, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2)
    {
        intercept(DEBUG, slf4jLogger, marker, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void debug(Marker marker, String format, Object... arguments)
    {
        intercept(DEBUG, slf4jLogger, marker, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#debug(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void debug(Marker marker, String msg, Throwable t)
    {
        intercept(DEBUG, slf4jLogger, marker, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled()
    {
        return slf4jLogger.isInfoEnabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(java.lang.String)
     */
    @Override
    public void info(String msg)
    {
        intercept(INFO, slf4jLogger, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
     */
    @Override
    public void info(String format, Object arg)
    {
        intercept(INFO, slf4jLogger, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void info(String format, Object arg1, Object arg2)
    {
        intercept(INFO, slf4jLogger, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
     */
    @Override
    public void info(String format, Object... arguments)
    {
        intercept(INFO, slf4jLogger, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void info(String msg, Throwable t)
    {
        intercept(INFO, slf4jLogger, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isInfoEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isInfoEnabled(Marker marker)
    {
        return slf4jLogger.isInfoEnabled(marker);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void info(Marker marker, String msg)
    {
        intercept(INFO, slf4jLogger, marker, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void info(Marker marker, String format, Object arg)
    {
        intercept(INFO, slf4jLogger, marker, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2)
    {
        intercept(INFO, slf4jLogger, marker, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void info(Marker marker, String format, Object... arguments)
    {
        intercept(INFO, slf4jLogger, marker, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#info(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void info(Marker marker, String msg, Throwable t)
    {
        intercept(INFO, slf4jLogger, marker, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled()
    {
        return slf4jLogger.isWarnEnabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg)
    {
        intercept(WARN, slf4jLogger, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
     */
    @Override
    public void warn(String format, Object arg)
    {
        intercept(WARN, slf4jLogger, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
     */
    @Override
    public void warn(String format, Object... arguments)
    {
        intercept(WARN, slf4jLogger, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void warn(String format, Object arg1, Object arg2)
    {
        intercept(WARN, slf4jLogger, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void warn(String msg, Throwable t)
    {
        intercept(WARN, slf4jLogger, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isWarnEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isWarnEnabled(Marker marker)
    {
        return slf4jLogger.isWarnEnabled(marker);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void warn(Marker marker, String msg)
    {
        intercept(WARN, slf4jLogger, marker, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void warn(Marker marker, String format, Object arg)
    {
        intercept(WARN, slf4jLogger, marker, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2)
    {
        intercept(WARN, slf4jLogger, marker, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void warn(Marker marker, String format, Object... arguments)
    {
        intercept(WARN, slf4jLogger, marker, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#warn(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void warn(Marker marker, String msg, Throwable t)
    {
        intercept(WARN, slf4jLogger, marker, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled()
    {
        return slf4jLogger.isErrorEnabled();
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(java.lang.String)
     */
    @Override
    public void error(String msg)
    {
        intercept(ERROR, slf4jLogger, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
     */
    @Override
    public void error(String format, Object arg)
    {
        intercept(ERROR, slf4jLogger, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void error(String format, Object arg1, Object arg2)
    {
        intercept(ERROR, slf4jLogger, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
     */
    @Override
    public void error(String format, Object... arguments)
    {
        intercept(ERROR, slf4jLogger, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void error(String msg, Throwable t)
    {
        intercept(ERROR, slf4jLogger, msg, t);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#isErrorEnabled(org.slf4j.Marker)
     */
    @Override
    public boolean isErrorEnabled(Marker marker)
    {
        return slf4jLogger.isErrorEnabled(marker);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String)
     */
    @Override
    public void error(Marker marker, String msg)
    {
        intercept(ERROR, slf4jLogger, marker, msg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void error(Marker marker, String format, Object arg)
    {
        intercept(ERROR, slf4jLogger, marker, format, arg);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2)
    {
        intercept(ERROR, slf4jLogger, marker, format, arg1, arg2);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void error(Marker marker, String format, Object... arguments)
    {
        intercept(ERROR, slf4jLogger, marker, format, arguments);
    }


    /**
     * {@inheritDoc}
     *
     * @see org.slf4j.Logger#error(org.slf4j.Marker, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void error(Marker marker, String msg, Throwable t)
    {
        intercept(ERROR, slf4jLogger, marker, msg, t);
    }

}
