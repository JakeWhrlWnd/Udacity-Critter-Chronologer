package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository  customerRepository;

    @Transactional
    public Optional<Pet> find(Long id) {
        return petRepository.findById(id);
    }
    public List<Pet> findAll() {
        return petRepository.findAll();
    }
    public List<Pet> getPetsByCustomerId(long customerId) {
        return petRepository.findAllByCustomerId(customerId);
    }
    public Pet savePetWithOwnerId(Pet pet, long ownerId) {
        Optional<Customer> customerOptional = customerRepository.findById(ownerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            pet.setCustomer(customer);
            pet = petRepository.save(pet);
            customerRepository.save(customer);
        }
        return pet;
    }
}
