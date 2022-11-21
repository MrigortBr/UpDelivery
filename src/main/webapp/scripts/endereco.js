function deletar(id){
    var fundo = document.getElementById("cardEndereco"+id)
    var height = fundo.clientHeight
    var nome = document.getElementById("nomeEndereco"+id)

    $.ajax({
	    url: "/UpDeliveryMaven/ControllerEndereco",
	    method: "GET",
	    async: false,
		data: {method: "apagarEndereco", nome: nome.value, id: id},
		success: (function (resposta){
		if (resposta == "true"){
            fundo.innerHTML = ""
            fundo.style.transition = ".5s"
            fundo.style.width = "0px"
            fundo.style.height = height+"px"
            setTimeout(() => {
                fundo.remove()
            }, 501);
	    }else{
	        criarErro("Erro no servidor ao apagar o endereco")
	    }})});
}

function adicionar(id){
    var nome = document.getElementById("nomeEndereco"+id)
    var rua = document.getElementById("ruaInput"+id)
    var bairro = document.getElementById("bairroInput"+id)
    var cidade = document.getElementById("cidadeInput"+id)
    var numero = document.getElementById("numeroInput"+id)
    var cep = document.getElementById("CEPInput"+id)
    var complemento = document.getElementById("complementoInput"+id)
    
    $.ajax({
	    url: "/UpDeliveryMaven/ControllerEndereco",
	    method: "GET",
	    async: false,
		data: {method: "adicionarEndereco", nome: nome.value, rua: rua.value, bairro: bairro.value, cidade: cidade.value, numero: numero.value, cep: cep.value, complemento: complemento.value},
		success: (function (resposta){
		if (resposta == "true"){
            location.reload()
	    }else{
	        criarErro("Erro no servidor ao adicionar o endereco")
	    }})});
}