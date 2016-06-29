# slf4j-interceptor #

Intercept log messages from *Commons Logging*, *log4j*, *java.util.logging* and *SLF4J*, and filter, duplicate or reroute them programmatically.


## Table of Contents ##

- [Intercepting Logging Messages](#intercepting-logging-messages)
- [Installation ](#installation)
- [Examples ](#examples)
- [Design Decisions ](#design-decisions)
- [License](#license)


## Intercepting Logging Messages ##

Logging frameworks are commonly used to provide asynchronous access to informational messages produced within code and within ancillary libraries. There are certain generalities found in the various logging frameworks, not least the ability to log messages written for one framework to a different framework at runtime. Yet the metaphor for output is that it is always written to an *appender* defined in a configuration file that says at what level to write and to where.

What has been difficult is to access logging messages within the message-producing application itself: When you are dealing with third-party libraries, being able to review their inner dialogue and reuse it elsewhere may be useful, and indeed that situation is the genesis for this project.

*slf4j-interceptor* builds off *slf4j* to allow log messages to be intercepted programmatically and decisions as to what to do with the messages to be made within your code. As an example, debug messages produced in a web application can be written as usual to a log file, but also be made available for display in administrative web pages in real-time. Add to this the ability to **absorb** and **duplicate** messages, or **reroute** them. This can be achieved against all logging frameworks supported by *slf4j*.

*slf4j-interceptor* works in the following modes: 
 * ABSORB - _Messages are absorbed and do not go to the logger nor interceptor_
 * FILTER - _Messages go only to the interceptor_
 * DUPLICATE - _Messages go to both the logger and the interceptor_
 * PASSTHROUGH - _Messages go only to the logger_

*slf4j-interceptor* can be configured with a *regex* filter - only messages that hit the *interceptor* and match on the filter will be handed on.

![Logging Interception](https://github.com/technosf/slf4j-interceptor/slf4ji.png)

## Installation ##

*slf4j-interceptor* is deployed in two or more libraries. The main *slf4j-interceptor* library provides the programmatic interface to the interceptor system, message output and configuration, plus some utilities. The other libraries each mimic a logging framework expected by the code doing the logging for the *interceptor*. *slf4j* itself uses substitute logging libraries to funnel messages to *slf4j* core, and from there to its destination. *slf4j-interceptor* extends this *slf4j* approach to put a programmatic event interface between the log call and the *slf4j* core. 

For example, to intercept *Apache Commons Logging* messages, the *Commons Logging* libraries would be removed from the classpath and replaced with *slf4j-i-commons-logging*. Adding a suitable *slf4j* output logging library would replicate the *Commons Logging* log appender functionality. By adding *slf4j-i-intercepter* provides programmatic access to the messages for your code. 


## Examples ##

Access to messages is provided _statically_ by *LoggerInterceptor*. As a class static block, where messages are copied on to _aStaticInterceptorStream_:

	{
        /*
         * Set the global log interceptor stream and have it filter what passes through.
         */
        LoggerInterceptor.setInterceptOutputStream(aStaticInterceptorStream);
        LoggerInterceptor.setInterceptorMode(Interceptor.Mode.FILTER);
    }

Log messages then arrive on _aStaticInterceptorStream_ to be process in the local code.	


## Design Decisions ##

Looking at the obvious approach to intercepting logging messages by using a custom appender was hindered by a dearth of examples, and as thinking evolved, by the position of appenders at the end of the logging pipeline. By grabbing the messages as they were written, the vector was clearer (i.e. the *Log* interface) and it provides the ability to route the messages prior to any further processing by underlying *Log* frameworks.

How to intercept *log* calls without touching the code making those calls? Investigating *AOP* indicated a substantial opportunity for intractable issues given the dynamic nature of *AOP* and resulting problems debugging and reaching a consistent approach. Taking a lead from *slf4j* itself, *log* implementations can be replaced by facades that do the interception. While a facade is susceptible to brittleness as the underlying framework changes, it is more easily managed and debugged. 

Given that *slf4j* has facades already in place, I decided to build of these to add log interception.


## License ##

slf4j-interceptor - Copyright 2016 technosf [https://github.com/technosf]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.