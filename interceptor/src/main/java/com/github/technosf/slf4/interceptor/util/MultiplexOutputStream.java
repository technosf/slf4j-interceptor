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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * OutputStream Multiplexer
 * <p>
 * Multiplexes data on an output stream to subscribing other output streams.
 * This implementation synchronizes on {@code flush} allowing threads expecting
 * data on subscribing output streams to wait.
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
    private Set<OutputStream> subscribers =
            Collections.synchronizedSet(new HashSet<OutputStream>());

    /**
     * Automatically flush output streams on writes ending with EOL char 10
     */
    private boolean autoFlush = false;

    /**
     * Prefix to prepend to output
     */
    private byte[] prefix = new byte[] {};

    /**
     * Postix to append to output
     */
    private byte[] postfix = new byte[] {};

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


    /* ----------------------------------------------------------------
     * 
     * Configuration setters
     * 
     * ----------------------------------------------------------------
     */

    /**
     * Sets autoFlush property, causing output streams to be flushed when writes
     * end with character 10 (LF).
     * 
     * @param autoFlush
     *            true to autoflush
     * @return this MultiplexOutputStream
     */
    public MultiplexOutputStream setAutoFlush(boolean autoFlush)
    {
        this.autoFlush = autoFlush;
        return this;
    }


    /**
     * Sets a prefix to write to output before each flush
     * 
     * @param prefix
     *            value to prepend to an output buffer before writing
     * @return this MultiplexOutputStream
     */
    public MultiplexOutputStream setPrefix(byte[] prefix)
    {
        this.prefix = prefix;
        return this;
    }


    /**
     * Sets a postfix to write to output before each flush
     * 
     * @param postfix
     *            value to append to an output buffer before writing
     * @return this MultiplexOutputStream
     */
    public MultiplexOutputStream setPostfix(byte[] postfix)
    {
        this.postfix = postfix;
        return this;
    }


    /* ----------------------------------------------------------------
     * 
     * Consumers
     * 
     * ----------------------------------------------------------------
     */
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
     * Is the given OutputStream a subscriber?
     * 
     * @param os
     *            the output stream
     * @return true if it's a subscriber
     */
    public boolean hasOutputStream(OutputStream os)
    {
        return subscribers.contains(os);
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
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            return subscribers.addAll(Arrays.asList(os));
        }
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
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            return subscribers.removeAll(Arrays.asList(os));
        }
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
     * {code flush} takes accumulated writes and pushes them onto subscribing
     * {@code OutputStream}s.
     * <p>
     * <em>flush</em> is synchronous and calls {@code notifyAll} once all
     * {@code OutputStream}s have been flushed.
     *
     * @see java.io.OutputStream#flush()
     */
    @Override
    public synchronized void flush() throws IOException
    {
        synchronized (publisher)
        /*
         * Lock on publisher, so that IO operations are consistent
         */
        {
            List<OutputStream> deadStreams = new ArrayList<>(); // Container streams found dead

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
                try
                {
                    if (writeFlag && b.length > 0)
                    /*
                     * Write only if a write operation happened previously
                     */
                    {
                        if (prefix.length > 0)
                            os.write(prefix);
                        os.write(b);
                        if (postfix.length > 0)
                            os.write(postfix);
                    }
                    os.flush();
                }
                catch (IOException e)
                /*
                 * Assume os is closed, add to dead list
                 */
                {
                    deadStreams.add(os);
                }
            }

            if (!deadStreams.isEmpty())
            /*
             * Remove dead streams
             */
            {
                subscribers.remove(deadStreams);
            }

            writeFlag = false;
        }
        notifyAll(); // Notify waiting threads that a flush has occured
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
            if (b[b.length - 1] == 10 && autoFlush)
                flush();
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
            if (b[len - 1] == 10 && autoFlush)
                flush();
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
            if (b == 10 && autoFlush)
                flush();
        }
    }
}
