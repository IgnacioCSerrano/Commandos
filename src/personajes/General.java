package personajes;

import generico.Celda;
import generico.Obra;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * Clase que representa un militar nazi general.
 *
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     Aliado
 * @see     Nazi
 * @see     BoinaVerde
 * @see     Zapador
 * @see     Espia
 * @see     ficheros.Escritura
 * @see     generico.Obra
 * @see     generico.Celda
 */
public class General extends Nazi { // extiende (hereda) clase Nazi

    /**
     * Método constructor por defecto.
     */
    public General() {
        super();    // invoca constructor por defecto de padre clase Nazi
    }

    /**
     * Método constructor parametrizado.
     *
     * @param nombre    Nombre de general
     * @param turno     Número de turno de general
     * @param idCelda   Número de celda de general
     * @param marca     Carácter representativo de general en mapa
     */
    public General(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Nazi
    }

    /**
     * Método constructor parametrizado.
     *
     * @param nombre    Nombre de general
     * @param turno     Número de turno de general
     * @param idCelda   Número de celda de general
     * @param marca     Carácter representativo de general en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de general
     */
    public General(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Nazi
    }
    
    /**
     * Método que lleva a cabo la acción de requisar obras de arte de aliado
     * pasado por parámetro, si es posible.
     * 
     * @param aliado    Objeto de clase Aliado            
     * @param celda     Objeto de clase Celda
     * @param pwSim     Flujo de escritura de clase PrintWriter
     */
    public void requisarObra(Aliado aliado, Celda celda, PrintWriter pwSim) {
        Iterator<Obra> it;
        Obra obra;
        BoinaVerde bVerde;
        Espia espia;
        Zapador zapador;
        if (aliado instanceof BoinaVerde) {                 // si aliado es instancia de clase BoinaVerde
            bVerde = (BoinaVerde) aliado;                   // obtiene boina verde mediante casting
            if (!bVerde.getConjuntoObras().isEmpty()) {     // si boina verde lleva alguna obra de arte consigo
                it = bVerde.getConjuntoObras().iterator();  // iterador apunta a primer objeto de conjunto
                while (it.hasNext()) {                      // mientras haya objetos en el conjunto
                    obra = it.next();                       // obra referencia objeto actual de conjunto y mueve puntero a siguiente 
                    celda.insertarObra(obra);               // inserta obra de arte en celda actual
                    bVerde.escribirObraRequisada(obra, pwSim);
                }
                bVerde.soltarObras();                       // boina verde pierde todas las obras que llevaba consigo
            }
        } else if (aliado instanceof Espia) {               // si aliado es instancia de clase Espia
            espia = (Espia) aliado;                         // obtiene espía mediante casting
            obra = espia.getObra();                         // obtiene obra de arte de espía
            if (!espia.getDisfrazado() && obra != null) {   // si espía no está disfrazado y transporta obra de arte
                espia.setObra(null);                        // espía pierde obra que llevaba consigo
                celda.insertarObra(obra);                   // inserta obra de arte en celda actual
                espia.escribirObraRequisada(obra, pwSim);
            }
        } else {                                            // si aliado es instancia de clase Zapador (aliado restante)                          
            zapador = (Zapador) aliado;                     // obtiene zapador mediante casting
            obra = zapador.getObra();                       // obtiene obra de arte de zapador
            if (obra != null) {                             // si zapador transporta obra de arte
                zapador.setObra(null);                      // zapador pierde obra que llevaba consigo
                celda.insertarObra(obra);                   // inserta obra de arte en celda actual
                zapador.escribirObraRequisada(obra, pwSim);
            }
        }
    }
    
    /**
     * Método que lleva a cabo acción de inspeccionar nueva celda en busca de aliados, 
     * capturar los que encuentre y requisar sus obras, si es posible.
     */
    @Override   // sobreescrito de clase Nazi
    public void inspeccionarCelda(PrintWriter pwSim) {
        Aliado aliado;
        Celda celda = this.getCelda();                          // obtiene celda en la que se encuentra general
        boolean hayAliado = celda.buscarAliado();               // determina si hay algún aliado en celda actual
        if (hayAliado) {                                        // si hay algún aliado en celda
            for (Militar militar : celda.getListaMilitares()) { // recorre lista de militares de celda
                if (militar instanceof Aliado) {                // si militar es instancia de clase Aliado
                    aliado = (Aliado) militar;                  // obtiene aliado mediante casting
                    this.capturarAliado(aliado, pwSim);         // captura aliado, si es posible
                    this.requisarObra(aliado, celda, pwSim);    // requisa obra u obras de aliado, si es posible
                }
            }
        }
    }

}
