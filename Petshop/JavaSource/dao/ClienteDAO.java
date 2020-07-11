package dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Cliente;
import utils.JPAUtils;

public class ClienteDAO {

	// Cadastrar Cliente
	public static void cadastrar(Cliente cliente) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}

	public static List<Cliente> listar() {
		EntityManager em = JPAUtils.getInstance();
		List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
		return clientes;
	}

	// Alterar Cliente
	public static void alterar(Cliente cliente) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}

	// Buscar Cliente por ID
	public static Cliente buscarPorId(int id) {
		EntityManager em = JPAUtils.getInstance();
		return em.find(Cliente.class, id);
	}

}
