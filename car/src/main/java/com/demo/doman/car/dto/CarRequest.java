/**
 * 
 */
package com.demo.doman.car.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author pavan
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotNull (message = "Name shoud not empty")
	private String name;
	private String model;
	private Date mfDate;
	
	/*
	 * public Date getMfDate() { return mfDate; } public void setMfDate(Date mfDate)
	 * { this.mfDate = mfDate; } public String getName() { return name; } public
	 * void setName(String name) { this.name = name; } public String getModel() {
	 * return model; } public void setModel(String model) { this.model = model; }
	 */
}
