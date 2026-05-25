package com.emkay.bank.banking_platform;

import org.springframework.boot.SpringApplication;

public class TestBankingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankingPlatformApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
