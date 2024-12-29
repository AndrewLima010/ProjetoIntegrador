package com.bierpdv.projetointegrador.service;


import com.bierpdv.projetointegrador.model.Cliente;
import com.bierpdv.projetointegrador.model.Produto;
import com.bierpdv.projetointegrador.model.Venda;
import com.bierpdv.projetointegrador.repository.ClienteRepository;
import com.bierpdv.projetointegrador.repository.ProdutoRepository;
import com.bierpdv.projetointegrador.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Venda registrarVenda(Long produtoId, Long clienteId, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que 0.");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        if (produto.getEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoRepository.save(produto);

        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setCliente(cliente);
        venda.setQuantidade(quantidade);
        return vendaRepository.save(venda);
    }
}
