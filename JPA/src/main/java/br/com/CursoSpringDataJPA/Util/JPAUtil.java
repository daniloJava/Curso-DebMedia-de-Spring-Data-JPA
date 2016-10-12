package br.com.CursoSpringDataJPA.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Classe do Padrão Singleton para criação de 1 unico 
 * objeto da classe.
 * 
 * @author silvad2
 *
 */
public class JPAUtil {

	private EntityManagerFactory factory;
	private static JPAUtil instance;
	
	/**Construtor privado para não ser acessado fora da classe, 
	 * e inicializado a unidade de persistencia que está configurado no persistence.xml
	 * 
	 */
	private JPAUtil(){
		this.factory = new Persistence().createEntityManagerFactory("Aula05");
	}
	
	/**Forçando a sincronização atraves,
	 * de uma forma que se não tiver uma instancia, criar uma 
	 * senão retornar a mesma instancia
	 * 
	 * @return JPAUtil - a propria instancia da classe.
	 */
	public static synchronized JPAUtil getInstance(){
		if(instance == null)
			instance = new JPAUtil();
		return instance;
	}
	
	/**Retornar uma conexão.
	 * 
	 * @return EntityManager - 
	 */
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
}

