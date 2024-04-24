const url = 'http://localhost:8080/Calculadora_primos';

// Método responsável por exibir o histórico de números digitados na tabela
function mostrar(calculos) {
    // Pegar o filtro selecionado
    var selecao = document.getElementById("selecaoOrdenacao");
    var atributo = selecao.value;
    // Função de comparação dinâmica com base no atributo selecionado
    function comparar(a, b) {
        return a[atributo] - b[atributo];
    }
    // Atualiza a lista HTML com a nova ordem
    calculos.sort(comparar);
    
    let tab = ``;
    let contador = 1; // Começa do último índice 
    for (let i = calculos.length - 1; i >= 0; i--) { // Percorre de trás para frente (primeiro elemento da tabela é o último número digitado)
        let calculo = calculos[i];
        tab += `
                <tr scope="row">
                    <td>${contador}</td>
                    <td>${calculo.numero}</td>
                    <td>${calculo.quantidadePrimos}</td>
                    <td>${calculo.tempoUtilizado} ns</td>
                    <td>
                        <button class="btn-historico" value="${calculo.id}" onclick="excluir(${calculo.id})"><img src="imagens/excluir.png" width="30" height="30"></button>
                    </td>
                </tr>
            `;
        contador++;
    }
  
    document.getElementById("tabelaHistorico").innerHTML = tab;

    // Carrega o rodapé somente após a tabela, para não causar um conflito visual na tela
    let rodape = document.getElementById("rodape-historico");
    rodape.style.display = 'block';
}

// Faz a requisição para excluir do banco de dados o histórico do número selecionado com base em seu id
async function excluir(id) {

    let mensagemErro = document.getElementById('mensagemErro');
    ocultarMensagem(mensagemErro);

    let mensagemSucesso = document.getElementById('mensagemSucesso');
    ocultarMensagem(mensagemSucesso);

   // URL do endpoint DELETE
    let urlComId = url+'/'+id;

    // Opções da requisição
    let options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    };

    fetch(urlComId, options)
        .then(response => {
            if (response.ok) {
                exibirMensagem(mensagemSucesso, '*** Item do histórico excluído com sucesso!');
                buscarHistorico(url);
            } else {
                exibirMensagem(mensagemErro, '*** Erro ao excluir item do histórico!');
            }
        })
        .catch(error => {
            console.error(error);
        });
}

// Método responsável por fazer a requisição para retornar uma lista contendo todos os números digitados e seus resutados
async function buscarHistorico (url) {

    let mensagemErro = document.getElementById('mensagemErro');

    try {
        const response = await fetch(url, {method: 'GET'});

        if (response.ok) {
            let data = await response.json();
            ocultarMensagem(mensagemErro);
            mostrar(data);
        } else {
            exibirMensagem(mensagemErro, '*** Erro: Problema ao carregar histórico!');
        }

    } catch (error) {
        console.error(error);
    }

}

function exibirMensagem(mensagemErro, texto) {
    mensagemErro.innerText = texto;
    mensagemErro.style.display = 'block';
}

function exibirMensagem(mensagem, texto) {
    mensagem.innerText = texto;
    mensagem.style.display = 'block';
}

function ocultarMensagem(mensagem) {
    mensagem.innerText = '';
    mensagem.style.display = 'none';
}

// Função chamada uma vez quando o HTML for carregado
document.addEventListener("DOMContentLoaded", function() {
    buscarHistorico(url);
});

// Adiciona um evento de alteração à caixa de seleção para reordenar a tabela
document.getElementById("selecaoOrdenacao").addEventListener("change", function() {
    buscarHistorico(url);
});
