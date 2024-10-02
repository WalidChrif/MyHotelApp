package com.myhotel.booking_management.service;

import com.myhotel.booking_management.entity.Booking;
import com.myhotel.booking_management.mapper.BookingMapper;
import com.myhotel.booking_management.model.BookingDTO;
import com.myhotel.booking_management.proxies.RoomProxy;
import com.myhotel.booking_management.proxies.CustomerProxy;
import com.myhotel.booking_management.repository.BookingRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepo bookingRepo;
    private final CustomerProxy customerProxy;
    private final RoomProxy roomProxy;
    private final RestTemplate restTemplate;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingRepo bookingRepo
            , CustomerProxy customerProxy, RoomProxy roomProxy, RestTemplate restTemplate) {
        this.bookingMapper = bookingMapper;
        this.bookingRepo = bookingRepo;
        this.customerProxy = customerProxy;
        this.roomProxy = roomProxy;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<BookingDTO> getBookings() {
        return bookingMapper.convertToDTOs(bookingRepo.findAll());
    }

    @Override
    public List<BookingDTO> getBookingBetween(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<Booking> bookings = bookingRepo.findAll().stream()
                .filter(booking -> booking.getStartDate().isAfter(startDate)
                        && booking.getEndDate().isBefore(endDate))
                .collect(Collectors.toList());
        return bookingMapper.convertToDTOs(bookings);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        return bookingMapper
                .convertToDTO(bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found")));
    }

    @Override
    public BookingDTO saveBooking(BookingDTO bookingDTO) {
        validateRoom(bookingDTO.getRoomId());
        validateCustomer(bookingDTO.getCustomerId());
        return bookingMapper.convertToDTO(bookingRepo.save(bookingMapper.convertToEntity(bookingDTO)));
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        validateRoom(bookingDTO.getRoomId());
        validateCustomer(bookingDTO.getCustomerId());
        booking.setCustomerId(bookingDTO.getCustomerId());
        booking.setRoomId(bookingDTO.getRoomId());
        booking.setStartDate(LocalDate.parse(bookingDTO.getStartDate()));
        booking.setEndDate(LocalDate.parse(bookingDTO.getEndDate()));
        return bookingMapper.convertToDTO(bookingRepo.save(booking));
    }

    public void validateCustomer(Long id) {
        Object customerObj = customerProxy.getCustomerById(id);
        if (customerObj == null) {
            throw new RuntimeException("Customer not found");
        }
    }

    public void validateRoom(String id) {
        Object roomObj = roomProxy.getRoomById(id);
        if (roomObj == null) {
            throw new RuntimeException("Room not found");
        }
    }
    @Override
    public Object testGetCustomer(Long id) {
        Object customerObj = customerProxy.getCustomerById(id);
        if (customerObj == null) {
            throw new RuntimeException("Customer not found by test");
        }
        return customerObj;
    }

    public Object testGetCustomerByRest(Long id) {
        HashMap<String, Long> uriVariables =  new HashMap<>();
        uriVariables.put("id",id);
        ResponseEntity<Object> customerObj =
                restTemplate.getForEntity("http://localhost:8100/api/v1/customers/{id}", Object.class, uriVariables);
        if (customerObj.getBody() == null) {
            throw new RuntimeException("Customer not found by RestTemplate");
        }
        return customerObj;
    }

}
