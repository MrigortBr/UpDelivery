function isCPF(cpf){
    cpf = cpf.replace(/\.|-/g, "")

    soma = 0
    soma += cpf[0] * 10;
    soma += cpf[1] * 9;
    soma += cpf[2] * 8;
    soma += cpf[3] * 7;
    soma += cpf[4] * 6;
    soma += cpf[5] * 5;
    soma += cpf[6] * 4;
    soma += cpf[7] * 3;
    soma += cpf[8] * 2;
    soma = (soma * 10) % 11;
    if(soma ==10 || soma == 11) soma = 0;
    if (soma != cpf[9]){
        criarErro("CPF Invalido")
        return false;
    }

    soma = 0
    soma += cpf[0] * 11;
    soma += cpf[1] * 10;
    soma += cpf[2] * 9;
    soma += cpf[3] * 8;
    soma += cpf[4] * 7;
    soma += cpf[5] * 6;
    soma += cpf[6] * 5;
    soma += cpf[7] * 4;
    soma += cpf[8] * 3;
    soma += cpf[9] * 2;
    soma = (soma * 10) % 11;
    if(soma ==10 || soma == 11) soma = 0;

    if (soma != cpf[10]){
        criarErro("O CPF digitado � invalido")
        return false;
    } 

    return true;
}

function isEmail(value){
    let emailPattern = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
    if (emailPattern.test(value) == true){
        return true
    }else{
        criarErro("O Email digitado é invalido")
        return false
    }
}

function isCelular(value){
    let pattern = /^[0-9]+$/;
    if (pattern.test(value) == true && value.length >= 11){
        return true;
    }else{
        criarErro("O Telefone digitado é invalido\nEx: 00 9 1234-1234")
        return false;
    }
}

function senhaConfirma(senha, confirmacao){
    if (senha === confirmacao){
        return true;
    }else{
        criarErro("As senhas não são iguais")
        return false;
    }
}

function criarErro(message){
	console.log(message)
    document.getElementById("principal").style.transition = ".5s";
    document.getElementById("principal").style.borderRadius = "0px"
    document.getElementById("principal").style.visibility = "visible";
    document.getElementById("principal").style.marginLeft = "2%";
    document.getElementById("principal").style.borderRadius = "25px";
    document.getElementById("titulo").textContent = "Erro Detectado";
    console.log(document.getElementById("erro").textContent)
    document.getElementById("erro").textContent = message;
    console.log(document.getElementById("erro").textContent)

    document.getElementById("img2Path").style.fill = "red"; 
    document.getElementById("img2Path").style.stroke = "red";
    var time = setTimeout(esconderAlert, 5000);
    console.log()
}

function esconderAlert(){
    document.getElementById("principal").style.visibility = "hidden";
    document.getElementById("principal").style.marginLeft = "0%"
    time = null;
}

function abrirConfiguracoes(){
    document.getElementById("configPerfil").style.visibility = "visible";
    document.getElementById("configUsuario").style.outline = "2px solid gray"
}


