package com.example.apidosonibus.service;

import com.example.apidosonibus.entity.Linha;
import com.example.apidosonibus.interfaces.ItinerarioSerciveInterface;
import com.example.apidosonibus.interfaces.LinhaServiceInterface;
import com.example.apidosonibus.repository.LinhaRepository;
import com.example.apidosonibus.rn.LinhaRN;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinhaService implements LinhaServiceInterface {

    @Autowired
    LinhaRepository linhaRepository;

    @Autowired
    ItinerarioSerciveInterface itinerarioServiceInterface;

    @Autowired
    LinhaRN linhaRN;

    //recebe uma api contendo todas as Linhas
    public List<Linha> consumirApi(){

        RestTemplate linhaRestTemplate = new RestTemplate();

        String linhaResponse =
                linhaRestTemplate.getForObject(
                        "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o",
                        String.class);



        List<Linha> linhas = new ArrayList<>();
        Type type = new TypeToken<List<Linha>>(){}.getType();
        Gson g = new Gson();
        linhas.addAll(g.fromJson(linhaResponse.toString(), type));

        for (Linha linha:linhas) {

            linhaRepository.saveComId(linha.getId(),linha.getCodigo(), linha.getNome());
        }
        return linhaRepository.findAll();
    }

    //cadastra nova linha
    public ResponseEntity<Linha> cadastrar(Linha linha){

        ResponseEntity<Linha> responseEntity = null;

        if (linhaRN.cadastrar(linha) != null){
            return linhaRN.cadastrar(linha);
        }
        linhaRepository.save(linha);
        return responseEntity.status(201).body(linha);
    }

    //busca todos os Linhas do banco
    public List<Linha> buscarTodos(){

        return linhaRepository.findAll();
    }

    //busca usando a id um Linha
    public ResponseEntity<Linha> buscarPorId(long id) throws IOException {


        ResponseEntity<Linha> responseEntity = null;

        Linha linha = linhaRepository.findById(id).orElse(null);

        if(linha == null){
            return responseEntity.notFound().build();
        }


        if(linha.getItinerarios().size() <= 0){
            linha.setItinerarios(itinerarioServiceInterface.adicionarApiPelaExterna(linha));
            System.out.println(linha.getItinerarios().get(0).getId());
            return responseEntity.status(201).body(linhaRepository.save(linha));
        }

        return responseEntity.ok(linha);
    }

    //altera um Linha no banco
    public ResponseEntity<Linha> alterar(Linha linhaNova){

        ResponseEntity<Linha> responseEntity = null;

        Optional<Linha> linhaVelha = linhaRepository.findById(linhaNova.getId());

        if(!linhaVelha.isPresent()){
            return responseEntity.notFound().build();
        }

        if (linhaRN.cadastrar(linhaNova) != null){
            return linhaRN.cadastrar(linhaNova);
        }

        return responseEntity.status(201).body(linhaRepository.save(linhaNova));
    }

    //deleta uma linha e seus itinerarios
    public ResponseEntity<Linha> deletar(Linha linha){

        ResponseEntity<Linha> responseEntity = null;

        Linha linhaVelha = linhaRepository.findById(linha.getId()).orElse(null);

        if(linhaVelha == null){
            return responseEntity.notFound().build();
        }

        linhaRepository.delete(linha);

        return responseEntity.ok().build();
    }

    //busca usando o nome uma Linha
    public ResponseEntity<List<Linha>> buscarPorNome(String nome){

        ResponseEntity<Optional<Linha>> responseEntity = null;

        List<Linha> linha = linhaRepository.findByNome(nome);

        if(linha == null){
            return responseEntity.notFound().build();
        }

        return responseEntity.ok(linha);
    }

    //busca todas linhas que estão a uma distancia de um ponto
    public ResponseEntity<List<Linha>> buscarPorDistancia(List distancia){

        ResponseEntity<Optional<Linha>> responseEntity = null;

        List<Linha> linha = linhaRepository.buscarPorDistancia(distancia.get(1),distancia.get(0),distancia.get(2));

        if(linha == null){
            return responseEntity.notFound().build();
        }

        return responseEntity.ok(linha);
    }

}
