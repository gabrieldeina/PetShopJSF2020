package dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Funcionario;
import utils.JPAUtils;

public class FuncionarioDAO {

	// Cadastrar Funcionario
	public static void cadastrar(Funcionario funcionario) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.persist(funcionario);
		em.getTransaction().commit();
	}

	public static List<Funcionario> listar() {
		EntityManager em = JPAUtils.getInstance();
		List<Funcionario> funcionarios = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class)
				.getResultList();
		return funcionarios;
	}

	// Remover Funcionario
	public static void remover(Funcionario funcionario) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.remove(em.merge(funcionario));
		em.getTransaction().commit();
	}

	// Alterar Funcionario
	public static void alterar(Funcionario funcionario) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.merge(funcionario);
		em.getTransaction().commit();
	}

	// Buscar Funcionario por ID
	public static Funcionario buscarPorId(int id) {
		EntityManager em = JPAUtils.getInstance();
		return em.find(Funcionario.class, id);
	}

}
