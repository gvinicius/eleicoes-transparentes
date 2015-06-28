package br.ufba.mata62.eleicoestransparentes;


import java.sql.SQLException;

import br.ufba.mata62.eleicoestransparentes.model.Bem;
import br.ufba.mata62.eleicoestransparentes.model.Candidato;
import br.ufba.mata62.eleicoestransparentes.model.Transacao;
import br.ufba.mata62.eleicoestransparentes.model.database.Comunicacao;
import br.ufba.mata62.eleicoestransparentes.model.database.Seed;
import br.ufba.mata62.eleicoestransparentes.utils.Path;
import br.ufba.mata62.eleicoestransparentes.utils.ReadCVS;


public class MainBD {
	
	public static void main(String[] args) {
		
		Seed.createTables();
		
		try {
			Comunicacao comm = new Comunicacao();
			
			for (Candidato t : ReadCVS.readCandidatos(Path.UFS[0])) {
				comm.insereCandidato(t);
			}
		
			for (Transacao t : ReadCVS.readPrestacaoContasPartidoReceita(Path.UFS[0])) {
				comm.insereTransacao(t);
			}
			
			for (Transacao t : ReadCVS.readPrestacaoContasPartidoDespesa(Path.UFS[0])) {
				comm.insereTransacao(t);
			}
			
			for (Transacao t : ReadCVS.readPrestacaoContasCandidatoDespesa(Path.UFS[0])) {
				comm.insereTransacao(t);
			}
			
			for (Transacao t : ReadCVS.readPrestacaoContasCandidatoReceita(Path.UFS[0])) {
				comm.insereTransacao(t);
			}
			
			for (Bem b : ReadCVS.readBens(Path.UFS[0])) {
				comm.insereBem(b);
			}
			
			comm.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public static void run(Transacao t,Comunicacao comm){
		try {
			comm.insereTransacao(t);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void run(Bem bem, Comunicacao comm){
		try {
			comm.insereBem(bem);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void run(Candidato cand, Comunicacao comm){
		try {
			comm.insereCandidato(cand);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
