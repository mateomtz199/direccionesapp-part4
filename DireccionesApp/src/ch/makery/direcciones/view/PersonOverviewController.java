package ch.makery.direcciones.view;

import ch.makery.direcciones.MainApp;
import ch.makery.direcciones.model.Persona;
import ch.makery.direcciones.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController {
	@FXML
	private TableView<Persona> personTable;
	@FXML
	private TableColumn<Persona, String> nombresColumna;
	@FXML
	private TableColumn<Persona, String> apellidosColumna;

	@FXML
	private Label nombreLabel;
	@FXML
	private Label apelidoLabel;
	@FXML
	private Label calleLabel;
	@FXML
	private Label codigoPostaLabel;
	@FXML
	private Label ciudadLabel;
	@FXML
	private Label onomasticoLabel;

	//Referencia a la clase MainApp
	private MainApp mainApp;
	/**
	 * Constructor
	 * Se llama al constructor antes del método initialize()
	 */
	public PersonOverviewController(){

	}
	/**
	 * Inicializa la clase de controlador
	 * Este método se llama automáticamente después de cargar el archivo fxml.
	 */
	@FXML
	private void initialize(){
		//Inicialice la tabla de personas con las dos columnas.
		nombresColumna.setCellValueFactory(cellData -> cellData.getValue().getNombre());
		apellidosColumna.setCellValueFactory(cellData -> cellData.getValue().getApellido());

		//Limpiar detalles de persona
		showPersonaDetails(null);

		//Escuche los cambios de selección y muestre los detalles de la persona cuando cambie.
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPersonaDetails(newValue));
	}
	/**
	 * Es llamado por la aplicación principal para devolverse una referencia a sí mismo.
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		personTable.setItems(mainApp.getPersonData());
	}
	/**
	 * Rellena todos los campos de texto para mostrar detalles sobre la persona.
	 * Si la persona especificada es nula, se borran todos los campos de texto.
	 * @param persona
	 */
	private void showPersonaDetails(Persona persona){
		if(persona != null){
			//Rellene las etiquetas con información del objeto persona.
			nombreLabel.setText(persona.getNombre().get());
			apelidoLabel.setText(persona.getApellido().get());
			calleLabel.setText(persona.getCalle().get());
			codigoPostaLabel.setText(persona.getCodigoPostal().get() + "");
			ciudadLabel.setText(persona.getCiudad().get());
			//¡Necesitamos una forma de convertir el cumpleaños en una Cadena!
			//onomasticoLabel.setText(...);
			onomasticoLabel.setText(DateUtil.format(persona.getOnomastico().get()));
		}else {
			//Si la persona es nula, quitar todo el texto
			nombreLabel.setText("");
			apelidoLabel.setText("");
			calleLabel.setText("");
			codigoPostaLabel.setText("");
			ciudadLabel.setText("");
			onomasticoLabel.setText("");
		}
	}
	/**
	 * Se llama cuando el usuario hace clic en el botón Eliminar.
	 */
	@FXML
	private void handleDeletePersona(){
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0){
			personTable.getItems().remove(selectedIndex);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Sin seleccion");
			alert.setHeaderText("No has seleccionado una persona");
			alert.setContentText("Por favor selecciona una persona de la lista para eliminar");
			alert.showAndWait();
		}
	}
	/**
	 * Se llama cuando el usuario hace clic en el botón Nuevo.
	 * Abre un cuadro de diálogo para editar detalles para una nueva persona.
	 */
	@FXML
	private void handleNewPerson(){
		Persona temPersona = new Persona();
		boolean okClicked = mainApp.showPersonEditDialog(temPersona);
		if(okClicked){
			mainApp.getPersonData().add(temPersona);
		}
	}
	/*
	 * Se llama cuando el usuario hace clic en el botón editar.
	 * Abre un cuadro de diálogo para editar detalles para la persona seleccionada.
	 */
	@FXML
	private void handleEditPerson(){
		Persona personaSeleccionada = personTable.getSelectionModel().getSelectedItem();
		if(personaSeleccionada != null){
			boolean okClicked = mainApp.showPersonEditDialog(personaSeleccionada);
			if(okClicked){
				showPersonaDetails(personaSeleccionada);
			}
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Sin seleccion");
			alert.setHeaderText("No has seleccionado una persona");
			alert.setContentText("Por favor selecciona una persona de la lista para eliminar");
			alert.showAndWait();
		}
	}
}
