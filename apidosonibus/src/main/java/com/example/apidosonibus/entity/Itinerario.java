package com.example.apidosonibus.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Itinerario{



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double lat;
    private Double lng;


}
