package com.example.FlightBooker.repository;

import com.example.FlightBooker.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {
    public Airport findByIata(String iata);

    List<Airport> findAllByOrderByCityAsc();
}
