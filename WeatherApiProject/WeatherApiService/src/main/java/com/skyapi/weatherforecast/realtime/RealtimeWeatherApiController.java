package com.skyapi.weatherforecast.realtime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyapi.weatherforecast.CommonUtility;
import com.skyapi.weatherforecast.GeolocationException;
import com.skyapi.weatherforecast.GeolocationService;
import com.skyapi.weatherforecast.common.Location;
import com.skyapi.weatherforecast.common.RealtimeWeather;
import com.skyapi.weatherforecast.location.LocationNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/realtime")
public class RealtimeWeatherApiController {
	
	private GeolocationService locationService;
	private RealtimeWeatherService realtimeWeatherService;
	
	public RealtimeWeatherApiController(GeolocationService locationService,
			RealtimeWeatherService realtimeWeatherService) {
		super();
		this.locationService = locationService;
		this.realtimeWeatherService = realtimeWeatherService;
		
	}
	
	@GetMapping
	public ResponseEntity<?> getRealtimeWeatherByIPAddress(HttpServletRequest request) {
		String ipAddress = CommonUtility.getIPAddress(request);
		
		try {
			Location locationFromIP = locationService.getLocation(ipAddress);
			RealtimeWeather realtimeWeather = realtimeWeatherService.getByLocation(locationFromIP);
			
			return ResponseEntity.ok(realtimeWeather);
		} catch (GeolocationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		} catch (LocationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
