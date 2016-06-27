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
package com.github.technosf.slf4.interceptor.util;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStream;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MultiplexOutputStreamTest //extends EasyMockSupport
{

    MultiplexOutputStream classUnderTest = new MultiplexOutputStream();

    OutputStream os1 = createNiceMock("os1", OutputStream.class);
    OutputStream os2 = createNiceMock("os2", OutputStream.class);
    OutputStream os3 = createNiceMock("os3", OutputStream.class);
    //    OutputStream os1 = createMock("os1", OutputStream.class);
    //    OutputStream os2 = createMock("os2", OutputStream.class);
    //    OutputStream os3 = createMock("os3", OutputStream.class);
    OutputStream[] osa = new OutputStream[] { os1, os2, os3 };


    @BeforeClass
    private void beforeClass() throws IOException
    {
        reset(osa);
        exercise(osa);
        replay(os1);
    }


    @AfterClass
    private void afterClass()
    {
        //verify(osa);
    }


    @Test
    public void addOutputStreams()
    {
        classUnderTest.addOutputStreams(osa);
    }


    @Test(dependsOnMethods = { "addOutputStreams" })
    public void getOutputStreams()
    {
        OutputStream[] xosa = classUnderTest.getOutputStreams();
        assertEqualsNoOrder(xosa, osa);
    }


    @Test(dependsOnMethods = { "getOutputStreams" })
    public void writeFlushClose() throws IOException
    {
        exercise(classUnderTest);
    }


    @Test(dependsOnMethods = { "writeFlushClose" })
    public void removeOutputStreams()
    {
        classUnderTest.addOutputStreams(osa);
        assertTrue(classUnderTest.removeOutputStreams(osa));
        assertEquals(classUnderTest.getOutputStreams().length, 0);
    }


    /* ---------------------------------------------------------------- */

    private void exercise(OutputStream... os) throws IOException
    {
        for (OutputStream o : os)
        {
            o.write("Hello World".getBytes());
            o.flush();
            o.write("Goodbye World".getBytes());
            o.flush();
            o.close();
        }
    }
}
