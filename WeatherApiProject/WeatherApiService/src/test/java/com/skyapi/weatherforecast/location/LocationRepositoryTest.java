package com.skyapi.weatherforecast.location;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.skyapi.weatherforecast.common.Location;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LocationRepositoryTest {
	
	@Autowired
	private LocationRepository repository;
	
	@Test
	public void testAddSuccess() {
		Location location = new Location();
		location.setCode("MBMH_IN");
		location.setCityName("Mumbai");
		location.setRegionName("Maharashtra");
		location.setCountryCode("IN");
		location.setCountryName("India");
		location.setEnabled(true);
		
		Location savedLocation = repository.save(location);
		
		assertThat(savedLocation).isNotNull();
		assertThat(savedLocation.getCode()).isEqualTo("MBMH_IN");
	}
	
	@Test
	public void testListSuccess() {
		List<Location> locations = repository.findUntrashed();
		
		assertThat(locations).isNotEmpty();
		
		locations.forEach(System.out::println);
	}
	
	@Test
	public void testGetNotFound() {
		String code = "ABCD";
		Location location = repository.findByCode(code);
		
		assertThat(location).isNull();
	}
	
	@Test
	public void testGetFound() {
		String code = "LACA_US";
		Location location = repository.findByCode(code);
		
		assertThat(location).isNotNull();
		assertThat(location.getCode()).isEqualTo(code);
	}
}
