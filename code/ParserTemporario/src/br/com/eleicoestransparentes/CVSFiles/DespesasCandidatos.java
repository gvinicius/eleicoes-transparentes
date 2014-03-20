package br.com.eleicoestransparentes.CVSFiles;

import br.com.eleicoestransparentes.annotations.CVSAttr;
import br.com.eleicoestransparentes.annotations.CVSClass;

@CVSClass(notation = "DespesasCandidatos",headerInFile = true)
public class DespesasCandidatos extends CVSFile {
	@CVSAttr(name = "Data e hora")
	public String dataEHora;
	@CVSAttr(name = "Sequencial Candidato")
	public String sequencialCandidato;
	@CVSAttr(name = "UF")
	public String UF;
	@CVSAttr(name = "Numero UE")
	public String numeroUE;
	@CVSAttr(name = "Municipio")
	public String municipio;
	@CVSAttr(name = "Sigla Partido")
	public String siglaPartido;
	@CVSAttr(name = "Numero candidato")
	public String numeroCandidato;
	@CVSAttr(name = "Cargo")
	public String cargo;
	@CVSAttr(name = "Nome candidato")
	public String nomeCandidato;
	@CVSAttr(name = "CPF do candidato")
	public String CPFCandidato;
	@CVSAttr(name = "Tipo do documento")
	public String tipoDoDocumento;
	@CVSAttr(name = "Numero do documento")
	public String numeroDoDocumento;
	@CVSAttr(name = "CPF/CNPJ do fornecedor")
	public String CPFCNPJDoFornecedor;
	@CVSAttr(name = "Nome do fornecedor")
	public String nomeDoFornecedor;
	@CVSAttr(name = "Nome Receita Fornecedor")
	public String nomeReceitaFornecedor;
	@CVSAttr(name = "Cod setor economico fornecedor")
	public String codSetorEconomicoFornecedor;
	@CVSAttr(name = "Setor economico fornecedor")
	public String setorEconomicoFornecedor;
	@CVSAttr(name = "Data da despesa")
	public String dataDaDespesa;
	@CVSAttr(name = "Valor despesa")
	public String valorDespesa;
	@CVSAttr(name = "Tipo despesa")
	public String tipoDespesa;
	@CVSAttr(name = "Descricao da despesa")
	public String descricaoDespesa;
}