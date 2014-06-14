package br.com.eleicoestransparentes.persistence.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Candidato")
public class Candidato extends PessoaFisica{
	
	@DatabaseField(canBeNull = false, unique = false)
	protected String numero;

	@DatabaseField
	protected String cargo;

	@DatabaseField
	protected String UF;

	@DatabaseField
	protected String municipio;

	@DatabaseField(foreign = true)
	protected Partido partido;
	
	@DatabaseField(unique=true)
	protected String sequencialCandidato;
	
	//TODO: Rever como irá fazer com a relação entre candidatos e eleição
//	@DatabaseField
//	protected List<Eleicao> eleicoes;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String getSequencialCandidato() {
		return sequencialCandidato;
	}

	public void setSequencialCandidato(String sequencialCandidato) {
		this.sequencialCandidato = sequencialCandidato;
	}
	
	
	
	

}