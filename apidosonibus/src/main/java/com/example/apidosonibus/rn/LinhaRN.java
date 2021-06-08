package com.example.apidosonibus.rn;

import com.example.apidosonibus.entity.Linha;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LinhaRN {

    public ResponseEntity<Linha> cadastrar(Linha linha){

        ResponseEntity<Linha> responseEntity = null;

        if(linha.getCodigo() == null || linha.getCodigo().isEmpty() ||  linha.getCodigo().trim().length() <= 0){
            return responseEntity.badRequest().header("Error: ","o codigo deve ter pelo menos 1 caracteres").build();
        }

        if(linha.getNome() == null || linha.getNome().isEmpty() ||  linha.getNome().trim().length() <= 3){
            return responseEntity.badRequest().header("Error: ","o nome deve ter mais de 3 caracteres").build();
        }

        return null;
    }

    public ResponseEntity<Linha> alterar(Linha linha){

        ResponseEntity<Linha> responseEntity = null;

        if(linha.getCodigo().isEmpty() || linha.getCodigo() == null || linha.getCodigo().trim().length() <= 0){
            return responseEntity.badRequest().header("Error: ","o codigo deve ter pelo menos 1 caracteres").build();
        }

        if(linha.getCodigo().isEmpty() || linha.getCodigo() == null || linha.getCodigo().trim().length() <= 3){
            return responseEntity.badRequest().header("Error: ","o codigo deve ter mais de 3 caracteres").build();
        }

        return null;
    }

}
