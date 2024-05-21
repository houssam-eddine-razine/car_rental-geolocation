package com.example.car_rental.services.customer;

import com.example.car_rental.dto.BookACarDto;
import com.example.car_rental.dto.CarDto;
import com.example.car_rental.dto.CarDtoListDto;
import com.example.car_rental.dto.SearchCarDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(Long carId);

    List<BookACarDto> getBookingsByUserId(Long userId);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);

    Optional<byte[]> generateReceipt(Long bookingId);

}
