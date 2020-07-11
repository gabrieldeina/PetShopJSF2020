package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.AnimalDAO;
import dao.AtendimentoDAO;
import models.Animal;
import models.Atendimento;

@Named
@SessionScoped
public class AtendimentoBean implements Serializable {

	// Constructor
	public AtendimentoBean() {
		atendimento = new Atendimento();
		atendimentos = new ArrayList<Atendimento>();
		animais = new ArrayList<Animal>();
	}

	// Attributes
	private static final long serialVersionUID = 8121800699380651480L;
	private Atendimento atendimento;
	private List<Atendimento> atendimentos;
	private List<Animal> animais;

	// Actions
	public String cadastrar() {
		if (AtendimentoDAO.cadastrar(atendimento)) {
			atendimento = new Atendimento();
			return "GerenciarAtendimento.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
				"Funcionário já tem agendamento nesse dia e hora!"));
		return null;
	}

	public String alterar() {
		AtendimentoDAO.alterar(atendimento);
		atendimento = new Atendimento();
		return "AlterarAtendimento.xhtml?faces-redirect=true";
	}

	public String remover(Atendimento atendimento) {
		if (AtendimentoDAO.removerPorData(atendimento.getId()) != null) {
			System.out.println(atendimento.getId() + ": Atendimento Removido");
			return "GerenciarAtendimento.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção!",
				"Atendimentos do dia atual ou dias anteriores não podem ser removidos!"));
		return null;
	}

	public String detalhar() {
		int idAtendimento = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idAtendimento"));
		atendimento = AtendimentoDAO.buscarPorId(idAtendimento);
		return "AlterarAtendimento.xhtml?faces-redirect=true";
	}

	// Getters & Seters
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Atendimento> getAtendimentos() {
		atendimentos = AtendimentoDAO.listar();
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<Animal> getAnimais() {
		animais = AnimalDAO.listar();
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

}
