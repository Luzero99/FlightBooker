package com.example.FlightBooker.dto;

public class AirportResponseDto {
    private String city;
    private String iata;

    public AirportResponseDto() {
    }

    public AirportResponseDto(String city, String iata) {
        this.city = city;
        this.iata = iata;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }
}
