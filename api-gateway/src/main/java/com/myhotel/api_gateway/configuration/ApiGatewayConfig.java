package com.myhotel.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/api/v1/rooms/**").uri("lb://room-management"))
                .route(p -> p.path("/api/v1/customers/**").uri("lb://customer-management"))
                .route(p -> p.path("/api/v1/bookings/**").uri("lb://booking-management"))
                //we add filters.stripPrefix cuz without it, it'll call http://localhost:8761/eureka
//                .route(p -> p.path("/eureka").filters(f -> f.stripPrefix(1)).uri("http://localhost:8761"))
                .route(p -> p.path("/eureka").filters(f -> f.setPath("/")).uri("http://naming-server:8761"))
                .route(p -> p.path("/eureka/**").uri("http://naming-server:8761"))
                .build();
    }

}
