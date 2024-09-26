package com.mycompany.p1_buscaminas;
import java.util.Scanner;

public class pruebaJuego {

	public static void main(String[] args) {
	
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Ingresa dimensiones, mine, ejeX, ejeY");
		int x = entrada.nextInt();
		int y = entrada.nextInt();
		int mine = entrada.nextInt();
		int tabX = entrada.nextInt();
		int tabY = entrada.nextInt();
		
		Juego obj = new Juego(x, y, mine, tabX, tabY);

	}

}
