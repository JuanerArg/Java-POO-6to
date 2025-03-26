package sistemaDeReservaDeVuelos;

import java.util.Scanner;

public class Main {

    public static final int NUM_FILAS = 11;
    public static final int NUM_COLUMNAS = 6;
    public static final String OPCION_MOSTRAR_ASIENTOS = "1";
    public static final String OPCION_RESERVAR_ASIENTO = "2";
    public static final String OPCION_CANCELAR_RESERVA = "3";
    public static final String OPCION_SALIR = "4";
    public static final String OPCION_BUSCAR_ASIENTO = "?";
    public static final int ASIENTO_OCUPADO = 1;
    public static final int ASIENTO_LIBRE = 0;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] asientos = inicializarMatriz();
        char[] filas = new char[] {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            String opcion = scan.nextLine();
            try {
                continuar = generarAccion(opcion, asientos, scan, filas);
            } catch(Exception e) {
                System.out.println("ERROR: Try Again");
            }
        }
        scan.close();
        return;
    }
    
    public static int[][] inicializarMatriz() {
        int[][] asientos = new int[NUM_FILAS][NUM_COLUMNAS];
        int fila = 0;
        while (fila < NUM_FILAS) {
            int columna = 0;
            while (columna < NUM_COLUMNAS) {
                if (fila == 0) {
                    asientos[fila][columna] = columna + 1;
                } else {
                    asientos[fila][columna] = ASIENTO_LIBRE;
                }
                columna++;
            }
            fila++;
        }
        return asientos;
    }
    
    public static void mostrarMenu() {
        System.out.println("**************************");
        System.out.println("1. Mostrar asientos");
        System.out.println("2. Reservar asiento");
        System.out.println("3. Cancelar reserva");
        System.out.println("4. Salir");
        System.out.println("**************************");
        System.out.print("Ingrese el numero de Opcion: ");
    }
    
    public static boolean generarAccion(String opcion, int[][] asientos, Scanner scan, char[] filas) {
        switch (opcion) {
            case OPCION_MOSTRAR_ASIENTOS:
                mostrarAsientos(asientos, filas);
                break;
            case OPCION_RESERVAR_ASIENTO:
                reservarAsiento(asientos, scan, filas);
                break;
            case OPCION_CANCELAR_RESERVA:
                cancelarReserva(asientos, scan, filas);
                break;
            case OPCION_SALIR:
                return false;
            case OPCION_BUSCAR_ASIENTO:
                buscarSiExiste(asientos, scan, filas);
                break;
            default:
                System.out.println("ERROR: Try Again");
                break;
        }
        return true;
    }
    
    public static void mostrarAsientos(int[][] asientos, char[] filas) {
        System.out.println("*************************");
        int fila = 0;
        while (fila < NUM_FILAS) {
            System.out.print(" " + filas[fila] + "   ");
            int columna = 0;
            while (columna < NUM_COLUMNAS) {
                System.out.print(asientos[fila][columna] + "   ");
                if (columna == 2) {
                    System.out.print("    ");
                }
                columna++;
            }
            System.out.print(filas[fila]);
            System.out.println();
            fila++;
        }
        System.out.println("*************************");
    }
    
    public static void reservarAsiento(int[][] asientos, Scanner scan, char[] filas) {
        boolean reservado = false;
        while (!reservado) {
            try {
                System.out.print("Ingrese la letra del asiento a reservar: ");
                String s = scan.nextLine();
                char letra = s.charAt(0);
                int numeroDeFila = 0;
                System.out.print("Ingrese el numero del asiento a reservar: ");
                int numeroDeColumna = Integer.parseInt(scan.nextLine());
                int i = 1;
                boolean encontrada = false;
                while (i < filas.length && !encontrada) {
                    if (letra == filas[i]) {
                        numeroDeFila = i;
                        encontrada = true;
                    }
                    i++;
                }
                if (!encontrada) {
                    System.out.println("La letra ingresada no es válida, intente nuevamente.");
                    continue;
                }
                if (asientos[numeroDeFila][numeroDeColumna - 1] != ASIENTO_OCUPADO) {
                    asientos[numeroDeFila][numeroDeColumna - 1] = ASIENTO_OCUPADO;
                    System.out.println("Asiento reservado con éxito.");
                    reservado = true;
                } else {
                    System.out.println("Este asiento está ocupado, intente otro.");
                }
            } catch(Exception e) {
                System.out.println("ERROR: Try Again");
            }
        }
    }
    
    public static void cancelarReserva(int[][] asientos, Scanner scan, char[] filas) {
        try {
            System.out.print("Ingrese la letra del asiento a cancelar: ");
            String s = scan.nextLine();
            char letra = s.charAt(0);
            int numeroDeFila = 0;
            System.out.print("Ingrese el número del asiento a cancelar: ");
            int numeroDeColumna = Integer.parseInt(scan.nextLine());
            int i = 1;
            boolean encontrada = false;
            while (i < filas.length && !encontrada) {
                if (letra == filas[i]) {
                    numeroDeFila = i;
                    encontrada = true;
                }
                i++;
            }
            if (!encontrada) {
                System.out.println("La letra ingresada no es válida.");
                return;
            }
            if (asientos[numeroDeFila][numeroDeColumna - 1] == ASIENTO_OCUPADO) {
                asientos[numeroDeFila][numeroDeColumna - 1] = ASIENTO_LIBRE;
                System.out.println("Reserva cancelada exitosamente.");
            } else {
                System.out.println("Este asiento no estaba ocupado.");
            }
        } catch(Exception e) {
            System.out.println("ERROR: Try Again");
        }
    }
    
    public static boolean buscarSiExiste(int[][] asientos, Scanner scan, char[] filas) {
        try {
            System.out.print("Ingrese la letra del asiento: ");
            String s = scan.nextLine();
            char letra = s.charAt(0);
            int numeroDeFila = 0;
            System.out.print("Ingrese el número del asiento: ");
            int numeroDeColumna = Integer.parseInt(scan.nextLine());
            int i = 1;
            boolean encontrada = false;
            while (i < filas.length && !encontrada) {
                if (letra == filas[i]) {
                    numeroDeFila = i;
                    encontrada = true;
                }
                i++;
            }
            if (!encontrada) {
                System.out.println("El asiento no existe.");
                return false;
            }
            if (numeroDeColumna < 1 || numeroDeColumna > (NUM_COLUMNAS - 1) || numeroDeFila < 1 || numeroDeFila >= filas.length) {
                System.out.println("El asiento no existe.");
                return false;
            }
            System.out.println("El asiento existe.");
            return true;
        } catch(Exception e) {
            System.out.println("ERROR: Try Again");
            return false;
        }
    }
}
