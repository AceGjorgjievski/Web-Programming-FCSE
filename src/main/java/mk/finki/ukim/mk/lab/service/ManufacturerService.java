package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    public List<Manufacturer> findAll();
    Optional<Manufacturer> save(String name, String country, String address);

    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name, String address);
}
