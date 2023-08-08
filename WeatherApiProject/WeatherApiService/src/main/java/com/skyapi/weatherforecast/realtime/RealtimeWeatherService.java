package com.skyapi.weatherforecast.realtime;

import org.springframework.stereotype.Service;

import com.skyapi.weatherforecast.common.Location;
import com.skyapi.weatherforecast.common.RealtimeWeather;
import com.skyapi.weatherforecast.location.LocationNotFoundException;
import com.skyapi.weatherforecast.location.LocationRepository;

@Service
public class RealtimeWeatherService {

	private RealtimeWeatherRepository realtimeWeatherRepo;
	private LocationRepository locationRepo;
	
	public RealtimeWeatherService(RealtimeWeatherRepository realtimeWeatherRepo, LocationRepository locationRepo) {
		super();
		this.realtimeWeatherRepo = realtimeWeatherRepo;
		this.locationRepo = locationRepo;
	}
	
	public RealtimeWeather getByLocation(Location location) {
		String countryCode = location.getCountryCode();
		String cityName = location.getCityName();
		
		RealtimeWeather realtimeWeather = realtimeWeatherRepo.findByCountryCodeAndCity(countryCode, cityName);
		
		if (realtimeWeather == null) {
			throw new LocationNotFoundException(countryCode, cityName);
		}
		
		return realtimeWeather;
	}
	
	public RealtimeWeather getByLocationCode(String locationCode) {
		RealtimeWeather realtimeWeather = realtimeWeatherRepo.findByLocationCode(locationCode);
		
		if (realtimeWeather == null) {
			throw new LocationNotFoundException(locationCode);
		}
		
		return realtimeWeather;
	}
	
}
