package com.erp.tferp.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.text.MessageFormat;
import com.erp.tferp.repositories.ILogbookRepository;
import com.erp.tferp.entities.LogbookEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogbookService {

  @Autowired
  private ILogbookRepository iLogbookRepository;

  public List<LogbookEntity> findAll() {
    log.debug("Find all");
    return this.iLogbookRepository.findAll();
  }

  public LogbookEntity findById(Integer id){
    log.debug("Find by Id");
    Optional<LogbookEntity> oplogbookEntity = this.iLogbookRepository.findById(id);
    return oplogbookEntity.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public LogbookEntity save(LogbookEntity logbookEntity){
    log.debug("Save");
    try {
      return this.iLogbookRepository.save(logbookEntity);
    } catch (IllegalArgumentException e) {
      log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
      throw e;
    }
  }

  public LogbookEntity update(Integer id, LogbookEntity logbookEntity) {
        log.debug("update");
      try {
        logbookEntity.setId(id);
        return this.save(logbookEntity);
      } catch (IllegalArgumentException e) {
        log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
        throw e;
      }
  }

  public void delete(Integer id){
    log.debug("delete");
    LogbookEntity logbookEntity = this.findById(id);
    this.iLogbookRepository.delete(logbookEntity);
  }

}
