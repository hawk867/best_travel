package com.danielesteban.best_travel;

import com.danielesteban.best_travel.domain.entities.ReservationEntity;
import com.danielesteban.best_travel.domain.entities.TicketEntity;
import com.danielesteban.best_travel.domain.entities.TourEntity;
import com.danielesteban.best_travel.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class BestTravelApplication implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final FlyRepository flyRepository;
    private final TicketRepository ticketRepository;
    private final ReservationRepository reservationRepository;
    private final TourRepository tourRepository;
    private final CustomerRepository customerRepository;


    public BestTravelApplication(HotelRepository hotelRepository, FlyRepository flyRepository,
                                 TicketRepository ticketRepository, ReservationRepository reservationRepository,
                                 TourRepository tourRepository, CustomerRepository customerRepository) {
        this.hotelRepository = hotelRepository;
        this.flyRepository = flyRepository;
        this.ticketRepository = ticketRepository;
        this.reservationRepository = reservationRepository;
        this.tourRepository = tourRepository;
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BestTravelApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var fly = flyRepository.findById(14L).get();
        var hotel = hotelRepository.findById(7L).get();
        var ticket = ticketRepository.findById(UUID.fromString("12345678-1234-5678-2236-567812345678")).get();
        var reservation = reservationRepository.findById(UUID.fromString("32345678-1234-5678-1234-567812345678")).get();
//        var customer = customerRepository.findById("BBMB771012HMCRR022").get();

        log.info(String.valueOf(fly));
        log.info(String.valueOf(hotel));
        log.info(String.valueOf(ticket));
        log.info(String.valueOf(reservation));
//        log.info(String.valueOf(customer));

        this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(System.out::println);

        var flyByTicket = flyRepository.findByTicketId(UUID.fromString("12345678-1234-5678-2236-567812345678"));
//        System.out.println(flyByTicket);

        hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100)).forEach(System.out::println);
        hotelRepository.findByPriceIsBetween(BigDecimal.valueOf(100), BigDecimal.valueOf(150)).forEach(System.out::println);
        hotelRepository.findByRatingGreaterThan(3).forEach(System.out::println);

//        var hotel12 = hotelRepository.findByReservationsId(UUID.fromString("32345678-1234-5678-1234-567812345678"));
//        System.out.println(hotel12);

        var customer = customerRepository.findById("BBMB771012HMCRR022").orElseThrow();
        log.info("Client name: " + customer.getFullName());

        var tour12 = TourEntity.builder()
                .customer(customer).build();

        var ticket12 = TicketEntity.builder()
                .id(UUID.randomUUID())
                .price(fly.getPrice().multiply(BigDecimal.TEN))
                .arrivalDate(LocalDate.now())
                .departureDate(LocalDate.now())
                .purchaseDate(LocalDate.now())
                .customer(customer)
                .tour(tour12)
                .fly(fly)
                .build();

        var reservation12 = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .dateTimeReservation(LocalDateTime.now())
                .dateEnd(LocalDate.now().plusDays(2))
                .dateStart(LocalDate.now().plusDays(1))
                .hotel(hotel)
                .customer(customer)
                .tour(tour12)
                .totalDays(1)
                .price(hotel.getPrice().multiply(BigDecimal.TEN))
                .build();

        System.out.println("--- SAVING ---");
        tour12.addReservation(reservation12);
        tour12.updateReservations();
        tour12.addTicket(ticket12);
        tour12.updateTickets();

        this.tourRepository.save(tour12);
    }
}
