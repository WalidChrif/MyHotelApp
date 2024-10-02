package com.myhotel.booking_management.controller;

import com.myhotel.booking_management.entity.Booking;
import com.myhotel.booking_management.model.BookingDTO;
import com.myhotel.booking_management.service.BookingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final Logger logger = LoggerFactory.getLogger(BookingController.class);


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingDTO> findAllBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("/{id}")
    BookingDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/between/{start}/{end}")
    public List<BookingDTO> getBookingBetween(@PathVariable String start, @PathVariable String end) {
        return bookingService.getBookingBetween(start, end);
    }

    @PostMapping
    public BookingDTO addBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.saveBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id, bookingDTO);
    }


    @GetMapping("/cus/{id}")
//    @TimeLimiter(name = "booking", fallbackMethod = "fallbackTimeLimiter")
//    @CircuitBreaker(name = "booking")
//    @Retry(name = "booking")
    public Object testGetCustomer(@PathVariable Long id) {
        logger.info("running testGetCustomer");
        return bookingService.testGetCustomer(id);
    }

    public CompletableFuture<Object> testGetCustomerForTimeLimiter(@PathVariable Long id) {
        logger.info("running testGetCustomerForTimeLimiter");
        return CompletableFuture.supplyAsync(() -> bookingService.testGetCustomer(id));
    }

    public Object fallback(RuntimeException runtimeException) {
        logger.info("running fallback method");
        return new Booking(Long.getLong("1"), LocalDate.now(), LocalDate.now(), "1", Long.parseLong("1"));
    }

    public Object fallbackRetry(RuntimeException runtimeException) {
        logger.info("running retry fallback method");
        return new Booking(Long.getLong("1"), LocalDate.now(), LocalDate.now(), "1", Long.parseLong("1"));
    }

    public CompletableFuture<Object> fallbackTimeLimiter(RuntimeException runtimeException) {
        logger.info("running TimeLimiter fallback method");
        return CompletableFuture.supplyAsync(() -> new Booking(Long.getLong("1"), LocalDate.now(), LocalDate.now(), "1", Long.parseLong("1")));
    }

    @GetMapping("/cus/{id}/rest")
    public Object testGetCustomerByRest(@PathVariable Long id) {
        logger.info("running RestTemplate testGetCustomer");
        return bookingService.testGetCustomerByRest(id);
    }


}
