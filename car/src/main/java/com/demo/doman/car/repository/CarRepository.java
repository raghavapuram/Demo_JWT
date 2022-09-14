package com.demo.doman.car.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.doman.car.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
		
	public Car save(Car car);
	public Optional<Car> findById(int id);
	public List<Car> findAll();
  
}
