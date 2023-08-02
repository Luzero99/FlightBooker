package com.example.FlightBooker.service;

import com.example.FlightBooker.model.Airport;
import com.example.FlightBooker.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public void saveAirport(Airport airport) {
        airportRepository.save(airport);
    }

    public Airport findAirportsByIata(String iata) {
        return airportRepository.findByIata(iata);
    }

    public List<Airport> findAll() {
        return airportRepository.findAllByOrderByCityAsc();
    }
}
