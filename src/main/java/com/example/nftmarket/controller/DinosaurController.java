package com.example.nftmarket.controller;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.repository.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dinosaurs")
public class DinosaurController {

    private final DinosaurRepository dinosaurRepository;

    @Autowired
    public DinosaurController(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    // 创建（增加）恐龙
    @PostMapping
    public ResponseEntity<Dinosaur> createDinosaur(@RequestBody Dinosaur dinosaur) {
        try {
            Dinosaur savedDinosaur = dinosaurRepository.save(dinosaur);
            return new ResponseEntity<>(savedDinosaur, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 查询所有恐龙
    @GetMapping("/all")
    public ResponseEntity<List<Dinosaur>> findAllDinosaurs() {
        try {
            List<Dinosaur> dinosaurs = (List<Dinosaur>) dinosaurRepository.findAll();
            if (dinosaurs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dinosaurs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据类型查询恐龙
    @GetMapping
    public ResponseEntity<List<Dinosaur>> findDinosaursByType(@RequestParam String dinosaurType) {
        try {
            List<Dinosaur> dinosaurs = dinosaurRepository.findByDinosaurType(dinosaurType);
            if (dinosaurs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dinosaurs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 根据ID查找恐龙
    @GetMapping("/{id}")
    public ResponseEntity<Dinosaur> findDinosaurById(@PathVariable String id) {
        Optional<Dinosaur> dinosaurData = dinosaurRepository.findById(id);

        return dinosaurData.map(dinosaur -> new ResponseEntity<>(dinosaur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
