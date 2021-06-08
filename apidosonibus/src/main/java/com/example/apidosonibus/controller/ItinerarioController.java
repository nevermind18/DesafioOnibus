package com.example.apidosonibus.controller;

import com.example.apidosonibus.entity.Itinerario;
import com.example.apidosonibus.interfaces.ItinerarioSerciveInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/itinerario")
public class ItinerarioController {

    @Autowired
    ItinerarioSerciveInterface itinerarioSerciveInterface;

    @PostMapping("/")
    public ResponseEntity<Itinerario> cadastrar(@RequestBody Itinerario itinerario){

        return itinerarioSerciveInterface.cadastrar(itinerario);
    }

    @GetMapping("/")
    public List<Itinerario> buscarTodos(){

        return itinerarioSerciveInterface.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerario> buscarPorId(@PathVariable long id) throws IOException, InterruptedException {

        return itinerarioSerciveInterface.buscarPorId(id);
    }

    @PutMapping("/")
    public ResponseEntity<Itinerario> alterar(@RequestBody Itinerario itinerario) {

        return itinerarioSerciveInterface.alterar(itinerario);
    }

    @DeleteMapping("/")
    public ResponseEntity<Itinerario> deletar(@RequestBody Itinerario itinerario) {

        return itinerarioSerciveInterface.deletar(itinerario);
    }

}
