package mk.finki.ukim.mk.lab.repository.impl;


import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturers.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public Optional<Manufacturer> findByName(String name) {
        return DataHolder.manufacturers.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst();
    }

    public Optional<Manufacturer> findByCountry(String country) {
        return DataHolder.manufacturers.stream()
                .filter(i -> i.getCountry().equals(country))
                .findFirst();
    }
}
