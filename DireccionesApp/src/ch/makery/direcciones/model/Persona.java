package ch.makery.direcciones.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modelo de la clase persona
 * @author Mateo Mtz
 *
 */
public class Persona {
	private StringProperty nombre;
	private StringProperty apellido;
	private StringProperty calle;
	private IntegerProperty codigoPostal;
	private StringProperty ciudad;
	private ObjectProperty<LocalDate> onomastico;
	/**
	 * Constructor con datos iniciales
	 * @param nombre
	 * @param apellido
	 */
	public Persona(String nombre, String apellido){
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);

		//
		this.calle = new SimpleStringProperty("Alguna calle");
		this.codigoPostal = new SimpleIntegerProperty(1234);
		this.ciudad = new SimpleStringProperty("Alguna ciudad");
		this.onomastico = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999,  2,  21));
	}
	/**
	 * Constructor por default
	 */
	public Persona(){
		this(null, null);
	}

	public StringProperty getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	public StringProperty getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = new SimpleStringProperty(apellido);
	}
	public StringProperty getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = new SimpleStringProperty(calle);
	}
	public IntegerProperty getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = new SimpleIntegerProperty(codigoPostal);
	}
	public StringProperty getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = new SimpleStringProperty(ciudad);
	}
	public ObjectProperty<LocalDate> getOnomastico() {
		return onomastico;
	}
	public void setOnomastico(LocalDate onomastico) {
		this.onomastico = new SimpleObjectProperty<LocalDate>(onomastico);
	}

}
