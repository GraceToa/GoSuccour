package com.gosuccour.dao;

import org.springframework.data.repository.CrudRepository;

import com.gosuccour.entity.Revision;

public interface IRevisionDao extends CrudRepository<Revision, Long>{

}
