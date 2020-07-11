package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.AnimalDAO;
import dao.ClienteDAO;
import models.Animal;
import models.Cliente;

@Named
@SessionScoped
public class AnimalBean implements Serializable {

	// Constructor
	public AnimalBean() {
		animal = new Animal();
		animais = new ArrayList<Animal>();
		clientes = new ArrayList<Cliente>();
	}

	// Attributes
	private static final long serialVersionUID = 3007716755904845823L;
	private Animal animal;
	private List<Animal> animais;
	private List<Cliente> clientes;

	// Actions
	public String cadastrar() {
		AnimalDAO.cadastrar(animal);
		animal = new Animal();
		return "GerenciarAnimal.xhtml?faces-redirect=true";
	}

	public String detalhar() {
		int idCliente = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAnimal"));
		animal = AnimalDAO.buscarPorId(idCliente);
		return "AlterarAnimal.xhtml?faces-redirect=true";
	}

	public String alterar() {
		AnimalDAO.alterar(animal);
		animal = new Animal();
		return "GerenciarAnimal.xhtml?faces-redirect=true";
	}

	// Getter & Seters
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimais() {
		animais = AnimalDAO.listar();
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Cliente> getClientes() {
		clientes = ClienteDAO.listar();
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
