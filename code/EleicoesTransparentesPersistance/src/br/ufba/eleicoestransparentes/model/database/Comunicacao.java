package br.ufba.eleicoestransparentes.model.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufba.eleicoestransparentes.model.AgenteEleitoral;
import br.ufba.eleicoestransparentes.model.Bem;
import br.ufba.eleicoestransparentes.model.Candidato;
import br.ufba.eleicoestransparentes.model.Comite;
import br.ufba.eleicoestransparentes.model.Eleicao;
import br.ufba.eleicoestransparentes.model.Partido;
import br.ufba.eleicoestransparentes.model.Pessoa;
import br.ufba.eleicoestransparentes.model.PessoaFisica;
import br.ufba.eleicoestransparentes.model.PessoaJuridica;
import br.ufba.eleicoestransparentes.model.SetorEconomico;
import br.ufba.eleicoestransparentes.model.Transacao;
import br.ufba.eleicoestransparentes.model.logicbeans.PessoaFisicaDoador;
import br.ufba.eleicoestransparentes.model.logicbeans.PessoaJuridicaDoador;
import br.ufba.eleicoestransparentes.model.logicbeans.PontosGrafico;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;

public class Comunicacao {

	private ORMDatabase database;
	private boolean debug = false;

	public Comunicacao() {
		database = new ORMDatabase();
	}

	public void close(){
		try {
			database.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Bem insereBem(final Bem bem) throws SQLException{
		Dao<Bem, String> bemDao = DaoManager.createDao(database.getConnection(), Bem.class);

		Candidato ormCand = getCandidato(bem.getCandidato().getSequencialCandidato());

		if (ormCand != null) {//Só nos interessa se houver candidato.
			bem.setCandidato(ormCand);
			return bemDao.createIfNotExists(bem);
		}
		return null;
	}

	public List<Bem> consultaBens() throws SQLException{

		List<Bem> bens = new ArrayList<Bem>();

		Dao<Bem, String> dao = DaoManager.createDao(database.getConnection(), Bem.class);

		List<Bem> ormBens = dao.queryForAll();
		for (Bem ormBem : ormBens) {
			bens.add(ormBem);
		}

		return bens;
	}

	public List<Bem> consultaBens(int candidato_id){
		try {
			Dao<Bem, String> candidatoDao = DaoManager.createDao(database.getConnection(), Bem.class);
			return candidatoDao.queryForEq("candidato_id",candidato_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SetorEconomico insereSetorEconomico(SetorEconomico setor) throws SQLException{

		Dao<SetorEconomico, String> setorDao = DaoManager.createDao(database.getConnection(), SetorEconomico.class);

		SetorEconomico c = (SetorEconomico) checkIfExists(setorDao, "codSetorEco",setor.getCodSetorEco());

		if(c != null) return c;

		return setorDao.createIfNotExists(setor);
	}


	public List<SetorEconomico> consultaSetoresEconomico() throws SQLException{

		List<SetorEconomico> itens = new ArrayList<SetorEconomico>();

		Dao<SetorEconomico, String> dao = DaoManager.createDao(database.getConnection(), SetorEconomico.class);

		List<SetorEconomico> ormItens = dao.queryForAll();
		for (SetorEconomico ormBem : ormItens) {
			itens.add(ormBem);
		}

		return itens;
	}

	public Candidato insereCandidato(Candidato cand) throws SQLException{

		Dao<Candidato, String> candidatoDao = DaoManager.createDao(database.getConnection(), Candidato.class);

		Candidato c = (Candidato) checkIfExists(candidatoDao, "sequencialCandidato",cand.getSequencialCandidato());

		if(c != null){
			return c;
		}
		AgenteEleitoral agente = insereAgenteEleitoral(new AgenteEleitoral());
		cand.setAgenteEleitoral(agente);

		if(cand.getPartido() != null && cand.getPartido().getId() <= 0){
			cand.setPartido(inserePartido(cand.getPartido()));
		}

		return candidatoDao.createIfNotExists(cand);
	}

	public Candidato getCandidato(String sequencialCandidato){
		Candidato orm = null;
		List<Candidato> listORM;
		Dao<Candidato, String> candidatoDao;
		try {
			candidatoDao = DaoManager.createDao(database.getConnection(), Candidato.class);
			listORM = candidatoDao.queryForEq("sequencialCandidato",sequencialCandidato);
			if(!listORM.isEmpty())
				orm = listORM.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orm;
	}

	public Candidato getCandidato(int id){
		Candidato orm = null;
		List<Candidato> listORM;
		Dao<Candidato, String> candidatoDao;
		try {
			candidatoDao = DaoManager.createDao(database.getConnection(), Candidato.class);
			listORM = candidatoDao.queryForEq("id",id);
			if(!listORM.isEmpty())
				orm = listORM.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orm;
	}

	public List<Candidato> consultaCandidatos() throws SQLException{

		List<Candidato> itens = new ArrayList<Candidato>();

		Dao<Candidato, String> dao = DaoManager.createDao(database.getConnection(), Candidato.class);

		QueryBuilder<Candidato, String> queryBuilder =	dao.queryBuilder();
		queryBuilder.orderBy("nome", true);

		List<Candidato> ormItens = dao.query(queryBuilder.prepare());
		for (Candidato orm : ormItens) {
			itens.add(orm);
		}

		return itens;
	}
	
	public List<AgenteEleitoral> consultaCandidatosAgentes() throws SQLException{

		List<Candidato> itens = new ArrayList<Candidato>();

		Dao<Candidato, String> dao = DaoManager.createDao(database.getConnection(), Candidato.class);

		QueryBuilder<Candidato, String> queryBuilder =	dao.queryBuilder();
		queryBuilder.orderBy("nome", true);

		List<Candidato> ormItens = dao.query(queryBuilder.prepare());
		List<AgenteEleitoral> agentes = new ArrayList<AgenteEleitoral>();
		for (Candidato orm : ormItens) {
			itens.add(orm);
			AgenteEleitoral a = new AgenteEleitoral();
			a.setPessoa(orm);
			agentes.add(a);
		}
		
		return agentes;
	}
	
	public List<AgenteEleitoral> consultaCandidatosAgentesNum(String num) throws SQLException{

		List<Candidato> itens = new ArrayList<Candidato>();

		Dao<Candidato, String> dao = DaoManager.createDao(database.getConnection(), Candidato.class);

		QueryBuilder<Candidato, String> queryBuilder =	dao.queryBuilder();
		queryBuilder.where().eq("numero", num);
		queryBuilder.orderBy("nome", true);

		List<Candidato> ormItens = dao.query(queryBuilder.prepare());
		List<AgenteEleitoral> agentes = new ArrayList<AgenteEleitoral>();
		for (Candidato orm : ormItens) {
			itens.add(orm);
			AgenteEleitoral a = new AgenteEleitoral();
			a.setPessoa(orm);
			agentes.add(a);
		}
		
		return agentes;
	}

	public List<Candidato> consultaCandidatos(int partido_id, String UF) throws SQLException{

		List<Candidato> itens = new ArrayList<Candidato>();


		Dao<Candidato, String> dao = DaoManager.createDao(database.getConnection(), Candidato.class);

		QueryBuilder<Candidato, String> queryBuilder =	dao.queryBuilder();
		queryBuilder.where().eq("partido_id", partido_id).and().eq("UF", UF);
		queryBuilder.orderBy("nome", true);

		List<Candidato> ormItens = dao.query(queryBuilder.prepare());
		for (Candidato orm : ormItens) {
			itens.add(orm);
		}

		return itens;
	}

	public Comite insereComite(Comite cand) throws SQLException{

		Dao<Comite, String> comiteDAO = DaoManager.createDao(database.getConnection(), Comite.class);


		if(cand.getSequencialComite() != null){
			Comite c = (Comite) checkIfExists(comiteDAO, "sequencialComite",cand.getSequencialComite());
	
			if(c != null) return c;
		}

		AgenteEleitoral agente = insereAgenteEleitoral(new AgenteEleitoral());
		cand.setAgenteEleitoral(agente);
		
		if(cand.getPartido() != null && cand.getPartido().getId() <= 0){
			cand.setPartido(inserePartido(cand.getPartido()));
		}

		return comiteDAO.createIfNotExists(cand);
	}

	public List<Comite> consultaComites() throws SQLException{

		List<Comite> itens = new ArrayList<Comite>();

		Dao<Comite, String> dao = DaoManager.createDao(database.getConnection(), Comite.class);

		List<Comite> ormItens = dao.queryForAll();
		for (Comite orm : ormItens) {
			itens.add(orm);
		}

		return itens;
	}

	public Eleicao insereEleicao(Eleicao eleicao) throws SQLException{
		Dao<Eleicao, String> eleicaoDAO = DaoManager.createDao(database.getConnection(), Eleicao.class);

		Eleicao c = (Eleicao) checkIfExists(eleicaoDAO, "ano",eleicao.getAno());

		if(c != null) return c;

		return eleicaoDAO.createIfNotExists(eleicao);
	}

	public List<Eleicao> consultaEleicoes() throws SQLException{

		List<Eleicao> itens = new ArrayList<Eleicao>();

		Dao<Eleicao, String> dao = DaoManager.createDao(database.getConnection(), Eleicao.class);

		List<Eleicao> ormItens = dao.queryForAll();
		for (Eleicao orm : ormItens) {
			itens.add(orm);
		}

		return itens;
	}

	public Partido inserePartido(Partido partido) throws SQLException{
		Dao<Partido, String> partidoDAO = DaoManager.createDao(database.getConnection(), Partido.class);

		Partido c = (Partido) checkIfExists(partidoDAO, "sigla",partido.getSigla());

		if(c != null) return c;

		AgenteEleitoral agente = insereAgenteEleitoral(new AgenteEleitoral());
		partido.setAgenteEleitoral(agente);

		return partidoDAO.createIfNotExists(partido);
	}

	public List<Partido> consultaPartidos() throws SQLException{

		Dao<Partido, String> dao = DaoManager.createDao(database.getConnection(), Partido.class);

		QueryBuilder<Partido, String> queryBuilder =	dao.queryBuilder();
		queryBuilder.orderBy("nome", true);

		List<Partido> partidos = dao.query(queryBuilder.prepare());

		return partidos;
	}

	public Partido consultaPartido(String sigla) throws SQLException{

		Dao<Partido, String> dao = DaoManager.createDao(database.getConnection(), Partido.class);

		QueryBuilder<Partido, String> queryBuilder =	dao.queryBuilder();
		queryBuilder.where().eq("sigla", sigla);

		List<Partido> partidos = dao.query(queryBuilder.prepare());

		if(partidos.isEmpty()) return null;

		return partidos.get(0);
	}


	public PessoaFisica inserePessoaFisica(PessoaFisica pessoa) throws SQLException{
		Dao<PessoaFisica, String> pessoaDAO = DaoManager.createDao(database.getConnection(), PessoaFisica.class);

		PessoaFisica c = (PessoaFisica) checkIfExists(pessoaDAO, "cpf",pessoa.getCpf());

		if(c != null) return c;
		AgenteEleitoral agente = insereAgenteEleitoral(new AgenteEleitoral());
		pessoa.setAgenteEleitoral(agente);

		return pessoaDAO.createIfNotExists(pessoa);
	}

	public List<PessoaFisica> consultaPessoasFisica() throws SQLException{

		List<PessoaFisica> itens = new ArrayList<PessoaFisica>();

		Dao<PessoaFisica, String> dao = DaoManager.createDao(database.getConnection(), PessoaFisica.class);

		List<PessoaFisica> ormItens = dao.queryForAll();
		for (PessoaFisica orm : ormItens) {
			itens.add(orm);
		}

		return itens;
	}

	public PessoaJuridica inserePessoaJuridica(PessoaJuridica pessoa) throws SQLException{
		Dao<PessoaJuridica, String> pessoaDAO = DaoManager.createDao(database.getConnection(), PessoaJuridica.class);

		PessoaJuridica c = (PessoaJuridica) checkIfExists(pessoaDAO, "cnpj",pessoa.getCnpj());

		if(c != null) return c;

		AgenteEleitoral agente = insereAgenteEleitoral(new AgenteEleitoral());
		pessoa.setAgenteEleitoral(agente);

		return pessoaDAO.createIfNotExists(pessoa);
	}

	public List<PessoaJuridica> consultaPessoasJuridica() throws SQLException{

		List<PessoaJuridica> itens = new ArrayList<PessoaJuridica>();

		Dao<PessoaJuridica, String> dao = DaoManager.createDao(database.getConnection(), PessoaJuridica.class);

		List<PessoaJuridica> ormItens = dao.queryForAll();
		for (PessoaJuridica orm : ormItens) {
			itens.add(orm);
		}


		return itens;
	}

	public Transacao insereTransacao(Transacao transacao) throws SQLException{

		Dao<Transacao, String> pessoaDAO = DaoManager.createDao(database.getConnection(), Transacao.class);

		if(transacao.getDebitado() != null && transacao.getDebitado().getId() <= 0){
			AgenteEleitoral agenteDebitado = transacao.getDebitado();
			if(agenteDebitado.getTipoAgente() == Pessoa.class){
				Pessoa debitado = agenteDebitado.getPessoa();
				if(debitado instanceof Partido){
					debitado = inserePartido((Partido)debitado);
				}else if(debitado instanceof Candidato){
					debitado = insereCandidato((Candidato)debitado);
				}else if(debitado instanceof PessoaJuridica){
					debitado = inserePessoaJuridica((PessoaJuridica)debitado);
				}else if(debitado instanceof PessoaFisica){
					debitado = inserePessoaFisica((PessoaFisica)debitado);
				}
				agenteDebitado = debitado.getAgenteEleitoral();
				transacao.setDebitado(agenteDebitado);
			}else if(agenteDebitado.getTipoAgente() == Comite.class){
				Comite debitado = agenteDebitado.getComite();
				debitado = insereComite(debitado);
				agenteDebitado = debitado.getAgenteEleitoral();
				transacao.setDebitado(agenteDebitado);
			}
		}


		if(transacao.getCreditado() != null && transacao.getCreditado().getId() <= 0){
			AgenteEleitoral agenteCreditado = transacao.getCreditado();
			if(agenteCreditado.getTipoAgente() == Pessoa.class){
				Pessoa creditado = agenteCreditado.getPessoa();
				if(creditado instanceof Partido){
					creditado = inserePartido((Partido)creditado);
				}else if(creditado instanceof Candidato){
					creditado = insereCandidato((Candidato)creditado);
				}else if(creditado instanceof PessoaJuridica){
					creditado = inserePessoaJuridica((PessoaJuridica)creditado);
				}else if(creditado instanceof PessoaFisica){
					creditado = inserePessoaFisica((PessoaFisica)creditado);
				}
				agenteCreditado = creditado.getAgenteEleitoral();
				agenteCreditado.setPessoa(creditado);
				transacao.setCreditado(agenteCreditado);
			}else if(agenteCreditado.getTipoAgente() == Comite.class){
				Comite creditado = agenteCreditado.getComite();
				creditado = insereComite(creditado);
				agenteCreditado = creditado.getAgenteEleitoral();
				agenteCreditado.setComite(creditado);
				transacao.setCreditado(agenteCreditado);
			}
		}

		return pessoaDAO.createIfNotExists(transacao);
	}



	public AgenteEleitoral insereAgenteEleitoral(AgenteEleitoral agenteDebitado) throws SQLException {
		Dao<AgenteEleitoral, String> eleicaoDAO = DaoManager.createDao(database.getConnection(), AgenteEleitoral.class);

		//		AgenteEleitoral c = null;
		//		if(agenteDebitado.getTipoAgente() == Pessoa.class){
			//			c = (AgenteEleitoral) checkIfExists(eleicaoDAO, "pessoa_id",agenteDebitado.getPessoa().getId()+"");
			//		}else if(agenteDebitado.getTipoAgente() == Comite.class){
				//			c = (AgenteEleitoral) checkIfExists(eleicaoDAO, "comite_id",agenteDebitado.getComite().getId()+"");
				//		}else{
					//			new InvalidAttributeValueException("AgenteEleitoral precisa ter um tipo");
					//		}
		//
		//		if(c != null) return c;

		return eleicaoDAO.createIfNotExists(agenteDebitado);
	}

	public List<Transacao> consultaTransacoes() throws SQLException{

		List<Transacao> itens = new ArrayList<Transacao>();

		Dao<Transacao, String> dao = DaoManager.createDao(database.getConnection(), Transacao.class);

		List<Transacao> ormItens = dao.queryForAll();
		for (Transacao orm : ormItens) {
			itens.add(orm);
		}

		return itens;
	}

	/**
	 * @param numero
	 * @param UF
	 * @param tipo "R" para receita e "D" para despesa.
	 * @throws SQLException
	 */
	public float consultaTransacaoPartido(int numero, String UF, String tipo) throws SQLException{

		MySqlDatabase db = new MySqlDatabase();

		String query = "select p.nome, sum(valor), t.tipo, count(*) as numero_transacoes "+ 
				"from Transacao t inner join Partido p on p.id = t.creditado_id and t.tipoCreditado = 'Partido' "+  
				"where  p.numero = "+numero+" and t.UF like '%"+UF+"%' "+ 
				"group by p.id "+ 
				"order by sum(valor) desc; ";

		if(tipo.equals("D")){
			query = "select p.nome, sum(valor), t.tipo, count(*) as numero_transacoes "+ 
					"from Transacao t inner join Partido p on p.id = t.debitado_id and t.tipoDebitado = 'Partido' "+  
					"where  p.numero = "+numero+" and t.UF like '%"+UF+"%' "+ 
					"group by p.id "+ 
					"order by sum(valor) desc; ";	
		}

		ResultSet result = db.query(query);
		System.out.println(query);

		float valor = 0;
		if(result.next()){
			System.out.println("Partido: "+result.getString(1)+" Valor: "+result.getString(2));
			valor = result.getFloat(2);
		}
		db.close();
		return valor;
	}

	/**
	 * @param numero
	 * @param UF
	 * @param tipoTransacao
	 * @return Uma lista de listas com os campos, nome, cargo, numero e valor, o primeiro objeto da lista contém os headers.
	 * @throws SQLException
	 */
	public List<List<String>> consultaTransacaoCandidatos(int numero,String UF,String tipoTransacao) throws SQLException {

		List<List<String>> queryResults = new ArrayList<List<String>>();
		MySqlDatabase db = new MySqlDatabase();

		String query = "select p.nome, p.cargo, p.numero, sum(valor) as valor, t.tipo "+ 
				"from Transacao t inner join Candidato p on p.id = t.debitado_id "+  
				"where t.tipoDebitado = 'Candidato' and t.tipo = 'D' and p.numero = "+numero+" and p.UF like '%"+UF+"%' "+ 
				"group by p.id "+ 
				"order by sum(valor) desc;";

		if(tipoTransacao.equals("R")){
			query = "select p.nome, p.cargo, p.numero, sum(valor), t.tipo "+ 
					"from Transacao t inner join Candidato p on p.id = t.creditado_id "+  
					"where t.tipoCreditado = 'Candidato' and t.tipo = 'R' and p.numero = "+numero+" and p.UF like '%"+UF+"%' "+ 
					"group by p.id "+ 
					"order by sum(valor) desc;";
		}

		List<String> header = new ArrayList<String>();

		header.add("nome");
		header.add("cargo");
		header.add("numero");
		header.add("valor");
		queryResults.add(header);

		ResultSet result = db.query(query);
		if(debug) System.out.println(query);

		while(result.next()){

			List<String> tupla = new ArrayList<String>();

			tupla.add(result.getString(1));
			tupla.add(result.getString(2));
			tupla.add(result.getString(3));
			tupla.add(result.getString(4));
			queryResults.add(tupla);

			if(debug) System.out.println("Candidato: "+result.getString(1)+" Valor: "+result.getString(4));
		}
		db.close();
		return queryResults;
	}

	/**
	 * @param numero
	 * @param UF
	 * @param tipoTransacao
	 * @return Uma lista de listas com os campos, nome, cargo, numero e valor, o primeiro objeto da lista contém os headers.
	 * @throws SQLException
	 */
	public float consultaTransacaoCandidato(String sequencialCandidato,String tipoTransacao) throws SQLException {

		MySqlDatabase db = new MySqlDatabase();

		String query = "select c.nome, sum(valor) "+
				"from Transacao t inner join Candidato c on c.id = t.debitado_id "+ 
				"where t.tipoDebitado = 'Candidato' and c.sequencialCandidato like '%"+sequencialCandidato+"%'";

		if(tipoTransacao.equals("R")){
			query = "select c.nome, sum(valor) "+
					"from Transacao t inner join Candidato c on c.id = t.creditado_id "+ 
					"where t.tipoCreditado = 'Candidato' and c.sequencialCandidato like '%"+sequencialCandidato+"%'";
		}

		ResultSet result = db.query(query);

		if(debug)		System.out.println(query);

		float valor = 0;
		if(result.next()){

			valor = result.getFloat(2);
			if(debug) System.out.println("Nome: "+result.getString(1)+" Valor: "+result.getString(2)); 
		}
		db.close();

		return valor;
	}

	public List<Transacao> consultaTransacao() throws SQLException{

		Dao<Transacao, String> transacaoDao = DaoManager.createDao(database.getConnection(), Transacao.class);

		return transacaoDao.queryForAll();
	}

	public List<PessoaJuridicaDoador>rankingMaioresDoadoresPessoaJuridica(String UF) throws SQLException{
		MySqlDatabase db = new MySqlDatabase();
		List<PessoaJuridicaDoador> doadores = new ArrayList<PessoaJuridicaDoador>();

		String queryPJ = "select pj.nome, pj.cnpj, t.tipoDebitado, sum(t.valor) as valor " +
				"from Transacao t inner join PessoaJuridica pj on pj.id = t.debitado_id  " +
				"where t.tipo = 'R' and t.tipoDebitado = 'PessoaJuridica' and t.UF like '%"+UF+"%' "+
				"group by t.debitado_id " +
				"order by sum(t.valor) desc LIMIT 20;";

		if(debug) System.out.println(queryPJ);

		ResultSet result = db.query(queryPJ);

		while(result.next()){
			PessoaJuridica pj = new PessoaJuridica();
			pj.setNome(result.getString(1));
			pj.setCnpj(result.getString(2));

			PessoaJuridicaDoador doador = new PessoaJuridicaDoador();

			doador.setPessoa(pj);
			doador.setValor(result.getFloat(4));

			doadores.add(doador);

			if(debug) System.out.println("Nome: "+pj.getNome()+" CNPJ: "+pj.getCnpj()+" VALOR: "+result.getFloat(4));
		}

		db.close();
		return doadores;
	}

	public List<PessoaFisicaDoador>rankingMaioresDoadoresPessoaFisica(String UF) throws SQLException{
		MySqlDatabase db = new MySqlDatabase();
		List<PessoaFisicaDoador> doadores = new ArrayList<PessoaFisicaDoador>();

		String queryPJ = "select pj.nome, pj.cpf, t.tipoDebitado, sum(t.valor) as valor " +
				"from Transacao t inner join PessoaFisica pj on pj.id = t.debitado_id  " +
				"where t.tipo = 'R' and t.tipoDebitado = 'PessoaFisica' and t.UF like '%"+UF+"%' "+
				"group by t.debitado_id " +
				"order by sum(t.valor) desc LIMIT 20;";

		if(debug) System.out.println(queryPJ);

		ResultSet result = db.query(queryPJ);

		while(result.next()){
			PessoaFisica pj = new PessoaFisica();
			pj.setNome(result.getString(1));
			pj.setCpf(result.getString(2));

			PessoaFisicaDoador doador = new PessoaFisicaDoador();
			doador.setPessoa(pj);
			doador.setValor(result.getFloat(4));

			doadores.add(doador);

			if(debug) System.out.println("Nome: "+pj.getNome()+" CPF: "+pj.getCpf()+" VALOR: "+result.getFloat(4));
		}

		db.close();
		return doadores;
	}

	private Object checkIfExists(Dao dao, String field, String value) throws SQLException{
		List listORM = dao.queryForEq(field,value);
		if(!listORM.isEmpty()){
			return listORM.get(0);
		}
		return null;
	}

	public void setDebug(boolean b) {
		this.debug = b;
	}


	
	//queries para os os graficos
	public ArrayList<PontosGrafico> topFinanciadoresPF() {
		MySqlDatabase db = new MySqlDatabase();

		String query =  "SELECT c.nome, SUM(valor) as valor FROM " +
				"PessoaFisica c INNER JOIN Transacao t on t.debitado_id = c.agenteEleitoral_id group by c.id order by sum(t.valor) DESC limit 10;";


		ArrayList<PontosGrafico> pontos = new ArrayList<PontosGrafico>();
		
		ResultSet result = null;
		try {
			result = db.query(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(query);

		
			try {
				while(result.next()){
				     //Retrieve by column name
				     String nome  = result.getString("c.nome");
				     double financimento = result.getDouble("valor");
				     PontosGrafico ponto = new PontosGrafico(nome, financimento);
				     pontos.add(ponto);
				  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			db.close();

		return pontos;
	}
	
	//queries para os os graficos
		public ArrayList<PontosGrafico> topFinanciadoresPJ() {
			MySqlDatabase db = new MySqlDatabase();

			String query =  "SELECT c.nome, SUM(valor) as valor FROM " +
					"PessoaJuridica c INNER JOIN Transacao t on t.debitado_id = c.agenteEleitoral_id group by c.id order by sum(t.valor) DESC limit 10;";


			ArrayList<PontosGrafico> pontos = new ArrayList<PontosGrafico>();
			
			ResultSet result = null;
			try {
				result = db.query(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(query);

			
				try {
					while(result.next()){
					     //Retrieve by column name
					     String nome  = result.getString("c.nome");
					     double financimento = result.getDouble("valor");
					     PontosGrafico ponto = new PontosGrafico(nome, financimento);
					     pontos.add(ponto);
					  }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				db.close();

			return pontos;
		}
	
	
	
	public ArrayList<PontosGrafico> topCandidatos() {
		MySqlDatabase db = new MySqlDatabase();

		String query =  "SELECT c.nome, SUM(valor) as TOTAL_ARRECADADO_CANDIDATO FROM Candidato c INNER JOIN Transacao t on t.creditado_id = c.agenteEleitoral_id group by c.id order by t.valor DESC limit 10;";


		ArrayList<PontosGrafico> pontos = new ArrayList<PontosGrafico>();
		
		ResultSet result = null;
		try {
			result = db.query(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(query);

		
			try {
				while(result.next()){
				     //Retrieve by column name
				     String nome  = result.getString("c.nome");
				     double arrecadacao = result.getDouble("TOTAL_ARRECADADO_CANDIDATO");
				     PontosGrafico ponto = new PontosGrafico(nome, arrecadacao);
				     pontos.add(ponto);
				  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			db.close();

		return pontos;
	}
	
}
