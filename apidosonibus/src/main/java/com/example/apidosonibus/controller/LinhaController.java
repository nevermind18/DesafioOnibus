package com.example.apidosonibus.controller;

import com.example.apidosonibus.entity.Linha;
import com.example.apidosonibus.interfaces.LinhaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/linha")
public class LinhaController {

    @Autowired
    LinhaServiceInterface linhaServiceInterface;

    @PostMapping("/consumirApi")
    public List<Linha> consumirApi() throws InterruptedException, IOException {

        return linhaServiceInterface.consumirApi();
    }

    @PostMapping("/")
    public ResponseEntity<Linha> cadastrar(@RequestBody Linha linha){

        return linhaServiceInterface.cadastrar(linha);
    }

    @GetMapping("/")
    public List<Linha> buscarTodos(){

        return linhaServiceInterface.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linha> buscarPorId(@PathVariable long id) throws IOException, InterruptedException {

        return linhaServiceInterface.buscarPorId(id);
    }

    @PutMapping("/")
    public ResponseEntity<Linha> alterar(@RequestBody Linha linha) {

        return linhaServiceInterface.alterar(linha);
    }

    @DeleteMapping("/")
    public ResponseEntity<Linha> deletar(@RequestBody Linha linha) {

        return linhaServiceInterface.deletar(linha);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Linha>> buscarPorNome(@PathVariable String nome){

        return linhaServiceInterface.buscarPorNome(nome);
    }

    @GetMapping("/distancia")
    public ResponseEntity<List<Linha>> buscarPorDistancia(@RequestBody List<String> distancia){

        return linhaServiceInterface.buscarPorDistancia(distancia);
    }

}
