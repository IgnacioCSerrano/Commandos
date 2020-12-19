package personajes;

import generico.Celda;
import generico.Const;
import generico.Obra;
import java.io.PrintWriter;
import java.util.LinkedHashSet;

/**
 * Clase que representa un militar aliado boina verde.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     Aliado
 * @see     ficheros.Escritura
 * @see     generico.Obra
 * @see     generico.Celda
 * @see     generico.Const
 */
public class BoinaVerde extends Aliado {    // extiende (hereda) clase Aliado
    
    /**
     * Conjunto de obras de arte que boina verde lleva consigo.
     */
    private final LinkedHashSet<Obra> conjObras;    // conjunto ordenado para que fichero registro muestre obras de arte en el mismo orden en el que fueron recogidas

    /**
     * Método constructor por defecto.
     */
    public BoinaVerde() {
        super();    // invoca constructor por defecto de padre clase Aliado
        this.conjObras = new LinkedHashSet<>();
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de boina verde
     * @param turno     Número de turno de boina verde
     * @param idCelda   Número de celda de boina verde
     * @param marca     Carácter representativo de boina verde en mapa
     */
    public BoinaVerde(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Aliado
        this.conjObras = new LinkedHashSet<>();
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de boina verde
     * @param turno     Número de turno de boina verde
     * @param idCelda   Número de celda de boina verde
     * @param marca     Carácter representativo de boina verde en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de boina verde
     */
    public BoinaVerde(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Aliado
        this.conjObras = new LinkedHashSet<>();
    }
    
    /**
     * Método que devuelve conjunto de obras de boina verde.
     * 
     * @return  Conjunto LinkedHashSet de objetos obras de clase Obra
     */
    public LinkedHashSet<Obra> getConjuntoObras() {
        return this.conjObras;
    }
    
    /**
     * Método que establece conjunto de obras de boina verde.
     * 
     * @param conjObras Conjunto de objetos obras de clase Obra
     */
    public void setConjuntoObras(LinkedHashSet<Obra> conjObras) {
        this.conjObras.clear(); // limpia conjunto para asegurar de que está vacío antes de cargar obras
        conjObras.forEach(obra -> {
            this.conjObras.add(obra);
        });
    }
    
    /**
     * Método que vacía conjunto de obras de boina verde.
     */
    public void soltarObras() {
        this.conjObras.clear();
    }
    
    /**
     * Método que añade obra al final del conjunto de obras de boina verde.
     * 
     * @param obra  Objeto de clase Obra
     */
    public void insertarObra(Obra obra) {
        this.conjObras.add(obra);
    }

    /**
     * Método que lleva a cabo acción de recoger obra de arte por parte de boina verde.
     */
    @Override   // sobreescrito de clase Aliado
    public void recogerObra(PrintWriter pwSim) {
        Obra obra;
        Celda celda = this.getCelda();  // obtiene celda en la que se encuentra boina verde
        while (celda.hayObra()) {       // mientras haya obras de arte en la celda
            obra = celda.getObra();     // obtiene obra del conjunto de obras de arte de celda
            this.insertarObra(obra);    // añade obra al final del conjunto de obras de arte de boina verde
            celda.borrarObra(obra);     // elimina obra recogida del conjunto de obras de arte de celda
            pwSim.println(this.getNombre() + Const.CLN + Const.QM + obra.getNombre() + Const.QM + Const.RESC1);
        }
    }
    
}
