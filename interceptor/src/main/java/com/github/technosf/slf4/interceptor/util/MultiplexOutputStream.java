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
package com.github.technosf.slf4.interceptor.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * OutStream multiplexer
 * 
 * @author technosf, Brogdan Matasaru
 * @see http://www.developer.com/java/other/article.php/757771/User-Code-
 *      Multiplexing-Output-Streams.htm
 */
public class MultiplexOutputStream
        extends OutputStream
{
    /**
     * 
     */
    private Vector<OutputStream> m_streams;


    /**
     * 
     */
    public MultiplexOutputStream()
    {
    }


    /**
     * @param os
     */
    public MultiplexOutputStream(OutputStream os)
    {
        addOutputStream(os);
    }


    /**
     * @return
     */
    public Enumeration<OutputStream> getOutputStreams()
    {
        if (m_streams != null)
            return m_streams.elements();
        return null;
    }


    /**
     * @param os
     */
    public void addOutputStream(OutputStream os)
    {
        if (os == null)
            return;
        if (m_streams == null)
            m_streams = new Vector<OutputStream>();
        m_streams.addElement(os);
    }


    /**
     * @param os
     */
    public void removeOutputStream(OutputStream os)
    {
        if (m_streams != null && os != null)
            m_streams.removeElement(os);
    }


    /* ---------------------------------------------------------------- */

    /**
     * {@inheritDoc}
     *
     * @see java.io.OutputStream#close()
     */
    @Override
    public void close() throws IOException
    {
        Enumeration<OutputStream> e = getOutputStreams();
        if (e == null)
            return;
        IOException ioe = null;
        while (e.hasMoreElements())
        {
            try
            {
                e.nextElement().close();
            }
            catch (IOException exc)
            {
                ioe = exc;
            }
        }
        if (ioe != null)
            throw ioe;
    }


    /**
     * {@inheritDoc}
     *
     * @see java.io.OutputStream#flush()
     */
    @Override
    public void flush() throws IOException
    {
        Enumeration<OutputStream> e = getOutputStreams();
        if (e == null)
            return;
        IOException ioe = null;
        while (e.hasMoreElements())
        {
            try
            {
                e.nextElement().flush();
            }
            catch (IOException exc)
            {
                ioe = exc;
            }
        }
        if (ioe != null)
            throw ioe;
    }


    /**
     * {@inheritDoc}
     *
     * @see java.io.OutputStream#write(int)
     */
    @Override
    public void write(int b) throws IOException
    {
        Enumeration<OutputStream> e;
        if ((e = getOutputStreams()) == null)
            return;

        IOException ioe = null;

        while (e.hasMoreElements())
        {
            try
            {
                e.nextElement().write(b);
            }
            catch (IOException exc)
            {
                ioe = exc;
            }
        }

        if (ioe != null)
            throw ioe;
    }
}
