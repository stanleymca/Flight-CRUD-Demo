package com.demo.springbootdynamodb.flight.dao;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.demo.springbootdynamodb.flight.entity.Flight;

@EnableScan
public interface FlightDAO extends CrudRepository<Flight, String>{
	
	Flight findById(String id);
	
	List<Flight> findAll();
}
