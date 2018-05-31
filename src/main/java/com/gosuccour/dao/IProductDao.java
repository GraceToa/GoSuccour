package com.gosuccour.dao;


import org.springframework.data.repository.CrudRepository;

import com.gosuccour.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long>{



}
