package ch.makery.direcciones.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Funciones de ayuda para manejar fechas.
 * @author Marco Jakob
 *
 */
public class DateUtil {
	/**
	 * El patrón de fecha que se utiliza para la conversión.
	 * Cambialo como quieras.
	 */
	private static final String DATE_PATTERN = "dd-MM-yyyy";
	/**
	 * Formateado de fecha
	 */
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	/**
	 * Devuelve la fecha que se pasó como parametro en una cadena
	 * @param date fecha en formato LocalDate
	 * @return fecha en un String
	 */
	public static String format(LocalDate date){
		if(date == null){
			return null;
		}
		return DATE_FORMATTER.format(date);
	}
	/**
	 * Convierte una cadena en formato de fecha a un objeto LocalDate
	 * Retorna nulo si la cadena no pudo convertirse
	 *
	 * @param dateString fecha como un String
	 * @return un objeto de tipo fecha, si no se pudo convertir retorna nulo
	 */
	public static LocalDate parse(String dateString){
		try {
			return DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * Comprueba la cadena si es una fecha valida
	 * @param dateString
	 * @return verdadero si la cadena es una fecha valida.
	 */
	public static boolean validDate(String dateString){
		//Intenta analizar la cadena
		return DateUtil.parse(dateString) != null;
	}
}
