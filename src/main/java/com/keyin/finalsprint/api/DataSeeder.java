package com.keyin.finalsprint.api;

import com.keyin.finalsprint.api.entity.Aircraft;
import com.keyin.finalsprint.api.entity.Airport;
import com.keyin.finalsprint.api.entity.City;
import com.keyin.finalsprint.api.entity.Passenger;
import com.keyin.finalsprint.api.entity.Flight;
import com.keyin.finalsprint.api.entity.Gate;
import com.keyin.finalsprint.api.repository.AircraftRepository;
import com.keyin.finalsprint.api.repository.AirportRepository;
import com.keyin.finalsprint.api.repository.CityRepository;
import com.keyin.finalsprint.api.repository.FlightRepository;
import com.keyin.finalsprint.api.repository.GateRepository;
import com.keyin.finalsprint.api.repository.PassengerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedDatabase(
    CityRepository cityRepo,
    AirportRepository airportRepo,
    AircraftRepository aircraftRepo,
    PassengerRepository passengerRepo,
    FlightRepository flightRepo,
    GateRepository gateRepo
) {
        return args -> {
            // --- 1. Create Cities ---
            City city1 = new City("Toronto", "ON", 3000000);
            City city2 = new City("Vancouver", "BC", 2500000);
            City city3 = new City("Montreal", "QC", 2000000);
            City city4 = new City("Calgary", "AB", 1200000);
            City city5 = new City("Halifax", "NS", 400000);
            City city6 = new City("Ottawa", "ON", 900000);
            City city7 = new City("St. John's", "NL", 200000);
            City city8 = new City("Edmonton", "AB", 1100000);
            City city9 = new City("Quebec City", "QC", 700000);
            City city10 = new City("Winnipeg", "MB", 750000);
            cityRepo.saveAll(Arrays.asList(city1, city2, city3, city4, city5, city6, city7, city8, city9, city10));

            // --- 2. Create Airports ---
            Airport airport1 = new Airport("Toronto Pearson", "YYZ");
            airport1.setCity(city1);
            Airport airport2 = new Airport("Vancouver Int", "YVR");
            airport2.setCity(city2);
            Airport airport3 = new Airport("Montreal-Trudeau", "YUL");
            airport3.setCity(city3);
            Airport airport4 = new Airport("Calgary Int", "YYC");
            airport4.setCity(city4);
            Airport airport5 = new Airport("Halifax Stanfield", "YHZ");
            airport5.setCity(city5);
            Airport airport6 = new Airport("Ottawa Macdonald–Cartier", "YOW");
            airport6.setCity(city6);
            Airport airport7 = new Airport("St. John's Intl", "YYT");
            airport7.setCity(city7);
            Airport airport8 = new Airport("Edmonton Intl", "YEG");
            airport8.setCity(city8);
            Airport airport9 = new Airport("Quebec City Jean Lesage", "YQB");
            airport9.setCity(city9);
            Airport airport10 = new Airport("Winnipeg Intl", "YWG");
            airport10.setCity(city10);
            airportRepo.saveAll(Arrays.asList(airport1, airport2, airport3, airport4, airport5, airport6, airport7, airport8, airport9, airport10));

            // --- 3. Create Aircraft ---
            Aircraft aircraft1 = new Aircraft("Boeing 737", "Air Canada", 150);
            Aircraft aircraft2 = new Aircraft("Airbus A320", "WestJet", 140);
            Aircraft aircraft3 = new Aircraft("Dash 8", "Jazz", 50);
            Aircraft aircraft4 = new Aircraft("Boeing 787", "Air Canada", 250);
            Aircraft aircraft5 = new Aircraft("CRJ-900", "Sky Regional", 80);
            Aircraft aircraft6 = new Aircraft("A321", "Air Transat", 200);
            Aircraft aircraft7 = new Aircraft("Q400", "Porter", 74);
            Aircraft aircraft8 = new Aircraft("737 MAX", "WestJet", 162);
            Aircraft aircraft9 = new Aircraft("Boeing 777", "Air Canada", 300);
            Aircraft aircraft10 = new Aircraft("A220", "Air Canada", 137);
            // Assign airports to aircraft
            aircraft1.setAirports(Arrays.asList(airport1, airport2));
            aircraft2.setAirports(Arrays.asList(airport3, airport4));
            aircraft3.setAirports(Arrays.asList(airport5, airport6));
            aircraft4.setAirports(Arrays.asList(airport7, airport8));
            aircraft5.setAirports(Arrays.asList(airport9, airport10));
            aircraft6.setAirports(Arrays.asList(airport1, airport5));
            aircraft7.setAirports(Arrays.asList(airport2, airport7));
            aircraft8.setAirports(Arrays.asList(airport3, airport8));
            aircraft9.setAirports(Arrays.asList(airport4, airport9));
            aircraft10.setAirports(Arrays.asList(airport6, airport10));
            aircraftRepo.saveAll(Arrays.asList(aircraft1, aircraft2, aircraft3, aircraft4, aircraft5, aircraft6, aircraft7, aircraft8, aircraft9, aircraft10));

            // --- 4. Create Passengers ---
            Passenger p1 = new Passenger("Alex", "Smith", "555-1234");
            p1.setCity(city1);
            p1.setAircraftList(Arrays.asList(aircraft1, aircraft2));
            Passenger p2 = new Passenger("Jamie", "Lee", "555-2345");
            p2.setCity(city2);
            p2.setAircraftList(Arrays.asList(aircraft3, aircraft4));
            Passenger p3 = new Passenger("Jordan", "White", "555-3456");
            p3.setCity(city3);
            p3.setAircraftList(Arrays.asList(aircraft5, aircraft6));
            Passenger p4 = new Passenger("Morgan", "Brown", "555-4567");
            p4.setCity(city4);
            p4.setAircraftList(Arrays.asList(aircraft7, aircraft8));
            Passenger p5 = new Passenger("Taylor", "Jones", "555-5678");
            p5.setCity(city5);
            p5.setAircraftList(Arrays.asList(aircraft9, aircraft10));
            Passenger p6 = new Passenger("Robin", "Moore", "555-6789");
            p6.setCity(city6);
            p6.setAircraftList(Arrays.asList(aircraft1, aircraft6));
            Passenger p7 = new Passenger("Sam", "Green", "555-7890");
            p7.setCity(city7);
            p7.setAircraftList(Arrays.asList(aircraft2, aircraft7));
            Passenger p8 = new Passenger("Drew", "Clark", "555-8901");
            p8.setCity(city8);
            p8.setAircraftList(Arrays.asList(aircraft3, aircraft8));
            Passenger p9 = new Passenger("Riley", "King", "555-9012");
            p9.setCity(city9);
            p9.setAircraftList(Arrays.asList(aircraft4, aircraft9));
            Passenger p10 = new Passenger("Casey", "Wright", "555-0123");
            p10.setCity(city10);
            p10.setAircraftList(Arrays.asList(aircraft5, aircraft10));
            passengerRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        
            // --- 5. Create Flights/ Gates ---
            // Optional: Create Gates if needed
           Gate gate1 = new Gate("A1", airport1); // For Toronto
           Gate gate2 = new Gate("B2", airport7); // For St. John's

            gateRepo.saveAll(Arrays.asList(gate1, gate2));


            // Example Flights
            Flight flight1 = new Flight();
            flight1.setFlightNumber("AC101");
            flight1.setDepartureAirport(airport1);
            flight1.setArrivalAirport(airport7);
            flight1.setAircraft(aircraft1);
            flight1.setGate(gate1);

            Flight flight2 = new Flight();
            flight2.setFlightNumber("WS202"); 
            flight2.setDepartureAirport(airport7);
            flight2.setArrivalAirport(airport2);
            flight2.setAircraft(aircraft2);
            flight2.setGate(gate2);


            flightRepo.saveAll(Arrays.asList(flight1, flight2));
        
        };
    }
}
