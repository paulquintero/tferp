package com.erp.tferp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.tferp.entities.PaperworkEntity;

public interface IPaperworkRepository extends JpaRepository<PaperworkEntity , Integer> {

}
