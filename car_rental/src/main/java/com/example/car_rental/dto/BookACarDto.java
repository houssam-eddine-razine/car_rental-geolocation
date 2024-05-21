package com.example.car_rental.dto;

import com.example.car_rental.enums.BookCarStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookACarDto {

    private Long id;


    private Date fromDate;

    @Getter
    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    @Setter
    @Getter
    private Long carId;

    @Getter
    private Long userId;

    private String username;

    private String email;


//    public Date getFromDate() {
//        return fromDate;
//    }
}
