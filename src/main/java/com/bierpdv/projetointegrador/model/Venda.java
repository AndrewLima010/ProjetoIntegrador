package com.bierpdv.projetointegrador.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Integer quantidade;
    private LocalDateTime dataVenda = LocalDateTime.now();


    public Venda(Produto produto, Cliente cliente, int quantidade) {
        this.produto = produto;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.dataVenda = LocalDateTime.now(); // Data gerada automaticamente
    }
}