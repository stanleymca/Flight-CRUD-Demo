package com.demo.springbootdynamodb.flight;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.demo.springbootdynamodb.flight.dao.FlightDAO;
import com.demo.springbootdynamodb.flight.entity.Flight;

@SpringBootApplication
public class FlightApplication {

	private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    
	@Autowired
	FlightDAO dao;
	
	/**
	 * Main method of Flight application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlightApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		createFlightTable();
		Flight flight1 = new Flight("LH", "0050", "DXB", "MUC", new BigDecimal("1850"), true);
		dao.save(flight1);
		
		Flight flight2 = new Flight("EK", "0410", "DXB", "SYD", new BigDecimal("3560"), true);
		dao.save(flight2);		
	}

	public void createFlightTable(){
        try {
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Flight.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            
        }
	}	
}
