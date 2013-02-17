/* Rodrigo Valladares Santana L1 
 * Programación de Aplicaciones Interactivas Práctica 4
 * 
 * Ejercicio 4.
 * 
 * */

import java.io.*;
import java.util.*;

public class ContarPalabras {
	
	//Expresión regular utilizada para separar las líneas del fichero en palabras
	static final String EXPRESIÓN_REGULAR = "\\,|\\;|\\:|\\.|\\¿|\\¡|\\?|\\!|\"|\\(|\\)|(\\s+)";
	static final String EMPIEZA_POR_LETRA = "[a-zA-Z].*";
	private TreeMap<String, Integer> conteoPalabras = new TreeMap<String, Integer>();
	
	public void mostrarPalabras() {
		//Conjunto de entradas de mapa del tipo <String, Integer>
		Set<Map.Entry<String, Integer>> lista = conteoPalabras.entrySet();
		//Iterador de entradas de mapa del tipo <String, Integer>
		Iterator<Map.Entry<String, Integer>> iterador = lista.iterator();
		while(iterador.hasNext()) {
			Map.Entry<String, Integer> entradaActual = iterador.next();
			System.out.println(entradaActual.getKey() + ": " + entradaActual.getValue());
		}
	}

	ContarPalabras(String nombreFichero) {
		try {
			File fichero = new File(nombreFichero);
			FileReader lectorFichero = new FileReader(fichero);
			BufferedReader leerLínea = new BufferedReader(lectorFichero);
			String línea;
			String [] palabras;
			
			//Lectura del fichero
			línea = leerLínea.readLine();
			while(línea != null) {
				//Se convirte la línea a minúscula para que no haya diferencias entre mayúscula y minúscula al contar ocurrencias
				línea = línea.toLowerCase();
				palabras = línea.split(EXPRESIÓN_REGULAR);
				
				for(int i = 0; i < palabras.length; i++) {
					//Si a cadena no comienza por letra, no se tiene en cuenta
					if(palabras[i].matches(EMPIEZA_POR_LETRA)) {
						if(conteoPalabras.containsKey(palabras[i]))
							conteoPalabras.put(palabras[i], conteoPalabras.get(palabras[i]) + 1);
						else
							conteoPalabras.put(palabras[i], 1);
					}
				}
				línea = leerLínea.readLine();
			}
		}
		catch(FileNotFoundException ficheroNoEncontrado) {
			System.out.println("Fichero no encontrado.");
		}
		catch(IOException errorEntradaSalida) {
			System.out.println("Error de entrada salida.");
		}
	}
	
	public static void main(String[] args) {
		try {
			ContarPalabras contarPalabras = new ContarPalabras(args[0]);
			contarPalabras.mostrarPalabras();
		}
		catch(ArrayIndexOutOfBoundsException sinArgumentos) {
			System.out.println("La aplicación debe ser llamada con un nombre de fichero de argumento.");
		}

	}

}
