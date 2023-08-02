package com.example.FlightBooker.service;

import com.example.FlightBooker.dto.FlightDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public FlightService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public List<FlightDto> getFlight(String fromIata, String toIata) {
        String baseUrl = "http://api.aviationstack.com/v1/flights";
        String apiKey = "8407b6da960f690d0e31ffae3486f9d8";

        String url = baseUrl +
                "?access_key=" + apiKey +
                "&dep_iata=" + fromIata +
                "&arr_iata=" + toIata;

        WebClient webClient = webClientBuilder.baseUrl(url).build();
        String response = webClient.get().retrieve().bodyToMono(String.class).block();

        List<FlightDto> flightDtoList = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode dataNode = jsonNode.get("data");

            if (dataNode.isArray()) {
                for (JsonNode flightNode : dataNode) {
                    String from = flightNode.get("departure").get("iata").asText();
                    String to = flightNode.get("arrival").get("iata").asText();
                    String airline = flightNode.get("airline").get("name").asText();
                    String departureDateStr = flightNode.get("departure").get("scheduled").asText();
                    String arrivalDateStr = flightNode.get("arrival").get("scheduled").asText();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                    Date departureDate = dateFormat.parse(departureDateStr);
                    Date arrivalDate = dateFormat.parse(arrivalDateStr);

                    FlightDto flightDto = new FlightDto(from, to, airline, departureDate, arrivalDate);
                    flightDtoList.add(flightDto);
                }
            }
        } catch (Exception e) {
            System.out.println("Something with wrong with convert flight data!");
        }

        return flightDtoList;
    }
}
