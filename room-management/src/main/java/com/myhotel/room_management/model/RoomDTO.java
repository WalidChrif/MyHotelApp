package com.myhotel.room_management.model;


public class RoomDTO {

    private String id;
    private int roomNumber;
    private int capacity;
    private double price;

    public RoomDTO() {
    }

    public RoomDTO(String id, int roomNumber, boolean availability, int capacity, double price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id='" + id + '\'' +
                ", roomNumber=" + roomNumber +
                ", capacity=" + capacity +
                ", price=" + price +
                '}';
    }
}
