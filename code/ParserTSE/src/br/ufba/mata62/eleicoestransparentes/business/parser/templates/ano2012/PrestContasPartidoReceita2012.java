package br.ufba.mata62.eleicoestransparentes.business.parser.templates.ano2012;

import br.ufba.mata62.eleicoestransparentes.business.annotations.Name;
import br.ufba.mata62.eleicoestransparentes.business.parser.templates.TemplateArquivo;
import br.ufba.mata62.eleicoestransparentes.utils.Path;

public class PrestContasPartidoReceita2012 extends TemplateArquivo{
	@Name("Data e hora")
	private String dataHora;
	@Name("Sequencial Partido")
	private String SequencialPartido;
	@Name("UF")
	private String UF;
	@Name("Numero UE")
	private String numeroUE;
	@Name("Municipio")
	private String Municipio;
	@Name("Tipo Partido")
	private String tipoPartido;
	@Name("Sigla Partido")
	private String siglaPartido;
	@Name("Tipo do documento")
	private String tipoDocumento;
	@Name("Numero do documento")
	private String numeroDocumento;
	@Name("CPF/CNPJ do doador")
	private String CPFCNPJDoador;
	@Name("Nome do doador")
	private String nomeDoador;
	@Name("Nome receita doador")
	private String nomeReceitaDoador;
	@Name("Sigla UE doador")
	private String siglaUEDoador;
	@Name("Numero partido doador")
	private String numeroPartidoDoador;
	@Name("Numero candidato doador")
	private String numeroCandidatoDoador;
	@Name("Cod setor economico doador")
	private String codSetorEconomicoDoador;
	@Name("Setor economico doador")
	private String setorEconomicoDoador;
	@Name("Data da receita")
	private String dataReceita;
	@Name("Valor receita")
	private String valorReceita;
	@Name("Tipo receita")
	private String tipoReceita;
	@Name("Fonte recurso")
	private String fonteRecurso;
	@Name("Especie recurso")
	private String especieRecurso;
	@Name("Descricao da receita")
	private String descricaoReceita;
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getSequencialPartido() {
		return SequencialPartido;
	}
	public void setSequencialPartido(String sequencialPartido) {
		SequencialPartido = sequencialPartido;
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
		return Municipio;
	}
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}
	public String getTipoPartido() {
		return tipoPartido;
	}
	public void setTipoPartido(String tipoPartido) {
		this.tipoPartido = tipoPartido;
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
	public String getCPFCNPJDoador() {
		return CPFCNPJDoador;
	}
	public void setCPFCNPJDoador(String cPFCNPJDoador) {
		CPFCNPJDoador = cPFCNPJDoador;
	}
	public String getNomeDoador() {
		return nomeDoador;
	}
	public void setNomeDoador(String nomeDoador) {
		this.nomeDoador = nomeDoador;
	}
	public String getNomeReceitaDoador() {
		return nomeReceitaDoador;
	}
	public void setNomeReceitaDoador(String nomeReceitaDoador) {
		this.nomeReceitaDoador = nomeReceitaDoador;
	}
	public String getSiglaUEDoador() {
		return siglaUEDoador;
	}
	public void setSiglaUEDoador(String siglaUEDoador) {
		this.siglaUEDoador = siglaUEDoador;
	}
	public String getNumeroPartidoDoador() {
		return numeroPartidoDoador;
	}
	public void setNumeroPartidoDoador(String numeroPartidoDoador) {
		this.numeroPartidoDoador = numeroPartidoDoador;
	}
	public String getNumeroCandidatoDoador() {
		return numeroCandidatoDoador;
	}
	public void setNumeroCandidatoDoador(String numeroCandidatoDoador) {
		this.numeroCandidatoDoador = numeroCandidatoDoador;
	}
	public String getCodSetorEconomicoDoador() {
		return codSetorEconomicoDoador;
	}
	public void setCodSetorEconomicoDoador(String codSetorEconomicoDoador) {
		this.codSetorEconomicoDoador = codSetorEconomicoDoador;
	}
	public String getSetorEconomicoDoador() {
		return setorEconomicoDoador;
	}
	public void setSetorEconomicoDoador(String setorEconomicoDoador) {
		this.setorEconomicoDoador = setorEconomicoDoador;
	}
	public String getDataReceita() {
		return dataReceita;
	}
	public void setDataReceita(String dataReceita) {
		this.dataReceita = dataReceita;
	}
	public String getValorReceita() {
		return valorReceita;
	}
	public void setValorReceita(String valorReceita) {
		this.valorReceita = valorReceita;
	}
	public String getTipoReceita() {
		return tipoReceita;
	}
	public void setTipoReceita(String tipoReceita) {
		this.tipoReceita = tipoReceita;
	}
	public String getFonteRecurso() {
		return fonteRecurso;
	}
	public void setFonteRecurso(String fonteRecurso) {
		this.fonteRecurso = fonteRecurso;
	}
	public String getEspecieRecurso() {
		return especieRecurso;
	}
	public void setEspecieRecurso(String especieRecurso) {
		this.especieRecurso = especieRecurso;
	}
	public String getDescricaoReceita() {
		return descricaoReceita;
	}
	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}
	
	@Override
	public String getNomeArquivo() {
		// TODO Auto-generated method stub
		return Path.FILE_RECEITA_PARTIDO;
	}
	@Override
	public int getAnoTemplate() {
		// TODO Auto-generated method stub
		return 2012;
	}
	
}
