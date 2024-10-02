package com.myhotel.room_management.service;

import com.myhotel.room_management.model.RoomDTO;

import java.util.List;

public interface RoomService {

    RoomDTO getRoomById(String id);
    RoomDTO saveRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(String id, RoomDTO roomDTO);
    List<RoomDTO> findAllRooms();
    List<RoomDTO> findRoomsByCapacity(int capacity);
    List<RoomDTO> findRoomsByPriceBetween(int minPrice, int maxPrice);
    List<RoomDTO> findRoomsByPriceLessThan(int maxPrice);

}
