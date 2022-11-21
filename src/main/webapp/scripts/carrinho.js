function comprar(idFornecedor){
    var cartao = document.getElementById("cartao"+idFornecedor);
    var tipo = document.getElementById("formaEntrega"+idFornecedor);
    alert(tipo.value)
    var card;
    if (tipo.value === "Retirada"){
		card = "null"
	}else if (tipo.value === "Entrega"){
		if (cartao.value === "Dinheiro"){
			card = "null"
		}else{
			card = cartao.value
		}
	}
    $.ajax({
	    url: "/UpDeliveryMaven/ControllerVenda",
	    method: "POST",
	    async: false,
		data: {method: "venderProduto", fornecedor: idFornecedor, nomeCartao: card},
		success: (function (resposta){
		if (resposta == "true"){
			apagar(idFornecedor)
	    }else{
	        criarErro("Erro no servidor ao finalizar produto tente novamente mais tarde")
	    }})});
}


function apagarCompra(idFornecedor){
	var cartao = document.getElementById("cartao"+idFornecedor);
    $.ajax({
	    url: "/UpDeliveryMaven/ControllerVenda",
	    method: "POST",
	    async: false,
		data: {method: "apagarProduto", fornecedor: idFornecedor},
		success: (function (resposta){
			apagar(idFornecedor)

		})});
}


function apagar(id){
	var produto = document.getElementById("pedido"+id)
	var height = produto.clientHeight
	produto.innerHTML = ""
	produto.style.height = height+"px"
	produto.style.transition = ".5s"
	produto.style.width = "2px"

	setTimeout(() => {
		produto.remove()
		detectarTodosProdutos();
		

	}, 500);
}

function detectarTodosProdutos(){
	if (document.getElementById("main").innerHTML.search("div") == -1){
		var h1 = document.createElement("h2")
		h1.style.marginLeft = "1%"
		h1.textContent = "Não Existe produtos em seu carrinho"
		document.getElementById("main").appendChild(h1)
	}

}

function formaDePagamento(id){
	var forma = document.getElementById("formaEntrega"+id)
	var formas = document.getElementById("formas"+id)


		var select = document.createElement("select")
		var option = document.createElement("option")
		var option2 = document.createElement("option")
		var label	= document.createElement("label")

		select.name = "formaPagamento"

		option.value = "Cartao"
		option2.value = "Dinheiro"
		option.innerText = "Cartao"
		option2.innerText = "Dinheiro"

		label.textContent = "Forma de Pagamento"
		label.for = "formaPagamento"
		
		select.id = "formaPagamento"+id
		select.setAttribute("onchange", `escolherCartao('${id}')`)

		select.append(option)
		select.append(option2)
		formas.append(label)
		formas.append(select)
	
}

function escolherCartao(id){
	var forma = document.getElementById("formaPagamento"+id)
	var forma2 = document.getElementById("formaEntrega"+id)
	var formas = document.getElementById("formas"+id)

	if (forma.value === "Cartao" && forma2.value == "Retirada"){
		var select = document.createElement("select")
		var option = document.createElement("option")
		var option2 = document.createElement("option")
		var option3 = document.createElement("option")
		var option4 = document.createElement("option")
		var br = document.createElement("br")

		var label	= document.createElement("label")

		select.name = "tipo"

		option.value = "Debito"
		option2.value = "Credito"
		option3.value = "Alimentação"
		option4.value = "Refeição"
		option.innerText = "Debito"
		option2.innerText = "Dinheiro"
		option3.innerText = "Alimentação"
		option4.innerText = "Refeição"


		label.textContent = "Tipo Do Cartao"
		label.for = "tipo"

		select.id = "tipo"+id
		
		formas.append(br)
		select.append(option)
		select.append(option2)
		select.append(option3)
		select.append(option4)
		formas.append(label)
		formas.append(select)
	}
}
