package com.example.nftmarket.controller;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.repository.elasticsearch.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/dinosaurs")
public class DinosaurController {

    private final DinosaurRepository dinosaurRepository;

    @Autowired
    public DinosaurController(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    // 查询恐龙
    @GetMapping("/search")
    public String searchDinosaurs(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String sex,
            Model model
    ) {
        List<Dinosaur> dinosaurs;
        if (type != null) {
            dinosaurs = dinosaurRepository.findByDinosaurType(type);
        } else if (color != null) {
            dinosaurs = dinosaurRepository.findByDinosaurColor(color);
        } else if (sex != null) {
            dinosaurs = dinosaurRepository.findByDinosaurSex(sex);
        } else {
            dinosaurs = (List<Dinosaur>) dinosaurRepository.findAll();
        }
        model.addAttribute("dinosaurs", dinosaurs);
        return "market";
    }

    // 增加一个恐龙
    @PostMapping
    public ResponseEntity<Dinosaur> addDinosaur(@RequestBody Dinosaur dinosaur) {
        Dinosaur savedDinosaur = dinosaurRepository.save(dinosaur);
        return new ResponseEntity<>(savedDinosaur, HttpStatus.CREATED);
    }
}
