package com.demo.doman.car.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.doman.car.dto.CarRequest;
import com.demo.doman.car.entity.Car;
import com.demo.doman.car.service.CarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cars")
@Slf4j
/**
 *  The Car Controller
 * @author pavan
 *
 */
public class CarController {

	
	@Autowired
	private CarService carService;

	@PostMapping("/")
	public Car saveCar(@Valid @RequestBody CarRequest  car) {

		log.info(" Save Car details, " + car);
		return carService.saveCar(car);

	}

	@GetMapping("/{id}")
	@Min(3)
	public ResponseEntity<?> getCar(@PathVariable("id") int id ) {
		
		log.info(" Get Car details of id " + id);
		Optional<Car> car = carService.getCar(id);
		if (car.isEmpty()) {
			return ResponseEntity.badRequest().body(" id is not valid ");
		}
		
		return ResponseEntity.badRequest().body(car.get());
	}

	@GetMapping("/findAll")
	public List<Car> findAll() {

		return carService.getAllCars();
	}
}
