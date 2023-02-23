package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {


    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);

    Balloon add(String text);
    Optional<Balloon> findById(Long id);

    Optional<Balloon> save(String name, String description, Long manufacturerId);
    void deleteById(Long id);

    Optional<Balloon> edit(Long balloonId, String name, String description, Long manufacturerId);
}
