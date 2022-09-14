package com.demo.doman.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.doman.car.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
		
	public User save(User car);
	public User findById(int id);
	public User findByName(String name);

  
}
