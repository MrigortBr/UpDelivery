function adicionarPedido(){
    var carrinho = document.getElementById("carrinho");
    var carrinho2 = document.getElementById("carrinho2");
    var fundo = document.querySelector(".fundo-pagina");
    carrinho2.style.marginLeft = "-1000px"
    fundo.style.overflowX = "hidden" 
    fundo.style.overflowY = "hidden"
    adicionar() 
    setTimeout(function a(){
        carrinho.style.transition = "1s"
        carrinho.style.marginLeft = "2000px"
        carrinho2.style.visibility = "visible";
        carrinho2.style.transition = "1s"
        setTimeout(() => {
            carrinho.style.visibility = "hidden";
            carrinho2.style.marginLeft = "0"
            setTimeout(() => {
                carrinho.style.marginLeft = "0px"
                setTimeout(() => {
                    fundo.style.overflowX = "" 
                    fundo.style.overflowY = "" 
                }, 1000);
            }, 700);
        }, 50);
    }, 0)
    
}

function retirarPedido(){
    var carrinho = document.getElementById("carrinho2");
    var carrinho2 = document.getElementById("carrinho");
    var fundo = document.querySelector(".fundo-pagina");
    carrinho2.style.marginLeft = "-1000px"
    fundo.style.overflowX = "hidden" 
    fundo.style.overflowY = "hidden" 

    setTimeout(function a(){
        carrinho.style.transition = "1s"
        carrinho.style.marginLeft = "2000px"
        carrinho2.style.visibility = "visible";
        carrinho2.style.transition = "1s"
        setTimeout(() => {
            carrinho.style.visibility = "hidden";
            carrinho2.style.marginLeft = "0"
            setTimeout(() => {
                carrinho.style.marginLeft = "0px"
                setTimeout(() => {
                    fundo.style.overflowX = "" 
                    fundo.style.overflowY = "" 
                }, 1000);
            }, 700);
        }, 50);
    }, 0)


}

function adicionar(){
    var contador = document.getElementById("contador").textContent;
    document.getElementById("contador").textContent = parseInt(contador) + 1;
    var contador = document.getElementById("contador").textContent;
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("id")
    $.ajax({
        url: "/UpDeliveryMaven/ControllerProduto",
        method: "GET",
        async: false,
        data: {method: "adicionarCarrinho", id: id, quantidade: contador},
        success: (function(resposta){
            if (resposta === "true"){    
            }else{
                criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            }
        })});
}

function subtrair(){
    var contador = document.getElementById("contador").textContent;
    
    if ((parseInt(contador) - 1) > 0){
        document.getElementById("contador").textContent = parseInt(contador) - 1
    }else{
        retirarPedido()
    }

    var contador = document.getElementById("contador").textContent;
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("id")
    $.ajax({
        url: "/UpDeliveryMaven/ControllerProduto",
        method: "GET",
        async: false,
        data: {method: "retirarCarrinho", id: id, quantidade: contador},
        success: (function(resposta){
            if (resposta === "true"){    
            }else{
                criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            }
        })});
}