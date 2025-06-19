package com.erp.tferp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.tferp.entities.LogbookEntity;

public interface ILogbookRepository extends JpaRepository<LogbookEntity , Integer> {

}
