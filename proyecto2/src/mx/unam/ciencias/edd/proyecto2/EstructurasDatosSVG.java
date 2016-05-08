package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

public class EstructurasDatosSVG {

	private SVGUtils utils;
	private String xml;

	public EstructurasDatosSVG () {
		utils = new SVGUtils();
		xml = "<?xml version='1.0' encoding='utf-8'?>";
	}

	public String lista (Lista<Integer> l) {
		String lista = "";
		int padding = 15, border = 25;
		int i = border, largoSVG = border;

		for (int e: l) {
			lista += utils.rectanguloConNumero(e, i, 40, padding, border);
			i += this.longitudNumero(e)*10+padding*2;
			if (e != l.getUltimo()) {
				lista += utils.dobleFlecha(i+2, 58);
			}
			i += border;
		}
		largoSVG += i;

		return xml + "<svg width='"+ largoSVG +"' height='100'>" + lista + "</svg>";
	}

	public String cola (Cola<Integer> c) {
		String cola = "";
		int padding = 15, border = 25;
		int i = border, largoSVG = border, e;

		while (!c.esVacia()) {
			e = c.saca();
			cola += utils.rectanguloConNumero(e, i, 40, padding, border);
			i += this.longitudNumero(e)*10+padding*2;
			if (!c.esVacia()) {
				cola += utils.flechaDerecha(i+2, 58);
			}
			i += border;
		}
		largoSVG += i;

		return xml + "<svg width='"+ largoSVG +"' height='100'>" + cola + "</svg>";
	}

	public String pilas (Pila<Integer> p) {
		String pila = "";
		int padding = 15, border = 25;
		int i = border, largoSVG = border, e;

		while (!p.esVacia()) {
			e = p.saca();
			pila += utils.rectanguloConNumero(e, i, 40, padding, border);
			i += this.longitudNumero(e)*10+padding*2;
			if (!p.esVacia()) {
				pila += utils.flechaDerecha(i+2, 58);
			}
			i += border;
		}
		largoSVG += i;

		return xml + "<svg width='"+ largoSVG +"' height='100'>" + pila + "</svg>";
	}

	public String arbolBinario (ArbolBinario<Integer> ab) {
		int padding = 15, largoSVG, altoSVG, radio;
		int iniX, iniY;
		String arbol;
		VerticeArbolBinario<Integer> max;

		if (ab.esVacio()) {
			return xml;
		}

		// Obteniendo el valor maximo del arbol.
		max = this.obtenerMaximo(ab.raiz());
		
		radio = (this.longitudNumero(max.get())*10+padding*2)/2;
		largoSVG = this.obtenerLongitudSVGArbol(ab,radio);
		altoSVG = this.obtenerAlturaSVGArbol(ab,radio);

		iniX = largoSVG/2;
		iniY = radio*3;

		arbol = this.obtenerVertices(ab.raiz(), radio, padding, largoSVG/2, iniX, iniY);
		return xml + "<svg width='"+ largoSVG +"' height='"+ altoSVG +"'>" + arbol + "</svg>";
	}

	private int longitudNumero (int n) {
		int i = 1;
		while (n >= 10) {
			n /= 10;
			i++;
		}
		return i;
	}

	private VerticeArbolBinario<Integer> obtenerMaximo(VerticeArbolBinario<Integer> vertice) {
		VerticeArbolBinario<Integer> izq = null, der = null, max;
		if (vertice == null) {
			return null;
		}
		if (!vertice.hayIzquierdo() && !vertice.hayDerecho()) {
			return vertice;
		} else {
			if (vertice.hayIzquierdo()) {
				izq = this.obtenerMaximo(vertice.getIzquierdo());	
			}
			if (vertice.hayDerecho()) {
				der = this.obtenerMaximo(vertice.getDerecho());	
			}
		}

		if (izq != null && der != null) {
			max = ((izq.get().compareTo(der.get())>=0)? izq : der);
		} else {
			if (izq == null){
				max = der;
			} else {
				max = izq;
			}		
		}

		return ((vertice.get().compareTo(max.get())>=0)? vertice : max);
	}

	private int obtenerLongitudSVGArbol (ArbolBinario<Integer> ab, int radio) {
		int numeroHojas = (int) Math.pow(2,ab.profundidad());
		return (numeroHojas+(numeroHojas/2)+2)*(radio*2);
	}

	private int obtenerAlturaSVGArbol (ArbolBinario<Integer> ab, int radio) {
		return (ab.profundidad()+3)*(radio*2);
	}

	private String obtenerVertices (VerticeArbolBinario<Integer> vertice, int radio, int padding, int i, int x, int y) {
		String arbol = "", color = "white", colorLetra = "black";
		i /= 2;
		// Recusivamente obteniendo los sub-arboles izquierdo y derecho.
		if (vertice.hayIzquierdo()) {
			arbol += utils.linea(x, y, x-i, y+radio*2);
			arbol += obtenerVertices(vertice.getIzquierdo(), radio, padding, i, x-i, y+radio*2);
		}
		if (vertice.hayDerecho()) {
			arbol += utils.linea(x, y, x+i, y+radio*2);
			arbol += obtenerVertices(vertice.getDerecho(), radio, padding, i, x+i, y+radio*2);
		}

		// Obteniendo si necesita color.
		if (vertice.toString().charAt(0) == ('R')) {
			color = "red";
			colorLetra = "white";
		}
		if (vertice.toString().charAt(0) == ('N')) {
			color = "black";
			colorLetra = "white";
		}

		arbol += utils.circuloConNumero(vertice.get(), x, y, padding, radio, color, colorLetra);
		return arbol;
	}
}