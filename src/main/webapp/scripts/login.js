function logar(){
    var senha = document.querySelector("#senha").value
    var login = document.querySelector("#email").value
    if (senha !== "" && login !== ""){
        $.ajax({
            url: "/UpDeliveryMaven/ControllerLogin",
            method: "GET",
            async: false,
            data: {method: "login", login: login, senha: senha},
            success: (function(resposta){
                console.log(resposta);
                if (resposta === "true"){
                    window.location.replace("index.jsp")
                }else{
                    criarErro("Senha ou Email invalidos")
                }
            })});	
    }else{
        criarErro("Preencha todos os campos")
    }
}




