package com.gosuccour.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gosuccour.entity.Client;

/**
 * CrudRepository nos facilita el crud
 * 
 * @author GraceToa
 *
 */
//public interface IClientDao extends CrudRepository<Client, Long> {
//
//}

/**
 * lesson 
 * PagingAndSortingRepository hereda de CrudRepository mantenemos los mismos metodos
 * ademas incluye los métodos Sort y Pageable para paginación
 *
 */
public interface IClientDao extends PagingAndSortingRepository<Client, Long> {

}