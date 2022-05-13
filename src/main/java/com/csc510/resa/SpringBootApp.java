package com.csc510.resa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the SpringBoot application with main method instead of WAR file Server
 * is embedded into the application
 * 
 * @author Steven Green
 */
@SpringBootApplication
public class SpringBootApp {

	/**
	 * Main method for the application. Starts the server
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}
