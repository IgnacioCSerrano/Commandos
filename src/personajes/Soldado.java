package personajes;

import generico.Celda;
import java.io.PrintWriter;

/**
 * Clase que representa un militar nazi soldado.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     Aliado
 * @see     Nazi
 * @see     ficheros.Escritura
 * @see     generico.Celda
 */
public class Soldado extends Nazi { // extiende (hereda) clase Nazi

    /**
     * Método constructor por defecto.
     */
    public Soldado() {
        super();    // invoca constructor por defecto de padre clase Nazi
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de soldado
     * @param turno     Número de turno de soldado
     * @param idCelda   Número de celda de soldado
     * @param marca     Carácter representativo de soldado en mapa
     */
    public Soldado(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Nazi
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de soldado
     * @param turno     Número de turno de soldado
     * @param idCelda   Número de celda de soldado
     * @param marca     Carácter representativo de soldado en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de soldado
     */
    public Soldado(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Nazi
    }
    
    /**
     * Método que lleva a cabo acción de inspeccionar nueva celda en busca de aliados 
     * y capturar los que encuentre, si es posible.
     */
    @Override   // sobreescrito de clase Nazi
    public void inspeccionarCelda(PrintWriter pwSim) {
        Aliado aliado;
        Celda celda = this.getCelda();                          // obtiene celda en la que se encuentra soldado
        boolean hayAliado = celda.buscarAliado();               // determina si hay algún aliado en celda actual
        if (hayAliado) {                                        // si hay algún aliado en celda
            for (Militar militar : celda.getListaMilitares()) { // recorre lista de militares de celda
                if (militar instanceof Aliado) {                // si militar es instancia de clase Aliado
                    aliado = (Aliado) militar;                  // obtiene aliado mediante casting
                    this.capturarAliado(aliado, pwSim);         // captura aliado, si es posible
                }
            }
        }
    }

}
