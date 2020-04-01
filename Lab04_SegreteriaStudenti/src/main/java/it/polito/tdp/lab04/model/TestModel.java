package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		
		Corso corso1 = model.getCorso("01KSUPG");
		Studente studente1 = model.getStudente("161245");
		
		System.out.println("Elenco tutti i corsi:\n" + model.getElencoCorsi() +"\n\n\n\n");
		System.out.println("Elenco tutti i studenti:\n" +model.getElencoStudenti() + "\n\n\n\n");
		
		System.out.println("Corso cercato:" + model.getCorso("01KSUPG") + "\n\n\n\n");
		System.out.println("Elenco studenti iscritti al corso " + corso1.toString() + ":\n" + model.getStudentiPerCorso(corso1));
		
		System.out.println("Studente cercato:" + model.getStudente("161245") + "\n\n\n\n");
		System.out.println("Elenco corsi frequentati dallo studente " + studente1.toString() + ":\n" + model.getCorsiStudente(studente1));
	}

}
