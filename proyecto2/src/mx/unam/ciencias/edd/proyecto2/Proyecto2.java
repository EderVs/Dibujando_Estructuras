package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

import mx.unam.ciencias.edd.*;
/**
 * <p>Proyecto 2</p>
 *
 * @author Eder Vs
 * @version 1.0
 * @since 13/05/2016.
 */
public class Proyecto2 {

	/**
	 *
     * Obtiene elementos de un arreglo para meterlos en una lista.
     *
     * @param l Lista de Integers donde se meteran los elementos.
     * @param elementos Arreglo de los elementos.
     * @return la Lista de Integers con los elementos.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static Lista<Integer> obtenerElementosLista (Lista<Integer> l, String[] elementos) throws NumberFormatException {
		for (String i: elementos) {
			l.agrega(Integer.parseInt(i));
		}
		return l;
	}

	/**
	 *
     * Obtiene elementos de un arreglo para meterlos en una cola.
     *
     * @param c Cola de Integers donde se meteran los elementos.
     * @param elementos Arreglo de los elementos.
     * @return la Cola de Integers con los elementos.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static Cola<Integer> obtenerElementosCola (Cola<Integer> c, String[] elementos) throws NumberFormatException {
		for (String i: elementos) {
			c.mete(Integer.parseInt(i));
		}
		return c;
	}

	/**
	 *
     * Obtiene elementos de un arreglo para meterlos en una Pila.
     *
     * @param p Pila de Integers donde se meteran los elementos.
     * @param elementos Arreglo de los elementos.
     * @return la Pila de Integers con los elementos.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static Pila<Integer> obtenerElementosPila (Pila<Integer> p, String[] elementos) throws NumberFormatException {
		for (String i: elementos) {
			p.mete(Integer.parseInt(i));
		}
		return p;
	}

	/**
	 *
     * Obtiene elementos de un arreglo para meterlos en un ArbolRojinegro.
     *
     * @param ab ArbolRojinegro de Integers donde se meteran los elementos.
     * @param elementos Arreglo de los elementos.
     * @return el ArbolRojinegro de Integers con los elementos.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static ArbolRojinegro<Integer> obtenerElementosArbolRN (ArbolRojinegro<Integer> ab, String[] elementos) throws NumberFormatException {
		for (String i: elementos) {
			ab.agrega(Integer.parseInt(i));
		}
		return ab;
	}

	/**
	 *
     * Obtiene elementos de un arreglo para meterlos en un ArbolAVL.
     *
     * @param ab ArbolAVL de Integers donde se meteran los elementos.
     * @param elementos Arreglo de los elementos.
     * @return el ArbolAVL de Integers con los elementos.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static ArbolAVL<Integer> obtenerElementosArbolAVL (ArbolAVL<Integer> ab, String[] elementos) throws NumberFormatException {
		for (String i: elementos) {
			ab.agrega(Integer.parseInt(i));
		}
		return ab;
	}

	/**
	 *
     * Obtiene elementos de un arreglo para volverlos Indexeables y meterlos en una Lista.
     *
     * @param li Lista de Indexable donde se meteran los elementos.
     * @param elementos Arreglo de los elementos.
     * @return la Lista de Indexable con los elementos.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static Lista<Indexable<Integer>> obtenerElementosListaIndexable (Lista<Indexable<Integer>> li, String[] elementos) throws NumberFormatException {
		Lista<Integer> l = obtenerElementosLista(new Lista<Integer>(), elementos);
		for (int i:l) {
			li.agrega(new Indexable<Integer>(i, i));
		}
		return li;
	}

	/**
	 *
     * Obtiene elementos y relaciones de dos arreglos para meterlos en una Grafica.
     *
     * @param g Grafica de Integers donde se meteran los elementos y sus relaciones.
     * @param elementos Arreglo de los elementos.
     * @param relaciones Arreglo de las relaciones.
     * @return la Grafica de Integers con los elementos y relaciones.
     * @throws NumberFormatException si alguno de los elementos no es entero.
     */
	public static Grafica<Integer> obtenerElementosGrafica(Grafica<Integer> g, String[] elementos, String[] relaciones) throws NumberFormatException {
		String[] arista;
		for (String i: elementos) {
			g.agrega(Integer.parseInt(i));
		}
		for (String i: relaciones) {
			arista = i.split(",");
			g.conecta(Integer.parseInt(arista[0]), Integer.parseInt(arista[1]));
		}
		return g;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = null;
		String estructura = "";
		String[] elementos = null, relaciones = null;;
		EstructurasDeDatos estructuraE = null;
		EstructurasDatosSVG edSVG = new EstructurasDatosSVG();
		Lista<Integer> lista;

		if (args.length == 0) {
			br = new BufferedReader(new InputStreamReader(System.in));
		} else {
			try {
				br = new BufferedReader(new FileReader(args[0]));
			} catch (IOException e) {
				System.out.println("No se encontro el archivo");
				return;
			}
		}

		// Leyendo la estructura que queremos recibir.
		try {
	        estructura = br.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}

		// Evaluando la estructura.
		if (estructura.charAt(0) != '#') {
			System.out.println("Error de sintaxis");
			return;
		}
		estructura = estructura.replace("#", "").replace(" ", "");
		try {
			estructuraE =  EstructurasDeDatos.valueOf(estructura);
		} catch (IllegalArgumentException e) {
			System.out.println("Estructura erronea");
		}
		
		// Leyendo elementos.
		try {
	        elementos = br.readLine().replace(" ", "").split(",");
		} catch (IOException e) {
			System.out.println("Error al introducir los elementos.");
			return;
		}
		try {
			switch (estructuraE) {
				case Lista:
					lista = obtenerElementosLista(new Lista<Integer>(), elementos);
					System.out.println(edSVG.lista(lista));
					break;
				case Cola:
					Cola<Integer> cola = new Cola<Integer>();
					cola = obtenerElementosCola(cola, elementos);
					System.out.println(edSVG.meteSaca(cola));
					break;
				case Pila:
					Pila<Integer> pila = new Pila<Integer>();
					pila = obtenerElementosPila(pila, elementos);
					System.out.println(edSVG.meteSaca(pila));
					break;
				case ArbolBinario:
					lista = obtenerElementosLista(new Lista<Integer>(), elementos);
					ArbolBinarioOrdenado<Integer> arbolO = new ArbolBinarioOrdenado<Integer>(lista);
					System.out.println(edSVG.arbolBinario(arbolO, estructuraE));
					break;
				case ArbolBinarioCompleto:
					lista = obtenerElementosLista(new Lista<Integer>(), elementos);
					ArbolBinarioCompleto<Integer> arbolC = new ArbolBinarioCompleto<Integer>(lista);
					System.out.println(edSVG.arbolBinario(arbolC, estructuraE));
					break;
				case ArbolRojinegro:
					ArbolRojinegro<Integer> arbolRN = new ArbolRojinegro<Integer>();
					arbolRN = obtenerElementosArbolRN(arbolRN, elementos);
					System.out.println(edSVG.arbolBinario(arbolRN, estructuraE));
					break;
				case ArbolAVL:
					ArbolAVL<Integer> arbolAVL = new ArbolAVL<Integer>();
					arbolAVL = obtenerElementosArbolAVL(arbolAVL, elementos);
					System.out.println(edSVG.arbolBinario(arbolAVL, estructuraE));
					break;
				case Monticulo:
					Lista<Indexable<Integer>> li = obtenerElementosListaIndexable(new Lista<Indexable<Integer>>(), elementos);
					MonticuloMinimo<Indexable<Integer>> monticulo = new MonticuloMinimo<Indexable<Integer>>(li);
					System.out.println(edSVG.monticulo(monticulo));
					break;
				case Grafica:
					try {
				        relaciones = br.readLine().replace(" ", "").split(";");
					} catch (IOException e) {
						System.out.println("Error al introducir los relaciones.");
						return;
					}
					Grafica<Integer> g = obtenerElementosGrafica(new Grafica<Integer>(), elementos, relaciones);
					System.out.println(edSVG.grafica(g));
					break;
			}	
		} catch (NumberFormatException e) {
			System.out.println("Se deben introducir numeros enteros.");
		}
	}
}