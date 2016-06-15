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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * OutputStream Multiplexer
 * <p>
 * Multiplexes data on an output stream to subscribing other output streams.
 * 
 * @author technosf
 * @see Inspired by Brogdan Matasaru,
 *      http://www.developer.com/java/other/article.php/757771/User-Code-
 *      Multiplexing-Output-Streams.htm
 */
public class MultiplexOutputStream
        extends OutputStream
{

    /**
     * The core OutputStream from which data will be published to the
     * subscribing streams
     */
    private final ByteArrayOutputStream publisher = new ByteArrayOutputStream();

    /**
     * The set of subscribing streams
     */
    private Set<OutputStream> subscribers = new HashSet<>();

    /**
     * Flags if there is output written to the publishing stream.
     */
    private boolean writeFlag = false;


    /**
     * Default constructor
     */
    public MultiplexOutputStream()
    {
    }


    /**
     * Constructor for initial subscribing {@code OutputStreams}
     * 
     * @param os
     *            the initial subscribers
     */
    public MultiplexOutputStream(OutputStream... os)
    {
        addOutputStreams(os);
    }


    /**
     * Returns the subscribing {@code OutputStream}s as an array
     * 
     * @return array of subscribers
     */
    public OutputStream[] getOutputStreams()
    {
        return subscribers.toArray(new OutputStream[] {});
    }


    /**
     * Add subscribing {@code OutputStream}s
     * 
     * @param os
     *            the subscribers to add
     * @return true is subscribers were added
     */
    public boolean addOutputStreams(OutputStream... os)
    {
        return subscribers.addAll(Arrays.asList(os));
    }


    /**
     * Removes {@code OutputStream}s from subscribers.
     * 
     * @param os
     *            the subscribers to remove
     * @return true is subscribers were removed
     */
    public boolean removeOutputStreams(OutputStream... os)
    {
        return subscribers.removeAll(Arrays.asList(os));
    }


    /* ---------------------------------------------------------------- 
     * 
     * OutputStream Methods
     * 
     * ----------------------------------------------------------------
     */

    /**
     * {@inheritDoc}
     *
     * @see java.io.OutputStream#close()
     */
    @Override
    public void close() throws IOException
    {
        int exceptions = 0;

        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            for (OutputStream os : subscribers)
            {
                try
                {
                    os.close();
                }
                catch (IOException e)
                {
                    exceptions++;
                }
            }
            /*
             * Drop the subscribers
             */
            subscribers.clear();
        }

        /*
         * Throw one exception for all errors after all subscribers have been processed
         */
        if (exceptions > 0)
            throw new IOException("Could not cleanly close " + exceptions
                    + " OutputStreams.");

    }


    /**
     * {@inheritDoc}
     *
     * @see java.io.OutputStream#flush()
     */
    @Override
    public void flush() throws IOException
    {
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            /*
             * Copy the publisher contents to be flushed and clear the publisher
             */
            byte[] b = publisher.toByteArray();
            publisher.reset();

            for (OutputStream os : subscribers)
            /*
             * Write and flush the publisher content to the subscribers
             */
            {
                if (writeFlag)
                /*
                 * Write only if a write operation happened previously
                 */
                {
                    os.write(b);
                }
                os.flush();
            }

            writeFlag = false;
        }
    }


    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     * @see java.io.OutputStream#write(byte[])
     */
    @Override
    public void write(byte[] b) throws IOException
    {
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            publisher.write(b);
            writeFlag = true;
        }
    }


    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     * @see java.io.OutputStream#write(byte[], int, int)
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            publisher.write(b, off, len);
            writeFlag = true;
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see java.io.OutputStream#write(int)
     */
    @Override
    public void write(int b) throws IOException
    {
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            publisher.write(b);
            writeFlag = true;
        }
    }
}
