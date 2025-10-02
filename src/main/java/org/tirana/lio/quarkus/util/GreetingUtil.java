package org.tirana.lio.quarkus.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingUtil {
	@ConfigProperty(name = "greeting.name", defaultValue = "Mimo")
	String greetingName;
	
	public String getGreeting() {
		return "Hello " + greetingName;
	}
}
