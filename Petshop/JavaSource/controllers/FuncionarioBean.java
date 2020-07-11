package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.FuncionarioDAO;
import models.Funcionario;

@Named
@SessionScoped
public class FuncionarioBean implements Serializable {

	// Constructor
	public FuncionarioBean() {
		funcionario = new Funcionario();
		funcionarios = new ArrayList<Funcionario>();
	}

	// Attributes
	private static final long serialVersionUID = 7192027294301769662L;
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;

	// Actions
	public String detalhar() {
		int idFuncionario = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFuncionario"));
		funcionario = FuncionarioDAO.buscarPorId(idFuncionario);
		return "AlterarFuncionario.xhtml?faces-redirect=true";
	}

	public String alterar() {
		FuncionarioDAO.alterar(funcionario);
		funcionario = new Funcionario();
		return "GerenciarFuncionario.xhtml?faces-redirect=true";
	}

	// Getters & Setters
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		funcionarios = FuncionarioDAO.listar();
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String cadastrar() {
		FuncionarioDAO.cadastrar(funcionario);
		funcionario = new Funcionario();
		return "GerenciarFuncionario.xhtml?faces-redirect=true";
	}

}
