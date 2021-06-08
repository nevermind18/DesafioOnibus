package com.example.apidosonibus.interfaces;

import com.example.apidosonibus.entity.Itinerario;
import com.example.apidosonibus.entity.Linha;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ItinerarioSerciveInterface {

    public List<Itinerario> adicionarApiPelaExterna(Linha linha) throws IOException;

    public ResponseEntity<Itinerario> cadastrar(Itinerario itinerario);

    public ResponseEntity<Itinerario> buscarPorId(long id) throws IOException;

    public ResponseEntity<Itinerario> alterar(Itinerario itinerarioNovo);

    public ResponseEntity<Itinerario> deletar(Itinerario itinerario);

    public List<Itinerario> buscarTodos();

}
