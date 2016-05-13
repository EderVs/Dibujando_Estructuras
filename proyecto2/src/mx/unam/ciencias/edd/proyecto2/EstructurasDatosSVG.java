package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.*;

public class EstructurasDatosSVG {

	private SVGUtils utils;
	private String xml;

	public EstructurasDatosSVG () {
		utils = new SVGUtils();
		xml = "<?xml version='1.0' encoding='utf-8'?>";
	}

	// Auxiliar para Graficas.
	private class VerticeCoordenada implements Comparable<VerticeCoordenada> {
		
		VerticeGrafica<Integer> vertice;
		double x;
		double y;

		public VerticeCoordenada(VerticeGrafica<Integer> vertice, double x, double y) {
			this.vertice = vertice;
			this.x = x;
			this.y = y;
		}

		@Override public int compareTo(VerticeCoordenada vc) {
			return this.vertice.getElemento().compareTo(vc.vertice.getElemento());
		}

		public boolean equals(VerticeCoordenada vc) {
			/*if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") VerticeCoordenada vc = (VerticeCoordenada)o;*/
            return vc.vertice.getElemento().equals(this.vertice.getElemento());
        }

        @Override public String toString() {
        	return this.vertice.getElemento().toString();
        }
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

	public String meteSaca (MeteSaca<Integer> ms) {
		String mss = "";
		int padding = 15, border = 25;
		int i = border, largoSVG = border, e;

		while (!ms.esVacia()) {
			e = ms.saca();
			mss += utils.rectanguloConNumero(e, i, 40, padding, border);
			i += this.longitudNumero(e)*10+padding*2;
			if (!ms.esVacia()) {
				mss += utils.flechaDerecha(i+2, 58);
			}
			i += border;
		}
		largoSVG += i;

		return xml + "<svg width='"+ largoSVG +"' height='100'>" + mss + "</svg>";
	}

	public String arbolBinario (ArbolBinario<Integer> ab, EstructurasDeDatos arbol_a) {
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

		arbol = this.obtenerVertices(ab.raiz(), radio, largoSVG/2, iniX, iniY, arbol_a);
		return xml + "<svg width='"+ largoSVG +"' height='"+ altoSVG +"'>" + arbol + "</svg>";
	}

	public String monticulo (MonticuloMinimo<Indexable<Integer>> mm) {
		ArbolBinarioCompleto<Integer> abc = new ArbolBinarioCompleto<Integer>();
		for (Indexable<Integer> i:mm) {
			abc.agrega(i.getElemento());
		}
		return this.arbolBinario(abc, EstructurasDeDatos.ArbolBinarioCompleto);
	}

	public String grafica (Grafica<Integer> g) {
		String grafica;
		int padding = 15, radio;
		int perimetro, max;
		double radioG;
		double largoSVG, altoSVG;

		max = this.obtenerMaximo(g);

		radio = (this.longitudNumero(max)*10+padding*2)/2;
		perimetro = g.getElementos()*radio*3;
		radioG = perimetro / 3.1416;

		largoSVG = altoSVG = radioG*2 + radio*2.0*2.0;

		grafica = this.obtenerVertices(g, radioG, radio, largoSVG/2, altoSVG/2);
		return xml + "<svg width='"+ largoSVG +"' height='"+ altoSVG +"'>" + grafica + "</svg>";
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

	private int obtenerMaximo (Grafica<Integer> g) {
		int max = 0;
		for (int i:g) {
			max = i;
			break;
		}
		for (int i: g) {
			if (max < i) {
				max = i;
			}
		}
		return max;
	}


	private int obtenerLongitudSVGArbol (ArbolBinario<Integer> ab, int radio) {
		int numeroHojas = (int) Math.pow(2,ab.profundidad());
		return (numeroHojas+(numeroHojas/2)+2)*(radio*2);
	}


	private int obtenerAlturaSVGArbol (ArbolBinario<Integer> ab, int radio) {
		return (ab.profundidad()+3)*(radio*2);
	}


	private String obtenerVertices (VerticeArbolBinario<Integer> vertice, int radio, int i, int x, int y, EstructurasDeDatos arbol_a) {
		String arbol = "", color = "white", colorLetra = "black";
		i /= 2;
		// Recusivamente obteniendo los sub-arboles izquierdo y derecho.
		if (vertice.hayIzquierdo()) {
			arbol += utils.linea(x, y, x-i, y+radio*2);
			arbol += obtenerVertices(vertice.getIzquierdo(), radio, i, x-i, y+radio*2, arbol_a);
		}
		if (vertice.hayDerecho()) {
			arbol += utils.linea(x, y, x+i, y+radio*2);
			arbol += obtenerVertices(vertice.getDerecho(), radio, i, x+i, y+radio*2, arbol_a);
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

		arbol += utils.circuloConNumero(vertice.get(), x, y, radio, color, colorLetra);
		if (arbol_a == EstructurasDeDatos.ArbolAVL) {
			arbol += utils.texto(vertice.toString().split(" ")[1], x+radio, y-(radio/2), "text-anchor='middle'");
		}
		return arbol;
	}


	private String obtenerVertices (Grafica<Integer> g, double radioG, int radio, double x, double y)  {
		String vertices = "", aristas = "", color = "white", colorLetra = "black";
		double angulo = Math.toRadians(360 / g.getElementos());
		double anguloi = 0, xi, yi;
		int i = 0;
		VerticeCoordenada coordenadai;
		VerticeGrafica<Integer> vi = null;
		VerticeCoordenada[] coordenadas = new VerticeCoordenada[g.getElementos()];
		Arreglos arr = new Arreglos();

		// Obteniendo Vertices y asignarles una coordenada.
		for (int v: g) {
			xi = radioG*Math.cos(anguloi);
			yi = radioG*Math.sin(anguloi);
			vertices += utils.circuloConNumero(v, x+xi, y+yi, radio, color, colorLetra);

			vi = g.vertice(v);
			coordenadai = new VerticeCoordenada(vi, x+xi, y+yi);
			coordenadas[i] = coordenadai;

			anguloi += angulo;
			i += 1;
		}


		// Obteniendo aristas.
		arr.quickSort(coordenadas);
		for (VerticeCoordenada v: coordenadas) {
			for (VerticeGrafica<Integer> vecino: v.vertice.vecinos()) {
				coordenadai = new VerticeCoordenada(vecino, 0, 0);
				coordenadai = coordenadas[arr.busquedaBinaria(coordenadas, coordenadai)];
				aristas += utils.linea(v.x, v.y, coordenadai.x, coordenadai.y);
			}
		}

		return aristas + vertices;
	}
}