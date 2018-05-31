package com.gosuccour.dao;

import org.springframework.data.repository.CrudRepository;

import com.gosuccour.entity.Facture;

public interface IFactureDao extends CrudRepository<Facture, Long> {

}
