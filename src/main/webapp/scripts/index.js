var ponteiro = 0
var ultimoValue = 0
var widget = ""

function clicar(name){
    widget = name
    ponteiro = event.pageX
}

function move(){
    var tamanhoTotalBarra = (document.getElementById(widget).scrollHeight)*0.1
    if (event.buttons == 1){
        if (ultimoValue > event.pageX){
            document.getElementById(widget).scrollLeft += tamanhoTotalBarra
        }else if(ultimoValue < event.pageX){
            document.getElementById(widget).scrollLeft -= tamanhoTotalBarra
        }
        ultimoValue = event.pageX
    }
}

function teste(){
    document.getElementById(widget).style.cursor = "pointer"		
}



function buscarPedido(){
	var input = document.getElementById("input").value ;
	window.location.replace("buscarProduto.jsp?busca="+input);
}