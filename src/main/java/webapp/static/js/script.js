// Validações e mensagens dinâmicas
document.addEventListener("DOMContentLoaded", () => {
    const formVenda = document.getElementById("form-venda");
    const formProduto = document.getElementById("form-produto");

    if (formVenda) {
        formVenda.addEventListener("submit", (e) => {
            e.preventDefault(); // Impede o envio padrão
            const cliente = document.getElementById("cliente").value.trim();
            const data = document.getElementById("data").value.trim();
            const valor = document.getElementById("valor").value.trim();

            let errorMessage = "";
            if (!cliente) errorMessage += "O campo Cliente é obrigatório.\n";
            if (!data) errorMessage += "O campo Data da Venda é obrigatório.\n";
            if (!valor || valor <= 0) errorMessage += "O Valor Total deve ser maior que zero.\n";

            if (errorMessage) {
                alert(errorMessage); // Exibe erros
            } else {
                alert("Venda registrada com sucesso!");
                formVenda.reset(); // Limpa o formulário
            }
        });
    }

    if (formProduto) {
        formProduto.addEventListener("submit", (e) => {
            e.preventDefault(); // Impede o envio padrão
            const nome = document.getElementById("nome").value.trim();
            const preco = document.getElementById("preco").value.trim();
            const estoque = document.getElementById("estoque").value.trim();

            let errorMessage = "";
            if (!nome) errorMessage += "O Nome do Produto é obrigatório.\n";
            if (!preco || preco <= 0) errorMessage += "O Preço deve ser maior que zero.\n";
            if (!estoque || estoque <= 0) errorMessage += "O Estoque deve ser maior que zero.\n";

            if (errorMessage) {
                alert(errorMessage); // Exibe erros
            } else {
                alert("Produto adicionado com sucesso!");
                formProduto.reset(); // Limpa o formulário
            }
        });
    }
});
