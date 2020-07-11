package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Animal implements Serializable {

	public Animal() {
		cliente = new Cliente();
	}

	// Atributos
	private static final long serialVersionUID = -6408746100007504284L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animals_seq_gen")
	@SequenceGenerator(name = "animals_seq_gen", sequenceName = "animals_id_seq")
	private int id;
	private String nome;
	private String tipo;
	@ManyToOne
	private Cliente cliente;
//	@OneToMany
//	private List<Atendimento> atendimentos;

	// Getter & Seters
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
