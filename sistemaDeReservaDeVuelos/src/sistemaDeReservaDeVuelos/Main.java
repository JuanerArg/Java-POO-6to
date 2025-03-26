import java.util.Scanner;

//La clase debería tener un nombre en español para que tenga
//coherencia con el resto del proyecto
public class Main {

	//EL ENUNCIADO DICE:
	//3.	Los atributos no pueden ser definidos a nivel global, 
	//o a nivel clase, sino que deben ser definidos dentro de los 
	//métodos correspondientes
    public static final int NUM_FILAS = 11; //Según el enunciado son 10 -> No cuenta la primer fila "en blanco"
    public static final int NUM_COLUMNAS = 6;
    
    //Le sacaría de OPCION_MOSTRAR_ASIENTOS la palabra OPCION
    //Quedaría más legible en el switch
    //Sería más fácil de tratar si fueran enteros en vez de cadenas
    public static final String OPCION_MOSTRAR_ASIENTOS = "1";
    public static final String OPCION_RESERVAR_ASIENTO = "2";
    public static final String OPCION_CANCELAR_RESERVA = "3";
    public static final String OPCION_SALIR = "4";
    public static final String OPCION_BUSCAR_ASIENTO = "?";
    
    public static final int ASIENTO_OCUPADO = 1;
    public static final int ASIENTO_LIBRE = 0; //Según el enunciado 2
    
    public static void main(String[] args) {
    	//Si bien yo uso s para abreviar el escaner, ya vimos que no es lo ideal,
    	//En otro caso sería mejor que se llame algo como escaner, entrada, etc 
        Scanner scan = new Scanner(System.in);
        int[][] asientos = inicializarMatriz();
        //El array de filas debería ser constante ya que no varía. 
        //Se inicializa asi:
        //char[] filas = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        //Es confuso que la primer fila sea ' '
        char[] filas = new char[] {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        
        boolean continuar = true;
        //Debería ser un do while, ya que la primera vez SIEMPRE ingresa
        while (continuar) {
            mostrarMenu();
 
            String opcion = scan.nextLine();//Debería ingresar un entero y verificar que el entero sea válido
            
            try { //Mal. No va el try ... catch acá
                continuar = generarAccion(opcion, asientos, scan, filas);
            } catch(Exception e) {
                System.out.println("ERROR: Try Again");//Mensaje en español Please. Es poco descriptivo además este mensaje
            }
        }
        scan.close();
        return;
    }
    
    public static int[][] inicializarMatriz() {
    	//NUM_FILAS y NUM_COLUMNAS debería ingresar por parámetros a la función igual que ASIENTO_LIBRE
        int[][] asientos = new int[NUM_FILAS][NUM_COLUMNAS];
        int fila = 0;
        //Esto quedaría muchísimo más legible con 2 FOR
        while (fila < NUM_FILAS) {
            int columna = 0;
            while (columna < NUM_COLUMNAS) {
            	//No entiendo para que sirve este IF
                if (fila == 0) {
                    asientos[fila][columna] = columna + 1;//???
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
  //public static boolean generarAccion(final String OPCION, final int[][] ASIENTOS, Scanner scan, final char[] FILAS) {
    public static boolean generarAccion(String opcion, int[][] asientos, Scanner scan, char[] filas) {
        switch (opcion) {
            case OPCION_MOSTRAR_ASIENTOS://->Esta constante debe entrar por parámetro
                mostrarAsientos(asientos, filas);
                break;
            case OPCION_RESERVAR_ASIENTO://->Esta constante debe entrar por parámetro
                reservarAsiento(asientos, scan, filas);
                break;
            case OPCION_CANCELAR_RESERVA://->Esta constante debe entrar por parámetro
                cancelarReserva(asientos, scan, filas);
                break;
            case OPCION_SALIR://->Esta constante debe entrar por parámetro
                return false;
            case OPCION_BUSCAR_ASIENTO://->Esta constante debe entrar por parámetro
                buscarSiExiste(asientos, scan, filas);
                break;
            default:
                System.out.println("ERROR: Try Again");//Mensaje en español Please. Es poco descriptivo además este mensaje
                break;
        }
        return true;
    }
    //public static void mostrarAsientos(final int[][] ASIENTOS, final char[] FILAS) {
    public static void mostrarAsientos(int[][] asientos, char[] filas) {
        System.out.println("*************************");
        int fila = 0;
        //Mucho más legible si usa for en vez de while acá
        while (fila < NUM_FILAS) {
            System.out.print(" " + filas[fila] + "   ");
            int columna = 0;
            while (columna < NUM_COLUMNAS) {
            	//Según el enunciado debe mostrar - si esta libre o x si esta ocupado
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
  //public static void mostrarAsientos(final int[][] ASIENTOS, Scanner scan, final char[] FILAS) {
    public static void reservarAsiento(int[][] asientos, Scanner scan, char[] filas) {
        boolean reservado = false;
        //es un do while ya que SIEMPRE ingresa al ciclo por lo menos 1 vez
        while (!reservado) {
            try {
                System.out.print("Ingrese la letra del asiento a reservar: ");
                String s = scan.nextLine();
                char letra = s.charAt(0);
                //La comprobación de si la letra es válida o no la pondría acá, y no haría
                //Que le pida el número de asiento hasta que la letra ingresada sea válida
                
                int numeroDeFila = 0;
                System.out.print("Ingrese el numero del asiento a reservar: ");
                //final int NUM_COLUMNA;
                int numeroDeColumna = Integer.parseInt(scan.nextLine());//NO
                //Hagan la función ingresarEntero igual que en clase y no hagan esto ! 
                
                int i = 1;
                boolean encontrada = false;
                //Podría hacer una función que se llame buscarEnteroEnArreglo quedará más legible  
                //y tendrá una función reutilizable por si la necesita en otro lado
                while (i < filas.length && !encontrada) {
                    if (letra == filas[i]) { 
                        numeroDeFila = i;
                        encontrada = true;
                    }
                    i++;
                }
                
                if (!encontrada) {
                    System.out.println("La letra ingresada no es válida, intente nuevamente.");
                    continue;//Dijimos que estaba prohibido usar esto por el momento
                }
                
                if (asientos[numeroDeFila][numeroDeColumna - 1] != ASIENTO_OCUPADO) {
                    asientos[numeroDeFila][numeroDeColumna - 1] = ASIENTO_OCUPADO;
                    System.out.println("Asiento reservado con éxito.");
                    reservado = true;
                } else {
                    System.out.println("Este asiento está ocupado, intente otro.");
                }
            } catch(Exception e) {//Demasiado genérico. No trata ningún error de manera específica
                System.out.println("ERROR: Try Again");//Mensaje en español Please. Es poco descriptivo además este mensaje 
            }
        }
    }
    //public static void cancelarReserva(final int[][] ASIENTOS, Scanner scan,final char[] FILAS) {
    public static void cancelarReserva(int[][] asientos, Scanner scan, char[] filas) {
        try {
        	//El ingresar letra de asiento puede ser una función y reutilizarse ya que
        	//tiene casi el mismo código cuando reserva asiento
            System.out.print("Ingrese la letra del asiento a cancelar: ");
            String s = scan.nextLine();
            char letra = s.charAt(0);
            
            int numeroDeFila = 0;
            System.out.print("Ingrese el número del asiento a cancelar: ");
            int numeroDeColumna = Integer.parseInt(scan.nextLine());//Mal. debe ingresar un entero y validar
            
            //Si hubiese hecho una función de búsqueda podría reutilizar.
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
            //Si ingreso algo con error no me permite volver a intentar
        } catch(Exception e) {//Mal. Idem que en la función de reserva
            System.out.println("ERROR: Try Again"); //Mensaje en español Please. Es poco descriptivo además este mensaje
        }
    }
    
    //public static boolean buscarSiExiste(final int[][] ASIENTOS, Scanner scan, final char[] FILAS) {
    public static boolean buscarSiExiste(int[][] asientos, Scanner scan, char[] filas) {
        try {
        	//Idem todo lo que se marco en los métodos anteriores
        	//De todas maneras esto en el enunciado esta mal. 
        	//Si corrobora que se haya ingresado bien tanto la fila 
        	//como la columna, el asiento siempre existira, por lo tanto
        	//no tiene ningun sentido que exista una función como esta
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
