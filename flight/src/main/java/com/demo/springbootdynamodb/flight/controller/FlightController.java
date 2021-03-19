package com.demo.springbootdynamodb.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springbootdynamodb.flight.dao.FlightDAO;
import com.demo.springbootdynamodb.flight.entity.Flight;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/flightService/flights")
@Api(value = "Stanley's Flight Management Demo Rest APIs")
public class FlightController {

	@Autowired
	private FlightDAO dao;

	@ApiOperation(value = "API to Get all the Flights"
            , response = Flight.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Flight Details Successfully found"),
            @ApiResponse(code = 404, message = "Flight record does not exist")})
	@GetMapping
	public @ResponseBody List<Flight> findAll() {
		return dao.findAll();
	}
	
	@ApiOperation(value = "API to Get Flight Details by ID", response = Flight.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Flight Details Successfully found for the given id"),
            @ApiResponse(code = 404, message = "Flight record does not exist for the given id")})
	@GetMapping(value = "/{id}")
	public @ResponseBody Flight findById(@PathVariable String id) {
		return dao.findById(id);
	}

	@ApiOperation(value = "API to Delete Flight Details by ID", response = Flight.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted the selected Flight Successfully"),
            @ApiResponse(code = 404, message = "Flight record does not exist for the given id")})
    @DeleteMapping(value = "/{id}")	
	public void deleteById(@PathVariable String id) {
		dao.delete(id);
	}

	@ApiOperation(value = "API to Create Flight Details", response = Flight.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Flight Details created Successfully")})
    @PostMapping(headers="Accept=application/json") 
	public @ResponseBody Flight addFlightEntry(@RequestBody Flight flightDetails) {
		Flight newFlight = dao.save(flightDetails);
		return newFlight;
	}
	
	@ApiOperation(value = "API to Update Flight Details", response = Flight.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated Flight Details Successfully")})
    @PutMapping(value = "/{id}", headers="Accept=application/json")
	public ResponseEntity<Flight> updateFlightEntry(@PathVariable String id, @RequestBody Flight flightDetails) {
		Flight flight = dao.findById(id);
		
		if (flight == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
		}
		
		flight.setAirlineCode(flightDetails.getAirlineCode());
		flight.setFlightNo(flightDetails.getFlightNo());
		flight.setOrigin(flightDetails.getOrigin());
		flight.setDestination(flightDetails.getDestination());
		flight.setTicketPrice(flightDetails.getTicketPrice());
		flight.setActive(flightDetails.getActive());
		dao.save(flight);

		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}
}
