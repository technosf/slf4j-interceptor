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

package org.apache.commons.logging;

import static org.testng.Assert.assertEquals;

import java.io.ByteArrayOutputStream;

import org.testng.annotations.Test;

import com.github.technosf.slf4.interceptor.Interceptor.Mode;
import com.github.technosf.slf4.interceptor.LoggerInterceptor;

public class LogIT
{
    Log testlog = LogFactory.getLog(LogIT.class);
    String testmessage = "This is a test message";


    @Test
    public void testNoOutputStream()
    {
        LoggerInterceptor.setInterceptOutputStream(null);
        LoggerInterceptor.setInterceptorMode(Mode.DUPLICATE);

        testlog.error(testmessage);
    }


    @Test
    public void testWithOutputStream()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        LoggerInterceptor.setInterceptOutputStream(outputStream);
        LoggerInterceptor.setInterceptorMode(Mode.DUPLICATE);

        testlog.error(testmessage);

        assertEquals(outputStream.toString(), testmessage + "\r\n");
    }

}
