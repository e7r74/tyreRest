package com.jpa.tyre.controller;

import com.jpa.tyre.dto.TyreDto;
import com.jpa.tyre.model.Tyre;
import com.jpa.tyre.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("tyre")
public class TyreController {
    private final TyreService tyreService;

    @GetMapping
    public ResponseEntity<?> getAllTyre(){
        List<TyreDto> tyres=tyreService.getAllTyres();
        if (tyres.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(tyres,HttpStatus.FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name ="id") Long id){
        TyreDto tyre =tyreService.getById(id);
        if (Objects.isNull(tyre)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(tyre, HttpStatus.OK);
        }
    }
    @PostMapping("/addtyre")
    public ResponseEntity<?> addTyre(@RequestBody TyreDto tyreDto){
       TyreDto addTyre= tyreService.addTyre(tyreDto);
       return new ResponseEntity<>(addTyre, HttpStatus.CREATED);
    }
    @PutMapping("/updatetyre/{id}")
    public ResponseEntity<?> updateTyre(@PathVariable(name="id") Long id,
                                        @RequestBody TyreDto tyreDto){
        TyreDto newTyre= tyreService.updateTyre(id,tyreDto);
        if (Objects.isNull(newTyre)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(newTyre,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTyre(@PathVariable(name="id") Long id){
        boolean tyre=tyreService.deleteTyre(id);
        if (tyre){
            return new ResponseEntity<>(HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
