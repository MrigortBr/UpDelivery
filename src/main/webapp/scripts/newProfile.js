base64 = "";

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


