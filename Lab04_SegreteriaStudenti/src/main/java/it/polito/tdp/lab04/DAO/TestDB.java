package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		StudenteDAO sdao = new StudenteDAO();
		
		Corso corso1 = cdao.getCorso("01KSUPG");
		Studente studente1 = sdao.getStudente("161245");
		
		System.out.println("Elenco tutti i corsi:\n" + cdao.getTuttiICorsi() +"\n\n\n\n");
		System.out.println("Elenco tutti i studenti:\n" +sdao.getTuttiGliStudenti() + "\n\n\n\n");
		
		System.out.println("Corso cercato:" + cdao.getCorso("01KSUPG") + "\n\n\n\n");
		System.out.println("Elenco studenti iscritti al corso " + corso1.toString() + ":\n" + cdao.getStudentiIscrittiAlCorso(corso1));
		
		System.out.println("Studente cercato:" + sdao.getStudente("161245") + "\n\n\n\n");
		System.out.println("Elenco corsi frequentati dallo studente " + studente1.toString() + ":\n" + sdao.getCorsiStudente(studente1));
		
	}

}
