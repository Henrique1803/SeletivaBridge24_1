const url = 'http://localhost:8080/Calculadora_primos';
let valorLimite = 1000000; // Número limite padrão 

const formulario = document.querySelector('form');
const Inumero = document.querySelector('.numero');

// Exibe na tela o resultado da operação
function mostrar(data) {
    const elementoQuantidadePrimos = document.getElementById('quantidadePrimos');
    const elementoTempoUtilizado = document.getElementById('tempoUtilizado');

    const quantidadePrimos = data.quantidadePrimos;
    elementoQuantidadePrimos.innerHTML = 'Quantidade de números primos: <span class="ressaltar">' + quantidadePrimos + '</span>';
    const tempoUtilizado = data.tempoUtilizado;
    elementoTempoUtilizado.innerHTML = 'Tempo que o servidor levou para fazer o cálculo: <span class="ressaltar">' + tempoUtilizado + ' ns</span>';

}

// Faz a requisição para calcular a quantidade primos menores que N e cadastrar o número no banco de dados
async function cadastrar (numero) {
    try {
        let response = await fetch(url,
            {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify({
                    numero: numero,
                    quantidadePrimos: 0,
                    tempoUtilizado: 0
                })
            }
        );

        if (!response.ok) {
            let mensagemErro = document.getElementById('mensagemErro');
            exibirErro(mensagemErro, Inumero, '*** Erro ao enviar a solicitação!');
        }
    
        let data = await response.json();

        return data;
    } catch (error) {
        console.error(error);
    } finally {

    }
}

// Método responsável por chamar a função de cadastro e, enquanto a operação não está pronta, mostrar uma animação de carregamento
async function calcular (numero) {
    mostrarLoader();

    try {
        const data = await cadastrar(numero);
        esconderLoader();
        mostrar(data);
    } catch (error) {
        console.error(error);
    }
};

// Validação do campo do formuçário
function validar () {
    let numero = Inumero.value.trim();
    let mensagemErro = document.getElementById('mensagemErro');

    if (numero === "" || !Number.isInteger(Number(numero)) || parseInt(numero) < 0) {
        exibirErro(mensagemErro, Inumero, '*** Erro: Preencha este campo um número inteiro não negativo!');
        return false;
    } else if(parseInt(numero) > valorLimite){
        exibirErro(mensagemErro, Inumero, '*** Erro: Os servidores conseguem processar somente um número menor ou igual a '+ valorLimite +'!');
        return false;
    }else{
        ocultarErro(mensagemErro, Inumero);
        return true;
    }
}

function exibirErro(mensagemErro, campo, texto) {
    campo.classList.add('is-invalid');
    mensagemErro.innerText = texto;
    mensagemErro.style.display = 'block';
}

function ocultarErro(mensagemErro, campo) {
    campo.classList.remove('is-invalid');
    mensagemErro.innerText = '';
    mensagemErro.style.display = 'none';
}

function esconderLoader() {
    document.getElementById('loading').style.display = 'none';
}

function mostrarLoader() {
    document.getElementById('loading').style.display = 'block';
}

function limpar() {
    document.getElementById('quantidadePrimos').innerHTML = '';
    document.getElementById('tempoUtilizado').innerHTML = '';
}

// Busca na API o valor máximo N que ela consegue calcular a quantidade de primos positivos menores
async function buscarValorLimite () {
    try {
        const response = await fetch(url+'/limite', {method: 'GET'});

        if (!response.ok) {
            valorLimite = 1000000;
        } else {
            let retorno = await response.json();
            valorLimite = parseInt(retorno);
        }
    } catch (error) {
        console.error(error);
        let mensagemErro = document.getElementById('mensagemErro');
        exibirErro(mensagemErro, Inumero, '*** Erro ao se conectar ao servidor!');
    }
}

formulario.addEventListener('submit', function (event) {
    limpar();
    event.preventDefault();
    if(validar()) {
        calcular(Inumero.value);
    }
});

document.addEventListener("DOMContentLoaded", function() {
    // Função chamada uma vez quando o HTML for carregado, para ser possível fazer a validação com o número limite
    buscarValorLimite();
});
