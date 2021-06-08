package com.example.apidosonibus.service;

import com.example.apidosonibus.dto.Dto;
import com.example.apidosonibus.entity.Itinerario;
import com.example.apidosonibus.entity.Linha;
import com.example.apidosonibus.interfaces.ItinerarioSerciveInterface;
import com.example.apidosonibus.repository.ItinerarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class ItinerarioService implements ItinerarioSerciveInterface {

    @Autowired
    ItinerarioRepository itinerarioRepository;


    //Adiciona um novo Itinerario ao banco de dados usando os dados de uma api
    public List<Itinerario> adicionarApiPelaExterna(Linha linha) throws IOException{

        RestTemplate itinerarioRestTemplate = new RestTemplate();

        String itinerarioResponse =
                itinerarioRestTemplate.getForObject(
                        "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+linha.getId(),
                        String.class);

        String result = itinerarioResponse;
        List<Itinerario> incomeSpotsList = itinerariosParaLinha(result, linha);


        return itinerarioRepository.saveAll(incomeSpotsList);

    }

    //faz a conversão do texto json para uma string contendo a Linha e os Itinerarios
    private List<Itinerario> itinerariosParaLinha(String result, Linha linha)
            throws IOException, JsonParseException {
        ObjectMapper mapper = new ObjectMapper();
        Dto map = mapper.readValue(result, Dto.class);

        List<Itinerario> itinerarios = new ArrayList<>();
        int i = 0;
        while (map.getDetails().containsKey("" + i)) {
            itinerarios.add(convertSpotMapToModel(linha, map, i++));

        }
        return itinerarios;
    }


    //faz o mapeamento da linha e retira os itinerarios dela
    private Itinerario convertSpotMapToModel(Linha linha, Dto map, int i) {
        Map innerMap = (Map) (map.getDetails().get("" + i));
        Itinerario itinerario = new Itinerario();
        itinerario.setLat(Double.valueOf((String) innerMap.get("lat")));
        itinerario.setLng(Double.valueOf((String) innerMap.get("lng")));


        return itinerario;
    }

    //Adiciona um novo Itinerario ao banco de dados
    public ResponseEntity<Itinerario> cadastrar(Itinerario itinerario){

        ResponseEntity<Itinerario> responseEntity = null;

        itinerario.setId(0);

        return responseEntity.status(201).body(itinerarioRepository.save(itinerario));
    }

    //busca todos os Itinerarios do banco
    public List<Itinerario> buscarTodos(){

        return itinerarioRepository.findAll();
    }

    //busca usando a id um Itinerario
    public ResponseEntity<Itinerario> buscarPorId(long id) throws IOException {


        ResponseEntity<Linha> responseEntity = null;

        Itinerario itinerario = itinerarioRepository.findById(id).orElse(null);

        if(itinerario == null){
            return responseEntity.notFound().build();
        }

        return responseEntity.ok(itinerario);
    }

    //altera um Itinerario no banco
    public ResponseEntity<Itinerario> alterar(Itinerario itinerarioNovo){

        ResponseEntity<Linha> responseEntity = null;

        Itinerario itinerarioVelho = itinerarioRepository.findById(itinerarioNovo.getId()).orElse(null);

        if(itinerarioVelho == null){
            return responseEntity.notFound().build();
        }

        return responseEntity.status(201).body(itinerarioRepository.save(itinerarioNovo));
    }

    //deleta um itinerario
    public ResponseEntity<Itinerario> deletar(Itinerario itinerario){

        ResponseEntity<Itinerario> responseEntity = null;

        Itinerario linhaVelha = itinerarioRepository.findById(itinerario.getId()).orElse(null);

        if(linhaVelha == null){
            return responseEntity.notFound().build();
        }

        itinerarioRepository.delete(itinerario);

        return responseEntity.ok().build();
    }
}
