base64 = null

function uploadFile(id){
    var target = document.getElementById("fotoProduto"+id);

    var file = document.getElementById("file"+id).files[0];
    
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


function alterarDados(id){
    document.getElementById("salvar"+id).style.visibility = "visible"
}

function alterarFotoProduto(id){
    document.getElementById("file"+id).click()
    alterarDados(id);
}


function transformarBase64(id){
    const getBase64StringFromDataURL = (dataURL) =>
    dataURL.replace('data:', '').replace(/^.+,/, '');

    const image = document.getElementById("fotoProduto"+id)

    const reader = new FileReader();

    fetch(image.src)
        .then((res) => res.blob())
        .then((blob) => {
            const reader = new FileReader();
            reader.onloadend = () => {
                base64 = getBase64StringFromDataURL(reader.result);
            };
            reader.readAsDataURL(blob);
        });


}

function deletar(id){
    var fundo = document.getElementById("produto"+id)
    var height = fundo.clientHeight
    
    fundo.style.margin = "auto"
    fundo.innerHTML = ""
    fundo.style.transition = "0.5s"
    fundo.style.width = "2px"
    fundo.style.height = height+"px"
    setTimeout(() => {
        fundo.remove()
    }, 500);

    $.ajax({
        url: "/UpDeliveryMaven/ControllerCardapio",
        method: "GET",
        async: false,
        data: {method: "deletarProduto", id: id},
        success: (function(resposta){
            if (resposta === "true"){
            }else{
                criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            }
        })});
}

function limpar(id){
    var img = document.getElementById("fotoProduto"+id)
    var nome = document.getElementById("nomeProduto"+id);
    var descricao = document.getElementById("descricaoProduto"+id);
    var tipo =  document.getElementById("tipoProduto"+id);
    var preco = document.getElementById("precoProduto"+id);
    var desconto = document.getElementById("desconto"+id);

    img.src = "produtos/image.png"
    nome.value = "";
    descricao.value = "";
    tipo.value = "";
    preco.value = "";
    desconto.value = "";



}

function atualizar(id){
    var img = document.getElementById("fotoProduto"+id)
    var nome = document.getElementById("nomeProduto"+id);
    var descricao = document.getElementById("descricaoProduto"+id);
    var tipo =  document.getElementById("tipoProduto"+id);
    var preco = document.getElementById("precoProduto"+id);
    var desconto = document.getElementById("desconto"+id);
    transformarBase64(id)

    if (nome.value != "" && descricao.value != "" && tipo.value != preco.value.replace(",", ".") != "" && desconto.value != ""){
        intervalo = setInterval(function(){
            if(base64 == null){ 
                console.log("atualizando"); 
            }else{
                clearInterval(intervalo)
                atualizarParteDois(id)
            }
        },1000); 
    }else{
        criarErro("Preencha todos os campos")
    }
}

function atualizarParteDois(id){
    var nome = document.getElementById("nomeProduto"+id);
    var descricao = document.getElementById("descricaoProduto"+id);
    var tipo =  document.getElementById("tipoProduto"+id);
    var preco = document.getElementById("precoProduto"+id);
    var desconto = document.getElementById("desconto"+id);
    $.ajax({
        url: "/UpDeliveryMaven/ControllerCardapio",
        method: "GET",
        async: false,
        data: {method: "atualizarProduto", nome: nome.value, descricao: descricao.value, tipo: tipo.value, preco: preco.value.replace(",", "."), desconto: (desconto.value.replace("%", ""))/100, id: id},
        success: (function(resposta){
            if (resposta === "true"){
                if (base64 != ""){
                    $.ajax({
                        url: "/UpDeliveryMaven/ControllerCardapio",
                        method: "POST",
                        async: false,
                        data: {method: "atualizarImagem", base64: base64, id: id, nome: nome.value},
                        success: (function(resposta){
                            console.log(resposta)
                        })});
                }    
            }else{
                criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            }
        })});
}

function adicionar(id){
    var nome = document.getElementById("nomeProduto"+id);
    var descricao = document.getElementById("descricaoProduto"+id);
    var tipo =  document.getElementById("tipoProduto"+id);
    var preco = document.getElementById("precoProduto"+id);
    var desconto = document.getElementById("desconto"+id);
    $.ajax({
        url: "/UpDeliveryMaven/ControllerCardapio",
        method: "GET",
        async: false,
        data: {method: "registrarProduto", nome: nome.value, descricao: descricao.value, tipo: tipo.value, preco: preco.value.replace(",", "."), desconto: (desconto.value.replace("%", ""))/100},
        success: (function(resposta){
            if (resposta === "true"){
                location.reload();
            }else{
                criarErro("Não foi possivel atualizar o registro tente novamente mais tarde")
            }
        })});
}