package mk.finki.ukim.mk.lab.repository.impl;


import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.awt.dnd.DropTarget;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {



    public List<Balloon> findAllBalloons() {
        return DataHolder.balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloonList.stream().filter(i ->i.getName().equals(text) ||
                i.getDescription().equals(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> findByManufacturerId(Long id) {
        return DataHolder.balloonList
                .stream()
                .filter(i -> i.getManufacturer().getId().equals(id))
                .findFirst();
    }

    public Optional<Balloon> findByDescription(String description) {
        return DataHolder.balloonList
                .stream()
                .filter(i -> i.getDescription().equals(description))
                .findFirst();
    }

    public Balloon add(String text) {
        if(text == null || text.isEmpty()) throw new IllegalArgumentException();

        Balloon b = new Balloon(text);

        DataHolder.balloonList.add(b);

        return b;
    }

    public Optional<Balloon> save(String name,
                                  String description,
                                  Manufacturer manufacturer) {
        DataHolder.balloonList.removeIf(i -> i.getName().equals(name));
        Balloon b = new Balloon(name, description, manufacturer);

        DataHolder.balloonList.add(b);


        return Optional.of(b);

    }

    public void delete(Long id) {
        DataHolder.balloonList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloonList
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }



}
