package org.mongo.controller;

import org.mongo.api.GarageRepository;
import org.mongo.entity.Garage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garages")
public class GarageController {

    private final GarageRepository garageRepository;

    public GarageController(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    @PostMapping
    public Garage insert(@RequestBody Garage garage){
        return garageRepository.insert(garage);
    }

    @DeleteMapping
    public void delete(@RequestBody Garage garage){
        garageRepository.delete(garage);
    }

    @PutMapping
    public Garage update(@RequestBody Garage garage){
        return garageRepository.save(garage);
    }

    @GetMapping
    public List<Garage> findAll(){
        return garageRepository.findAll();
    }
}
