package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;
	Corso corso = null;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompleta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void cercaCorsi(ActionEvent event) {
    	
    	String matricola = txtMatricola.getText().trim();
    	
    	String string = "";
    	Studente studente = model.getStudente(matricola);
    	
    	if(matricola.equals(""))
    		string = "DEVI INSERIRE UNA MATRICOLA";
    	else {
    		if(studente!=null) {
    			txtCognome.setText(studente.getCognome());
    			txtNome.setText(studente.getNome());
				for(Corso c : model.getCorsiStudente(studente))
					string += c.toString() + "\n";
    		}else 
    			string = "MATRICOLA NON ESISTENTE";
    	}
    	
    	txtResult.setText(string);
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
    	String string = "";
    	
    	if(corso==null)
    		string = "DEVI SELEZIONARE UN CORSO";
    	else
    		for(Studente s : model.getStudentiPerCorso(corso))
    			string += s.toString() + "\n";
    	
    	if(corso!=null && string.equals(""))
    		string = "NESSUNO STUDENTE ISCRITTO";
    	
    	txtResult.setText(string);
    }

    @FXML
    void doCompleta(ActionEvent event) {
    	txtResult.clear();
    	String matricola = txtMatricola.getText().trim();
    	Studente studente = model.getStudente(matricola);
    	if(matricola.equals(""))
    		txtResult.setText("DEVI INSERIRE UNA MATRICOLA");
    	else if(studente!=null) {
			txtCognome.setText(studente.getCognome());
			txtNome.setText(studente.getNome());
    	}else
    		txtResult.setText("MATRICOLA NON ESISTENTE");
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void scegliCorso(ActionEvent event) {
    	corso = boxCorsi.getValue();
    }

    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
		this.model = model;
		boxCorsi.getItems().addAll(this.model.getElencoCorsi());
	}
	
}
