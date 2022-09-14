/**
 * 
 */
package com.demo.doman.car.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.demo.doman.car.dto.CarRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Car Entiry
 * @author pavan
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String model;
	private Date mfDate;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Car(CarRequest carRequest) {
		super();
		name = carRequest.getName();
		model = carRequest.getModel();
		mfDate = carRequest.getMfDate();
	}




	/*
	 * public Date getMfDate() { return mfDate; } public void setMfDate(Date mfDate)
	 * { this.mfDate = mfDate; } public String getName() { return name; } public
	 * void setName(String name) { this.name = name; } public String getModel() {
	 * return model; } public void setModel(String model) { this.model = model; }
	 */

}
