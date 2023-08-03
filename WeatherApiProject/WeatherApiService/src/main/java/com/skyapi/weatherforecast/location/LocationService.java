package com.skyapi.weatherforecast.location;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skyapi.weatherforecast.common.Location;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LocationService {
	
	private LocationRepository repo;
	
	public LocationService(LocationRepository repo) {
		super();
		this.repo = repo;
	}

	public Location add(Location location) {
		return repo.save(location);
	}
	
	public List<Location> list() {
		return repo.findUntrashed();
	}
	
	public Location get(String code) {
		Location location = repo.findByCode(code);
		
		if (location == null) {
			throw new LocationNotFoundException(code);
		}
		
		return location;
	}
}
