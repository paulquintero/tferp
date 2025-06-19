package com.erp.tferp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Logbook")
@Getter
@Setter
public class LogbookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String economicNumber;
	private String vin;
	private String insurancePolicy;
	private String deliveryDate;
	private String payDate;
	private String logbookEndDate;
	private String link;
	private Integer paperworkId;
	private String creationDate;
    
}
