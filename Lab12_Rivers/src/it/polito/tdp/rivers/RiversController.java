/**
 * Sample Skeleton for 'Rivers.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<String> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    private Model model ;
    
    @FXML
    void handleFiume(ActionEvent event) {
    	String river = this.boxRiver.getValue();
    	
    	if(river!=null) {
    		
    		List<Flow> flows = model.getFlows(river) ;
    		double avg = model.getAvgFlows(flows);
    		
    		this.txtStartDate.setText(""+flows.get(0).getDay());
    		this.txtEndDate.setText(""+flows.get(flows.size()-1).getDay());
    		this.txtNumMeasurements.setText(""+flows.size());
    		this.txtFMed.setText(""+avg);
    		
    	} else this.txtResult.setText("Seleziona un fiume.");
    }

    @FXML
    void handleSimula(ActionEvent event) {
    	try {
    		int k = Integer.parseInt(this.txtK.getText());
        	String river = this.boxRiver.getValue();
        	double fmed = Integer.parseInt(this.txtFMed.getText());
        	
    		model.simula(k, river, fmed);
    		
    	} catch(NumberFormatException e) {
    		this.txtResult.setText("Inserisci un valore numerico intero valido.");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model = m ;
    	List<River> rivers = m.getRivers();
    	
    	if(!rivers.isEmpty())
	    	for(River r : rivers)
	    		this.boxRiver.getItems().add(r.getName());
    }
}
