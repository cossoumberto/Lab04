package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(corso);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codins) {
		
		final String sql = "SELECT * FROM corso WHERE codins = ?";
		
		Corso corso = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				String codins1 = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				System.out.println(codins1 + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				corso = new Corso(codins, numeroCrediti, nome, periodoDidattico);
			}
			
			conn.close();
			return corso;
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "SELECT * FROM studente WHERE matricola IN (SELECT matricola FROM iscrizione WHERE codins = ?)";
		
		List<Studente> studentiCorso = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String matricola = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);
				Studente studente = new Studente(matricola, cognome, nome, CDS);
				studentiCorso.add(studente);
			}
			
			conn.close();
			return studentiCorso;
		}catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteAlCorso(Studente studente, Corso corso) {
		 final String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?, ?)";
		 
		 try {
			 Connection conn = ConnectDB.getConnection();
			 PreparedStatement st = conn.prepareStatement(sql);
			 st.setString(1, studente.getMatricola());
			 st.setString(2, corso.getCodins());
			 st.executeQuery();
			 conn.close();
			 return true;
		 } catch (SQLException e) {
				// e.printStackTrace();
			 	return false;
			}
	}

}
