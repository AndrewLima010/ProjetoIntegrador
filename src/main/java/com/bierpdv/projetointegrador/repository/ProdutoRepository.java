package com.bierpdv.projetointegrador.repository;

import com.bierpdv.projetointegrador.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
