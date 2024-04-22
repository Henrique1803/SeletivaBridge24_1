const url = 'http://localhost:8080/Calculadora_primos';

const formulario = document.querySelector('form');
const Inumero = document.querySelector('.numero');

function mostrar(data) {
    const elementoQuantidadePrimos = document.getElementById('quantidadePrimos');
    const elementoTempoUtilizado = document.getElementById('tempoUtilizado');

    // Verifica se a propriedade quantidadePrimos está presente no objeto data
    if (data.hasOwnProperty('quantidadePrimos')) {
        // Exibe a quantidade de números primos na tela
        const quantidadePrimos = data.quantidadePrimos;
        elementoQuantidadePrimos.innerHTML = 'Quantidade de números primos: <span class="ressaltar">' + quantidadePrimos + '</span>';
    } else {
        // Se a propriedade quantidadePrimos não estiver presente, exibe uma mensagem de erro
        elementoQuantidadePrimos.innerText = 'Erro: propriedade quantidadePrimos não encontrada no JSON.';
    }

    // Verifica se a propriedade tempoUtilizado está presente no objeto data
    if (data.hasOwnProperty('tempoUtilizado')) {
        // Exibe o tempo utilizado para o cálculo na tela
        const tempoUtilizado = data.tempoUtilizado;
        elementoTempoUtilizado.innerHTML = 'Tempo gasto para o cálculo: <span class="ressaltar">' + tempoUtilizado + ' ns</span>';
    } else {
        // Se a propriedade quantidadePrimos não estiver presente, exibe uma mensagem de erro
        elementoTempoUtilizado.innerText = 'Erro: propriedade tempoUtilizado não encontrada no JSON.';
    }
}

/*async function getAPI (url) {
    mostrarLoader();

    const response = await fetch(url, {method: 'GET'});

    var data = await response.json();
    console.log(data);

    if (response) esconderLoader();
    //mostrar(data);
}*/


async function cadastrar () {
    mostrarLoader();

    try {
        const response = await fetch(url,
            {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify({
                    numero: Inumero.value,
                    quantidadePrimos: 0,
                    tempoUtilizado: 0
                })
            }
        );

        if (!response.ok) {
            var mensagemErro = document.getElementById('mensagemErro');
            exibirErro(mensagemErro, Inumero, '*** Erro ao enviar a solicitação!');
            throw new Error('Erro ao enviar a solicitação.');
        }

        const data = await response.json();
        console.log(data);

        esconderLoader();
        mostrar(data);
    } catch (error) {
        console.error(error);
        esconderLoader();
    }
};

function validar () {
    var numero = Inumero.value.trim();
    var mensagemErro = document.getElementById('mensagemErro');

    if (numero === "" || !Number.isInteger(Number(numero))) {
        exibirErro(mensagemErro, Inumero, '*** Erro: Preencha este campo um número inteiro não negativo!');
        return false;
    } else {
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
    document.getElementById('tempoUtilizado').innerHTML = '';
}

formulario.addEventListener('submit', function (event) {
    limpar();
    event.preventDefault();
    if(validar()) {
        cadastrar();
    }
});
