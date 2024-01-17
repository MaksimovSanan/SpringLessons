package ru.maksimov.RESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maksimov.RESTApp.models.RentalObject;
import ru.maksimov.RESTApp.repositories.RentalObjectRepository;
import ru.maksimov.RESTApp.util.exceptions.BookNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RentalObjectService {
    private final RentalObjectRepository rentalObjectRepository;

    @Autowired
    public RentalObjectService(RentalObjectRepository rentalObjectRepository) {
        this.rentalObjectRepository = rentalObjectRepository;
    }

    public List<RentalObject> findAll() {
        return rentalObjectRepository.findAll();
    }

    public RentalObject findById(int id) {
        return rentalObjectRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Transactional
    public void save(RentalObject rentalObject) {
        rentalObjectRepository.save(rentalObject);
    }

    @Transactional
    public void update(int id, RentalObject rentalObject) {
        rentalObject.setId(id);
        rentalObjectRepository.save(rentalObject);
    }
    @Transactional
    public void delete(RentalObject rentalObject) {
        rentalObjectRepository.delete(rentalObject);
    }
}
