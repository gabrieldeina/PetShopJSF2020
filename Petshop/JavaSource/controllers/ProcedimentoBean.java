package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import dao.ProcedimentoDAO;
import models.Procedimento;

@Named
@SessionScoped
public class ProcedimentoBean implements Serializable {

	// Constructor
	public ProcedimentoBean() {
		procedimento = new Procedimento();
		procedimentos = new ArrayList<Procedimento>();
	}

	// Attributes
	private static final long serialVersionUID = 8525771838151482265L;
	private Procedimento procedimento;
	private List<Procedimento> procedimentos;

	// Actions
	public String cadastrar() {
		ProcedimentoDAO.cadastrar(procedimento);
		procedimento = new Procedimento();
		return "GerenciarProcedimento.xhtml?faces-redirect=true";
	}

	public String detalhar() {
		int idProcedimento = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProcedimento"));
		procedimento = ProcedimentoDAO.buscarPorId(idProcedimento);
		return "AlterarProcedimento.xhtml?faces-redirect=true";
	}

	public String alterar() {
		ProcedimentoDAO.alterar(procedimento);
		procedimento = new Procedimento();
		return "GerenciarProcedimento.xhtml?faces-redirect=true";
	}

	// Getters & Setters
	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public List<Procedimento> getProcedimentos() {
		procedimentos = ProcedimentoDAO.listar();
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}

}
