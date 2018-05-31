package com.gosuccour.dao;

import org.springframework.data.repository.CrudRepository;

import com.gosuccour.entity.Car;

public interface ICarDao extends CrudRepository<Car,Long>{

}
