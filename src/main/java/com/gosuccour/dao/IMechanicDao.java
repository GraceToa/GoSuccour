package com.gosuccour.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gosuccour.entity.Mechanic;

public interface IMechanicDao extends PagingAndSortingRepository<Mechanic, Long>{

}
