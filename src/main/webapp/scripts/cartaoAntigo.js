number = "5"
validade = "5"
cvv = "5"
apelido = "";
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
            card[cont].style.width = card[cont].value.length * 17+"px"
        }
    }

    for (var cont =0; cont < validity.length; cont++){
        validity[cont].style.width = validity[cont].value.length * 17+"px"        
    }

    for (var cont =0; cont < code.length; cont++){
        code[cont].style.width = code[cont].value.length * 16+"px"    
    }

}

function mostrarCartao(id, id2){
    var olho  = document.getElementById(id2)
    var card = document.getElementById(id)
    var cardValue = card.value

    if (card.type == "password"){
        card.type = "text";
        olho.src = "files/olhoaberto.svg"
        card.style.width = cardValue.length*17+"px"

    }else{
        card.type = "password";
        olho.src = "files/olhofechado.svg"
        card.style.width = cardValue.length*10.5+"px"
    }
}

function editar(id){
    if (idEditando != ""){
        desistirEditar(idEditando)
        console.log(idEditando)
    }

    var surname = document.getElementById("surnameCardInput"+id)
    var card = document.getElementById("cardInput"+id);
    var validity =  document.getElementById("validityCardInput"+id);
    var code = document.getElementById("codeCardInput"+id)
    var svgDelete = document.getElementById("svgDelete"+id)
    var svgEdit = document.getElementById("svgEdit"+id)
	
    apelido = surname.value;
    number = card.value;
    validade = validity.value;
    cvv = code.value;
    idEditando = id;

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

    mostrarCartao("cardInput"+id, "olho"+id)
}

function desistirEditar(id){
    var surname = document.getElementById("surnameCardInput"+id)
    var card = document.getElementById("cardInput"+id);
    var validity =  document.getElementById("validityCardInput"+id);
    var code = document.getElementById("codeCardInput"+id)
    var svgDelete = document.getElementById("svgCheck"+id)
    var svgEdit = document.getElementById("svgX"+id)

    surname.value = apelido
    card.value = number
    validity.value = validade
    code.value = cvv

    surname.setAttribute('disabled', true);
    card.setAttribute('disabled', true);
    validity.setAttribute('disabled', true);
    code.setAttribute('disabled', true);

    svgDelete.src = "files/delete.svg"
    svgEdit.src = "files/edit.svg"
    svgEdit.id = "svgEdit"+id
    svgDelete.id = "svgDelete"+id
    svgEdit.setAttribute("onClick", "editar("+id+")")
    svgDelete.setAttribute("onClick", "deletar("+id+")")

    loadCartoes();
}

function alterarCartao(id){
    var card = document.getElementById("cardInput"+id);
    var cardValue = card.value

    card.style.width = cardValue.length*17+"px"
}

function alterarDados(id){
    var data = document.getElementById("validityCardInput"+id);
    if (data.value.search("/") != -1){
		if (data.value.slice(0, data.value.search("/"))<13){
			if (data.value.slice(data.value.search("/")+1)>22){
	        	alert("Enviando Por Json")
		        var surname = document.getElementById("surnameCardInput"+id)
		        var card = document.getElementById("cardInput"+id);
		        var validity =  document.getElementById("validityCardInput"+id);
		        var code = document.getElementById("codeCardInput"+id)
		        var svgDelete = document.getElementById("svgCheck"+id)
		        var svgEdit = document.getElementById("svgX"+id)
		
		        surname.setAttribute('disabled', true);
		        card.setAttribute('disabled', true);
		        validity.setAttribute('disabled', true);
		        code.setAttribute('disabled', true);
		
		        svgDelete.src = "files/delete.svg"
		        svgEdit.src = "files/edit.svg"
		        svgEdit.id = "svgEdit"+id
		        svgDelete.id = "svgDelete"+id
		        svgEdit.setAttribute("onClick", "editar("+id+")")
		        svgDelete.setAttribute("onClick", "deletar("+id+")")
		        console.log(validity.value.search("-"))
		        loadCartoes();
			}else{
				criarErro("Ano ja passado")
			}
			
		}else{
			criarErro("Mes Invalido")
		}
    }else{
		criarErro("Digite a data desta forma (01/22)")
	}
    
}

function deletar(id){
    alert("deletando por Json")
}

function apagar(){
    document.getElementById("surnameCardInput0").value = "Apelido do cartao"
    document.getElementById("cardInput0").value = "..."
    document.getElementById("validityCardInput0").value = "..."
    document.getElementById("codeCardInput0").value = "..."
}

function adicionarCartao(){
    var surname = document.getElementById("surnameCardInput0").value
    var card = document.getElementById("cardInput0").value
    var validity =  document.getElementById("validityCardInput0").value
    var code = document.getElementById("codeCardInput0").value
    var data = document.getElementById("validityCardInput0");
    if (data.value.search("/") != -1){
		if (data.value.slice(0, data.value.search("/"))<13){
			if (data.value.slice(data.value.search("/")+1)>22){
                if (surname !== "" && card.length > 13 && validity.length == 5 && code.length == 3){
                    alert("valido")
                }else{
                    alert("invalido")
                }
            }else{
				criarErro("Ano ja passado")
			}
			
		}else{
			criarErro("Mes Invalido")
		}
    }else{
		criarErro("Digite a data desta forma (01/22)")
	}
    



}