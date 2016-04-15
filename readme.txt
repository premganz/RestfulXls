About
This software is indtended to provide a restful gateway for a variety of services such as 
a) (PRIMARILY) xml databases in xls 97-2003 format.
b) cgi scripts
c) MQ based templating services in a pattern of event propogation and fetching templated text
d) UI for invoking restulf services

Some of this software is based on JasonIsh 's work in Github and his files bear the copyright notice distributed by him.
For all the other files not bearing the copyright notice, I attach this MIT licence read as below.  

The idea is to demonstrate how to interoperate three languages which have their own strenghts. Java as the mainstream programming language and ability to build complex applications.

Python has as one of its strengths the ability to work closely with native code with its CTypes library. Groovy is very strong in text content templating.

Of course welding these together requires external dependencies. Here I would need activemq and apache httpd. The integration of java with python would be through cgi script invocation via python from a httpClient. For groovy it would be an mq based interaction. I am including the associated resources like httpd.conf example in the resources for reference while setting up the dependencies.

And  you will need to install Python in your machine. 2.7 preferred.

Use test url
http://localhost:8082/readxl/Tyres


Licence:

* The MIT License (MIT)
 * Copyright (C) 2014 PremGanesh
 * Copyright (C) 2012 JasonIsh
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files , to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED , WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.


