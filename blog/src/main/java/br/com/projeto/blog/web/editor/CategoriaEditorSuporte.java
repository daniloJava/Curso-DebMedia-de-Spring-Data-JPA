package br.com.projeto.blog.web.editor;

import java.util.Collection;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import br.com.projeto.blog.entity.Categoria;
import br.com.projeto.blog.service.CategoriaService;

/**Classe para o Spring Reconhecer que quando ele precisar
 * recuperar uma lista de categorias sem o ID ele irá buscar
 * essa lasse e converter os elementos pelo metodo
 * 
 * ConvertElement
 * 
 * CustomCollectionEditor - transforma cada um dos Ids selecionados na pagina
 * para um Id de Categoria
 * 
 * 
 * 
 * @author Danilo Silva
 *
 */
public class CategoriaEditorSuporte extends CustomCollectionEditor{
	
	private CategoriaService service;
	
	public CategoriaEditorSuporte(Class<? extends Collection> collectionType,
			CategoriaService service) {
		super(collectionType);
		this.service = service;
		
	}

	/**Classe responsavel para recuperar o objeto atravez do ID
	 * 
	 */
	@Override
	protected Object convertElement(Object element) {
		
		Long id = Long.valueOf((String) element);
		Categoria categoria = service.findById(id);
		
		return super.convertElement(categoria);
	}	

	
	
}
