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
 * Logging interceptor definition
 * 
 * @author technosf
 * @since 0.0.1
 * @version 0.0.1
 */
public interface Interceptor
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
     * @return
     */
    Mode getMode();


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
