package sistemaDeReservaDeVuelos;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int[][] asientos = inicializarMatriz();
		char[] filas = new char[] {' ','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
		do {
		mostrarMenu();
		String opcion = scan.nextLine();
		generarAccion(opcion, asientos, scan, filas);
		}while(true);
	}
	
	//Creamos la matriz y la llenamos
	//ACLARACION: La matriz tiene espacios extra porque hay que poner la referencia para saber que asiento es cual
	public static int[][] inicializarMatriz() {
		int[][] asientos = new int[11][6];
		
		for(int fila = 0; fila < asientos.length; fila++) {
			for(int columna = 0; columna < asientos[0].length; columna++) {
				if( fila == 0) {
					asientos[fila][columna] = columna+1;
				}else asientos[fila][columna] = 0;
			}
		}
		return asientos;
	}//Listo
	
	public static void mostrarMenu() {
		System.out.println("**************************");
		System.out.println("1. Mostrar asientos");
		System.out.println("2. Reservar asiento");
		System.out.println("3. Cancelar reserva");
		System.out.println("4. Salir");
		System.out.println("**************************");
		System.out.print("Ingrese el numero de Opcion: ");
	}//Listo
	
	public static void generarAccion(String opcion, int[][] asientos, Scanner scan, char[] filas) {
		switch(opcion) {
		
		case "1":
			mostrarAsientos(asientos, filas);
			break;
			
		case "2":
			reservarAsiento(asientos, scan, filas);
			break;
			
		case "3":
			cancelarReserva(asientos, scan, filas);
			break;
		
		case "4":
			scan.close();
			System.exit(0);
			break;
			
		case "?":
			buscarSiExiste(asientos, scan, filas);
			break;
			
		default: 
			System.out.println("ERROR: Opcion Invalida");
			break;
		}
	}//Listo
	
	public static void mostrarAsientos(int[][] asientos, char[] filas) {
		System.out.println("*************************");
		
		for(int fila = 0; fila < asientos.length; fila++) {
			System.out.print(" " + filas[fila] + "   ");
			
			for(int columna = 0; columna < asientos[0].length; columna++) {
				System.out.print(asientos[fila][columna] + "   ");
				if(columna == 2) {
					System.out.print("    ");
				}
			}
			System.out.print(filas[fila]);
			
			System.out.println();
		}
			System.out.println("*************************");
	}
	
	public static void reservarAsiento(int[][] asientos, Scanner scan, char[] filas) {
		do {
			System.out.print("Ingrese la letra del asiento a reservar");
			String s = scan.nextLine();
			char fila = s.charAt(0);
			int numeroDeFila = 0;
			System.out.print("Ingrese el numero del asiento a reservar");
			int numeroDeColumna = Integer.parseInt(scan.nextLine());
		
			for(int i = 1; i < filas.length; i++) {
				if(fila == filas[i]) {
					numeroDeFila = i;
					break;
				}
			}
		
			if(asientos[numeroDeFila][numeroDeColumna - 1] != 1) {
				asientos[numeroDeFila][numeroDeColumna - 1] = 1;
				break;
			}else System.out.println("Este asiento esta ocupado, intente otro");
	}while(true);
	}
	
	public static void cancelarReserva(int[][] asientos, Scanner scan, char[] filas) {
        System.out.print("Ingrese la letra del asiento a cancelar: ");
        String s = scan.nextLine();
        char fila = s.charAt(0);
        int numeroDeFila = 0;

        System.out.print("Ingrese el número del asiento a cancelar: ");
        int numeroDeColumna = Integer.parseInt(scan.nextLine());

        for (int i = 1; i < filas.length; i++) {
            if (fila == filas[i]) {
                numeroDeFila = i;
                break;
            }
        }

        if (asientos[numeroDeFila][numeroDeColumna - 1] == 1) {
            asientos[numeroDeFila][numeroDeColumna - 1] = 0;
        } else {
            System.out.println("Este asiento no estaba ocupado.");
        }
    }

    public static boolean buscarSiExiste(int[][] asientos, Scanner scan, char[] filas) {
        System.out.print("Ingrese la letra del asiento: ");
        String s = scan.nextLine();
        char fila = s.charAt(0);
        int numeroDeFila = 0;

        System.out.print("Ingrese el número del asiento: ");
        int numeroDeColumna = Integer.parseInt(scan.nextLine());

        for (int i = 1; i < filas.length; i++) {
            if (fila == filas[i]) {
                numeroDeFila = i;
                break;
            }
        }

        if (numeroDeColumna < 1 || numeroDeColumna > 5 || numeroDeFila < 1 || numeroDeFila >= filas.length) {
            System.out.println("El asiento no existe.");
            return false;
        }
        return true;
    }
}
