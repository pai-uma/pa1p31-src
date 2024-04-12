
package datos;

/**
 * Clase que representa excepciones no comprobadas para tratar
 * las situaciones anómalas.
 */
public class DatosException extends RuntimeException {
	public DatosException() {
		super();
	}
	public DatosException(String m) {
		super(m);
	}
}
