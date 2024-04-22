const url = 'http://localhost:8080/Calculadora_primos';

function mostrar(calculos) {
    let tab = ``;
  
    for (let calculo of calculos) {
      tab += `
                <tr scope="row">
                    <td>${calculo.id}</td>
                    <td>${calculo.numero}</td>
                    <td>${calculo.quantidadePrimos}</td>
                    <td>${calculo.tempoUtilizado}</td>
                    <td>
                        <button class="btn-historico" value="${calculo.id}" onclick="excluir(${calculo.id})"><img src="imagens/excluir.png" width="30" height="30"></button>
                    </td>
                </tr>
          `;
    }
  
    document.getElementById("tabelaHistorico").innerHTML = tab;

    // Carrega o rodapé somente após a tabela, para não causar um conflito visual na tela
    var rodape = document.getElementById("rodape-historico");
    rodape.style.display = 'block';
}

async function excluir(id) {

    var mensagemErro = document.getElementById('mensagemErro');
    ocultarMensagem(mensagemErro);

    var mensagemSucesso = document.getElementById('mensagemSucesso');
    ocultarMensagem(mensagemSucesso);

   // URL do endpoint DELETE
    var urlComId = url+'/'+id;

    // Configurar as opções da requisição
    var options = {
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
  
async function buscarHistorico (url) {

    var mensagemErro = document.getElementById('mensagemErro');

    try {
        const response = await fetch(url, {method: 'GET'});

        if (response.ok) {
            var data = await response.json();
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

  document.addEventListener("DOMContentLoaded", function() {
    // Função chamada uma vez quando o HTML for carregado
    buscarHistorico(url);
});