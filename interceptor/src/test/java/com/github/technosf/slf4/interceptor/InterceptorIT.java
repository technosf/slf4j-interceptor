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

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.strictMock;
import static org.easymock.EasyMock.verify;
import static org.testng.Assert.assertEquals;

import org.slf4j.Logger;
import org.testng.annotations.Test;

/**
 * Interceptor integration tests
 * 
 * @author technosf
 * @since 0.0.1
 * @version 0.0.1
 */
public class InterceptorIT
{
    Logger mockLogger = strictMock(Logger.class);

    String msg = "Test log message {}";


    @Test
    public void passthrough()
    {
        reset(mockLogger);
        exerciseLogger(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.PASSTHROUGH);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.PASSTHROUGH);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
    }


    @Test
    public void absorb()
    {
        reset(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.ABSORB);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.ABSORB);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
    }


    @Test
    public void filterAll()
    {
        reset(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.FILTER);
        loggerInterceptor.setFilter(Interceptor.REGEX_MATCH_ALL);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.FILTER);
        assertEquals(loggerInterceptor.getFilter(),
                Interceptor.REGEX_MATCH_ALL);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
    }


    @Test
    public void filterNone()
    {
        reset(mockLogger);
        exerciseLogger(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.FILTER);
        loggerInterceptor.setFilter(Interceptor.REGEX_MATCH_NONE);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.FILTER);
        assertEquals(loggerInterceptor.getFilter(),
                Interceptor.REGEX_MATCH_NONE);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
    }


    @Test
    public void duplicate()
    {
        reset(mockLogger);
        exerciseLogger(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.DUPLICATE);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.DUPLICATE);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
    }


    private void exerciseLogger(Logger logger)
    {
        logger.info(msg);
        logger.debug(msg);
        logger.warn(msg);
        logger.trace(msg);

        logger.info(msg, 1);
        logger.debug(msg, 1);
        logger.warn(msg, 1);
        logger.trace(msg, 1);

        logger.info(msg, 1, 2);
        logger.debug(msg, 1, 2);
        logger.warn(msg, 1, 2);
        logger.trace(msg, 1, 2);

        logger.info(msg, 1, 2, 3, 4, 5);
        logger.debug(msg, 1, 2, 3, 4, 5);
        logger.warn(msg, 1, 2, 3, 4, 5);
        logger.trace(msg, 1, 2, 3, 4, 5);
    }
}
