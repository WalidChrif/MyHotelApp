package com.myhotel.room_management.repository;

import com.myhotel.room_management.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//public interface RoomRepo extends JpaRepository<Room, Long> {
public interface RoomRepo extends MongoRepository<Room, String> {

    List<Room> findRoomsByCapacity(int capacity);
    List<Room> findRoomsByPriceBetween(int minPrice, int maxPrice);
    List<Room> findRoomsByPriceLessThan(int maxPrice);
}
