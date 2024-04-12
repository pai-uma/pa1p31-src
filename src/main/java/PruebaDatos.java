
import datos.*;

import java.util.Arrays;

public class PruebaDatos {
	public static void main(String[] args) {
		try {
			double min = Double.parseDouble(args[0]);
			double max = Double.parseDouble(args[1]);
			Datos datos = new Datos(Arrays.copyOfRange(args, 2, args.length), min, max);

			System.out.println();
			System.out.println(datos);

			System.out.println();
			try {
				datos.setRango("0;4");
				System.out.println(datos);
			} catch (DatosException e) {
				System.err.println(e.getMessage());
			}
			
			System.out.println();
			try {
				datos.setRango("15 25");
				System.out.println(datos);
			} catch (DatosException e) {
				System.err.println(e.getMessage());
			}
			
			//System.out.println();
			//try {
			//	datos.setRango("15 ; hola");
			//	System.out.println(datos);
			//} catch (DatosException e) {
			//	System.err.println(e.getMessage());
			//	System.err.println("Error en los datos al establecer el rango: "+e.getMessage());
			//}
			
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error, no hay valores suficientes");
		} catch (NumberFormatException e) {
			System.err.println("Error, al convertir un valor a numero real ("+e.getMessage()+")");
		} catch (DatosException e) {
			System.err.println(e);
		}
	}
}
