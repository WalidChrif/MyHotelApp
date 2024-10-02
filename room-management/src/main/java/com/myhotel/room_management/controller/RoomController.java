package com.myhotel.room_management.controller;

import com.myhotel.room_management.model.RoomDTO;
import com.myhotel.room_management.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDTO> findAllRooms() {
        return roomService.findAllRooms();
    }

    @GetMapping("/{id}")
    public RoomDTO findRoomById(@PathVariable String id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/price-between/{min-price}/{max-price}")
    public List<RoomDTO> findRoomsByPriceBetween(
            @PathVariable("min-price") int minPrice
            , @PathVariable(name = "max-price") int maxPrice) {
        System.out.println("min-price " + minPrice + ", max-price " + maxPrice);
        return roomService.findRoomsByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/max-price/{max-price}")
    public List<RoomDTO> findRoomsByPriceLessThan(@PathVariable(name = "max-price") int maxPrice) {
        return roomService.findRoomsByPriceLessThan(maxPrice);
    }

    @GetMapping("/capacity/{capacity}")
    public List<RoomDTO> findRoomsByCapacity(@PathVariable int capacity) {
        return roomService.findRoomsByCapacity(capacity);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.saveRoom(roomDTO);
    }

    @PutMapping
    public RoomDTO updateRoom(@PathVariable String id, @RequestBody RoomDTO roomDTO) {
        return roomService.updateRoom(id, roomDTO);
    }


}
