package barcosP;

import java.util.Scanner;

public class JuegoBarcos {

	// Tablero de juego
	public char tableroBarcos[][];

	// Tablero de mis disparos
	public char tableroDisparos[][];

	public static final char AGUA = 'A';
	public static final char DISPARO = 'D';
	public static final char TOCADO = 'T';
	public static final char BARCO = 'B';

	public static final int ARRIBA = 0;
	public static final int ABAJO = 1;
	public static final int DERECHA = 2;
	public static final int IZQUIERDA = 3;
	
	public static final int TABLERO_DISPAROS = 0;
	public static final int TABLERO_BARCOS = 1;
	

	// Constructor
	public JuegoBarcos() {
		super();

		// Defino el tamaño del tablero
		tableroBarcos = new char[10][10];
		// Defino el tamaño del tablero
		tableroDisparos = new char[10][10];
		// Inicializamos las casillas
		inicializarJuego();

	}
	
	public void ejecutarJuego()
	{
		
		Scanner teclado = new Scanner(System.in);
		int posX;
		int posY;
		while (true)
		{
			System.out.println("Preparados para disparar!");
			//Recibimos los valores
			System.out.println("Coordenada X(horizontal):");
			posX = teclado.nextInt();
			System.out.println("Coordenada Y(vertical):");
			posY = teclado.nextInt();
			
			this.disparo(posX, posY);
			mostrarTablero(TABLERO_DISPAROS);
		
		}
		
		//teclado.close();
	}

	public void mostrarTablero(int tipoTablero) {

		if (tipoTablero == TABLERO_BARCOS)
		{	
		System.out.println(" ");

		// Recorremos los 10 arrays
		for (int i = 0; i < tableroBarcos.length; i++) {

			// barcosNavegando[i] es un array, lo recorremos
			for (int j = 0; j < tableroBarcos[i].length; j++) {
				// Mostramos por pantalla todas las posiciones
				System.out.print(tableroBarcos[i][j] + " ");

			}

			// Pasamos a la siguiente linea
			System.out.println();

		}

		System.out.println(" ");
		}
		
		if (tipoTablero == TABLERO_DISPAROS)
		{	
		System.out.println(" ");

		// Recorremos los 10 arrays
		for (int i = 0; i < tableroDisparos.length; i++) {

			// barcosNavegando[i] es un array, lo recorremos
			for (int j = 0; j < tableroDisparos[i].length; j++) {
				// Mostramos por pantalla todas las posiciones
				System.out.print(tableroDisparos[i][j] + " ");

			}

			// Pasamos a la siguiente linea
			System.out.println();

		}

		System.out.println(" ");
		}
	
		
	}

	int generarDireccion() {
		return (int) (Math.random() * 4);
	}

	/**
	 * Posiciona aleatoria
	 * 
	 * @return un numero entre 0 y 9
	 */
	int valorAleatorio() {
		return (int) (Math.random() * 10);
	}

	public boolean rellenarTablero() {
		boolean resultado = true;
		boolean posicionado = false;

		// Ponemos el de 4
		while (!posicionado)
			posicionado = posicionarBarco(4);

		// Ponemos los dos de 3
		for (int i = 0; i < 2; i++) {
			posicionado = false;
			// Ponemos los dos de 3
			while (!posicionado)
				posicionado = posicionarBarco(3);
		}

		// Ponemos los tres de 2
		for (int i = 0; i < 3; i++) {
			posicionado = false;
			while (!posicionado)
				posicionado = posicionarBarco(2);
		}

		// Ponemos los cuatro de 1
		for (int i = 0; i < 4; i++) {
			posicionado = false;
			while (!posicionado)
				posicionado = posicionarBarco(1);
		}

		return resultado;
	}

	/**
	 * Posiciona en el tablero un barco de ese tamaño aleatoriamente
	 * 
	 * @param tamano
	 * @return
	 */
	public boolean posicionarBarco(int tamano) {

		int posInicialX = valorAleatorio();
		int posInicialY = valorAleatorio();
		boolean resultado = true;

		// La posicion inicial del barco tiene que ser agua
		while (tableroBarcos[posInicialX][posInicialY] != AGUA) {
			posInicialX = valorAleatorio();
			posInicialY = valorAleatorio();
		}

		int posX = posInicialX;
		int posY = posInicialY;
		// Dirección en la cual vamos a dibujar el barco
		int direccion = this.generarDireccion();

		// Comprobamos que alrededor de las posiciones del barco
		// Todo es agua
		for (int i = 0; i < tamano; i++) {

			try {
				// Primero compruebo que la posicion actual es agua
				if (tableroBarcos[posX][posY] != AGUA)
					return false;

				// Comprobamos la posicion superior izquierda
				if (posX - 1 >= 0 && posY - 1 >= 0 && tableroBarcos[posX - 1][posY - 1] != AGUA)
					return false;

				// Comprobamos la posicion superior
				if (posY - 1 >= 0 && tableroBarcos[posX][posY - 1] != AGUA)
					return false;

				// Comprobamos la posicion superior derecha
				if (posX + 1 <= 9 && posY - 1 >= 0 && tableroBarcos[posX + 1][posY - 1] != AGUA)
					return false;

				// Comprobamos la posicion izquierda
				if (posX - 1 >= 0 && tableroBarcos[posX - 1][posY] != AGUA)
					return false;

				// Comprobamos la posicion derecha
				if (posX + 1 <= 9 && tableroBarcos[posX + 1][posY] != AGUA)
					return false;

				// Comprobamos la posicion inferior izquierda
				if (posX - 1 >= 0 && posY + 1 >= 0 && tableroBarcos[posX - 1][posY + 1] != AGUA)
					return false;

				// Comprobamos la posicion inferior
				if (posY + 1 <= 9 && tableroBarcos[posX][posY + 1] != AGUA)
					return false;

				// Comprobamos la posicion inferior derecha
				if (posX + 1 <= 9 && posY + 1 <= 9 && tableroBarcos[posX + 1][posY + 1] != AGUA)
					return false;

				// Pasamos a la siguiente posicion
				switch (direccion) {
				case ARRIBA:
					posY--;
					break;
				case ABAJO:
					posY++;
					break;
				case DERECHA:
					posX++;
					break;
				case IZQUIERDA:
					posX--;
					break;
				}
				// Controlamos que la posicion del barco siguiente
				// Sea fuera del array
			} catch (ArrayIndexOutOfBoundsException ae) {
				return false;
			}

		} // FINALIZAMOS DE COMPROBAR LAS POSICIONES DEL BARCO

		// Reseteamos posx y posy a las posiciones iniciales del barco
		posX = posInicialX;
		posY = posInicialY;

		// Dibujamos el barco
		for (int i = 0; i < tamano; i++) {
			// Dibujamos el barco
			tableroBarcos[posX][posY] = BARCO;

			// Pasamos a la siguiente posicion
			switch (direccion) {
			case ARRIBA:
				posY--;
				break;
			case ABAJO:
				posY++;
				break;
			case DERECHA:
				posX++;
				break;
			case IZQUIERDA:
				posX--;
				break;
			}

		}

		return resultado;

	}

	public void inicializarJuego() {

		// Recorremos los 10 arrays
		for (int i = 0; i < tableroBarcos.length; i++) {

			// barcosNavegando[i] es un array, lo recorremos
			for (int j = 0; j < tableroBarcos[i].length; j++) {
				// Inicializo a agua todas las posiciones
				tableroBarcos[i][j] = this.AGUA;

			}

		}

		// Recorremos los 10 arrays
		for (int i = 0; i < tableroDisparos.length; i++) {

			// barcosNavegando[i] es un array, lo recorremos
			for (int j = 0; j < tableroDisparos[i].length; j++) {
				// Inicializo a agua todas las posiciones
				tableroDisparos[i][j] = this.AGUA;

			}

		}
		// Rellenamos de barcos el tablero objetivo
		rellenarTablero();

	}

	/**
	 * Recibe unas coordenadas y dispara, mostrando en nuestro Mapa de juego si se
	 * ha acertado o no
	 * 
	 * @param posX
	 * @param posY
	 */
	public void disparo(int posX, int posY) {
		
		if (posX >= 0 && posX <= 9 && posY >= 0 && posY <= 9)

			switch (tableroBarcos[posX][posY]) {

			case AGUA:
				tableroDisparos[posX][posY] = DISPARO;
				break;
				
			case BARCO:
				tableroDisparos[posX][posY] = TOCADO;
				break;
	
			}

	}

}
