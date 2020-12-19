package personajes;

import ficheros.Escritura;
import generico.Celda;
import generico.Const;
import generico.Mapa;
import generico.Obra;
import generico.Utilidad;
import java.io.PrintWriter;

/**
 * Clase que representa un militar aliado.
 *
 * @author  Ignacio Cuevas Serrano
 * @see     Militar
 * @see     ficheros.Escritura
 * @see     generico.Obra
 * @see     generico.Mapa
 * @see     generico.Celda
 * @see     generico.Utilidad
 * @see     generico.Const
 */
public abstract class Aliado extends Militar {  // extiende (hereda) clase Militar

    /**
     * Situación de cautividad de aliado.
     */
    private boolean capturado;

    /**
     * Situación de finalización de misión de aliado.
     */
    private boolean enPuntoEncuentro;

    /**
     * Método constructor por defecto.
     */
    public Aliado() {
        super();    // invoca constructor por defecto de padre clase Militar
        this.capturado = false;
        this.enPuntoEncuentro = false;
    }

    /**
     * Método constructor parametrizado.
     *
     * @param nombre    Nombre de aliado
     * @param turno     Número de turno de aliado
     * @param idCelda   Número de celda de aliado
     * @param marca     Carácter representativo de aliado en mapa
     */
    public Aliado(String nombre, int turno, int idCelda, char marca) {
        super(nombre, turno, idCelda, marca);   // invoca constructor parametrizado de padre clase Militar
        this.capturado = false;
        this.enPuntoEncuentro = false;
    }

    /**
     * Método constructor parametrizado.
     *
     * @param nombre    Nombre de aliado
     * @param turno     Número de turno de aliado
     * @param idCelda   Número de celda de aliado
     * @param marca     Carácter representativo de aliado en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de aliado
     */
    public Aliado(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        super(nombre, turno, idCelda, marca, vRuta);    // invoca constructor parametrizado de padre clase Militar
        this.capturado = false;
        this.enPuntoEncuentro = false;
    }

    /**
     * Método que devuelve situación de cautividad de aliado.
     *
     * @return  Booleano true si está capturano o false si no está capturado
     */
    public boolean getCapturado() {
        return this.capturado;
    }

    /**
     * Método que devuelve situación de finalización de misión de aliado.
     *
     * @return  Booleano true si está en Punto de Encuentro o false si no está en Punto de Encuentro
     */
    public boolean getEnPuntoEncuentro() {
        return this.enPuntoEncuentro;
    }

    /**
     * Método que establece situación de cautividad de aliado.
     *
     * @param capturado Booleano true o false
     */
    public void setCapturado(boolean capturado) {
        this.capturado = capturado;
    }

    /**
     * Método que establece situación de finalización de misión de aliado.
     *
     * @param enPuntoEncuentro  Booleano true o false
     */
    public void setEnPuntoEncuentro(boolean enPuntoEncuentro) {
        this.enPuntoEncuentro = enPuntoEncuentro;
    }
    
    /**
     * Método que escribe en el fichero de simulación la obra requisada al aliado
     * por el general nazi.
     * 
     * @param obra  Objeto de clase obra
     * @param pwSim Flujo de escritura de clase PrintWriter para archivo de simulación
     */
    public void escribirObraRequisada(Obra obra, PrintWriter pwSim) {
        String nomAliado = this.getNombre();    // obtiene nombre de aliado
        String nomObra = obra.getNombre();      // obtiene nombre de obra
        pwSim.println(nomAliado + Const.CLN + Const.QM + nomObra + Const.QM + Const.REQU);
    }
    
    /**
     * Método que determina si aliado está en Punto de Encuentro.
     *
     * @param pwReg Flujo de escritura de clase PrintWriter para archivo de registro
     * @return      Booleano true si aliado está en Punto de Encuentro o false si aliado no está en Punto de Encuentro
     */
    public boolean estaEnPuntoEncuentro(PrintWriter pwReg) {
        Mapa mapa;
        Celda celda;
        if (!this.enPuntoEncuentro) {                   // si aliado no está ya en Punto de Encuentro
            celda = this.getCelda();                    // obtiene celda en la que se encuentra aliado
            if (celda.getPuntoDeEncuentro()) {          // si aliado acaba de llegar a Punto de Encuentro
                this.enPuntoEncuentro = true;           // modifica atributo
                mapa = Mapa.getInstancia();             // carga objeto mapa (patrón Singleton)
                mapa.aliadoEnPunto();                   // suma una unidad al número de aliados en Punto de Encuntro
                Escritura.registrarObra(this, pwReg);   // escribe obra u obras de arte rescatadas por aliado en archivo de registro
            }
        }
        return this.enPuntoEncuentro;
    }

    /**
     * Método que determina si está libre de nazis la celda de mapa donde debe
     * dirigirse aliado según primer movimiento de su ruta.
     *
     * @return  Booleano true si perímetro es seguro (libre de nazis) o false si perímetro no es seguro (hay al menos un nazi)
     */
    public boolean asegurarPerimetro() {
        Mapa mapa;
        Celda celdaDestino;
        int idCeldaOrigen, idCeldaDestino, filaDestino, columnaDestino;
        boolean hayNazi, perimetroSeguro;
        if (!this.listaRutaVacia()) {                                       // si array de rutas no está vacío
            mapa = Mapa.getInstancia();                                     // carga objeto mapa (patrón Singleton)
            idCeldaOrigen = this.getIdCelda();                              // obtiene número de celda origen (actual)
            idCeldaDestino = this.calcularSiguienteIdCelda();               // calcula número de celda destino de aliado según su ruta
            if (mapa.hayCamino(idCeldaOrigen, idCeldaDestino)) {            // si es posible moverse desde celda origen a celda destino
                filaDestino = Utilidad.calcularFila(idCeldaDestino);        // calcula fila de celda a partir de su número
                columnaDestino = Utilidad.calcularColumna(idCeldaDestino);  // calcula columna de celda a partir de su número
                celdaDestino = mapa.getCelda(filaDestino, columnaDestino);  // carga objeto celda a partir de su fila y columna
                hayNazi = celdaDestino.buscarNazi();                        // determina si hay algún nazi en la celda destino de aliado
                perimetroSeguro = !hayNazi;                                 // establece perímetro seguro si no hay nazi en destino y viceversa
            } else {                                                        // si no es posible moverse desde celda origen a celda destino
                perimetroSeguro = true;                                     // permímetro es siempre seguro si no hay camino
            }
        } else {                                                            // si array de rutas está vacío
            perimetroSeguro = true;                                         // permímetro es siempre seguro si no hay movimiento posible
        }
        return perimetroSeguro;
    }
    
    /**
     * Método que lleva a cabo todas las acciones del aliado en su turno.
     */
    @Override   // sobreescrito de clase Militar
    public void realizarAccion(PrintWriter pwSim, PrintWriter pwReg) {
        int celdaInicial, celdaActual;
        String nombre = this.getNombre();               // obtiene nombre de aliado
        if (!this.capturado) {                          // si aliado no está capturado
            if (!this.estaEnPuntoEncuentro(pwReg)) {    // si aliado no está en Punto de Encuentro
                if (this.asegurarPerimetro()) {         // si perímetro es seguro
                    celdaInicial = this.getIdCelda();   // obtiene celda inicial de aliado antes de moverse
                    this.mover(pwSim);                  // aliado se mueve, si hay camino posible
                    celdaActual = this.getIdCelda();    // obtiene celda actual de aliado después de moverse
                    if (celdaActual != celdaInicial) {  // si aliado se ha movido de celda por haber camino posible
                        this.recogerObra(pwSim);        // aliado busca obra (u obras) de arte en nueva celda y la(s) recoge si encuentra
                    }
                } else {                                // si perímetro no es seguro
                    pwSim.println(nombre + Const.CLN + Const.PNA);
                }
            } else {                                    // si aliado está en Punto de Encuentro
                pwSim.println(nombre + Const.PE);
            }
        } else {                                        // si aliado está capturado
            pwSim.println(nombre + Const.CLN + Const.CAP);
            this.capturado = false;                     // aliado consigue liberarse
        }
        this.siguienteTurno();                          // actualiza turno de aliado
    };
    
    /**
     * Método abstracto para recoger obra.
     * 
     * @param pwSim Flujo de escritura de clase PrintWriter para archivo de simulación
     */
    public abstract void recogerObra(PrintWriter pwSim);

}
