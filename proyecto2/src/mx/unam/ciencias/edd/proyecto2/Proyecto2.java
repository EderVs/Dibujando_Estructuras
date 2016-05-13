package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mx.unam.ciencias.edd.*;

public class Proyecto2 {

	public static Lista<Integer> obtenerElementosLista (Lista<Integer> l, String elementos) {
		for (String i: elementos.split(", ")) {
			l.agrega(Integer.parseInt(i));
		}
		return l;
	}

	public static Cola<Integer> obtenerElementosCola (Cola<Integer> c, String elementos) {
		for (String i: elementos.split(", ")) {
			c.mete(Integer.parseInt(i));
		}
		return c;
	}

	public static Pila<Integer> obtenerElementosPila (Pila<Integer> p, String elementos) {
		for (String i: elementos.split(", ")) {
			p.mete(Integer.parseInt(i));
		}
		return p;
	}

	public static ArbolRojinegro<Integer> obtenerElementosArbolRN (ArbolRojinegro<Integer> ab, String elementos) {
		for (String i: elementos.split(", ")) {
			ab.agrega(Integer.parseInt(i));
		}
		return ab;
	}

	public static ArbolAVL<Integer> obtenerElementosArbolAVL (ArbolAVL<Integer> ab, String elementos) {
		for (String i: elementos.split(", ")) {
			ab.agrega(Integer.parseInt(i));
		}
		return ab;
	}

	public static Lista<Indexable<Integer>> obtenerElementosListaIndexable (Lista<Indexable<Integer>> li, String elementos) {
		Lista<Integer> l = obtenerElementosLista(new Lista<Integer>(), elementos);
		for (int i:l) {
			li.agrega(new Indexable<Integer>(i, i));
		}
		return li;
	}

	public static Grafica<Integer> obtenerElementosGrafica(Grafica<Integer> g, String elementos, String relaciones) {
		String[] arista;
		for (String i: elementos.split(", ")) {
			g.agrega(Integer.parseInt(i));
		}
		for (String i: relaciones.split(";")) {
			arista = i.split(",");
			g.conecta(Integer.parseInt(arista[0]), Integer.parseInt(arista[1]));
		}
		return g;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String estructura = "", elementos = "", relaciones = "";
		EstructurasDeDatos estructuraE = null;
		EstructurasDatosSVG edSVG = new EstructurasDatosSVG();
		Lista<Integer> lista;

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
	        elementos = br.readLine();
		} catch (IOException e) {
			System.out.println("Error al introducir los elementos.");
			return;
		}

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
			        relaciones = br.readLine();
				} catch (IOException e) {
					System.out.println("Error al introducir los relaciones.");
					return;
				}
				Grafica<Integer> g = obtenerElementosGrafica(new Grafica<Integer>(), elementos, relaciones);
				System.out.println(edSVG.grafica(g));
				break;
		}	
	}
}