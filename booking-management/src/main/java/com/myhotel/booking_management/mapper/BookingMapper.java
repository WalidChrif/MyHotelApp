package com.myhotel.booking_management.mapper;

import com.myhotel.booking_management.entity.Booking;
import com.myhotel.booking_management.model.BookingDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {
    
    public Booking convertToEntity(BookingDTO bookingDTO) {
        Booking theBooking = new Booking();
        theBooking.setId(bookingDTO.getId());
        theBooking.setStartDate(LocalDate.parse(bookingDTO.getStartDate()));
        theBooking.setEndDate(LocalDate.parse(bookingDTO.getEndDate()));
        theBooking.setCustomerId(bookingDTO.getCustomerId());
        theBooking.setRoomId(bookingDTO.getRoomId());
        return theBooking;
    }

    public BookingDTO convertToDTO(Booking booking) {
        BookingDTO theBooking = new BookingDTO();
        theBooking.setId(booking.getId());
        theBooking.setStartDate(booking.getStartDate().toString());
        theBooking.setEndDate(booking.getEndDate().toString());
        theBooking.setCustomerId(booking.getCustomerId());
        theBooking.setRoomId(booking.getRoomId());
        return theBooking;
    }

    public List<BookingDTO> convertToDTOs(List<Booking> bookings) {
        return bookings.stream()
//                .map(booking -> convertToDTO(booking))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}