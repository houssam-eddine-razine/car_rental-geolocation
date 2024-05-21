package com.example.car_rental.repository;

import com.example.car_rental.dto.BookACarDto;
import com.example.car_rental.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar,Long>{

    List<BookACar> findAllByUserId(Long userId);
}
