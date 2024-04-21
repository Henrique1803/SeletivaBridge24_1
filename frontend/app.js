const formulario = document.querySelector("form");
const Inumero = document.querySelector(".numero");

function cadastrar () {
    fetch("http://localhost:8080/Calculadora_primos",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                numero: Inumero.value,
                quantidadePrimos: 0,
                tempoUtilizado: 0
            })
        }
    )
    .then(function (res) { console.log(res) })
    .catch(function (res) { console.log(res) })
};

function limpar () {
    Inumero.value = "";
}

function validar () {
    const numero = Inumero.value.trim();

    if (numero === "" || !Number.isInteger(Number(numero))) {
        return false;
    }

    return true;
}

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    if(validar()) {
        cadastrar();
        limpar();
    } else {
        //dialog de erro
        document.getElementById('cor_titulo_modal').className = 'modal-header text-danger'
        document.getElementById('modal_titulo').innerHTML = 'Erro no cálculo dos números primos!';
        document.getElementById('modal_conteudo').innerHTML = 'Existem campos obrigatórios que não foram preenchidos corretamente!';
        document.getElementById('botao_modal').innerHTML = 'Voltar';
        document.getElementById('botao_modal').className = 'btn btn-danger';
        $('#modalRegistraDespesa').modal('show');
    }
    
});
