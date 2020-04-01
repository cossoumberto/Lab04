package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 * Ottengo tutti gli studenti nel db
	 */
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String matricola = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				// Crea un nuovo JAVA Bean Corso
				Studente studente = new Studente(matricola, cognome, nome, CDS);
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.add(studente);
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/*
	 * Data una matricola, ottengo lo studente
	 */
	public Studente getStudente(String matricola) {
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		
		Studente studente = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String matricola1 = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);
				studente = new Studente(matricola1, cognome, nome, CDS);
			}
			
			conn.close();
			return studente;
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	/*
	 * Ottengo tutti i corsi frequentati da uno studente
	 */
	public List<Corso> getCorsiStudente(Studente studente) {
		
		final String sql = "SELECT * FROM corso WHERE codins IN (SELECT codins FROM iscrizione WHERE matricola = ?)";
		
		List<Corso> corsiStudente = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsiStudente.add(corso);
			}
			
			conn.close();
			return corsiStudente;
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}
