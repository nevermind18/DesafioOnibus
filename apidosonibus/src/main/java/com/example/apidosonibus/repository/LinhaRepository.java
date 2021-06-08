package com.example.apidosonibus.repository;


import com.example.apidosonibus.entity.Linha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Long> {

    public List<Linha> findByNome(String nome);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query( value = "insert into linha value(:id, :codigo, :nome)", nativeQuery = true)
    void saveComId(@Param("id") long id, @Param("codigo") String code, @Param("nome") String nome);

    @Query(value = "SELECT * FROM linha inner join itinerario on linha.id = itinerario.id_nome " +
            "where ST_Distance_Sphere(point(itinerario.lat, itinerario.lng), " +
            "    point(:lat, :lng)) < :area", nativeQuery = true)
    List<Linha> buscarPorDistancia(@Param("lng") Object lng, @Param("lat") Object lat, @Param("area") Object area);

}
