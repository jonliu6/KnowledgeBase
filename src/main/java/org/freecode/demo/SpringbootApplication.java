package org.freecode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Application Entry
 *
 */
@SpringBootApplication(scanBasePackages = "org.freecode.demo")
public class SpringbootApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(SpringbootApplication.class, args);
    }
}
