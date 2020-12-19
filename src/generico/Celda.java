package generico;

import personajes.Aliado;
import personajes.Militar;
import personajes.Nazi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase que representa una celda del mapa de juego.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     Obra
 * @see     Const
 * @see     personajes.Militar
 * @see     personajes.Aliado
 * @see     personajes.Nazi
 */
public class Celda {
    
    /**
     * Número de celda.
     */
    private int idCelda;
    
    /**
     * Existencia de disfraz en celda.
     */
    private boolean disfraz;
    
    /**
     * Condición de Punto de Encuentro de celda.
     */
    private boolean puntoDeEncuentro;
    
    /**
     * Lista de militares que ocupan celda.
     */
    private final ArrayList<Militar> lMilitares;
    
    /**
     * Conjunto de obras de arte que ocupan celda.
     */
    private final HashSet<Obra> conjObras;

    /**
     * Método constructor por defecto.
     */
    public Celda() {
        this.idCelda = 0;
        this.disfraz = false;
        this.puntoDeEncuentro = false;
        this.lMilitares = new ArrayList<>();
        this.conjObras = new HashSet<>();
    }

    /**
     * Método constructor parametrizado.
     * 
     * @param idCelda   Número de celda
     */
    public Celda(int idCelda) {
        this.idCelda = idCelda;
        this.disfraz = false;
        this.puntoDeEncuentro = false;
        this.lMilitares = new ArrayList<>();
        this.conjObras = new HashSet<>();
    }
    
    /**
     * Método que devuelve número de celda.
     * 
     * @return  Número int de celda
     */
    public int getIdCelda() {
        return this.idCelda;
    }

    /**
     * Método que devuelve existencia de disfraz en celda.
     * 
     * @return  Booleano true si celda contiene disfraz o false si celda no contiene disfraz
     */
    public boolean getDisfraz() {
        return this.disfraz;
    }

    /**
     * Método que devuelve condición de Punto de Encuentro de celda.
     * 
     * @return  Booleano true si celda es Punto de Encuentro o false si celda no es Punto de Encuentro
     */
    public boolean getPuntoDeEncuentro() {
        return this.puntoDeEncuentro;
    }
    
    /**
     * Método que devuelve lista de militares que ocupan celda.
     * 
     * @return  Lista ArrayList de objetos militares de clase Militar que ocupan celda
     */
    public ArrayList<Militar> getListaMilitares() {
        return this.lMilitares;
    }
    
    /**
     * Método que devuelve conjunto de obras que ocupan celda.
     * 
     * @return  Conjunto HashSet de objetos obras de clase Obra que ocupan celda
     */
    public HashSet<Obra> getConjuntoObras() {
        return this.conjObras;
    }
    
    /**
     * Método que establece número de celda.
     * 
     * @param idCelda   Número de celda
     */
    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }
    
    /**
     * Método que establece existencia de disfraz en celda.
     * 
     * @param disfraz   Booleano true si hay disfraz en mapa o false si no hay disfraz en mapa
     */
    public void setDisfraz(boolean disfraz) {
        this.disfraz = disfraz;
    }

    /**
     * Método que establece condición de Punto de Encuentro de celda.
     * 
     * @param puntoDeEncuentro  Booleano true si celda es Punto de Encuentro o false si celda no es Punto de Encuentro
     */
    public void setPuntoDeEncuentro(boolean puntoDeEncuentro) {
        this.puntoDeEncuentro = puntoDeEncuentro;
    }
    
    /**
     * Método que establece lista de militares que ocupan celda.
     * 
     * @param lMilitares    Lista de objetos militares de clase Militar
     */
    public void setListaMilitares(ArrayList<Militar> lMilitares) {
        this.lMilitares.clear();    // limpia lista para asegurar de que está vacía antes de cargar militares
        lMilitares.forEach((militar) -> {
            this.lMilitares.add(militar);
        });
    }
    
    /**
     * Método que establece conjunto de obras que ocupan celda.
     * 
     * @param conjObras Conjunto de objetos obras de clase Obra
     */
    public void setConjuntoObras(HashSet<Obra> conjObras) {
        this.conjObras.clear(); // limpia conjunto para asegurar de que está vacío antes de cargar obras
        conjObras.forEach((obra) -> {
            this.conjObras.add(obra);
        });
    }
    
    /**
     * Método que devuelve tamaño de lista de militares de celda.
     * 
     * @return  Número int de militares que ocupan celda
     */
    public int getNumeroMilitares() {
        return this.lMilitares.size();
    }
    
    /**
     * Método que añade militar al final de lista de militares de celda.
     * 
     * @param militar   Objeto de clase Militar
     */
    public void insertarMilitar(Militar militar) {
        this.lMilitares.add(militar);
    }
    
    /**
     * Método que busca y devuelve militar de lista de militares de celda.
     * 
     * @param nombre    Nombre de militar buscado
     * @return          Objeto de clase Militar o null si búsqueda fracasa 
     */
    public Militar buscarMilitar(String nombre) {
        Militar aux, militar = null;
        boolean encontrado = false;
        Iterator <Militar> it = this.lMilitares.iterator(); // iterador apunta a primer objeto de lista
        while (it.hasNext() && !encontrado) {               // mientras haya objetos en lista y no se haya encontrado militar buscado
            aux = it.next();                                // aux referencia dirección de memoria de objeto actual de lista y mueve puntero a siguiente
            if (aux.getNombre().equals(nombre)) {           // si hay coincidencia de nombre de objeto aux
                militar = aux;                              // militar referencia dirección de memoria de objeto coincidente
                encontrado = true;                          // cambia valor de booleano para salir prematuramente del bucle
            }
        }
        return militar;                                     // retorna militar o null (valor inicial) si no se encontró militar durante iteración
        /*        
        for (Militar militar : this.lMilitares) {
            if (militar.getNombre().equals(nombre)) {
                return militar;
            }
        }
        return null;
        */
    }
    
    /**
     * Método que borra militar de lista de militares de celda.
     * 
     * @param nombre    Nombre de militar
     */
    public void borrarMilitar(String nombre) {
        Militar militar = this.buscarMilitar(nombre);   // busca y retorna objeto militar de lista por nombre (devuelve null si no hay coincidencia)
        this.lMilitares.remove(militar);                // elimina militar de lista (si recibe null tan solo elimina primera instancia nula, si existe)
    }
    
    /**
     * Método que retorna un militar de la celda que ocupa una determinada posición en lista.
     * 
     * @param posicion  Posición numérica de militar en la lista
     * @return          Objeto de clase Militar o null si número está fuera de los límites de la lista   
     */
    public Militar getMilitar(int posicion) {
        Militar militar;
        try {
            militar = this.lMilitares.get(posicion);    // obtiene militar que ocupa posición indicada
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Const.CON_IOB_EXC);
            militar = null;
        }
        return militar;
    }
    
    /**
     * Método que determina si hay algún militar aliado en celda.
     * 
     * @return  Booleano true si hay aliado en celda o false si no hay aliado en celda
     */
    public boolean buscarAliado() {
        int i = 0;
        boolean hayAliado = false;
        while (i < this.lMilitares.size() && !hayAliado) {
            if (this.lMilitares.get(i) instanceof Aliado) { // si objeto de lista en posición i es instancia de clase Aliado
                hayAliado = true;                           // cambia valor de booleano y sale prematuramente del bucle
            }
            i++;
        }
        return hayAliado;
        /* 
        for (Militar militar : this.lMilitares) {
            if (militar instanceof Aliado) {
                return true;
            }
        }
        return false;
        */
    }

    /**
     * Método que determina si hay algún militar nazi en celda.
     * 
     * @return  Booleano true si hay nazi en celda o false si no hay nazi en celda
     */
    public boolean buscarNazi() {
        int i = 0;
        boolean hayNazi = false;
        while (i < this.lMilitares.size() && !hayNazi) {
            if (this.lMilitares.get(i) instanceof Nazi) {   // si objeto de lista en posición i es instancia de clase Nazi
                hayNazi = true;                             // cambia valor de booleano y sale prematuramente del bucle
            }
            i++;
        }
        return hayNazi;
        /*
        for (Militar militar : this.lMilitares) {
            if (militar instanceof Nazi) {
                return true;
            }
        }
        return false;
        */
    }
    
    /**
     * Método que devuelve existencia de obra de arte en celda.
     * 
     * @return  Booleano true si hay alguna obra en celda o false si no hay ninguna obra en celda
     */
    public boolean hayObra() {
        return !this.conjObras.isEmpty();
    }
    
    /**
     * Método que añade obra al conjunto de obras de celda.
     * 
     * @param obra  Objeto de clase Obra
     */
    public void insertarObra(Obra obra) {
        this.conjObras.add(obra);
    }
    
    /**
     * Método que devuelve obra del conjunto.
     * 
     * @return  Objeto de clase Obra o null si conjunto está vacío
     */
    public Obra getObra() {
        Iterator<Obra> it = this.conjObras.iterator();  // iterador apunta a objeto de conjunto
        Obra obra = it.hasNext() ? it.next() : null;    // obra recoge objeto de conjunto que interador apunta o null si no hay ninguna obra en celda
        return obra;
    }
    
    /**
     * Método que borra obra de conjunto de obras de celda.
     * 
     * @param obra  Objeto de clase Obra
     */
    public void borrarObra(Obra obra) {
        this.conjObras.remove(obra);    // elimina obra de conjunto (si recibe null tan solo elimina primera instancia nula, si existe)
    }

    /**
     * Método que devuelve marca de militar individual o número militares que ocupan la celda.
     * 
     * @return  '0' si no hay militar, marca String de militar si hay un militar, número int de militares si hay más de un militar
     */
    @Override   // sobreescrito de java.lang.Object
    public String toString() {
        if (!this.lMilitares.isEmpty()) {
            if (this.lMilitares.size() == 1) {
                // valueOf convierte a String el parámetro recibido char
                return String.valueOf(this.lMilitares.get(0).getMarca());
            } else {
                // valueOf convierte a String el parámetro recibido int
                return String.valueOf(this.lMilitares.size());
            }        
        } else if (this.hayObra()) {
            return Const.INTERROGACION;
        } else {
            return Const.CERO;
        }
    }
}
