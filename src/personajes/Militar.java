package personajes;

import generico.Celda;
import generico.Const;
import generico.Mapa;
import generico.Utilidad;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase que representa un militar.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     ficheros.Escritura
 * @see     generico.Mapa
 * @see     generico.Celda
 * @see     generico.Utilidad
 * @see     generico.Const
 */
public abstract class Militar {

    /**
     * Nombre de militar.
     */
    private String nombre;
    
    /**
     * Número de turno de militar.
     */
    private int turno;
    
    /**
     * Número de celda de militar.
     */
    private int idCelda;
    
    /**
     * Carácter representativo de militar en mapa.
     */
    private char marca;
    
    /**
     * Lista con direcciones cardinales de ruta de militar.
     */
    private final ArrayList<Character> listaRuta;
    
    /**
     * Método constructor por defecto.
     */
    public Militar() {
        this.nombre = Const.EMP_STR;
        this.turno = 0;
        this.idCelda = 0;
        this.marca = Const.EMP_CHA;
        this.listaRuta = new ArrayList<>();
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de militar
     * @param turno     Número de turno de militar
     * @param idCelda   Número de celda de militar
     * @param marca     Carácter representativo de militar en mapa
     */
    public Militar(String nombre, int turno, int idCelda, char marca) {
        this.nombre = nombre;
        this.turno = turno;
        this.idCelda = idCelda;
        this.marca = marca;
        this.listaRuta = new ArrayList<>();
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param nombre    Nombre de militar
     * @param turno     Número de turno de militar
     * @param idCelda   Número de celda de militar
     * @param marca     Carácter representativo de militar en mapa
     * @param vRuta     Vector con direcciones cardinales de ruta de militar
     */
    public Militar(String nombre, int turno, int idCelda, char marca, char[] vRuta) {
        this.nombre = nombre;
        this.turno = turno;
        this.idCelda = idCelda;
        this.marca = marca;
        this.listaRuta = new ArrayList<>();
        for (char ruta : vRuta) {
            this.listaRuta.add(ruta);
        }
    }

    /**
     * Método que devuelve nombre de militar.
     * 
     * @return  Nombre String de militar
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Método que devuelve número de turno de militar.
     * 
     * @return  Número int de turno de militar
     */
    public int getTurno() {
        return this.turno;
    }
    
    /**
     * Método que devuelve número de celda de militar.
     * 
     * @return  Número int de celda de militar
     */
    public int getIdCelda() {
        return this.idCelda;
    }
    
    /**
     * Método que devuelve carácter representativo de militar en mapa.
     * 
     * @return  Carácter char representativo de militar en mapa
     */
    public char getMarca() {
        return this.marca;
    }
    
    /**
     * Método que devuelve lista con direcciones cardinales de rutas de militar.
     * 
     * @return  Lista ArrayList de caracteres char que representan direcciones cardinales de ruta de militar
     */
    public ArrayList<Character> getRuta() {
        return this.listaRuta;
    }

    /**
     * Método que establece nombre de militar.
     * 
     * @param nombre    Nombre String de militar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que establece número de turno de militar.
     * 
     * @param turno Número int de turno de militar
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    /**
     * Método que establece número de celda de militar.
     * 
     * @param idCelda   Número de celda de militar
     */
    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    /**
     * Método que establece carácter representativo de militar en mapa.
     * 
     * @param marca Carácter representativo de militar en mapa
     */
    public void setMarca(char marca) {
        this.marca = marca;
    }
    
    /**
     * Método que establece lista con direcciones cardinales de ruta de militar a partir de vector de caracteres.
     * 
     * @param vRuta Vector de caracteres con direcciones cardinales
     */
    public void cargarMovimientos(char[] vRuta) {
        this.listaRuta.clear(); // limpia lista de rutas para asegurar de que está vacía antes de cargar movimientos
        for (char ruta : vRuta) {
            this.listaRuta.add(ruta);
        }
    }

    /**
     * Método para mostrar lista con direcciones cardinales de rutas de militar.
     */
    public void mostrarRuta() {
        this.listaRuta.forEach(ruta -> {
            System.out.print(Const.SB1 + ruta + Const.SB2);
        });
        System.out.print(Const.LB);
    }

    /**
     * Método que devuelve primera dirección de lista de rutas de militar.
     * 
     * @return  Dirección cardinal char correspondiente a la primera posición de la lista de rutas de militar
     */
    public char cargarPrimerMovimiento() {
        return this.listaRuta.get(0);
    }

    /**
     * Método que borra primera dirección de lista de rutas de militar.
     */
    public void borrarPrimerMovimiento() {
        this.listaRuta.remove(0);
    }

    /**
     * Método que añade dirección al final de lista de rutas de militar.
     * 
     * @param ruta  Dirección cardinal char 
     */
    public void insertarMovimiento(char ruta) {
        this.listaRuta.add(ruta);
    }

    /**
     * Método que devuelve estado de ausencia de contenido de lista de rutas de militar.
     * 
     * @return  Booleano true si lista está vacía o false si lista no está vacía
     */
    public boolean listaRutaVacia() {
        return this.listaRuta.isEmpty();
    }
    
     /**
     * Método que devuelve celda en la que se encuentra el militar.
     *
     * @return  Objeto celda que ocupa el militar
     */
    public Celda getCelda() {
        Mapa mapa = Mapa.getInstancia();                        // carga objeto mapa (patrón Singleton)
        int fila = Utilidad.calcularFila(this.idCelda);         // calcula fila de celda a partir de su id
        int columna = Utilidad.calcularColumna(this.idCelda);   // calcula columna de celda a partir de su id
        Celda celda = mapa.getCelda(fila, columna);             // carga objeto celda a partir de su fila y columna
        return celda;
    }

    /**
     * Método que devuelve número de celda de mapa donde debe dirigirse militar 
     * según primer movimiento de su ruta.
     * 
     * @return  Número int id de celda destino de militar
     */
    public int calcularSiguienteIdCelda() {
        int idCeldaDestino;
        Mapa mapa = Mapa.getInstancia();
        char ruta = this.cargarPrimerMovimiento();                  // obtiene primer movimiento de ruta de militar
        switch (Character.toUpperCase(ruta)) {                      // evalúa carácter pasado a mayúscula para cubrir casos de rutas mayúscula y minúscula
            case Const.NORTH:
                idCeldaDestino = this.idCelda - mapa.getColumnas();
                break;
            case Const.EAST:
                idCeldaDestino = this.idCelda + 1;
                break;
            case Const.SOUTH:
                idCeldaDestino = this.idCelda + mapa.getColumnas();
                break;
            case Const.WEST:
                idCeldaDestino = this.idCelda - 1;
                break;
            default:
                idCeldaDestino = this.idCelda;                      // militar se mantiene en celda origen si carácter ruta no corresponde con punto cardinal
        }
        return idCeldaDestino;
    }
    
    /**
     * Método que define movimiento de militar desde su celda de mapa actual 
     * a celda de mapa que corresponde según primer movimiento de su ruta.
     * 
     * @param pwSim    Flujo de escritura de clase PrintWriter para archivo de simulación
     */
    public void mover(PrintWriter pwSim) {
        int idCeldaOrigen, idCeldaDestino;
        char ruta;
        Mapa mapa;
        if (!this.listaRutaVacia()) {                               // si lista de rutas no está vacío
            mapa = Mapa.getInstancia();                             // carga objeto mapa (patrón Singleton)
            idCeldaOrigen = this.getIdCelda();                      // obtiene nº de celda origen (actual)
            idCeldaDestino = this.calcularSiguienteIdCelda();       // calcula nº de celda destino de militar según su ruta
            if (mapa.hayCamino(idCeldaOrigen, idCeldaDestino)) {    // si es posible moverse desde celda origen a celda destino
                this.setIdCelda(idCeldaDestino);                    // actualiza nº de celda de militar (origen) con nº de celda destino
                mapa.borrarMilitar(this.nombre, idCeldaOrigen);     // borra militar de lista de militares de celda anterior (celda origen)
                mapa.insertarMilitar(this);                         // inserta militar en lista de militares de celda actual (celda destino)
            } else {                                                // si no es posible moverse desde celda origen a celda destino
                ruta = this.cargarPrimerMovimiento();               // obtiene primer movimiento de ruta de militar
                pwSim.println(this.nombre + Const.CLN + Const.IMP_MOV1 + Const.RB1 + ruta + Const.RB2);
            }
            this.borrarPrimerMovimiento();                          // borra primer movimiento de lista de rutas de militar
        } else {                                                    // si lista de rutas está vacío
            pwSim.println(this.nombre + Const.CLN + Const.IMP_MOV2);
        }
    }
    
    /**
     * Método que comprueba si militar puede llevar a cabo su acción 
     * en el turno de mapa actual.
     * 
     * @return  Booleano true si turno de militar coincide con turno de mapa o false si turno de militar no coincide con turno de mapa
     */
    public boolean comprobarTurno() {
        Mapa mapa = Mapa.getInstancia();                // carga objeto mapa (patrón Singleton)
        boolean pase = this.turno == mapa.getTurno();   // militar tiene pase si su turno coincide con turno de mapa
        return pase;
    }
    
    /**
     * Método que incrementa en una unidad el número de turno de militar.
     */
    public void siguienteTurno() {
        this.turno++;
    }
    
    /**
     * Método abstracto para realizar acción.
     * 
     * @param pwSim Flujo de escritura de clase PrintWriter para archivo de simulación
     * @param pwReg Flujo de escritura de clase PrintWriter para archivo de registro
     */
    public abstract void realizarAccion(PrintWriter pwSim, PrintWriter pwReg);
    
}
