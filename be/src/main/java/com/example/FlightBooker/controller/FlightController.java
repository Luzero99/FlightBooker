package com.example.FlightBooker.controller;

import com.example.FlightBooker.dto.FlightDto;
import com.example.FlightBooker.service.FlightService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public List<FlightDto> getFlight(@RequestParam @NotBlank(message = "From Iata is required.") String fromIata, @RequestParam @NotBlank(message = "To Iata is required.") String toIata) {
        return flightService.getFlight(fromIata, toIata);
    }
}
