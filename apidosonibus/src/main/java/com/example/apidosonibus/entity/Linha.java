package com.example.apidosonibus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Linha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String codigo;

    @OneToMany
    @JoinColumn(name = "id_nome")
    private List<Itinerario> itinerarios;
}
