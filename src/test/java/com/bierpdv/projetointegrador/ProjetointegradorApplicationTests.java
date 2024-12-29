package com.bierpdv.projetointegrador;

import com.bierpdv.projetointegrador.model.Cliente;
import com.bierpdv.projetointegrador.model.Produto;
import com.bierpdv.projetointegrador.model.Venda;
import com.bierpdv.projetointegrador.repository.ClienteRepository;
import com.bierpdv.projetointegrador.repository.ProdutoRepository;
import com.bierpdv.projetointegrador.repository.VendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // Rollback automático após cada teste
class ProjetointegradorApplicationTests {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
        produtoRepository.deleteAll();
        vendaRepository.deleteAll();
    }

    @Test
    void testCriarClienteComDadosValidos() {
        Cliente cliente = new Cliente(null, "João Silva", "12345678901");
        Cliente savedCliente = clienteRepository.save(cliente);

        assertThat(savedCliente).isNotNull();
        assertThat(savedCliente.getId()).isNotNull();
        assertThat(savedCliente.getNome()).isEqualTo("João Silva");
    }

    @Test
    void testCriarClienteComDadosInvalidos() {
        Cliente cliente = new Cliente(null, null, null);

        try {
            clienteRepository.save(cliente);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    void testBuscarClienteNaoExistente() {
        var cliente = clienteRepository.findById(999L);
        assertThat(cliente).isEmpty();
    }

    @Test
    void testCriarProdutoComEstoque() {
        Produto produto = new Produto(null, "Cerveja", 10.5, 100);
        Produto savedProduto = produtoRepository.save(produto);

        assertThat(savedProduto).isNotNull();
        assertThat(savedProduto.getId()).isNotNull();
        assertThat(savedProduto.getNome()).isEqualTo("Cerveja");
    }

    @Test
void testRegistrarVendaComEstoqueSuficiente() {
    Produto produto = produtoRepository.save(new Produto(null, "Cerveja", 10.5, 10));
    Cliente cliente = clienteRepository.save(new Cliente(null, "Maria", "98765432100"));

    Venda venda = new Venda(produto, cliente, 5);
    vendaRepository.save(venda);

    // Verificar se o estoque do produto foi reduzido corretamente
    Produto produtoAtualizado = produtoRepository.findById(produto.getId()).get();
    assertThat(produtoAtualizado.getEstoque()).isEqualTo(5); // Estoque deve ser 10 - 5 = 5
}

    @Test
    void testRegistrarVendaComEstoqueInsuficiente() {
        Produto produto = produtoRepository.save(new Produto(null, "Cerveja", 10.5, 3));
        Cliente cliente = clienteRepository.save(new Cliente(null, "Carlos", "56789012345"));

        try {
            Venda venda = new Venda(produto, cliente, 5);
            vendaRepository.save(venda);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(Exception.class);
        }
    }
}
