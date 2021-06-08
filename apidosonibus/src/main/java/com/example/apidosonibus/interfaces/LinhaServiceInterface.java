package com.example.apidosonibus.interfaces;

import com.example.apidosonibus.entity.Linha;
import com.example.apidosonibus.service.LinhaService;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface LinhaServiceInterface {

    public List<Linha> consumirApi() throws InterruptedException, IOException;

    public ResponseEntity<Linha> cadastrar(Linha linha);

    public List<Linha> buscarTodos();

    public ResponseEntity<Linha> buscarPorId(long id) throws IOException, InterruptedException;

    public ResponseEntity<Linha> alterar(Linha linhaNova);

    public ResponseEntity<Linha> deletar(Linha linha);

    public ResponseEntity<List<Linha>> buscarPorNome(String nome);

    public ResponseEntity<List<Linha>> buscarPorDistancia(List distancia);

}
