package ch.makery.direcciones;

import java.io.IOException;

import ch.makery.direcciones.model.Persona;
import ch.makery.direcciones.view.PersonEditDialogController;
import ch.makery.direcciones.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Directorios App");

		initRootLayout();
		showPersonaOverview();

		//Colocar el icono
		this.primaryStage.getIcons().add(new Image("file:resources/images/contacto.png"));
	}
	/**
	 * Inicializa el diseño raíz.
	 */
	public void initRootLayout(){
		try{
			//Carga el diseño raíz del archivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			//Muestra la escena que contiene el diseño raíz.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Muestra la descripción general de la persona dentro del diseño raíz.
	 */
	public void showPersonaOverview(){
		try{
			//Carga datos de persona
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonaOverview.fxml"));
			AnchorPane personaOverview = (AnchorPane) loader.load();

			//Carga los datos de la persona en el centro del diseño raíz.
			rootLayout.setCenter(personaOverview);

			//Darle al controlador acceso a la App
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Retorna el escenario principal
	 * @return
	 */
	public Stage getPriStage(){
		return primaryStage;
	}
	/**
	 * Lista de personas en una ObservableList
	 */
	private ObservableList<Persona> personData = FXCollections.observableArrayList();
	/**
	 * Constructor
	 */
	public MainApp(){
		//Agregar algunas personas a la lista
		personData.add(new Persona("Juan", "Perez"));
		personData.add(new Persona("Pedro", "Perez"));
		personData.add(new Persona("Pablo", "Perez"));
	}
	/**
	 * Devuelve los datos como una lista observable de personas.
	 * @return
	 */
	public ObservableList<Persona> getPersonData(){
		return personData;
	}
	/**
	 * Abre un cuadro de diálogo para editar detalles para la persona especificada.
	 * Si el usuario hace clic en Aceptar, los cambios se guardan en el objeto
	 * de persona proporcionado y se devuelve verdadero.
	 *
	 * @param persona objeto persona a editar
	 * @return  verdadero si el usuario hizo clic en Aceptar, falso en caso contrario.
	 */
	public boolean showPersonEditDialog(Persona persona){
		try {
			//Cargue el archivo fxml y cree una nueva escena para el
			//cuadro de diálogo emergente.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
					MainApp.class.getResource("view/PersonEditDialog.fxml")
					);
			AnchorPane page = (AnchorPane) loader.load();

			//Crear el cuadro de diálogo de la escena
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editar persona");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			//Envia la persona al controlador
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(persona);

			//Muestra el diálogo y espera hasta que el usuario lo cierre.
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
