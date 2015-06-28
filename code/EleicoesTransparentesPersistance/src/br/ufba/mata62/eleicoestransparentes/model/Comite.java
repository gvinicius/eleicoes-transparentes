package br.ufba.mata62.eleicoestransparentes.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Comite")
public class Comite {

	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull = false)
	private String UF;

	@DatabaseField
	private String municipio;

	@DatabaseField
	private String tipo;
	
	@DatabaseField(foreign = true)
	private Partido partido;
	
	@DatabaseField(unique = true)
	private String sequencialComite;
	
	@DatabaseField(foreign = true)
	private AgenteEleitoral agenteEleitoral;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String getSequencialComite() {
		return sequencialComite;
	}

	public void setSequencialComite(String sequencialComite) {
		this.sequencialComite = sequencialComite;
	}

	public AgenteEleitoral getAgenteEleitoral() {
		return agenteEleitoral;
	}

	public void setAgenteEleitoral(AgenteEleitoral agenteEleitoral) {
		this.agenteEleitoral = agenteEleitoral;
	}

}
