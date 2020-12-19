package personajes;

import generico.Celda;
import generico.Const;
import generico.Obra;
import java.io.PrintWriter;

/**
 * Clase que representa un militar aliado zapador.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     Aliado
 * @see     ficheros.Escritura
 * @see     generico.Obra
 * @see     generico.Celda
 * @see     generico.Const
 */
public class Zapador extends Aliado {   // extiende (hereda) clase Aliado
        
    /**
     * Obra de arte que zapador lleva consigo.
     */
    private Obra obra;

    /**
     * Método constructor por defecto.
     */
    public Zapador() {
        super();    // invoca constructor por defecto de padre clase Aliado
        this.obra = null;
    }
    
    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de zapador
     * @param turno     Número de turno de zapador
     * @param idCelda   Número de celda de zapador
     * @param marca     Carácter representativo de zapador en mapa
     */
    public Zapador(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Aliado
        this.obra = null;
    }
    
    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de zapador
     * @param turno     Número de turno de zapador
     * @param idCelda   Número de celda de zapador
     * @param marca     Carácter representativo de zapador en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de zapador
     */
    public Zapador(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Aliado
        this.obra = null;
    }
    
    /**
     * Método que devuelve objeto obra de arte de zapador.
     * 
     * @return  Objeto de clase Obra o null
     */
    public Obra getObra() {
        return this.obra;
    }
    
    /**
     * Método que establece obra de arte que zapador lleva consigo.
     * 
     * @param obra  Objeto de clase Obra o null
     */
    public void setObra(Obra obra) {
        this.obra = obra;   // atributo apunta a obra existente (no crea nuevo objeto)
    }
    
    /**
     * Método que muestra obra de arte que zapador lleva consigo.
     */
    public void mostrarObra() {
        if (this.obra != null) {
            System.out.println(Const.TAB + Const.RESC3 + this.getNombre() + Const.RESC4);
            this.obra.mostrarObra();
        } else {
            System.out.println(this.getNombre() + Const.RESC6);
        }
    }

    /**
     * Método que lleva a cabo acción de recoger obra de arte por parte de zapador.
     */
    @Override   // sobreescrito de clase Aliado
    public void recogerObra(PrintWriter pwSim) {
        Obra obraCelda;
        Celda celda = this.getCelda();          // obtiene celda en la que se encuentra zapador
        if (celda.hayObra()) {                  // si hay obra de arte en celda
            obraCelda = celda.getObra();        // obtiene obra del conjunto de obras de arte de celda
            if (this.obra == null) {            // si zapador no lleva obra de arte consigo
                this.obra = obraCelda;          // atributo obra de zapador apunta a objeto obra
                celda.borrarObra(obraCelda);    // elimina obra recogida del conjunto de obras de arte de celda
                pwSim.println(this.getNombre() + Const.CLN + Const.QM + obraCelda.getNombre() + Const.QM + Const.RESC1);
            } else {                            // si zapador ya lleva obra de arte consigo
                pwSim.println(this.getNombre() + Const.CLN + Const.RESC2 + Const.QM + obraCelda.getNombre() + Const.QM + Const.PRD);
            }
        }
    }

}
