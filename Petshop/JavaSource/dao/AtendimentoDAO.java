package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import models.Atendimento;
import models.Funcionario;
import utils.JPAUtils;

public class AtendimentoDAO {

	// Cadastrar Atendimento
	public static boolean cadastrar(Atendimento atendimento) {
		if (buscarPorHoraDataFuncionario(atendimento.getHora(), atendimento.getData(),
				atendimento.getFuncionario()) == null) {
			EntityManager em = JPAUtils.getInstance();
			em.getTransaction().begin();
			em.persist(atendimento);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	// Buscar Agenda do Funcionário
	public static Atendimento buscarPorHoraDataFuncionario(Date horaNova, Date dataNova, Funcionario funcionario) {
		try {
			EntityManager em = JPAUtils.getInstance();
			TypedQuery<Atendimento> atendimento = em.createQuery(
					"SELECT at FROM Atendimento at WHERE at.data = :dataNova AND at.hora = :horaNova AND at.funcionario = :idFuncionario",
					Atendimento.class).setParameter("dataNova", dataNova).setParameter("horaNova", horaNova)
					.setParameter("idFuncionario", funcionario);
			return atendimento.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// Listar Atendimentos
	public static List<Atendimento> listar() {
		EntityManager em = JPAUtils.getInstance();
		List<Atendimento> atendimentos = em.createQuery("SELECT at FROM Atendimento at", Atendimento.class)
				.getResultList();
		return atendimentos;
	}

	// Remover Atendimento
	public static void remover(Atendimento atendimento) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(atendimento));
		em.getTransaction().commit();
	}

	// Remover Atendimento Data
	public static Atendimento removerPorData(int id) {
		try {
			EntityManager em = JPAUtils.getInstance();
			Date dateTime = new Date();
			Atendimento at = null;

			TypedQuery<Atendimento> atendimento = em
					.createQuery("SELECT at FROM Atendimento at WHERE at.id = :id AND at.data > :dateTime",
							Atendimento.class)
					.setParameter("id", id).setParameter("dateTime", dateTime);
			em.getTransaction().begin();
			at = (Atendimento) atendimento.getSingleResult();
			em.remove(em.merge(at));
			em.getTransaction().commit();
			return at;
		} catch (NoResultException e) {
			return null;
		}

	}

	// Alterar Atendimento
	public static void alterar(Atendimento funcionario) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.merge(funcionario);
		em.getTransaction().commit();
	}

	// Buscar Atendimento por ID
	public static Atendimento buscarPorId(int id) {
		EntityManager em = JPAUtils.getInstance();
		return em.find(Atendimento.class, id);
	}

}
