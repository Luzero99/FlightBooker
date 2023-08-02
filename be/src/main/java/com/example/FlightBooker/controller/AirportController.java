package com.example.FlightBooker.controller;

import com.example.FlightBooker.dto.AirportResponseDto;
import com.example.FlightBooker.model.Airport;
import com.example.FlightBooker.service.AirportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;
    private final ModelMapper modelMapper;
    @Autowired
    public AirportController(AirportService airportService, ModelMapper modelMapper) {
        this.airportService = airportService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public void addAirport(@RequestBody Airport airport) {
        airportService.saveAirport(airport);
    }

    @GetMapping
    public List<AirportResponseDto> getAllAirport() {
        List<Airport> airports = airportService.findAll();
        return airports.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AirportResponseDto convertToDto(Airport airport) {
        AirportResponseDto airportResponseDto = modelMapper.map(airport, AirportResponseDto.class);
        return airportResponseDto;
    }
}
