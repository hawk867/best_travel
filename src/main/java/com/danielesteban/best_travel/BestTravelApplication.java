package com.danielesteban.best_travel;

import com.danielesteban.best_travel.domain.repositories.FlyRepository;
import com.danielesteban.best_travel.domain.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BestTravelApplication implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final FlyRepository flyRepository;

    public BestTravelApplication(HotelRepository hotelRepository, FlyRepository flyRepository) {
        this.hotelRepository = hotelRepository;
        this.flyRepository = flyRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BestTravelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var fly = flyRepository.findById(14L).get();
        var hotel = hotelRepository.findById(7L).get();

        log.info(String.valueOf(fly));
        log.info(String.valueOf(hotel));
    }
}
