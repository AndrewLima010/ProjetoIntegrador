package com.bierpdv.projetointegrador.controller;

import com.bierpdv.projetointegrador.model.Venda;
import com.bierpdv.projetointegrador.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping("/test-connection")
public String testConnection() {
    return "Conexão com o banco de dados está funcionando!";
}
    
    @PostMapping
    public ResponseEntity<?> registrarVenda(@RequestParam Long produtoId, @RequestParam Long clienteId, @RequestParam int quantidade) {
        try {
            Venda venda = vendaService.registrarVenda(produtoId, clienteId, quantidade);
            return ResponseEntity.status(201).body(venda);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
