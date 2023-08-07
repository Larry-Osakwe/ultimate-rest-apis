package com.skyapi.weatherforecast;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;

public class IP2LocationTest {

	private String DBPath = "\\Users\\larry\\Dev\\Tutorials\\UltimateRestApis\\WeatherApiProject\\WeatherApiService\\src\\main\\resources\\ip2locdb\\IP2LOCATION-LITE-DB3.BIN";

	@Test
	public void testInvalidIP() throws IOException {
		IP2Location ipLocator = new IP2Location();
		ipLocator.Open(DBPath);
		
		String ipAddress = "abc";
		IPResult ipResult = ipLocator.IPQuery(ipAddress);
		
		assertThat(ipResult.getStatus()).isEqualTo("INVALID_IP_ADDRESS");
		
		System.out.println(ipResult);
		
	}
	
}
