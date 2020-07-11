package dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Procedimento;
import utils.JPAUtils;

public class ProcedimentoDAO {

	// Cadastrar Atendimento
	public static void cadastrar(Procedimento procedimento) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.persist(procedimento);
		em.getTransaction().commit();
	}

	// Listar
	public static List<Procedimento> listar() {
		EntityManager em = JPAUtils.getInstance();
		List<Procedimento> procedimentos = em.createQuery("SELECT p FROM Procedimento p", Procedimento.class)
				.getResultList();
		return procedimentos;
	}

	// Alterar Procedimento
	public static void alterar(Procedimento procedimento) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.merge(procedimento);
		em.getTransaction().commit();
	}

	// Buscar Atendimento por ID
	public static Procedimento buscarPorId(int id) {
		EntityManager em = JPAUtils.getInstance();
		return em.find(Procedimento.class, id);
	}

	// Remover Atendimento
//		public static void remover(Procedimento procedimento) {
//			EntityManager em = JPAUtils.getInstance();
//			em.getTransaction().begin();
//			em.remove(em.merge(procedimento));
//			em.getTransaction().commit();
//		}

}
