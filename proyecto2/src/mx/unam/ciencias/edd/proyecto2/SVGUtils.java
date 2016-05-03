package mx.unam.ciencias.edd.proyecto2;

public class SVGUtils {

	/* Utils */
	private int longitudNumero (int n) {
		int i = 1;
		while (n >= 10) {
			n /= 10;
			i++;
		}
		return i;
	}

	/* textos */
	public String texto (String texto, double x, double y, String extra) {
		return "<text x='"+ x +"' y='"+ y +"' font-size='20' "+ extra +">"+ texto +"</text>";
	}

	public String numero (int n, double x, double y) {
		return "<text x='"+ x +"' y='"+ y +"' font-size='20'>"+ n +"</text>";
	}

	/* Figuras */
	public String rectangulo (double base, double altura, double x, double y) {
		return "<rect x='"+ x +"' y='"+ y +"' width='"+ base +"' height='"+ altura +"' stroke='black' stroke-width='1' fill='white'/>";
	}

	/* Figuras con Textos */
	public String rectanguloConTexto (String texto, double x, double y) {
		return this.rectangulo(texto.length()*10+10, 25, x, y) + this.texto(texto, x+5, y+20, "");
	}

	public String rectanguloConNumero (int n, double x, double y, int padding, int altura) {
		return this.rectangulo(this.longitudNumero(n)*10+padding*2, altura, x, y) + this.numero(n, x+padding, y+20);
	}

	/* Flechas */
	public String dobleFlecha (double x, double y) {
		return this.texto("↔", x, y, "font-weight='bold'");
	}

	public String flechaDerecha (double x, double y) {
		return this.texto("→", x, y, "font-weight='bold'");
	}
}