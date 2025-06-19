package com.erp.tferp.services;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.text.MessageFormat;
import com.erp.tferp.repositories.IPaperworkRepository;
import com.erp.tferp.entities.PaperworkEntity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaperworkService {

  @Autowired
  private IPaperworkRepository iPaperworkRepository;

  public List<PaperworkEntity> findAll() {
    log.debug("Find all");
    return this.iPaperworkRepository.findAll();
  }

  public PaperworkEntity findById(Integer id){
    log.debug("Find by Id");
    Optional<PaperworkEntity> oppaperworkEntity = this.iPaperworkRepository.findById(id);
    return oppaperworkEntity.orElseThrow(() -> new RuntimeException("Not found"));
  }

  public PaperworkEntity save(PaperworkEntity paperworkEntity){
    log.debug("Save");
    try {
      return this.iPaperworkRepository.save(paperworkEntity);
    } catch (IllegalArgumentException e) {
      log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
      throw e;
    }
  }

  public PaperworkEntity update(Integer id, PaperworkEntity paperworkEntity) {
        log.debug("update");
      try {
        paperworkEntity.setId(id);
        return this.save(paperworkEntity);
      } catch (IllegalArgumentException e) {
        log.error(MessageFormat.format("Error produced while saving the information: {0}",e.getMessage()));
        throw e;
      }
  }

  public void delete(Integer id){
    log.debug("delete");
    PaperworkEntity paperworkEntity = this.findById(id);
    this.iPaperworkRepository.delete(paperworkEntity);
  }

}
