package com.edmarzungo.pedidosnamao.repositories;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardapioRepository extends JpaRepository<CardapioModel, UUID> {
}
