package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exeptions.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.exeptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryBalloonRepository;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;


    public BalloonServiceImpl(BalloonRepository balloonRepository,
                              ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return this.balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        if(text == null || text.isEmpty()) throw new IllegalArgumentException();

        return this.balloonRepository.findAllByNameOrDescription(text, text);
    }

    @Override
    public Balloon add(String text) {
        if(text == null || text.isEmpty()) throw new IllegalArgumentException();

        return this.balloonRepository.save(new Balloon(text));
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer m = this.manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.balloonRepository.deleteByName(name);

        //todo trebe cel object da se prati vo save() ili ne ? kako vo auds @29:50
        //@25:20 https://www.youtube.com/watch?v=rZeg6XxkijY ne se vrakja balonot
        //koj shto go kreiravme, tuku ona shto ni go vrakja save metodot od balloon repo

        return Optional.of(this.balloonRepository.save(new Balloon(name,description,m)));
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> edit(Long balloonId, String name, String description, Long manufacturerId) {
        Balloon b = this.balloonRepository
                .findById(balloonId)
                .orElseThrow(() -> new BalloonNotFoundException(balloonId));

        b.setName(name);
        b.setDescription(description);

        Manufacturer m = this.manufacturerRepository
                .findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        b.setManufacturer(m);

        return Optional.of(this.balloonRepository.save(b));
    }
}
