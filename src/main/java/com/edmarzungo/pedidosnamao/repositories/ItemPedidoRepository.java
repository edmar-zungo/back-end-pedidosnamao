package com.edmarzungo.pedidosnamao.repositories;

import com.edmarzungo.pedidosnamao.domain.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, UUID> {
}
