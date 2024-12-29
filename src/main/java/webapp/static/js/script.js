document.getElementById("form-cliente").addEventListener("submit", async (e) => {
    e.preventDefault();

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();

    if (!nome || !email) {
        alert("Nome e email são obrigatórios.");
        return;
    }

    try {
        const response = await fetch('http://localhost:8082/clientes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome, email })
        });

        if (!response.ok) {
            const errorData = await response.json();
            alert(`Erro: ${errorData.error}`);
            return;
        }

        const data = await response.json();
        alert(`Cliente cadastrado com sucesso! ID: ${data.id}`);
        document.getElementById("form-cliente").reset();
    } catch (err) {
        console.error('Erro ao enviar dados:', err);
        alert('Erro ao cadastrar cliente. Tente novamente mais tarde.');
    }
});
