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

/**
 * Analogue to SLF4J log levels, with helper to translate and log at the correct
 * level
 * 
 * @author technosf
 * @since 0.0.1
 * @version 0.0.1
 */
public enum LogLevel {
    TRACE(00),
    DEBUG(10),
    INFO(20),
    WARN(30),
    ERROR(40);

    /**
     * slf4j log level
     */
    public final int logLevel;


    /**
     * The log enum plus equivalent SLF4J log level
     * 
     * @param slf4jLogLevel
     *            slf4j log level
     */
    LogLevel(int slf4jLogLevel)
    {
        logLevel = slf4jLogLevel;
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param msg
     */
    public final void log(Logger log, String msg)
    {
        switch (this)
        {
            case TRACE:
                log.trace(msg);
                break;
            case DEBUG:
                log.debug(msg);
                break;
            case INFO:
                log.info(msg);
                break;
            case WARN:
                log.warn(msg);
                break;
            case ERROR:
                log.error(msg);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param format
     * @param arg
     */
    public final void log(Logger log, String format, Object arg)
    {
        switch (this)
        {
            case TRACE:
                log.trace(format, arg);
                break;
            case DEBUG:
                log.debug(format, arg);
                break;
            case INFO:
                log.info(format, arg);
                break;
            case WARN:
                log.warn(format, arg);
                break;
            case ERROR:
                log.error(format, arg);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param format
     * @param arg1
     * @param arg2
     */
    public final void log(Logger log, String format, Object arg1,
            Object arg2)
    {
        switch (this)
        {
            case TRACE:
                log.trace(format, arg1, arg2);
                break;
            case DEBUG:
                log.debug(format, arg1, arg2);
                break;
            case INFO:
                log.info(format, arg1, arg2);
                break;
            case WARN:
                log.warn(format, arg1, arg2);
                break;
            case ERROR:
                log.error(format, arg1, arg2);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param format
     * @param arguments
     */
    public final void log(Logger log, String format,
            Object... arguments)
    {
        switch (this)
        {
            case TRACE:
                log.trace(format, arguments);
                break;
            case DEBUG:
                log.debug(format, arguments);
                break;
            case INFO:
                log.info(format, arguments);
                break;
            case WARN:
                log.warn(format, arguments);
                break;
            case ERROR:
                log.error(format, arguments);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param msg
     * @param t
     */
    public final void log(Logger log, String msg, Throwable t)
    {
        switch (this)
        {
            case TRACE:
                log.trace(msg, t);
                break;
            case DEBUG:
                log.debug(msg, t);
                break;
            case INFO:
                log.info(msg, t);
                break;
            case WARN:
                log.warn(msg, t);
                break;
            case ERROR:
                log.error(msg, t);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param marker
     * @param msg
     */
    public final void log(Logger log, Marker marker, String msg)
    {
        switch (this)
        {
            case TRACE:
                log.trace(marker, msg);
                break;
            case DEBUG:
                log.debug(marker, msg);
                break;
            case INFO:
                log.info(marker, msg);
                break;
            case WARN:
                log.warn(marker, msg);
                break;
            case ERROR:
                log.error(marker, msg);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param marker
     * @param format
     * @param arg
     */
    public final void log(Logger log, Marker marker, String format, Object arg)
    {
        switch (this)
        {
            case TRACE:
                log.trace(marker, format, arg);
                break;
            case DEBUG:
                log.debug(marker, format, arg);
                break;
            case INFO:
                log.info(marker, format, arg);
                break;
            case WARN:
                log.warn(marker, format, arg);
                break;
            case ERROR:
                log.error(marker, format, arg);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     */
    public final void log(Logger log, Marker marker, String format, Object arg1,
            Object arg2)
    {
        switch (this)
        {
            case TRACE:
                log.trace(marker, format, arg1, arg2);
                break;
            case DEBUG:
                log.debug(marker, format, arg1, arg2);
                break;
            case INFO:
                log.info(marker, format, arg1, arg2);
                break;
            case WARN:
                log.warn(marker, format, arg1, arg2);
                break;
            case ERROR:
                log.error(marker, format, arg1, arg2);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param marker
     * @param format
     * @param arguments
     */
    public final void log(Logger log, Marker marker, String format,
            Object... arguments)
    {
        switch (this)
        {
            case TRACE:
                log.trace(marker, format, arguments);
                break;
            case DEBUG:
                log.debug(marker, format, arguments);
                break;
            case INFO:
                log.info(marker, format, arguments);
                break;
            case WARN:
                log.warn(marker, format, arguments);
                break;
            case ERROR:
                log.error(marker, format, arguments);
                break;
            default:
                break;
        }
    }


    /**
     * Log with the logger
     * 
     * @param log
     * @param marker
     * @param msg
     * @param t
     */
    public final void log(Logger log, Marker marker, String msg, Throwable t)
    {
        switch (this)
        {
            case TRACE:
                log.trace(marker, msg, t);
                break;
            case DEBUG:
                log.debug(marker, msg, t);
                break;
            case INFO:
                log.info(marker, msg, t);
                break;
            case WARN:
                log.warn(marker, msg, t);
                break;
            case ERROR:
                log.error(marker, msg, t);
                break;
            default:
                break;
        }
    }

}
