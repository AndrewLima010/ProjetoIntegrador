/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bierpdv.projetointegrador.controller;

import com.bierpdv.projetointegrador.model.Produto;
import com.bierpdv.projetointegrador.model.Venda;
import com.bierpdv.projetointegrador.service.VendaService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VendaController extends HttpServlet {
    private VendaService vendaService;

    @Override
    public void init() throws ServletException {
        vendaService = new VendaService(); // Dependência de serviço
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String produtoNome = request.getParameter("produto");
        double quantidade = Double.parseDouble(request.getParameter("quantidade"));

        Produto produto = new Produto(produtoNome, 10.0); // Preço fixo apenas para exemplo
        Venda venda = vendaService.registrarVenda(produto, quantidade);

        request.setAttribute("venda", venda);
        RequestDispatcher dispatcher = request.getRequestDispatcher("venda.jsp");
        dispatcher.forward(request, response);
    }
}