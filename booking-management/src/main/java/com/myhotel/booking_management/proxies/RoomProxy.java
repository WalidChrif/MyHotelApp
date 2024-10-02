package com.myhotel.booking_management.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "room-management")
public interface RoomProxy {


    @GetMapping("/api/v1/rooms/{id}")
    public Object getRoomById(@PathVariable String id);



}
