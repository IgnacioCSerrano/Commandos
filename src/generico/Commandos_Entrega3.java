package generico;

import ficheros.Escritura;
import ficheros.Lectura;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import personajes.Militar;

/**
 * Clase pricipal que lleva a cabo la simulación del programa.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     Mapa
 * @see     Utilidad
 * @see     Const
 * @see     ficheros.Lectura
 * @see     ficheros.Escritura
 * @see     personajes.Militar
 */
public class Commandos_Entrega3 {
    
    /**
     * Método que pide a usuario que introduzca un número correspondiente 
     * a la opción del menú deseada.
     * 
     * @return  Número int
     */
    public static int pedirOpcion() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        try {
            System.out.print(Const.OPC);
            opcion = entrada.nextInt();
        } catch (InputMismatchException e) {
            opcion = 0;
            entrada.next(); // limpia buffer de entrada
        }
        return opcion;
    }
    
    /**
     * Método que lleva a cabo la simulación del proyecto, creando el mapa
     * correspondiente a la opción escogida, iniciando las estructuras necesarias,
     * desarrollando los turnos de cada personaje y registrando los datos de la 
     * simulación y obras de arte rescatadas en sendos ficheros generados ad hoc.
     * 
     * @param opcion    Número int de opción escogida por usuario
     */
    public static void simulacion(int opcion) {
        FileWriter fwSim, fwReg;
        PrintWriter pwSim, pwReg;
        Iterator<Militar> it;
        Militar militar;
        String tamMapa, ficheroSim, ficheroReg;
        Mapa mapa = Mapa.getInstancia();                                        // carga objeto mapa (patrón Singleton)
        tamMapa = Const.VTAM[opcion - 1];                                       // obtiene nombre de fichero de inicio en función de posición de vector de tamaños
        mapa.crearMapa(tamMapa);                                                // crea mapa con datos correspondientes a tamaño elegido
        if (Lectura.cargarFichero(tamMapa)) {                                   // si carga de fichero de inicio ha sido correcta
            ficheroSim = Utilidad.calcularNombreFichero(Const.BASE_SIM);        // determina nombre de fichero de simulación
            ficheroReg = Utilidad.calcularNombreFichero(Const.BASE_REG);        // determina nombre de fichero de registro
            fwSim = Escritura.abrirFlujoFW(ficheroSim);                         // crea flujo FileWriter de fichero de simulación
            fwReg = Escritura.abrirFlujoFW(ficheroReg);                         // crea flujo FileWriter de fichero de registro
            pwSim = Escritura.abrirFlujoPW(fwSim);                              // crea flujo PrintWriter de fichero de simulación
            pwReg = Escritura.abrirFlujoPW(fwReg);                              // crea flujo PrintWriter de fichero de registro
            while (mapa.getNumAliadosEncuentro() < mapa.getNumAliadosMapa()) {  // mientras nº de aliados en Punto de Encuentro sea menor que nº total de aliados  
                pwSim.println(Const.LB + Const.TUR + mapa.getTurno());
                it = mapa.getColaPersonajes().iterator();                       // iterador apunta a primer objeto de cola
                while (it.hasNext()) {                                          // mientras haya objetos en cola
                    militar = it.next();                                        // militar referencia objeto actual de cola y mueve puntero a siguiente
                    if (militar.comprobarTurno()) {                             // si turno de militar coincide con turno actual de mapa
                        militar.realizarAccion(pwSim, pwReg);                   // militar lleva a cabo sus acciones en el turno y registra mensajes y datos, si procede
                    }
                }
                mapa.pintarMatriz(pwSim);                                       // escribe situación de mapa en turno actual
                mapa.siguienteTurno();                                          // actualiza turno de mapa
            }
            Escritura.cerrarFlujoPW(pwSim);                                     // cierra flujo PrintWriter de fichero de simulación
            Escritura.cerrarFlujoPW(pwReg);                                     // cierra flujo PrintWriter de fichero de registro
            Escritura.cerrarFlujoFW(fwSim);                                     // cierra flujo FileWriter de fichero de simulación
            Escritura.cerrarFlujoFW(fwReg);                                     // cierra flujo FileWriter de fichero de registro
//            Escritura.escribirRegistroLog();  //// método alternativo para escribir registro de obras de arte de aliados al final de simulación
            System.out.println(Const.LB + Const.SIM_SUC1 + mapa.getTipo() + Const.SIM_SUC2);
        } else {
            System.out.println(Const.LB + Const.SIM_ERR);
        }
    }

    public static void main(String[] args) {
        int opcion;
        System.out.println(Const.WELCOME + Const.LB);
        System.out.println(Const.PED_OPC);
        do {
            System.out.println(Const.LB + Const.MENU1);
            System.out.println(Const.MENU2);
            System.out.println(Const.MENU3);
            System.out.println(Const.MENU4 + Const.LB);
            opcion = pedirOpcion();
            switch (opcion) {
                case 1:
                case 2:
                case 3:
                    simulacion(opcion);
                    break;
                case 4:
                    System.out.println(Const.LB + Const.EXIT);
                    break;
                default:
                    System.out.println(Const.LB + Const.OPC_ERR);
                    opcion = 0;
            }
        } while (opcion != 4); 
    }
    
}
