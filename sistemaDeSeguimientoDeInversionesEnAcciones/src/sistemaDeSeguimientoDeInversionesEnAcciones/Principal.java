package sistemaDeSeguimientoDeInversionesEnAcciones;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		final int MAXIMO_DE_ACCIONES = 100;
		final int ATRIBUTOS_POR_ACCION = 5;
		
		Scanner s = new Scanner(System.in);
		
		String[][] acciones = new String[MAXIMO_DE_ACCIONES][ATRIBUTOS_POR_ACCION];
		
		boolean primerCompraHecha = false;
		agregarAccion(acciones, s, primerCompraHecha);
	}
	
	public static void agregarAccion(String[][] acciones, Scanner s, boolean primeraCompraHecha) {
		if(!primeraCompraHecha) {
			int cantAcciones = 0;
			int accionID = 0;
			boolean  huboError= false;
			System.out.println("Ingrese la cantidad de acciones que quiere comprar");
			cantAcciones = ingresarEntero(s, 100, 0);
			
			do {
				huboError = false;
				acciones[accionID][0] = Integer.toString(accionID);
				System.out.println("Ingrese el nombre de la empresa");
				acciones[accionID][1] = s.nextLine();
				System.out.println("Ingrese cuanto vale la accion ahora mismo");
				do {
					try {
						acciones[accionID][2] = Integer.toString(s.nextInt());
					}catch(InputMismatchException e) {
						System.out.println("Intente otra vez");
						huboError = true;
					}
				}while(!huboError);
				accionID++;
			}while(accionID <= (cantAcciones-1));		
		}
		
	}

	public static void eliminarAccion() {
		
	}
	
	public static void actualizarPrecioAccion() {
		
	}
	
	public static void consultarAccion() {
		
	}
	
	public static void calcularGananciaPerdida() {
		
	}
	
	public static void mostrarMenu() {
		
		
	}
	
	public static void procesarMenu() {
		
	}
	
	public static int ingresarEntero(Scanner s, final int MAX, final int MIN) {
		int numero = 0;
		return numero;
	}
	
}
