package com.api.ecommerce.usecase.saleauthorize.repository;

import com.api.ecommerce.entity.SaleTable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<SaleTable, String> {
}
