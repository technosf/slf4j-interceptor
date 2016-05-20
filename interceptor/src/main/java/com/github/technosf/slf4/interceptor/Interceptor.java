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

import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * Definition of a logging Interceptor
 * <p>
 * The interceptor can be set to an operating {@code Mode}, and to filter log
 * messages when in the <em>FILTER</em> {@code Mode}. Implementing classes
 * provide the means to intercept the log calls
 * 
 * @author technosf
 * @since 0.0.1
 * @version 0.0.1
 */
public interface Interceptor
        extends Logger
{

    /**
     * Interceptor operational mode, defining who gets to log
     */
    enum Mode {

        ABSORB(false, false),
        FILTER(false, true),
        DUPLICATE(true, true),
        PASSTHROUGH(true, false);

        /**
         * log enablements
         */
        public final boolean logToLogger, logToInterceptor;


        /**
         * Define who gets to log for each mode
         * 
         * @param logToLogger
         * @param logToInterceptor
         */
        Mode(boolean logToLogger, boolean logToInterceptor)
        {
            this.logToLogger = logToLogger;
            this.logToInterceptor = logToInterceptor;
        }
    }


    /**
     * The interceptor mode
     * 
     * @return the mode
     */
    Mode getMode();

    /* ---------------------------------------------------------------- */

    /**
     * Match all regex
     */
    static String REGEX_MATCH_ALL = ".*";

    /**
     * Match no regex
     */
    static String REGEX_MATCH_NONE = "(?!)";


    /**
     * Returns the filter the Interceptor should apply to determine if the
     * message should go to the underlying logger in FILTER mode
     * 
     * @return the filter regex
     */
    String getFilter();


    /**
     * Sets the filter the Interceptor should be applying.
     * <p>
     * If the this filter string is empty or null, the match all filter should
     * be applied
     * 
     * @param filterRegex
     *            the filter regex
     */
    void setFilter(String filterRegex);


    /**
     * Filter messages from the underlying logger.
     * <p>
     * Return true if the message should be filtered and not be passed to the
     * underlying logger
     * 
     * @param msg
     *            the message to test in the filter
     * @return true if this message should be filtered from the underlying
     *         logger
     */
    boolean filter(String msg);


    /* ---------------------------------------------------------------- */

    /**
     * Sets the output stream through which intercepted logs are pushed.
     * 
     * @param outputStream
     *            the output stream
     */
    void setOutputStream(OutputStream outputStream);


    /* ---------------------------------------------------------------- */

    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param msg
     */
    void intercept(LogLevel logLevel, Logger log, String msg);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param format
     * @param arg
     */
    void intercept(LogLevel logLevel, Logger log, String format, Object arg);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param format
     * @param arg1
     * @param arg2
     */
    void intercept(LogLevel logLevel, Logger log, String format, Object arg1,
            Object arg2);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param format
     * @param arguments
     */
    void intercept(LogLevel logLevel, Logger log, String format,
            Object... arguments);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param msg
     * @param t
     */
    void intercept(LogLevel logLevel, Logger log, String msg, Throwable t);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param marker
     * @param msg
     */
    void intercept(LogLevel logLevel, Logger log, Marker marker, String msg);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param marker
     * @param format
     * @param arg
     */
    void intercept(LogLevel logLevel, Logger log, Marker marker, String format,
            Object arg);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param marker
     * @param format
     * @param arg1
     * @param arg2
     */
    void intercept(LogLevel logLevel, Logger log, Marker marker, String format,
            Object arg1,
            Object arg2);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param marker
     * @param format
     * @param arguments
     */
    void intercept(LogLevel logLevel, Logger log, Marker marker, String format,
            Object... arguments);


    /**
     * SLF4J logging call analogue
     * 
     * @param logLevel
     * @param log
     * @param marker
     * @param msg
     * @param t
     */
    void intercept(LogLevel logLevel, Logger log, Marker marker, String msg,
            Throwable t);

}
