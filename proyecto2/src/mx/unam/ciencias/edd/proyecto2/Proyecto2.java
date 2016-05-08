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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String estructura = "", elementos = "";
		Lista<String> estructuras_permitidas = new Lista<String>();
		EstructurasDatosSVG edSVG = new EstructurasDatosSVG();
		Lista<Integer> lista;

		estructuras_permitidas.agrega("Lista");
		estructuras_permitidas.agrega("Pila");
		estructuras_permitidas.agrega("Cola");
		estructuras_permitidas.agrega("ArbolBinario");
		estructuras_permitidas.agrega("ArbolBinarioCompleto");
		estructuras_permitidas.agrega("ArbolRojinegro");
		estructuras_permitidas.agrega("ArbolAVL");
		estructuras_permitidas.agrega("Grafica");

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
		estructura = estructura.substring(2);
		if (!estructuras_permitidas.contiene(estructura)) {
			System.out.println("Estructura erronea.");
			return;
		}
		
		// Leyendo elementos.
		try {
	        elementos = br.readLine();
		} catch (IOException e) {
			System.out.println("Error al introducir los elementos.");
			return;
		}

		switch (estructura) {
			case "Lista":
				lista = obtenerElementosLista(new Lista<Integer>(), elementos);
				System.out.println(edSVG.lista(lista));
				break;
			case "Cola":
				Cola<Integer> cola = new Cola<Integer>();
				cola = obtenerElementosCola(cola, elementos);
				System.out.println(edSVG.cola(cola));
				break;
			case "Pila":
				Pila<Integer> pila = new Pila<Integer>();
				pila = obtenerElementosPila(pila, elementos);
				System.out.println(edSVG.pilas(pila));
				break;
			case "ArbolBinario":
				lista = obtenerElementosLista(new Lista<Integer>(), elementos);
				ArbolBinarioOrdenado<Integer> arbolO = new ArbolBinarioOrdenado<Integer>(lista);
				System.out.println(edSVG.arbolBinario(arbolO));
				break;
			case "ArbolBinarioCompleto":
				lista = obtenerElementosLista(new Lista<Integer>(), elementos);
				ArbolBinarioCompleto<Integer> arbolC = new ArbolBinarioCompleto<Integer>(lista);
				System.out.println(edSVG.arbolBinario(arbolC));
				break;
			case "ArbolRojinegro":
				ArbolRojinegro<Integer> arbolRN = new ArbolRojinegro<Integer>();
				arbolRN = obtenerElementosArbolRN(arbolRN, elementos);
				System.out.println(edSVG.arbolBinario(arbolRN));
				break;
		}	
	}
}