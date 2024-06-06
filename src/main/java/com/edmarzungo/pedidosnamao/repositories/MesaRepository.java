package com.edmarzungo.pedidosnamao.repositories;

import com.edmarzungo.pedidosnamao.domain.MesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MesaRepository extends JpaRepository<MesaModel, UUID> {
}
