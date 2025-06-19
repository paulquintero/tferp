package com.erp.tferp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erp.tferp.services.LogbookService;
import com.erp.tferp.entities.LogbookEntity;

import java.util.List;

@RestController
@RequestMapping(path ="logbook")
public class LogbookController {

  @Autowired
  private LogbookService logbookService;

  @GetMapping
  public ResponseEntity<List<LogbookEntity>> findAll() {
    return  ResponseEntity.ok(this.logbookService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<LogbookEntity> findById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(this.logbookService.findById(id));
  }

  @PostMapping
  public ResponseEntity<LogbookEntity> save(@RequestBody LogbookEntity logbookEntity){
    return  ResponseEntity.ok(this.logbookService.save(logbookEntity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<LogbookEntity> update(@PathVariable("id") Integer id, @RequestBody LogbookEntity logbookEntity){
    return  ResponseEntity.ok(this.logbookService.update(id, logbookEntity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
    this.logbookService.delete(id);
    return ResponseEntity.ok("Success");
  }

}
