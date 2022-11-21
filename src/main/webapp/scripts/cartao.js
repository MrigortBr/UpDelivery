number = "5"
validade = "5"
cvv = "5"
apelido = "";
tipo = "";
idEditando = "";

function loadCartoes(){
    var surname = document.querySelectorAll(".surnameCard")
    var card = document.querySelectorAll(".cardInput");
    var validity =  document.querySelectorAll(".validityCardInput");
    var code = document.querySelectorAll(".codeCardInput")

    for (var cont =0; cont < surname.length; cont++){
        surname[cont].style.width = surname[cont].value.length * 16+"px"    
    }

    for (var cont =0; cont < card.length; cont++){
        if (card[cont].type == "password"){
            card[cont].style.width = card[cont].value.length * 10.5+"px"
        }else{
            card[cont].style.width = card[cont].value.length * 15+"px"
        }
    }

    for (var cont =0; cont < validity.length; cont++){
        validity[cont].style.width = validity[cont].value.length * 17+"px"        
    }

    for (var cont =0; cont < code.length; cont++){
        code[cont].style.width = code[cont].value.length * 16+"px"    
    }
}

function lerCartaoNumero(id){
    var cardInput = document.getElementById("cardInput"+id)
    var resposta =  detectarBandeira(cardInput.value.replaceAll(" ", ""));
    document.getElementById("bank"+id).src = "bancos/"+resposta+".svg";
}

function lerValidade(id){
    var validade = document.getElementById("validityCardInput"+id)
}

function lerCVV(id){
    var validade = document.getElementById("codeCardInput"+id)

}


function deletar(id){
    var surname = document.getElementById("surnameCardInput"+id)
    var card = document.getElementById("cardInput"+id);
    
    var cardValue = card.value
    
    var validity =  document.getElementById("validityCardInput"+id);
    var code = document.getElementById("codeCardInput"+id)
    var tipo = document.getElementById("tipo"+id)
    var aviso = document.createElement("p")
    aviso.innerText = "Cartao Deletado"
    var divCard = document.getElementById("cardDiv"+id);
    var height = divCard.clientHeight
    divCard.style.transition = ".5s"
    divCard.innerHTML = ""
    divCard.style.width = "2px"
    divCard.style.height = height+"px"
    setTimeout(() => {
        divCard.remove()
    }, 500);
    
	$.ajax({
	    url: "/UpDeliveryMaven/ControllerCartao",
	    method: "GET",
	    async: false,
		data: {method: "apagarCartao", cartao: cardValue, id: id},
		success: (function (resposta){
		if (resposta == "true"){
	    }else{
	        criarErro("Erro no servidor ao apagar o cartao")
	    }})});


}

function detectarBandeira(number){
    var cartoes = {
        Visa: /^4[0-9]{12}(?:[0-9]{3})/,
        Mastercard: /^5[1-5][0-9]{14}/,
        Amex: /^3[47][0-9]{13}/,
        DinersClub: /^3(?:0[0-5]|[68][0-9])[0-9]{11}/,
        Discover: /^6(?:011|5[0-9]{2})[0-9]{12}/,
        JCB: /^(?:2131|1800|35\d{3})\d{11}/
        };
        for (var cartao in cartoes) {
            if (number.match(cartoes[cartao])){
                return cartao;
            } 
        }
        
        return false;
}

function mostrarCartao(id){
    var olho  = document.getElementById("olho"+id)
    var card = document.getElementById("cardInput"+id)
    var cardValue = card.value

    if (card.type == "password"){
        card.type = "text";
        olho.src = "files/olhoaberto.svg"
        card.style.width = cardValue.length*15+"px"

    }else{
        card.type = "password";
        olho.src = "files/olhofechado.svg"
        card.style.width = cardValue.length*10.5+"px"
    }
}

function editar(id){
    try {
        if (idEditando != ""){
            desistirEditar(idEditando)
        }    
    } catch (error) {
        
    }
   
    var surname = document.getElementById("surnameCardInput"+id)
    var card = document.getElementById("cardInput"+id);
    var validity =  document.getElementById("validityCardInput"+id);
    var typeCard = document.getElementById("tipo"+id)
    var code = document.getElementById("codeCardInput"+id)
    var svgDelete = document.getElementById("svgDelete"+id)
    var svgEdit = document.getElementById("svgEdit"+id)
	
    apelido = surname.value
    number = card.value
    validade = validity.value
    cvv = code.value
    idEditando = id
    tipo = typeCard.innerText

    card.type = "password"
    surname.removeAttribute('disabled');
    card.removeAttribute('disabled');
    validity.removeAttribute('disabled');
    code.removeAttribute('disabled');

    svgDelete.src = "files/check-circle.svg"
    svgEdit.src = "files/x-circle.svg"
    svgEdit.id = "svgX"+id
    svgDelete.id = "svgCheck"+id
    svgEdit.setAttribute("onClick", "desistirEditar("+id+")")
    svgDelete.setAttribute("onClick", "alterarDados("+id+")")
    typeCard.setAttribute("onclick", "mudarTipo("+id+")")
    mostrarCartao(id)
}

function mudarTipo(id){
    var tipo = document.getElementById("tipo"+id)
    if (tipo.innerText === "debito >"){
	
        tipo.innerText = "credito >"
        
    }else if (tipo.innerText === "credito >"){
        tipo.innerText = "Alimentacao >"
        
    }else if (tipo.innerText === "Alimentacao >"){
	
        tipo.innerText = "Refeicao >"
        
    }else if (tipo.innerText === "Refeicao >"){
        tipo.innerText = "debito >"
    }
}

function desistirEditar(id){
    var surname = document.getElementById("surnameCardInput"+id)
    var card = document.getElementById("cardInput"+id);
    var validity =  document.getElementById("validityCardInput"+id);
    var code = document.getElementById("codeCardInput"+id)
    var typeCard = document.getElementById("tipo"+id)
    var svgDelete = document.getElementById("svgCheck"+id)
    var svgEdit = document.getElementById("svgX"+id)

    surname.value = apelido
    card.value = number
    validity.value = validade
    code.value = cvv
    typeCard.innerText = tipo

    surname.setAttribute('disabled', true);
    card.setAttribute('disabled', true);
    validity.setAttribute('disabled', true);
    code.setAttribute('disabled', true);

    svgDelete.src = "files/delete.svg"
    svgEdit.src = "files/edit.svg"
    svgEdit.id = "svgEdit"+id
    svgDelete.id = "svgDelete"+id
    svgEdit.setAttribute("onClick", "editar('"+id+"')")
    svgDelete.setAttribute("onClick", "deletar('"+id+"')")
    typeCard.removeAttribute("onClick")

    loadCartoes();
}

function alterarDados(id){
    var data = document.getElementById("validityCardInput"+id);
    var surname = document.getElementById("surnameCardInput"+id)
    var card = document.getElementById("cardInput"+id);
    var validity =  document.getElementById("validityCardInput"+id);
    var code = document.getElementById("codeCardInput"+id)
    var typeCard = document.getElementById("tipo"+id)

    
    
    
	var bandeira = detectarBandeira(card.value.replaceAll(" ", ""));
	var numeroCartao = card.value.replaceAll(" ", "");
	console.log(typeCard.innerText)
	var tipoCartao = typeCard.innerText.replaceAll(" >", "");
	console.log(tipoCartao)
        if (data.value != "" && surname != "" && card.value != "" && code.value != ""){
            if (validarData(data)){
                if (bandeira != false){
						$.ajax({
	        	url: "/UpDeliveryMaven/ControllerCartao",
	        	method: "POST",
	        	async: false,
				data: {method: "alterarCartao", nome: surname.value, cartao: numeroCartao, cvv: code.value, validade: validity.value, tipo: tipoCartao, bandeira: bandeira, id: id},
				success: (function (resposta){
					if (resposta == "true"){
							location.reload()
	                    }else{
	                        criarErro("Erro no servidor ao alterar o cartao")
	                    }
				})
					});  
				}else{
					criarErro("Numero do cartao invalido")
				}
            }
        }else{
            criarErro("Preencha todos os campos")
        }
			
	        	
}

function validarData(data){
    if (data.value.search("/") != -1 && data.value.search("-") == -1){
        if (data.value.slice(0, data.value.search("/"))<13){
            if (data.value.slice(data.value.search("/")+1)>22){
                return true;
            }else{
                criarErro("Ano ja passado")
            }
        }else{
			criarErro("Mes Invalido")
            return false;
        }
    }else{
        criarErro("Digite a data desta forma (01/22)")
        return false;
    }
}

function cancelarNovo(){
    var surname = document.getElementById("surnameCardInput0")
    var card = document.getElementById("cardInput0");
    var validity =  document.getElementById("validityCardInput0");
    var typeCard = document.getElementById("tipo0")
    var code = document.getElementById("codeCardInput0")

    surname.value = "Novo Cartão"
    card.value = "0000 0000 0000 0000";
    validity.value = "--/--"
    typeCard.innerText = "Debito >"
    code.value = "---"

}

function adicionar(){
    var surname = document.getElementById("surnameCardInput0")
    var card = document.getElementById("cardInput0");
    var data =  document.getElementById("validityCardInput0");
    var typeCard = document.getElementById("tipo0")
    var code = document.getElementById("codeCardInput0")
    var validity =  document.getElementById("validityCardInput0");




	var bandeira = detectarBandeira(card.value.replaceAll(" ", ""));
	var numeroCartao = card.value.replaceAll(" ", "");
	var tipoCartao = typeCard.innerText.replaceAll(" >", "");

    if (data.value != "" && surname != "" && card.value != "" && code.value != ""){
        if (validarData(data)){
			if (bandeira != false){
			$.ajax({
	        	url: "/UpDeliveryMaven/ControllerCartao",
	        	method: "POST",
	        	async: false,
				data: {method: "adicionarCartao", nome: surname.value, cartao: numeroCartao, cvv: code.value, validade: validity.value, tipo: tipoCartao, bandeira: bandeira},
				success: (function (resposta){
					if (resposta == "true"){
							location.reload();
	                    }else{
	                        criarErro("Erro no servidor ao salvar o cartao")
	                    }
				})
			});
			}else{
				criarErro("Cartao invalido")
			}
        }
    }else{
        criarErro("Preencha todos os campos")
    }
}