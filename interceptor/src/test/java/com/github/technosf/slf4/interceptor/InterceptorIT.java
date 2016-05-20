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

import java.io.ByteArrayOutputStream;

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

    String msgproduced =
            "Test log message {}\r\nTest log message {}\r\nTest log message {}\r\nTest log message {}\r\nTest log message {}\r\n"
                    +
                    "Test log message 1\r\nTest log message 1\r\nTest log message 1\r\nTest log message 1\r\nTest log message 1\r\n"
                    +
                    "Test log message 1\r\nTest log message 1\r\nTest log message 1\r\nTest log message 1\r\nTest log message 1\r\n"
                    +
                    "Test log message 1\r\nTest log message 1\r\nTest log message 1\r\nTest log message 1\r\nTest log message 1\r\n";


    @Test
    public void passthrough()
    {
        reset(mockLogger);
        exerciseLogger(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.PASSTHROUGH);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.PASSTHROUGH);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        loggerInterceptor.setOutputStream(outputStream);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
        assertEquals(outputStream.toString(), "");
    }


    @Test
    public void absorb()
    {
        reset(mockLogger);
        replay(mockLogger);

        LoggerInterceptor loggerInterceptor = new LoggerInterceptor(mockLogger);
        loggerInterceptor.setMode(Interceptor.Mode.ABSORB);
        assertEquals(loggerInterceptor.getMode(), Interceptor.Mode.ABSORB);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        loggerInterceptor.setOutputStream(outputStream);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);
        assertEquals(outputStream.toString(), "");
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

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        loggerInterceptor.setOutputStream(outputStream);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);

        String output = outputStream.toString();
        assertEquals(output, msgproduced);
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

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        loggerInterceptor.setOutputStream(outputStream);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);

        assertEquals(outputStream.toString(), msgproduced);
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

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        loggerInterceptor.setOutputStream(outputStream);

        exerciseLogger(loggerInterceptor);

        verify(mockLogger);

        assertEquals(outputStream.toString(), msgproduced);
    }


    private void exerciseLogger(Logger logger)
    {
        logger.info(msg);
        logger.debug(msg);
        logger.warn(msg);
        logger.error(msg);
        logger.trace(msg);

        logger.info(msg, 1);
        logger.debug(msg, 1);
        logger.warn(msg, 1);
        logger.error(msg, 1);
        logger.trace(msg, 1);

        logger.info(msg, 1, 2);
        logger.debug(msg, 1, 2);
        logger.warn(msg, 1, 2);
        logger.error(msg, 1, 2);
        logger.trace(msg, 1, 2);

        logger.info(msg, 1, 2, 3, 4, 5);
        logger.debug(msg, 1, 2, 3, 4, 5);
        logger.warn(msg, 1, 2, 3, 4, 5);
        logger.error(msg, 1, 2, 3, 4, 5);
        logger.trace(msg, 1, 2, 3, 4, 5);
    }
}
