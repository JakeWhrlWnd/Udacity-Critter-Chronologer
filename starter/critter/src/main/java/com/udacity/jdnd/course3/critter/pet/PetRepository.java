package com.udacity.jdnd.course3.critter.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public class PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByCustomerId(Long customerId);
    @Override
    @Query("select p from Pet p left join fetch p.customer")
    List<Pet> findAll();
}
