package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.ClienteDAO;
import models.Cliente;

@Named
@SessionScoped
public class ClienteBean implements Serializable {

	// Constructor
	public ClienteBean() {
		cliente = new Cliente();
		clientes = new ArrayList<Cliente>();
	}

	// Attributes
	private static final long serialVersionUID = -7684274722058782894L;
	private Cliente cliente;
	private List<Cliente> clientes;

	// Actions
	public String cadastrar() {
		ClienteDAO.cadastrar(cliente);
		cliente = new Cliente();
		return "GerenciarCliente.xhtml?faces-redirect=true";
	}

	public String detalhar() {
		int idCliente = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCliente"));
		cliente = ClienteDAO.buscarPorId(idCliente);
		return "AlterarCliente.xhtml?faces-redirect=true";
	}

	public String alterar() {
		ClienteDAO.alterar(cliente);
		cliente = new Cliente();
		return "GerenciarCliente.xhtml?faces-redirect=true";
	}

//	public void remover() {
//		ClienteDAO.remover(cliente);
//		// return "GerenciarCliente.xhtml?faces-redirect=true"; // Escrever URL e deixar como public String
//	}

	// Getters & Setters
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		clientes = ClienteDAO.listar();
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
