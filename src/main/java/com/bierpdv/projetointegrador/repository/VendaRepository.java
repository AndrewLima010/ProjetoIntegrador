/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bierpdv.projetointegrador.repository;

import com.bierpdv.projetointegrador.model.Venda;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    private List<Venda> vendas = new ArrayList<>();

    public void salvarVenda(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listarVendas() {
        return vendas;
    }
}

