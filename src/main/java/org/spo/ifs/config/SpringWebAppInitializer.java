/*
 * The MIT License (MIT)
 * Copyright (C) 2012 Jason Ish
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the â€œSoftwareâ€�), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED â€œAS ISâ€�, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.spo.ifs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;

public class SpringWebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * See {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer}.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Set the application context for the Spring MVC web tier.
     *
     * @See {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer}
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfiguration.class};
    }

    /**
     * Map the Spring MVC servlet as the root.
     *
     * @See {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer}
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Override to squelch a meaningless log message when embedded.
     */
    @Override
    protected void registerContextLoaderListener(
            ServletContext servletContext) {
    }
}
