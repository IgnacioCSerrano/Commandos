package personajes;

import generico.Celda;
import generico.Const;
import generico.Obra;
import java.io.PrintWriter;

/**
 * Clase que representa un militar aliado espía.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     Aliado
 * @see     ficheros.Escritura
 * @see     generico.Obra
 * @see     generico.Celda
 * @see     generico.Const
 */
public class Espia extends Aliado { // extiende (hereda) clase Aliado
    
    /**
     * Estado de camuflaje de espía por llevar puesto disfraz.
     */
    private boolean disfrazado;
    
    /**
     * Obra de arte que espía lleva consigo.
     */
    private Obra obra;
    
    /**
     * Método constructor por defecto.
     */
    public Espia() {
        super();    // invoca constructor por defecto de padre clase Aliado
        this.disfrazado = false;
        this.obra = null;
    }
    
    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de espía
     * @param turno     Número de turno de espía
     * @param idCelda   Número de celda de espía
     * @param marca     Carácter representativo de espía en mapa
     */
    public Espia(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Aliado
        this.disfrazado = false;
        this.obra = null;
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de espía
     * @param turno     Número de turno de espía
     * @param idCelda   Número de celda de espía
     * @param marca     Carácter representativo de espía en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de espía
     */
    public Espia(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Aliado
        this.disfrazado = false;
        this.obra = null;
    }

    /**
     * Método que devuelve objeto obra de arte de espía.
     * 
     * @return  Objeto de clase Obra o null
     */
    public Obra getObra() {
        return this.obra;
    }
    
    /**
     * Método que devuelve estado de camuflaje de espía por llevar puesto disfraz.
     * 
     * @return  Booleano true si espía está disfrazado o false si espía no está disfrazado
     */
    public boolean getDisfrazado() {
        return disfrazado;
    }
    
    /**
     * Método que establece obra de arte que espía lleva consigo.
     * 
     * @param obra  Objeto de clase Obra o null
     */
    public void setObra(Obra obra) {
        this.obra = obra;   // atributo apunta a obra existente (no crea nuevo objeto)
    }
    
    /**
     * Método que establece estado de camuflaje de espía por llevar puesto disfraz.
     * 
     * @param disfrazado    Booleano true o false
     */
    public void setDisfrazado(boolean disfrazado) {
        this.disfrazado = disfrazado;
    }
    
    /**
     * Método que muestra obra de arte que espía lleva consigo.
     */
    public void mostrarObra() {
        if (this.obra != null) {
            System.out.println(Const.TAB + Const.RESC1 + this.getNombre() + Const.RESC2);
            this.obra.mostrarObra();
        } else {
            System.out.println(this.getNombre() + Const.RESC3);
        }
    }
    
    /**
     * Método que lleva a cabo acción de espía de buscar disfraz en celda 
     * en la que se encuentra y disfrazarse en caso de encontrarlo.
     * 
     * @param pwSim Flujo de escritura de clase PrintWriter para archivo de simulación
     */
    public void recogerDisfraz(PrintWriter pwSim) {
        String nombre;
        Celda celda = this.getCelda();  // obtiene celda en la que se encuentra espía
        if (celda.getDisfraz()) {       // si celda contiene disfraz
            nombre = this.getNombre();  // obtiene nombre de espía
            this.disfrazado = true;     // espía se disfraza
            celda.setDisfraz(false);    // disfraz desaparece de celda
            pwSim.println(nombre + Const.CLN + Const.DISG);
        }
    }
    
    /**
     * Método que escribe en el fichero de simulación el mensaje de que espía 
     * ha sido capaz de engañar a militar nazi por llevar puesto el disfraz
     * 
     * @param pwSim Flujo de escritura de clase PrintWriter para archivo de simulación
     */
    public void mostrarMensaje(PrintWriter pwSim) {
        pwSim.println(this.getNombre() + Const.CLN + Const.MISLED);
    }
    
    /**
     * Método que comprueba perímetro para espía, que tiene un tratamiento diferente
     * al resto de aliados por la condición del disfraz, que asegura siempre el camino
     * 
     * @return Booleano true si perímetro es seguro (libre de nazis) o false si perímetro no es seguro (hay al menos un nazi)
     */
    @Override   // sobreescrito de clase Aliado
    public boolean asegurarPerimetro() {
        boolean perimetroSeguro;
        perimetroSeguro = this.disfrazado ? true : super.asegurarPerimetro(); // perímetro es siempre seguro si espía está disfrazado, si no, tiene que comprobarlo
        return perimetroSeguro;
    }
    
    /**
     * Método que lleva a cabo acción de recoger obra de arte por parte de espía.
     */
    @Override   // sobreescrito de clase Aliado
    public void recogerObra(PrintWriter pwSim) {
        Obra obraCelda;
        Celda celda = this.getCelda();          // obtiene celda en la que se encuentra espía
        if (celda.hayObra()) {                  // si hay obra de arte en celda
            obraCelda = celda.getObra();        // obtiene obra del conjunto de obras de arte de celda
            if (this.obra == null) {            // si espía no lleva obra de arte consigo
                this.obra = obraCelda;          // atributo obra de espía apunta a objeto obra
                celda.borrarObra(obraCelda);    // elimina obra recogida del conjunto de obras de arte de celda
                pwSim.println(this.getNombre() + Const.CLN + Const.QM + obraCelda.getNombre() + Const.QM + Const.RESC1);
            } else {                            // si zapador ya lleva obra de arte consigo
                pwSim.println(this.getNombre() + Const.CLN + Const.RESC2 + Const.QM + obraCelda.getNombre() + Const.QM + Const.PRD);
            }
        }
    }
    
    /**
     * Método que lleva a cabo todas las acciones de espía en su turno, que tiene
     * un tratamiento diferente al resto de aliados por incluir la recogida del disfraz.
     */
    @Override   // sobreescrito de clase Aliado
    public void realizarAccion(PrintWriter pwSim, PrintWriter pwReg) {
        int celdaInicial, celdaActual;
        if (this.disfrazado) {                  // si espía está disfrazado
            super.realizarAccion(pwSim, pwReg); // realiza acciones comunes de aliado
        } else {                                // si espía no está disfrazado
            celdaInicial = this.getIdCelda();   // obtiene celda inicial de espía, antes de realizar acciones
            super.realizarAccion(pwSim, pwReg); // realiza acciones comunes de aliado
            celdaActual = this.getIdCelda();    // obtiene celda actual de espía, después de realizar acciones
            if (celdaActual != celdaInicial) {  // si espía se ha movido de celda al realizar acción
                this.recogerDisfraz(pwSim);     // espía busca disfraz en nueva celda y se disfraza si lo encuentra
            }
        }
    }

}
