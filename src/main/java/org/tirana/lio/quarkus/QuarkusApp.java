package org.tirana.lio.quarkus;

import org.tirana.lio.quarkus.util.FizzBuzzExecutor;
import org.tirana.lio.quarkus.util.GreetingUtil;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

	GreetingUtil greetingUtil;

	FizzBuzzExecutor fizzBuzzExecutor;

	public QuarkusApp(GreetingUtil greetingUtil, FizzBuzzExecutor fizzBuzzExecutor) {
		super();
		this.greetingUtil = greetingUtil;
		this.fizzBuzzExecutor = fizzBuzzExecutor;
	}

	@Override
	public int run(String... args) throws Exception {
		System.out.println(this.greetingUtil.getGreeting());
		fizzBuzzExecutor.execute();
		return 0;
	}

}
