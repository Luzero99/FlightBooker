package com.example.FlightBooker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name="airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="city")
    @NotBlank(message = "The city is required.")
    private String city;

    @Column(name="iata", unique = true)
    @NotBlank(message = "The iata is required.")
    private String iata;

    public Airport() {

    }

    public Airport(String city, String iata) {
        this.city = city;
        this.iata = iata;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String town) {
        this.city = town;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", iata='" + iata + '\'' +
                '}';
    }
}
