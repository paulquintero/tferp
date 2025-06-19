package com.erp.tferp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erp.tferp.services.PaperworkService;
import com.erp.tferp.entities.PaperworkEntity;

import java.util.List;

@RestController
@RequestMapping(path ="paperworks")
public class PaperworkController {

  @Autowired
  private PaperworkService paperworkService;

  @GetMapping
  public ResponseEntity<List<PaperworkEntity>> findAll() {
    return  ResponseEntity.ok(this.paperworkService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaperworkEntity> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.paperworkService.findById(id));
  }

  @PostMapping
  public ResponseEntity<PaperworkEntity> save(@RequestBody PaperworkEntity paperworkEntity){
    return  ResponseEntity.ok(this.paperworkService.save(paperworkEntity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PaperworkEntity> update(@PathVariable("id") Integer id, @RequestBody PaperworkEntity paperworkEntity){
    return  ResponseEntity.ok(this.paperworkService.update(id, paperworkEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    this.paperworkService.delete(id);
    return ResponseEntity.ok("Success");
  }

}
