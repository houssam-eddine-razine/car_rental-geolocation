package com.example.car_rental.controller;


import com.example.car_rental.dto.BookACarDto;
import com.example.car_rental.dto.CarDto;
import com.example.car_rental.dto.SearchCarDto;
import com.example.car_rental.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> carDtoList = customerService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }

    @CrossOrigin(origins = "http://localhost:52132")
    @PostMapping("/car/book")
    public ResponseEntity<Void> bookACar(@RequestBody BookACarDto bookACarDto){
        boolean success = customerService.bookACar(bookACarDto);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
        CarDto carDto = customerService.getCarById(carId);
        if(carDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDto);
    }

    @GetMapping("/car/bookings/{userId}")
    public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar(@RequestBody SearchCarDto searchCarDto) {
        return ResponseEntity.ok(customerService.searchCar(searchCarDto));

    }


    @GetMapping("/car/bookings/receipt/{bookingId}")
    public ResponseEntity<byte[]> generateReceipt(@PathVariable Long bookingId) {
        Optional<byte[]> receiptData = customerService.generateReceipt(bookingId);
        if (receiptData.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "receipt.pdf");
            return new ResponseEntity<>(receiptData.get(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
