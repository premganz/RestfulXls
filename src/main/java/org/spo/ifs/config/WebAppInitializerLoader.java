package org.spo.ifs.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

public class WebAppInitializerLoader implements ServletContextListener {

    private WebApplicationInitializer initializers[];

    public WebAppInitializerLoader(WebApplicationInitializer initializers[]) {
        this.initializers = initializers;
    }

   
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        for (WebApplicationInitializer initializer: initializers) {
            try {
                initializer.onStartup(servletContextEvent.getServletContext());
            }
            catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
