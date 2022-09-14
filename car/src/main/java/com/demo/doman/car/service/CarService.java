package com.demo.doman.car.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.doman.car.dto.CarRequest;
import com.demo.doman.car.entity.Car;
import com.demo.doman.car.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CarService {

	@Autowired
	CarRepository carRepository;

	public Car saveCar(CarRequest carRequest) {
		Car car = new Car(carRequest);
		return carRepository.save(car);

	}

	public Optional<Car> getCar(int id) { 
		Optional<Car> car = carRepository.findById(id); 
		return car;
	}

	public List<Car> getAllCars() { 
		log.info(" getAllCars" );
		return carRepository.findAll(); 
	}



}
