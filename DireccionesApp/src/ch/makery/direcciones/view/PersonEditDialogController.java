package ch.makery.direcciones.view;

import ch.makery.direcciones.model.Persona;
import ch.makery.direcciones.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Diálogo para editar detalles de una persona
 * @author Yo
 *
 */
public class PersonEditDialogController {
	@FXML
	private TextField nombreField;
	@FXML
	private TextField apellidoField;
	@FXML
	private TextField calleField;
	@FXML
	private TextField codigoPostalField;
	@FXML
	private TextField ciudadField;
	@FXML
	private TextField onomasticoField;

	private Stage dialogStage;
	private Persona persona;
	private boolean okClicked = false;
	/**
	 * Inicializa la clase de controlador.
	 * Este método se llama automáticamente después de cargar el archivo fxml.
	 */
	@FXML
	private void initialize(){

	}
	/**
	 * Establece el escenario de este diálogo.
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	/**
	 * Establece la persona a editar en el diálogo.
	 * @param persona
	 */
	public void setPerson(Persona persona){
		this.persona = persona;
		nombreField.setText(persona.getNombre().get());
		apellidoField.setText(persona.getApellido().get());
		calleField.setText(persona.getCalle().get());
		codigoPostalField.setText(persona.getCodigoPostal().get() + "");
		ciudadField.setText(persona.getCiudad().get());
		onomasticoField.setText(DateUtil.format(persona.getOnomastico().get()));
		onomasticoField.setPromptText("dd-mm-yyyy");
	}
	/**
	 * Devuelve verdadero si el usuario hizo clic en aceptar
	 * falso de lo contrario
	 * @return
	 */
	public boolean isOkClicked(){
		return okClicked;
	}
	/**
	 * Valida la entrada del usuario en los campos de texto.
	 * @return verdadero si la entrada es valida.
	 */
	private boolean isInputValid(){
		String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "El nombre no es valido!\n";
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "El apellido no es valido!\n";
        }
        if (calleField.getText() == null || calleField.getText().length() == 0) {
            errorMessage += "La calle no es valido!\n";
        }

        if (codigoPostalField.getText() == null || codigoPostalField.getText().length() == 0) {
            errorMessage += "El código postal no es valido!\n";
        } else {
            // Intenta analizar el código postal en un int
            try {
                Integer.parseInt(codigoPostalField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "El Código Postal no es valido, debe se un entero!\n";
            }
        }

        if (ciudadField.getText() == null || ciudadField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (onomasticoField.getText() == null || onomasticoField.getText().length() == 0) {
            errorMessage += "Onomastico no es valido!\n";
        } else {
            if (!DateUtil.validDate(onomasticoField.getText())) {
                errorMessage += "El onomastico no es valido. Usa el formato dd-mm-yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrar mensaje de error.
        	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Campos no validos");
			alert.setHeaderText("Por favor corrige los campos no validos");
			alert.setContentText(errorMessage);
			alert.showAndWait();
            return false;
        }
	}
	/**
	 * Se llama cuando el usuario hace clic en Aceptar
	 */
	@FXML
	private void handleOk(){
		if(isInputValid()){
			persona.setNombre(nombreField.getText());
			persona.setApellido(apellidoField.getText());
			persona.setCalle(calleField.getText());
			persona.setCodigoPostal(Integer.parseInt(codigoPostalField.getText()));
			persona.setCiudad(ciudadField.getText());
			persona.setOnomastico(DateUtil.parse(onomasticoField.getText()));
			okClicked = true;
			dialogStage.close();
		}
	}
	/**
	 * Se llama cuando el usuario hace clic en cancelar.
	 */
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}
}
