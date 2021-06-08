package com.example.apidosonibus.repository;

import com.example.apidosonibus.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario,Long> {
}
