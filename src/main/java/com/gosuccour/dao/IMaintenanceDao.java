package com.gosuccour.dao;

import org.springframework.data.repository.CrudRepository;

import com.gosuccour.entity.Maintenance;

public interface IMaintenanceDao extends CrudRepository<Maintenance, Long>{

}
