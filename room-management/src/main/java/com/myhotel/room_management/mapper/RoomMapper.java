package com.myhotel.room_management.mapper;

import com.myhotel.room_management.entity.Room;
import com.myhotel.room_management.model.RoomDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {
    
    public Room convertToEntity(RoomDTO roomDTO) {
        Room theRoom = new Room();
        theRoom.setId(roomDTO.getId());
        theRoom.setRoomNumber(roomDTO.getRoomNumber());
        theRoom.setCapacity(roomDTO.getCapacity());
        theRoom.setPrice(roomDTO.getPrice());
        return theRoom;
    }

    public RoomDTO convertToDTO(Room room) {
        RoomDTO theRoom = new RoomDTO();
        theRoom.setId(room.getId());
        theRoom.setRoomNumber(room.getRoomNumber());
        theRoom.setPrice(room.getPrice());
        theRoom.setCapacity(room.getCapacity());
        return theRoom;
    }

    public List<RoomDTO> convertToDTOs(List<Room> rooms) {
        return rooms.stream()
//                .map(room -> convertToDTO(room))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}