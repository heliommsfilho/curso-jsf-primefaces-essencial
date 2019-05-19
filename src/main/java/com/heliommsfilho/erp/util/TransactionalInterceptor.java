package com.heliommsfilho.erp.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional /* Intercepta classes/métodos com esta anotação */
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = -6578970197499153627L;
	
	@Inject
	private EntityManager manager;
	
	/* Este método executa antes do método interceptado */
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean transacaoCriadaPeloInterceptador = false;
		
		try {
			if (!trx.isActive()) {
				
				/* Se houver alguma transação que não foi criada pelo interceptador
				 * a abertura e o fechamento descarta as alterações. Isso evita que
				 * ao realizar o commit na transação do interceptador sejam confirmadas
				 * também coisas de transações não gerenciadas pelo interceptador. 
				 * */
				trx.begin();
				trx.rollback();
				
				/* Abre a transação e marca que foi criada pelo interceptador*/
				trx.begin();
				
				transacaoCriadaPeloInterceptador = true;
			}
			
			/* Invoca o método que foi interceptado */
			return context.proceed();
		} catch(Exception e) {
			
			/* Se houve algum erro no método interceptado e a transação foi
			 * criada pelo interceptador, faz rollback 
			 * */
			if (trx != null && transacaoCriadaPeloInterceptador) {
				trx.rollback();
			}
			
			throw e;
		} finally {
			
			/* Se não houve erro, faz o commit (se cair no catch, isActive() estará false
			 * e a transação não será confirmada mesmo com o finally) 
			 * */
			if (trx != null && trx.isActive() && transacaoCriadaPeloInterceptador) {
				trx.commit();
			}
		}
	}
}
