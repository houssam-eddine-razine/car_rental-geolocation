package com.example.car_rental.dto;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class CarDtoListDto {

    private String brand;

    private String type;

    private String transmission;

    private String color;

    @Setter
    private List<CarDto> carDtoList;
}
