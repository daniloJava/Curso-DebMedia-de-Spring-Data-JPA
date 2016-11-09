/**quando le o documentom e le chama nosso função criada.
 * Exemplificando o metod on, ele recebe a ação, recebe qual a proprierada e 
 * uma função.
 * 
 * 
 */
$(document).ready(function () {
	$( "#save-ajax" ).submit(function( event ) {
		  event.preventDefault();	
		  
		  //para que o Spring security entenda a informação por ajax é precisso colocar  
		  var token = $("input[name='_csrf']").atrr('value');
		  
		  
		  $.post('/blog/postagem/ajax/save?_csrf='+token, $(this).serialize() )
		  	.done(function(result){
		  		/**Restando o se o resultado
		  		 * da classe PostagemAjaxValidator deu Erro ou não
		  		 */
		  		if(result.status != 'FAIL'){
		  		
				  	$("#info").empty().append(
				  			"<p> Postagem salva com sucesso</p>" +
				  			"<p> Abrir Postagem: <a href='/blog/' " + result.postagem.permaLink + "'>" 
				  			 + result.postagem.titulo +"</a> </p>"
				  		);	
				  	
				  		$('#save-ajax').each(function(){
				  			this.reset();
				  		});
				 $('#titulo-error').empty();
				 $('#texto-error').empty()
				  		
				  		
		  		}else{
		  			$('#titulo-error').empty().append(result.tituloError);
		  			$('#texto-error').empty().append(result.textoError);
		  			
		  		}
		  	})
		  	.fail(function(error){
		  		$("#info").empty().append("<ṕ>Error Status:" +
		  				error,status + 
		  				error.statusText + "</p>")
		  	});
		});
	
	
	$(document).on('click', 'button[id*="button_"]', function (a){
		var pageNumber = $(this).val();
		tbody(pageNumber);
	});
	
	/**para cada tecla do telado precionada.
	 * ele recupera o valor texta com a expreção regular
	 * e chava a funçao search passando o valor
	 * 
	 */
	$('#search').keyup(function (){
		var value = $(this).val();
		
		var exp = new RegExp('^[a-zA-Z0-9]');
		
		if (exp.test(value)){
			search(value);
		}else{
			tbody(1);
		}
			
		
		
		
	});
	
});

function search(value){
	var url 
	var autorID = $('#table-ajax').attr('title');

	if(autorID > 0 ){
		url = "/blog/postagem/ajax/autor/" + autorID + "/titulo/" + value + "/page/1";
	}else{
		url = "/blog/postagem/ajax/titulo/" + value + "/page/1";
	}
	$('#tableAjax').load(url, function(response, status, xhr) {
		if (status == "error") {
			var msg = "Sorry but there was an error: ";
			$("#info").html(msg + xhr.status + " " + xhr.statusText);
		}
		
	});
}


/**Função JS pra criar a paginação via Ajax
 * 
 * ele recupera atravez do id uma resposta da função,
 * um status que deu errado ou não, e um Xhr que é a mensagem do erro
 * 
 * se der erro ele apresenta a mensagem de erro do Id #info
 * 
 * caso der tudo certo, ele reupera o botal e desabilita atravez do .attr
 * 
 * @param page
 * @returns
 */
function tbody(page){
	var url = "/blog/postagem/ajax/page/" + page;
	
	//recupera o valor que está no campo de busca
	var titulo = $('#search').val();
	
	//recebe o id do autror, para filtrar as postagens somente do Autor
	var autorID = $('#table-ajax').attr('title');
	
	if(autorID > 0 && titulo.length > 0){
		url = "/blog/postagem/ajax/autor/" + autorID + "/titulo/" + titulo + "/page/" + page;
	}
	
	else{
		if(titulo.length > 0){//valida se tem alguma coisa preenchida,
			//se tiver algo preenchido ele leva pra outra Url, passando o valor do titulo e o numero d apagina
			url = "/blog/postagem/ajax/titulo/" + titulo + "/page/" + page;
		} else if(autorID > 0){//valida se é um autor ou não
			url = "/blog/postagem/ajax/autor/"+ autorID + "/page/" + page;
		}else{
			// se estiver tudo vazio então continua com o fluxo normal.
			url = "/blog/postagem/ajax/page/" + page;
		}
	}
	$("#tableAjax").load(url, function(response, status, xhr) {
		if (status == "error") {
			var msg = "Sorry but there was an error: ";
			$("#info").html(msg + xhr.status + " " + xhr.statusText);
		}
		
//		if(status == "success"){
//			$('button').each(function() {
//				
//				var id = '#' + $(this).attr('id');
//				if($(id).attr('disabled') == 'disabled'){
//					$(id).removeAttr('disabled');
//				}
//			});
//		$('#button_' + page).attr('disabled', 'disabled');
//		}
	});
}
