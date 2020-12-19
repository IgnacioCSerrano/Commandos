package personajes;

import generico.Const;
import java.io.PrintWriter;

/**
 * Clase que representa un militar nazi.
 *
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     Aliado
 * @see     Espia
 * @see     ficheros.Escritura
 * @see     generico.Const
 */
public abstract class Nazi extends Militar {    // extiende (hereda) clase Militar

    /**
     * Método constructor por defecto.
     */
    public Nazi() {
        super();    // invoca constructor por defecto de padre clase Militar
    }

    /**
     * Método constructor parametrizado.
     *
     * @param nombre    Nombre de nazi
     * @param turno     Número de turno de nazi
     * @param idCelda   Número de celda de nazi
     * @param marca     Carácter representativo de nazi en mapa
     */
    public Nazi(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Militar
    }

    /**
     * Método constructor parametrizado.
     *
     * @param nombre    Nombre de nazi
     * @param turno     Número de turno de nazi
     * @param idCelda   Número de celda de nazi
     * @param marca     Carácter representativo de nazi en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de nazi
     */
    public Nazi(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Militar
    }

    /**
     * Método para capturar al aliado pasado por parámetro, si es posible.
     * 
     * @param aliado    Objeto de clase Aliado            
     * @param pwSim     Flujo de escritura de clase PrintWriter
     */
    public void capturarAliado(Aliado aliado, PrintWriter pwSim) {
        Espia espia;
        if (aliado instanceof Espia) {          // si aliado es instancia de clase Espia
            espia = (Espia) aliado;             // obtiene espía mediante casting
            if (espia.getDisfrazado()) {        // si espía está disfrazado
                espia.mostrarMensaje(pwSim);
            } else if (!espia.getCapturado()) { // si espía no está capturado ya
                espia.setCapturado(true);       // captura espía
            }
        } else if (!aliado.getCapturado()) {    // si aliado es instancia de clase distinta de Espia y no está capturado ya
            aliado.setCapturado(true);          // captura aliado
        }
    };
    
    /**
     * Método que define movimiento de nazi, que tiene un tratamiento diferente 
     * al de aliado por ser seguir una ruta circular.
     */
    @Override   // sobreescrito de clase Militar
    public void mover(PrintWriter pwSim) {
        char ruta = this.cargarPrimerMovimiento();  // obtiene primer movimiento de ruta de nazi
        super.mover(pwSim);                         // realiza movimiento común de militar
        this.insertarMovimiento(ruta);              // inserta ruta actual al final de lista de rutas de nazi
    }
    
    /**
     * Método para llevar a cabo las acciones del nazi en su turno.
     */
    @Override   // sobreescrito de clase Militar
    public void realizarAccion(PrintWriter pwSim, PrintWriter pwReg) {
        this.mover(pwSim);              // nazi se mueve
        this.inspeccionarCelda(pwSim);  // nazi busca, captura aliados en nueva celda y (solo general) requisa sus obras de arte, si puede
        this.siguienteTurno();          // actualiza turno de nazi
    }

    /**
     * Método abstracto para inspeccionar la celda en busca de aliados.
     * 
     * @param pwSim Flujo de escritura de clase PrintWriter para archivo de simulación
     */
    public abstract void inspeccionarCelda(PrintWriter pwSim);

}
