package com.example.FlightBooker.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class FlightDto {
    private String from;
    private String to;

    private String airline;
    private Date departure;
    private Date arrival;

    public FlightDto() {
    }

    public FlightDto(String from, String to, String airline, Date departure, Date arrival) {
        this.from = from;
        this.to = to;
        this.airline = airline;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
}
