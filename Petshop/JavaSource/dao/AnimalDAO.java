package dao;

import java.util.List;

import javax.persistence.EntityManager;

import models.Animal;
import utils.JPAUtils;

public class AnimalDAO {

	// Cadastrar Animal
	public static void cadastrar(Animal animal) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.persist(animal);
		em.getTransaction().commit();
	}

	// Listar Animais
	public static List<Animal> listar() {
		EntityManager em = JPAUtils.getInstance();
		List<Animal> animais = em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
		return animais;
	}

	// Remover Animal
//	public static void remover(Animal animal) {
//		EntityManager em = JPAUtils.getInstance();
//		em.getTransaction().begin();
//		em.remove(em.merge(animal));
//		em.getTransaction().commit();
//	}

	// Alterar Animal
	public static void alterar(Animal animal) {
		EntityManager em = JPAUtils.getInstance();
		em.getTransaction().begin();
		em.merge(animal);
		em.getTransaction().commit();
	}

	// Buscar Animal por ID
	public static Animal buscarPorId(int id) {
		EntityManager em = JPAUtils.getInstance();
		return em.find(Animal.class, id);
	}

}
