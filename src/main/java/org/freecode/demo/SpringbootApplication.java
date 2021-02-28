package org.freecode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SpringBoot Application Entry
 * to initialize Servlet context required by application container (eg tomcat), implement SpringBootServletInitializer.
 * for standalone jar deployment with embedded container, no need to implement SpringBootServletInitializer.
 */
@SpringBootApplication(scanBasePackages = "org.freecode.demo")
public class SpringbootApplication extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
    	SpringApplication.run(SpringbootApplication.class, args);
    }
}
