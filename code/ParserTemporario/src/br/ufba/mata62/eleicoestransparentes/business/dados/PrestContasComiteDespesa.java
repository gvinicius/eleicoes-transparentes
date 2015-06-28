package br.ufba.mata62.eleicoestransparentes.business.dados;

import br.ufba.mata62.eleicoestransparentes.business.annotations.Name;

public class PrestContasComiteDespesa {
	@Name("Data e hora")
	private String dataHora;
	@Name("Sequencial Comite")
	private String sequencialComite;
	@Name("UF")
	private String UF;
	@Name("Numero UE")
	private String numeroUE;
	@Name("Municipio")
	private String municipio;
	@Name("Tipo Comite")
	private String tipoComite;
	@Name("Sigla Partido")
	private String siglaPartido;
	@Name("Tipo do documento")
	private String tipoDocumento;
	@Name("Numero do documento")
	private String numeroDocumento;
	@Name("CPF/CNPJ do fornecedor")
	private String CPFCNPJFornecedor;
	@Name("Nome do fornecedor")
	private String nomeFornecedor;
	@Name("Nome Receita Fornecedor")
	private String nomeReceitaFornecedor;
	@Name("Cod setor economico fornecedor")
	private String codSetorEconomicoFornecedor;
	@Name("Setor economico fornecedor")
	private String setorEconomicoFornecedor;
	@Name("Data da despesa")
	private String dataDespesa;
	@Name("Valor despesa")
	private String valorDespesa;
	@Name("Tipo despesa")
	private String tipoDespesa;
	@Name("Descricao da despesa")
	private String descricaoDespesa;
	
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getSequencialComite() {
		return sequencialComite;
	}
	public void setSequencialComite(String sequencialComite) {
		this.sequencialComite = sequencialComite;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getNumeroUE() {
		return numeroUE;
	}
	public void setNumeroUE(String numeroUE) {
		this.numeroUE = numeroUE;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getTipoComite() {
		return tipoComite;
	}
	public void setTipoComite(String tipoComite) {
		this.tipoComite = tipoComite;
	}
	public String getSiglaPartido() {
		return siglaPartido;
	}
	public void setSiglaPartido(String siglaPartido) {
		this.siglaPartido = siglaPartido;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getCPFCNPJFornecedor() {
		return CPFCNPJFornecedor;
	}
	public void setCPFCNPJFornecedor(String cPFCNPJFornecedor) {
		CPFCNPJFornecedor = cPFCNPJFornecedor;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getNomeReceitaFornecedor() {
		return nomeReceitaFornecedor;
	}
	public void setNomeReceitaFornecedor(String nomeReceitaFornecedor) {
		this.nomeReceitaFornecedor = nomeReceitaFornecedor;
	}
	public String getCodSetorEconomicoFornecedor() {
		return codSetorEconomicoFornecedor;
	}
	public void setCodSetorEconomicoFornecedor(String codSetorEconomicoFornecedor) {
		this.codSetorEconomicoFornecedor = codSetorEconomicoFornecedor;
	}
	public String getSetorEconomicoFornecedor() {
		return setorEconomicoFornecedor;
	}
	public void setSetorEconomicoFornecedor(String setorEconomicoFornecedor) {
		this.setorEconomicoFornecedor = setorEconomicoFornecedor;
	}
	public String getDataDespesa() {
		return dataDespesa;
	}
	public void setDataDespesa(String dataDespesa) {
		this.dataDespesa = dataDespesa;
	}
	public String getValorDespesa() {
		return valorDespesa;
	}
	public void setValorDespesa(String valorDespesa) {
		this.valorDespesa = valorDespesa;
	}
	public String getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}
	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}
	
}