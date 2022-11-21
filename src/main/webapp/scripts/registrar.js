function fazerRegistro(){
	    var nome = document.getElementById("nome").value
	    var sobrenome = document.getElementById("sobrenome").value
	    var cpf = document.getElementById("cpf").value
	    cpf = cpf.replace(/\.|-/g, "");
	    var telefone = document.getElementById("telefone").value
	    var dataNasc = document.getElementById("datasNasc").value
	    var email = document.getElementById("email").value
	    var senha = document.getElementById("senha").value
	    var senhaconfirma = document.getElementById("senhaconfirma").value
	    if (nome != "" && sobrenome != "" && cpf != "" && telefone != "" && datasNasc != "" && email != "" && senha != "" && senhaconfirma != ""){
	        if (isCPF(cpf) == true && isEmail(email) && senhaConfirma(senha, senhaconfirma) && isCelular(telefone)){
	            $.ajax({
	                url: "/UpDeliveryMaven/ControllerRegistrar",
	                method: "GET",
	                async: false,
	                data: {method: "registrarPessoa", nome: nome, sobrenome: sobrenome, cpf: cpf, telefone: telefone, dataNasc: dataNasc, email: email, senha: senha},
	                success: (function(resposta){
	                    if (resposta == "true"){
	                        window.location.replace("login.jsp")
	                    }else{
	                        window.alert("Erro ao criar conta, contacte nosso suporte")
	                    }
	                })});
	        }
	    }else{
	        criarErro("Preencha todos os campos!")
	    }
}

function fazerRegistroFornecedor(){
	var nome = document.getElementById("nome").value
	var cnpj = document.getElementById("cnpj").value
	var tipoEntrega = document.getElementById("tipoEntrega").value
	var precoEntrega = document.getElementById("precoEntrega").value
	var email = document.getElementById("email").value
	var tipo = document.getElementById("tipo").value
	var senha = document.getElementById("senha").value
	var senhaconfirma = document.getElementById("senhaconfirma").value

	if (nome != "" && cnpj != "" && tipoEntrega != "" && precoEntrega != "" && tipo != "" && email != "" && senha != "" && senhaconfirma != ""){
		if (isEmail(email) && senhaConfirma(senha, senhaconfirma)){
			$.ajax({
				url: "/UpDeliveryMaven/ControllerRegistrar",
				method: "GET",
				async: false,
				data: {method: "registrarFornecedor", nome: nome, cnpj: cnpj, tipoEntrega: tipoEntrega, precoEntrega: precoEntrega, tipo: tipo, email: email, senha: senha},
				success: (function(resposta){
					if (resposta == "true"){
						window.location.replace("login.jsp")
					}else{
						
						window.alert("Erro ao criar conta, contacte nosso suporte")
					}
				})});}
	}else{
		criarErro("Preencha todos os campos!")
	}
	}
