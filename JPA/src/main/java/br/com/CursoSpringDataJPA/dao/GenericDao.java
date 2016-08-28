package br.com.CursoSpringDataJPA.dao;

import java.io.Serializable;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.action.internal.EntityIdentityInsertAction;
import org.hibernate.type.SerializableType;

import br.com.CursoSpringDataJPA.Util.JPAUtil;

/**Extendendo Serializable, quando trabalha com javaPersistence, precisa ser 
 * do tipo Serializable.
 * 
 * 
 * @author silvad2
 *
 * @param <T>
 */
public abstract class GenericDao<T extends Serializable>{

	private Class<T> aClass;
	
	/**Recebendo e inicializando o tipo da Classe.
	 * 
	 * @param aClass
	 */
	protected GenericDao(Class<T> aClass){
		this.aClass = aClass;
	}
	
	/**Só sendo permitido acesso a esse metodo as classes
	 * que estenderem esse metodo.
	 * 
	 * 
	 * @return EntityManager- instancia do JPAUtil Testando com o .getInstance() se já existe, e um EntityManager. 
	 */
	protected EntityManager getEntityManager(){
		return JPAUtil.getInstance().getEntityManager();
	}
	
	/**retorna a contagem de registro do banco
	 * 
	 * @return - a ontagem dos dados
	 */
	public long count() {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin(); // iniciando a transação

		Query query = manager.createQuery("select count(c) from " + aClass.getSimpleName() + "c");

		long count = (Long) query.getSingleResult();

		return count;
	}
	
	/**Procura somente 1 resultado
	 * 
	 * @param Jpql
	 * @param params
	 * @return
	 */
	public T findOne(String Jpql, Object... params) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin(); // iniciando a transação

		Query query = manager.createQuery(Jpql);

		// For para caminhar com os parametros passados
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		T entity = (T) query.getSingleResult();
		manager.getTransaction().commit();
		manager.close(); // liberar o objeto da memoria

		return entity;
	}
	
	
	/**Criar uma lista apartir de uma queri passada por parametro
	 * 
	 * @param Jpql String - Recebe a string JPQL
	 * @param params - Lista de parametros para consulta
	 * @return List<T> - Lista dos resultados.
	 */
	public List<T> find(String Jpql, Object... params){
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();   //iniciando a transação
		
		
		Query query = manager.createQuery(Jpql);
		
		 //For para caminhar com os parametros passados
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		
		List<T> entities = query.getResultList();
		manager.getTransaction().commit(); 
		manager.close(); //liberar o objeto da memoria
		
		return entities;
	}
	/** Retorna todos os dados do banco.
	 * 
	 * @return List<T> - de dados do banco
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		
		//recebe uma conexão 
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();   //iniciando a transação
		
		//informando uma query de JPQL trabalhando com classes e suas propriedades
		//.getSimpleName() quecupera o Nome da classe
		Query query = manager.createQuery("from " + aClass.getSimpleName());
		//recupera dentro da lista
		List<T> entities = query.getResultList();
		
		manager.getTransaction().commit(); // fixar a transação.
		manager.close(); //liberar o objeto da memoria
		
		return entities;
		
	}
	
	
	/**Consulta por Id para retornar um único objeto
	 * 
	 * @return - a classe completa 
	 */
	public T findById(Long id){
		//recebe uma conexão 
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();   //iniciando a transação
		T entity  = (T) manager.find(aClass, id);	//recuperando Objeto completo
		manager.getTransaction().commit(); // fixar a transação.
		manager.close(); //liberar o objeto da memoria
		
		return entity;
	}
	
	/**Metodo para salvar a entidade.
	 * 
	 * @param entity - será recebido apartir do generico da classe.
	 */
	public void save(T entity){
		
		//recebe uma conexão 
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();   //iniciando a transação
		manager.persist(entity);  //persistindo o Objeto.
		manager.getTransaction().commit(); // fixar a transação.
		manager.close(); //liberar o objeto da memoria
		
	}
	
	/**Metodo para atualizar a classe
	 * 
	 * @param entity - será recebido apartir do generico da classe.
	 */
	public void update(T entity){
		//recebe uma conexão 
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();   //iniciando a transação
		manager.merge(entity);  //atualizar no banco atravez do objeto.
		manager.getTransaction().commit(); // fixar a transação.
		manager.close(); //liberar o objeto da memoria
	}
	
	
	/**Metodo para deletar 	o objeto do banco
	 * 
	 * @param id - receberar o ID paara a conculta antes da deleção
	 */
	public void delete(Long id){
		//recebe uma conexão 
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();   //iniciando a transação
		
		//manager.remove(manager.find(aClass, id));  //Consulta pelo Id e depois a deleção.
		//a Diferença do find é que ele busca todo o conteudo
		//e o getReference busca pela referencia do objeto pelo Id.
		manager.remove(manager.getReference(aClass, id));// assim é mais rapido
		
		manager.getTransaction().commit(); // fixar a transação.
		manager.close(); //liberar o objeto da memoria
	}
	
}
