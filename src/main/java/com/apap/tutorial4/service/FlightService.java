package com.apap.tutorial4.service;


import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(Long Id);
}
