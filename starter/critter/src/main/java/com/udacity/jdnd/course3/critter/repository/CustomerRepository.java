package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    @Query("select c from Customer c left join fetch c.pets")
    List<Customer> findAll();
}
