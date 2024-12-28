/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bierpdv.projetointegrador.service;

import com.bierpdv.projetointegrador.model.Produto;
import com.bierpdv.projetointegrador.model.Venda;

public class VendaService {

    public Venda registrarVenda(Produto produto, double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que 0.");
        }
        return new Venda(produto, quantidade);
    }
}