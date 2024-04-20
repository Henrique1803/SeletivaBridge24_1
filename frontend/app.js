const formulario = document.querySelector("form");
const Inumero = document.querySelector(".numero");

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
