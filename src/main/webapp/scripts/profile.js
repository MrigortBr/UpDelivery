var base64 = "";

function pegarArquivo(){
    const inputElement = document.querySelector("#file");
    inputElement.click()
}
function uploadFile(){
    var target = document.querySelector("#imgUsuario");

    var file = document.querySelector("input[type=file]").files[0];
    
    var reader = new FileReader();
                
    reader.onloadend = function (){
    target.src = reader.result;
    base64 = reader.result;
    };
    
    if (file){
        reader.readAsDataURL(file);	

        
    }else{
        target.src = "";
    }
}

function solicitarSenha(){
    document.getElementById("blur").style.visibility = "visible";
    document.getElementById("senha").style.gridArea = "sn"
    document.getElementById("senhaInput").placeholder = "Digite sua Senha"
    document.getElementById("senhaNova").style.visibility = "hidden";
    document.getElementById("confirmarSenha").style.visibility = "hidden";
   	var button = document.getElementById("continuar")
    button.setAttribute("onclick", "confirmarAlteracao()");

}

function alterarSenha(){
    document.getElementById("senha").style.gridArea = "s"
    document.getElementById("senhaNova").style.gridArea = "sn"
    document.getElementById("confirmarSenha").style.gridArea = "snc"
    document.getElementById("senhaNova").style.visibility = "visible";
    document.getElementById("confirmarSenha").style.visibility = "visible";
    
    document.getElementById("blur").style.visibility = "visible";
    var button = document.getElementById("continuar")
    button.setAttribute("onclick", "confirmarAlterarSenha()");
}
function confirmarAlterarSenha(){
	var senha = document.getElementById("senhaInput").value
	var senhaNova = document.getElementById("senhaNovaInput").value
	var senhaNovaConfirma = document.getElementById("confirmarSenhaInput").value

    if (senha != "" && senhaNova != "" && senhaNovaConfirma != "" && senhaNova === senhaNovaConfirma){
		$.ajax({
	            url: "/UpDeliveryMaven/ControllerProfile",
	            method: "GET",
	            async: false,
	            data: {method: "validarSenha", senha: senha},
	            success: (function(resposta){
					if (resposta === "true"){
						console.log("validada")
						$.ajax({
			            url: "/UpDeliveryMaven/ControllerProfile",
			            method: "GET",
			            async: false,
			            data: {method: "alterarSenha",  novaSenha: senhaNova},
			            success: (function(resposta){
							if (resposta === "true"){
								location.reload();
							}else{
                        		criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            				}
            		})});	
				
			
					}else{
						criarErro("Senha incorreta");
					}
	            })});
	    
	}else{
        criarErro("Preencha todos os campos corretamente")
    }

}
function apagarConta(){
    var senha = document.getElementById("senhaInput").value
    if (senha != ""){
		$.ajax({
	            url: "/UpDeliveryMaven/ControllerProfile",
	            method: "GET",
	            async: false,
	            data: {method: "validarSenha", senha: senha},
	            success: (function(resposta){
					if (resposta === "true"){
						$.ajax({
			            url: "/UpDeliveryMaven/ControllerProfile",
			            method: "GET",
			            async: false,
			            data: {method: "apagarConta"},
			            success: (function(resposta){
							if (resposta === "true"){
								location.reload();
							}else{
                        		criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            				}
            		})});	
				
			
					}else{
						criarErro("Senha incorreta");
					}
	            })});
	    
	}else{
        criarErro("Preencha todos os campo")
    }

}

function confirmarAlteracao(){
    var nome = document.getElementById("nome").value
    var sobrenome = document.getElementById("sobrenome").value
    var cpf = document.getElementById("cpf").value
    cpf = cpf.replace(/\.|-/g, "");
    var telefone = document.getElementById("telefone").value
    var dataNasc = document.getElementById("datasNasc").value
    var email = document.getElementById("email").value
    var senha = document.getElementById("senhaInput").value
    if (nome != "" && sobrenome != "" && cpf != "" && telefone != "" && datasNasc != "" && email != "" && senha != ""){
        var inputValidador = document.getElementById("datasNasc").type
        if (validarSenha(senha) === "true"){
            if (inputValidador === "date"){
                if (isCPF(cpf) && isEmail(email) && isCelular(telefone)){  
                    alterarRegistro(nome,sobrenome, cpf, telefone, dataNasc, email);
                }
            }else if(inputValidador === "number"){
                if (isEmail(sobrenome)){
                    alterarRegistro(nome,sobrenome, cpf, telefone, dataNasc, email);
                }
            }    
                
            }else{
                criarErro("Senha incorreta")
            }
        }else{
            criarErro("Preencha todos os campos")
        }        
    }

function alterarRegistro(nome,sobrenome, cpf, telefone, dataNasc, email){
    var resultado = "";
    $.ajax({
    url: "/UpDeliveryMaven/ControllerProfile",
    method: "GET",
    async: false,
    data: {method: "atualizarUsuario", nome: nome, sobrenome: sobrenome, cpf: cpf, telefone: telefone, dataNasc: dataNasc, email: email},
    success: (function(resposta){
        if (resposta === "true"){
            if (base64 != ""){
                $.ajax({
                    url: "/UpDeliveryMaven/ControllerProfile",
                    method: "POST",
                    async: false,
                    data: {method: "atualizarImagem", base64: base64},
                    success: (function(resposta){
                        location.reload();
                    })});
            }
            location.reload();

        }else{
            criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
        }
    })});
}

function validarSenha(senha){
    var resultado = "";
    var resultadoSalvar = "";
    $.ajax({
    url: "/UpDeliveryMaven/ControllerProfile",
    method: "GET",
    async: false,
    data: {method: "validarSenha", senha: senha},
    success: (function(resposta){
        resultado = resposta;
    })});

    return resultado;
}

function abrirApagarConta(){
	solicitarSenha();
	var button = document.getElementById("continuar")
    button.setAttribute("onclick", "apagarConta()");
}
function fecharPopUp(){
    document.getElementById("blur").style.visibility = "hidden";
    document.getElementById("senhaNova").style.visibility = "";
    document.getElementById("confirmarSenha").style.visibility = "";
}