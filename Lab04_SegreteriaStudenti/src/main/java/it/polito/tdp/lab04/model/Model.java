package it.polito.tdp.lab04.model;

import java.util.List;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO = new CorsoDAO();
	StudenteDAO studenteDAO = new StudenteDAO();

	public List<Corso> getElencoCorsi() {
		return corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> getElencoStudenti() {
		return studenteDAO.getTuttiGliStudenti();
	}
	
	public Corso getCorso(String codins) {
		return corsoDAO.getCorso(codins);
	}
	
	public List<Studente> getStudentiPerCorso(Corso corso){
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudente(String matricola) {
		return studenteDAO.getStudente(matricola);
	}
	
	public List<Corso> getCorsiStudente(Studente studente){
		return studenteDAO.getCorsiStudente(studente);
	}
}
