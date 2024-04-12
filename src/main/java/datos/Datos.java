
package datos;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase Datos asumiendo la excepción no comprobada DatosException.
 * La clase Datos (del paquete datos) contiene sendas listas (del tipo ArrayList),
 * una con una secuencia de números reales y otra con los posibles errores que se
 * han producido en su construcción. Además, también incluye los valores min y
 * max que serán utilizados en algunas operaciones.
 * 
 * @author PA1
 *
 */
public class Datos {
	// Variable para almacenar la lista de datos
	private List<Double> datos;
	
	// Variable para almacenar información sobre los errores detectados en el formato de los datos
	private List<String> errores;
	
	// Variables que representan un rango para realizar algunas operaciones
	private double min, max;
	
	/**
	 * Constructor que crea un objeto con los datos proporcionados en el array del primer argumento, 
	 * y los valores mínimo y máximo pasados en el segundo y tercer argumentos. 
	 * @param d		Array con los datos a almacenar
	 * @param min	Mínimo
	 * @param max	Máximo
	 */
	public Datos(String[] d, double min, double max) {
		procesarDatos(d);
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Calcula y devuelve la media aritmética correspondiente a los datos almacenados
	 * que se encuentren dentro del rango delimitado por los valores min
	 * y max (ambos inclusive). Lanza la excepción DatosException con el mensaje
	 * “No hay datos en el rango especificado” si no hay elementos dentro
	 * del rango especificado.
	 * 
	 * @return	Media aritmética de los datos dentro del intervalo [min,max]
	 */
	public double calcMedia() {
		double suma = 0;
		int n = 0;
		for (double x : datos) {
			if (min <= x && x <= max) {
				suma += x;
				++n;
			}
		}
		if (n == 0) {
			throw new DatosException("No hay datos en el rango especificado");
		}
		return suma / n;
	}
	
	/**
	 * Calcula y devuelve la desviación típica correspondiente a los datos almacenados
	 * que se encuentren dentro del rango delimitado por los valores min y
	 * max (ambos inclusive). Propaga la excepcion DatosException lanzada por
	 * el método calcMedia si no hay elementos dentro del rango especificado.
	 * 
	 * @return	Desviación típica de los datos en el intervalo [min,max]
	 */
	public double calcDesvTipica() {
		double media = this.calcMedia();
		double suma = 0;
		int n = 0;
		for (double x : datos) {
			if (min <= x && x <= max) {
				suma += Math.pow(x - media, 2);
				++n;
			}
		}
		return Math.sqrt(suma / n);
	}
	
	/**
	 * Actualiza los valores de los atributos min y max a los valores que deberán
	 * ser extraídos del parámetro de tipo String, considerando que el primer
	 * valor se corresponde con min, el segundo valor se corresponde con max, y
	 * ambos valores se encuentran separados por el símbolo de punto y coma (;).
	 * Lanza la excepción DatosException (con el mensaje “Error en los datos
	 * al establecer el rango”) en el caso de que se produzca algún error en la
	 * extracción de los dos valores.
	 * 
	 * @param minmax
	 */
	public void setRango(String minmax) {
		try {
			int idx = minmax.indexOf(';');
			min = Double.parseDouble(minmax.substring(0, idx));
			max = Double.parseDouble(minmax.substring(idx+1));
		} catch (IndexOutOfBoundsException e) {
			throw new DatosException("Error en los datos al establecer el rango");
		} catch (NumberFormatException e) {
			throw new DatosException("Error en los datos al establecer el rango");
		} 
	}
	
	/**
	 * Devuelve la lista de datos del objeto.
	 * 
	 * @return	Una lista con los datos
	 */
	public List<Double> getDatos() {
		return datos;
	}
	
	/**
	 * Devuelve la lista de errores que contiene el objeto.
	 * 
	 * @return	Lista con los errores detectados
	 */
	public List<String> getErrores() {
		return errores;
	}
	
	/**
	 * Devuelve la representación textual del objeto.
	 * 
	 * @return Cadena con la representación textual
	 */
	@Override
	public String toString() {
		String str = "Min: " + min + ", Max: " + max + ",\n";
		str += datos.toString();
		str += ",\n";
		str += errores.toString();
		str += ",\n";
		try {
			str += "Media: " + calcMedia();
		} catch (DatosException e) {
			str += "Media: ERROR";
		}
		str += ", ";
		try {
			str += "DesvTipica: " + calcDesvTipica();
		} catch (DatosException e) {
			str += "DesvTipica: ERROR";
		}
		return str;
	}
	
	/**
	 * Método privado para almacenar en la lista de datos, la información 
	 * almacenada en el array que se pasa como argumento. En caso de que 
	 * los datos no tengan el formato numérico correcto, se incluirán en la
	 * lista de errores.
	 * 
	 * @param dat	Array con los datos a almacenar
	 */
	private void procesarDatos(String[] dat) {
		datos = new ArrayList<>();
		errores = new ArrayList<>();
		for (String d : dat) {
			try {
				datos.add(Double.parseDouble(d));
			} catch (NumberFormatException e) {
				errores.add(d);
			}
		}
	}
}
