package com.skyapi.weatherforecast.realtime;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyapi.weatherforecast.BadRequestException;
import com.skyapi.weatherforecast.CommonUtility;
import com.skyapi.weatherforecast.GeolocationException;
import com.skyapi.weatherforecast.GeolocationService;
import com.skyapi.weatherforecast.common.Location;
import com.skyapi.weatherforecast.common.RealtimeWeather;
import com.skyapi.weatherforecast.location.LocationNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/realtime")
public class RealtimeWeatherApiController {
	
	private GeolocationService locationService;
	private RealtimeWeatherService realtimeWeatherService;
	private ModelMapper modelMapper;
	
	public RealtimeWeatherApiController(GeolocationService locationService,
			RealtimeWeatherService realtimeWeatherService, ModelMapper modelMapper) {
		super();
		this.locationService = locationService;
		this.realtimeWeatherService = realtimeWeatherService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping
	public ResponseEntity<?> getRealtimeWeatherByIPAddress(HttpServletRequest request) {
		String ipAddress = CommonUtility.getIPAddress(request);
		
		try {
			Location locationFromIP = locationService.getLocation(ipAddress);
			RealtimeWeather realtimeWeather = realtimeWeatherService.getByLocation(locationFromIP);
			
			RealtimeWeatherDTO dto = modelMapper.map(realtimeWeather, RealtimeWeatherDTO.class);
			
			return ResponseEntity.ok(dto);
		} catch (GeolocationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		} catch (LocationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{locationCode}")
	public ResponseEntity<?> getRealtimeWeatherByLocationCode(@PathVariable("locationCode") String locationCode) {
		try {
			RealtimeWeather realtimeWeather = realtimeWeatherService.getByLocationCode(locationCode);
			RealtimeWeatherDTO dto = modelMapper.map(realtimeWeather, RealtimeWeatherDTO.class);
			return ResponseEntity.ok(dto);
		} catch (LocationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{locationCode}")
	public ResponseEntity<?> updateRealtimeWeather(@PathVariable("locationCode") String locationCode,
			@RequestBody @Valid RealtimeWeatherDTO dto) {
		
		try {
			RealtimeWeather realtimeWeather  = modelMapper.map(dto, RealtimeWeather.class);
			realtimeWeather.setLocationCode(locationCode);
			
			RealtimeWeather updatedRealtimeWeather = realtimeWeatherService.update(locationCode, realtimeWeather);
			
			RealtimeWeatherDTO retiDTO = modelMapper.map(updatedRealtimeWeather, RealtimeWeatherDTO.class);
			
			return ResponseEntity.ok(retiDTO);
		} catch (LocationNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
	}
}
