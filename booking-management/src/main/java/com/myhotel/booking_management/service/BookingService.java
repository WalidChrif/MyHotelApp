package com.myhotel.booking_management.service;

import com.myhotel.booking_management.entity.Booking;
import com.myhotel.booking_management.model.BookingDTO;

import java.util.List;

public interface BookingService {

    List<BookingDTO> getBookings();
    BookingDTO getBookingById(Long id);
    BookingDTO saveBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);
    List<BookingDTO> getBookingBetween(String start, String end);
    Object testGetCustomer(Long id);
    Object testGetCustomerByRest(Long id);
}
