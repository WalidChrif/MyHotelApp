package com.myhotel.booking_management.repository;

import com.myhotel.booking_management.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
