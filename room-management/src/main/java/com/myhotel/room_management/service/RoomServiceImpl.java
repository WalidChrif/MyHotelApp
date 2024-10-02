package com.myhotel.room_management.service;

import com.myhotel.room_management.mapper.RoomMapper;
import com.myhotel.room_management.entity.Room;
import com.myhotel.room_management.model.RoomDTO;
import com.myhotel.room_management.repository.RoomRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final RoomMapper roomMapper;
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

    public RoomServiceImpl(RoomRepo roomRepo, RoomMapper roomMapper, RabbitTemplate rabbitTemplate
    ) {
        this.roomRepo = roomRepo;
        this.roomMapper = roomMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public RoomDTO getRoomById(String id) {
        return roomMapper
                .convertToDTO(roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found")));
    }

    @Override
    public RoomDTO saveRoom(RoomDTO roomDTO) {
        LOGGER.info("sending through rabbitmq --> {}", roomDTO.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey,roomDTO);
        return roomMapper.convertToDTO(roomRepo.save(roomMapper.convertToEntity(roomDTO)));
    }

    @Override
    public RoomDTO updateRoom(String id, RoomDTO roomDTO) {
        Room room = roomRepo.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setPrice(roomDTO.getPrice());
        room.setCapacity(roomDTO.getCapacity());
        return roomMapper.convertToDTO(roomRepo.save(room));
    }

    @Override
    public List<RoomDTO> findAllRooms() {
        return roomMapper.convertToDTOs(roomRepo.findAll());
    }

    @Override
    public List<RoomDTO> findRoomsByCapacity(int capacity) {
        return roomMapper.convertToDTOs(roomRepo.findRoomsByCapacity(capacity));
    }

    @Override
    public List<RoomDTO> findRoomsByPriceBetween(int minPrice, int maxPrice) {
        return roomMapper.convertToDTOs(roomRepo.findRoomsByPriceBetween(minPrice, maxPrice));
    }

    @Override
    public List<RoomDTO> findRoomsByPriceLessThan(int maxPrice) {
        return roomMapper.convertToDTOs(roomRepo.findRoomsByPriceLessThan(maxPrice));
    }


}
