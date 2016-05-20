/*
 * Copyright 2001-2004 The Apache Software Foundation.
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

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * Abstract implementation of Interceptor calls
 * <p>
 * Implements filtering at the class level
 * 
 * @author technosf
 * @since 0.0.1
 * @version 0.0.1
 */
public abstract class AbstractInterceptor
        implements Interceptor
{

    /**
     * Filter regex with default match-all
     */
    private static String FILTER_REGEX = REGEX_MATCH_ALL;

    /**
     * Intercept mode, defaulting to PASSTHROUGH
     */
    private Mode mode = Mode.PASSTHROUGH;


    /**
     * Sets the regex filter at the class level
     * 
     * @param filterRegex
     *            the filter
     */
    public static final void setInterceptorFilter(String filterRegex)
    {
        if (filterRegex == null || filterRegex.isEmpty())
        {
            FILTER_REGEX = REGEX_MATCH_ALL;
        }
        else
        {
            FILTER_REGEX = filterRegex;
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#getMode()
     */
    @Override
    public final Mode getMode()
    {
        return mode;
    }


    /**
     * Sets the Interceptor mode
     * 
     * @param mode
     *            the mode
     */
    public final void setMode(Mode mode)
    {
        this.mode = mode;
    }


    /* ---------------------------------------------------------------- */

    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#getFilter()
     */
    @Override
    public final String getFilter()
    {
        return FILTER_REGEX;
    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#setFilter(java.lang.String)
     */
    @Override
    public final void setFilter(String filterRegex)
    {
        setInterceptorFilter(filterRegex);
    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#filter(java.lang.String)
     */
    @Override
    public final boolean filter(String msg)
    {
        return msg.matches(FILTER_REGEX);
    }


    /**
     * Test the log message with the filter regex
     * 
     * @param msg
     *            the message to test
     * @return true if log should be filtered
     */
    private final boolean filter(LogLevel level, String msg)
    {
        return filter(msg);
    }


    /**
     * Test the log message with the filter regex
     * 
     * @param tuple
     *            the message to test
     * @return true if log should be filtered
     */
    private final boolean filter(LogLevel level, FormattingTuple tuple)
    {
        return filter(tuple.getMessage());
    }


    /* ---------------------------------------------------------------- */

    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#intercept(com.github.technosf.slf4.interceptor.LogLevel,
     *      org.slf4j.Logger, java.lang.String)
     */
    @Override
    public final void intercept(LogLevel logLevel, Logger log, String msg)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor && formatAndLog(logLevel, msg)))
        {
            logLevel.log(log, msg);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#intercept(com.github.technosf.slf4.interceptor.LogLevel,
     *      org.slf4j.Logger, java.lang.String, java.lang.Object)
     */
    @Override
    public final void intercept(LogLevel logLevel, Logger log, String format,
            Object arg)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, format, arg)))
        {
            logLevel.log(log, format, arg);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#intercept(com.github.technosf.slf4.interceptor.LogLevel,
     *      org.slf4j.Logger, java.lang.String, java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public final void intercept(LogLevel logLevel, Logger log, String format,
            Object arg1, Object arg2)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, format, arg1, arg2)))
        {
            logLevel.log(log, format, arg1, arg2);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#intercept(com.github.technosf.slf4.interceptor.LogLevel,
     *      org.slf4j.Logger, java.lang.String, java.lang.Object[])
     */
    @Override
    public final void intercept(LogLevel logLevel, Logger log, String format,
            Object... arguments)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, format, arguments)))
        {
            logLevel.log(log, format, arguments);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see com.github.technosf.slf4.interceptor.Interceptor#intercept(com.github.technosf.slf4.interceptor.LogLevel,
     *      org.slf4j.Logger, java.lang.String, java.lang.Throwable)
     */
    @Override
    public final void intercept(LogLevel logLevel, Logger log, String msg,
            Throwable t)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor && formatAndLog(logLevel, msg, t)))
        {
            logLevel.log(log, msg, t);
        }
    }


    @Override
    public final void intercept(LogLevel logLevel, Logger log, Marker marker,
            String msg)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, msg)))
        {
            logLevel.log(log, marker, msg);
        }
    }


    @Override
    public final void intercept(LogLevel logLevel, Logger log, Marker marker,
            String format, Object arg)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, format, arg)))
        {
            logLevel.log(log, marker, format, arg);
        }
    }


    @Override
    public final void intercept(LogLevel logLevel, Logger log, Marker marker,
            String format, Object arg1, Object arg2)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, format, arg1, arg2)))
        {
            logLevel.log(log, marker, format, arg1, arg2);
        }
    }


    @Override
    public final void intercept(LogLevel logLevel, Logger log, Marker marker,
            String format, Object... arguments)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, format, arguments)))
        {
            logLevel.log(log, marker, format, arguments);
        }
    }


    @Override
    public final void intercept(LogLevel logLevel, Logger log, Marker marker,
            String msg, Throwable t)
    {
        if (mode.logToLogger
                || (mode.logToInterceptor
                        && formatAndLog(logLevel, msg, t)))
        {
            logLevel.log(log, marker, msg, t);
        }
    }


    /* ----------------------------------------------------------------
     * 
     * SLF4J Simple Logger Helpers
     * 
     * ----------------------------------------------------------------
     */

    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arg1
     * @param arg2
     */
    private boolean formatAndLog(LogLevel level, String msg)
    {
        return !filter(level, msg);
    }


    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arg1
     * @param arg2
     */
    private boolean formatAndLog(LogLevel level, String format, Object arg)
    {
        FormattingTuple tp = MessageFormatter.format(format, arg);
        return !filter(level, tp);
    }


    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arg1
     * @param arg2
     */
    private boolean formatAndLog(LogLevel level, String format, Object arg1,
            Object arg2)
    {
        FormattingTuple tp = MessageFormatter.format(format, arg1, arg2);
        return !filter(level, tp);
    }


    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arguments
     *            a list of 3 ore more arguments
     */
    private boolean formatAndLog(LogLevel level, String format,
            Object... arguments)
    {
        FormattingTuple tp = MessageFormatter.arrayFormat(format, arguments);
        return !filter(level, tp);
    }

    /* ---------------------------------------------------------------- */

}
