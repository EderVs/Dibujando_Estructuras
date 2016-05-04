package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

public class EstructurasDatosSVG {

	private SVGUtils utils;
	private String xml;

	public EstructurasDatosSVG () {
		utils = new SVGUtils();
		xml = "<?xml version='1.0' encoding='utf-8'?>";
	}

	private int longitudNumero (int n) {
		int i = 1;
		while (n >= 10) {
			n /= 10;
			i++;
		}
		return i;
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
		int padding = 15;
		String arbol = utils.circuloConNumero(ab.raiz().get(), 500, 500, padding, ab.raiz().get(), "white");
		return xml + "<svg width='1000' height='1000'>" + arbol + "</svg>";
	}
}