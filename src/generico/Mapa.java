package generico;

import java.io.PrintWriter;
import java.util.LinkedList;
import personajes.BoinaVerde;
import personajes.Espia;
import personajes.General;
import personajes.Militar;
import personajes.Soldado;
import personajes.Zapador;

/**
 * Clase que representa el mapa de juego.
 *
 * @author  Ignacio Cuevas Serrano
 * @see     Celda
 * @see     Utilidad
 * @see     Const
 * @see     ficheros.Lectura
 * @see     ficheros.Escritura
 * @see     personajes.Militar
 * @see     personajes.BoinaVerde
 * @see     personajes.Zapador
 * @see     personajes.Espia
 * @see     personajes.General
 * @see     personajes.Soldado
 */
public class Mapa {

    /**
     * Instancia única de clase Mapa.
     */
    private static Mapa miMapa = null;
    
    /**
     * Número de filas de mapa.
     */
    private int filas;
    
    /**
     * Número de columnas de mapa.
     */
    private int columnas;
    
    /**
     * Tamaño de mapa en formato 'FxC'.
     */
    private String tipo;
    
    /**
     * Número de turno de mapa.
     */
    private int turno;
    
    /**
     * Número de aliados que hay en el Punto de Encuentro.
     */
    private int numAliadosEncuentro;

    /**
     * Número de aliados que hay en el mapa.
     */
    private int numAliadosMapa;
    
    /**
     * Cola de militares que hay en mapa.
     */
    private LinkedList<Militar> colaPersonajes;

    /**
     * Matriz de celdas de mapa.
     */
    private Celda[][] matriz;
    
    /**
     * Matriz de adyacencia.
     */
    private int[][] adyacencia;

    /**
     * Método constructor por defecto.
     */
    private Mapa() { // constructor privado por uso de patrón Singleton (obliga a usar método getInstancia(), asegurando de esta manera que existe una única instancia)
        this.filas = 0;
        this.columnas = 0;
        this.tipo = Const.EMP_STR;
        this.turno = 0;
        this.numAliadosEncuentro = 0;
        this.numAliadosMapa = 0;
        this.colaPersonajes = new LinkedList<>();
        this.matriz = null;
        this.adyacencia = null;
    }

    /**
     * Método para crear la instancia del patrón Singleton.
     *
     * @return  Objeto de clase Mapa
     */
    public static Mapa getInstancia() {
        if (miMapa == null) {
            miMapa = new Mapa();
        }
        return miMapa;
    }
    
    /**
     * Método que devuelve número de filas de mapa.
     *
     * @return  Número int de filas de mapa
     */
    public int getFilas() {
        return this.filas;
    }
    
    /**
     * Método que devuelve número de columnas de mapa.
     *
     * @return  Número int de columnas de mapa
     */
    public int getColumnas() {
        return this.columnas;
    }
    
    /**
     * Método que devuelve tamaño de mapa.
     *
     * @return  Tamaño String de mapa en formato 'FxC'
     */
    public String getTipo() {
        return this.tipo;
    }
    
    /**
     * Método que devuelve número de turno de mapa.
     *
     * @return  Número int de turno de mapa
     */
    public int getTurno() {
        return this.turno;
    }
    
    /**
     * Método que devuelve número de aliados situados en el Punto de Encuentro.
     *
     * @return  Número int de aliados en Punto de Encuentro
     */
    public int getNumAliadosEncuentro() {
        return this.numAliadosEncuentro;
    }
    
    /**
     * Método que devuelve número de aliados que hay en la simulación.
     *
     * @return  Número int de aliados de mapa
     */
    public int getNumAliadosMapa() {
        return this.numAliadosMapa;
    }
    
    /**
     * Método que devuelve la cola de personajes del mapa.
     * 
     * @return  Cola LinkedList de militares 
     */
    public LinkedList<Militar> getColaPersonajes() {
        return this.colaPersonajes;
    }
    
    /**
     * Método que establece número de filas de mapa.
     *
     * @param filas Número de filas de mapa
     */
    public void setFilas(int filas) {
        this.filas = filas;
    }
    
    /**
     * Método que establece número de columnas de mapa.
     *
     * @param columnas   Número de columnas de mapa
     */
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    
    /**
     * Método que establece tamaño de mapa.
     *
     * @param tipo  Tamaño de mapa en formato 'FxC'
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Método que establece número de turno de mapa.
     *
     * @param turno Número de turno de mapa
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    /**
     * Método que establece número de aliados situados en el Punto de Encuentro.
     *
     * @param numAliadosEncuentro   Número de aliados en Punto de Encuentro
     */
    public void setNumAliadosEncuentro(int numAliadosEncuentro) {
        this.numAliadosEncuentro = numAliadosEncuentro;
    }
    
    /**
     * Método que establece número de aliados que hay en la simulación.
     *
     * @param numAliadosMapa    Número de aliados de mapa
     */
    public void setNumAliadosMapa(int numAliadosMapa) {
        this.numAliadosMapa = numAliadosMapa;
    }
    
    /**
     * Método que establece la cola de personajes del mapa.
     * 
     * @param colaPersonajes    Cola de militares 
     */
    public void setColaPersonajes(LinkedList<Militar> colaPersonajes) {
        this.colaPersonajes.clear();    // limpia cola para asegurar de que está vacía antes de cargar personajes
        colaPersonajes.forEach(this.colaPersonajes::add);
    }
    
    /**
     * Método que establece los valores por defecto de los atributos de mapa.
     */
    private void reiniciarMapa() { 
        this.filas = 0;
        this.columnas = 0;
        this.turno = 0;
        this.numAliadosEncuentro = 0;
        this.numAliadosMapa = 0;
        this.colaPersonajes = new LinkedList<>();
        this.matriz = null;
        this.adyacencia = null;
    }

    /**
     * Método que establece los datos de mapa, inicializa las celdas 
     * y crea matriz de adyacencia del tamaño adecuado, según el parámetro recibido.
     * 
     * @param tamMapa   Nombre de fichero de inicio con datos de filas y columnas de mapa
     */
    public void crearMapa(String tamMapa) {
        int cont = 0;
        if (this.matriz != null) {  // reinicia los valores por defecto de mapa si no es la primera vez que se invoca el método
            this.reiniciarMapa();
        }
        if (tamMapa.equalsIgnoreCase(Const.TRESPORTRES)) {
            this.filas = Const.TRES;
            this.columnas = Const.TRES;
        } else if (tamMapa.equalsIgnoreCase(Const.CINCOPORCINCO)) {
            this.filas = Const.CINCO;
            this.columnas = Const.CINCO;
        } else {
            this.filas = Const.OCHO;
            this.columnas = Const.OCHO;
        }
        this.matriz = new Celda[this.filas][this.columnas];
        // Inicializa celdas
        for (Celda[] celda : this.matriz) {
            for (int j = 0; j < celda.length; j++) {
                celda[j] = new Celda(cont);
                cont++;
            }
        }
        // Carga Punto de Encuentro
        this.matriz[this.filas - 1][this.columnas - 1].setPuntoDeEncuentro(true);
        this.adyacencia = new int[this.filas * this.filas][this.columnas * this.columnas];
        // Rellena matriz de adyacencia con 'ceros'
        for (int[] vFila : this.adyacencia) {
            for (int i = 0; i < vFila.length; i++) {
                vFila[i] = 0;
            }
        }
        // Actualiza celdas que tienen unos, dependiendo del mapa
        if (tamMapa.equalsIgnoreCase(Const.TRESPORTRES)) {
            this.crearMapa3x3();
        } else if (tamMapa.equalsIgnoreCase(Const.CINCOPORCINCO)) {
            this.crearMapa5x5();
        } else {
            this.crearMapa8x8();
        }
    }
    
    /* 'Abre' caminos en la matriz de adyacencia del Mapa 3x3 */
    private void crearMapa3x3(){
        // Actualiza celdas que tienen 'unos'
        this.adyacencia[0][1] = 1;
        this.adyacencia[0][3] = 1;
        
        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;
        this.adyacencia[1][4] = 1;
        
        this.adyacencia[2][1] = 1;
        
        this.adyacencia[3][0] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][6] = 1;
        
        this.adyacencia[4][1] = 1;
        this.adyacencia[4][3] = 1;
        this.adyacencia[4][7] = 1;
        
        this.adyacencia[6][3] = 1;
        this.adyacencia[6][7] = 1;
        
        this.adyacencia[7][4] = 1;
        this.adyacencia[7][6] = 1;
        this.adyacencia[7][8] = 1;
        
        this.adyacencia[8][7] = 1;
    }
    
    /* 'Abre' caminos en la matriz de adyacencia del Mapa 5x5 */
    private void crearMapa5x5(){
        // Actualiza celdas que tienen 'unos'
        this.adyacencia[0][1] = 1;

        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;
        
        this.adyacencia[2][1] = 1;
        this.adyacencia[2][3] = 1;
        this.adyacencia[2][7] = 1;
        
        this.adyacencia[3][2] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][8] = 1;
        
        this.adyacencia[4][3] = 1;
        this.adyacencia[4][9] = 1;
        
        this.adyacencia[7][2] = 1;
        this.adyacencia[7][8] = 1;
        this.adyacencia[7][12] = 1;
        
        this.adyacencia[8][3] = 1;
        this.adyacencia[8][7] = 1;
        this.adyacencia[8][9] = 1;
        
        this.adyacencia[9][4] = 1;
        this.adyacencia[9][8] = 1;
        
        this.adyacencia[10][11] = 1;
        this.adyacencia[10][15] = 1;

        this.adyacencia[11][10] = 1;
        this.adyacencia[11][12] = 1;
        
        this.adyacencia[12][7] = 1;
        this.adyacencia[12][11] = 1;
        this.adyacencia[12][17] = 1;
        
        this.adyacencia[15][10] = 1;
        this.adyacencia[15][20] = 1;
        
        this.adyacencia[17][12] = 1;
        this.adyacencia[17][22] = 1;
        
        this.adyacencia[20][15] = 1;

        this.adyacencia[22][17] = 1;
        this.adyacencia[22][23] = 1;
        
        this.adyacencia[23][22] = 1;
        this.adyacencia[23][24] = 1;
        
        this.adyacencia[24][23] = 1;
    }

    /* 'Abre' caminos en la matriz de adyacencia del Mapa 8x8 */    
    private void crearMapa8x8(){
        // Actualiza celdas que tienen 'unos'
        this.adyacencia[0][1] = 1;
        this.adyacencia[0][8] = 1;
        
        this.adyacencia[1][0] = 1;
        this.adyacencia[1][2] = 1;
        
        this.adyacencia[2][1] = 1;
        this.adyacencia[2][3] = 1;
        this.adyacencia[2][10] = 1;
        
        this.adyacencia[3][2] = 1;
        this.adyacencia[3][4] = 1;
        this.adyacencia[3][11] = 1;
        
        this.adyacencia[4][3] = 1;
        this.adyacencia[4][5] = 1;
        this.adyacencia[4][12] = 1;
        
        this.adyacencia[5][4] = 1;
        this.adyacencia[5][6] = 1;
        
        this.adyacencia[6][5] = 1;
        this.adyacencia[6][7] = 1;
        
        this.adyacencia[7][6] = 1;
        
        this.adyacencia[8][0] = 1;
        this.adyacencia[8][16] = 1;
        
        this.adyacencia[10][2] = 1;
        this.adyacencia[10][11] = 1;
        this.adyacencia[10][18] = 1;
        
        this.adyacencia[11][3] = 1;
        this.adyacencia[11][10] = 1;
        this.adyacencia[11][12] = 1;
        this.adyacencia[11][19] = 1;
        
        this.adyacencia[12][4] = 1;
        this.adyacencia[12][11] = 1;
        this.adyacencia[12][20] = 1;
        
        this.adyacencia[16][8] = 1;
        this.adyacencia[16][24] = 1;
        
        this.adyacencia[18][10] = 1;
        this.adyacencia[18][19] = 1;
        this.adyacencia[18][26] = 1;
        
        this.adyacencia[19][11] = 1;
        this.adyacencia[19][18] = 1;
        this.adyacencia[19][20] = 1;
        this.adyacencia[19][27] = 1;
        
        this.adyacencia[20][12] = 1;
        this.adyacencia[20][19] = 1;
        this.adyacencia[20][21] = 1;
        this.adyacencia[20][28] = 1;
        
        this.adyacencia[21][20] = 1;
        this.adyacencia[21][22] = 1;
        this.adyacencia[21][29] = 1;
        
        this.adyacencia[22][21] = 1;
        this.adyacencia[22][23] = 1;
        this.adyacencia[22][30] = 1;
        
        this.adyacencia[23][22] = 1;
        this.adyacencia[23][31] = 1;
        
        this.adyacencia[24][16] = 1;
        this.adyacencia[24][25] = 1;
        this.adyacencia[24][32] = 1;
        
        this.adyacencia[25][24] = 1;
        this.adyacencia[25][26] = 1;
        this.adyacencia[25][33] = 1;
        
        this.adyacencia[26][18] = 1;
        this.adyacencia[26][25] = 1;
        this.adyacencia[26][27] = 1;
        
        this.adyacencia[27][19] = 1;
        this.adyacencia[27][26] = 1;
        this.adyacencia[27][28] = 1;
        this.adyacencia[27][35] = 1;
        
        this.adyacencia[28][20] = 1;
        this.adyacencia[28][27] = 1;
        this.adyacencia[28][29] = 1;
        
        this.adyacencia[29][21] = 1;
        this.adyacencia[29][28] = 1;
        this.adyacencia[29][30] = 1;
        
        this.adyacencia[30][22] = 1;
        this.adyacencia[30][29] = 1;
        this.adyacencia[30][31] = 1;
        
        this.adyacencia[31][23] = 1;
        this.adyacencia[31][30] = 1;
        this.adyacencia[31][39] = 1;
        
        this.adyacencia[32][24] = 1;
        this.adyacencia[32][33] = 1;
        this.adyacencia[32][40] = 1;
        
        this.adyacencia[33][25] = 1;
        this.adyacencia[33][32] = 1;
        
        this.adyacencia[35][27] = 1;
        this.adyacencia[35][43] = 1;
        
        this.adyacencia[39][31] = 1;
        this.adyacencia[39][47] = 1;
        
        this.adyacencia[40][32] = 1;
        this.adyacencia[40][48] = 1;
        
        this.adyacencia[43][35] = 1;
        this.adyacencia[43][51] = 1;
        
        this.adyacencia[46][47] = 1;
        this.adyacencia[46][54] = 1;
        
        this.adyacencia[47][39] = 1;
        this.adyacencia[47][46] = 1;
        this.adyacencia[47][55] = 1;
        
        this.adyacencia[48][40] = 1;
        this.adyacencia[48][49] = 1;
        this.adyacencia[48][56] = 1;
        
        this.adyacencia[49][48] = 1;
        this.adyacencia[49][50] = 1;
        this.adyacencia[49][57] = 1;
        
        this.adyacencia[50][49] = 1;
        this.adyacencia[50][51] = 1;
        
        this.adyacencia[51][43] = 1;
        this.adyacencia[51][50] = 1;
        this.adyacencia[51][52] = 1;
        this.adyacencia[51][59] = 1;
        
        this.adyacencia[52][51] = 1;
        this.adyacencia[52][53] = 1;
        this.adyacencia[52][60] = 1;
        
        this.adyacencia[53][52] = 1;
        this.adyacencia[53][54] = 1;
        this.adyacencia[53][61] = 1;
        
        this.adyacencia[54][46] = 1;
        this.adyacencia[54][53] = 1;
        this.adyacencia[54][55] = 1;
        this.adyacencia[54][62] = 1;
        
        this.adyacencia[55][47] = 1;
        this.adyacencia[55][54] = 1;
        this.adyacencia[55][63] = 1;
        
        this.adyacencia[56][48] = 1;
        this.adyacencia[56][57] = 1;
        
        this.adyacencia[57][49] = 1;
        this.adyacencia[57][56] = 1;
        
        this.adyacencia[59][51] = 1;
        this.adyacencia[59][60] = 1;
        
        this.adyacencia[60][52] = 1;
        this.adyacencia[60][59] = 1;
        this.adyacencia[60][61] = 1;
        
        this.adyacencia[61][53] = 1;
        this.adyacencia[61][60] = 1;
        this.adyacencia[61][62] = 1;
        
        this.adyacencia[62][54] = 1;
        this.adyacencia[62][61] = 1;
        this.adyacencia[62][63] = 1;
        
        this.adyacencia[63][55] = 1;
        this.adyacencia[63][62] = 1;
    }
    
    /**
     * Método que carga el difraz de espía en celda del mapa especificada.
     *
     * @param idCelda                       Número de celda
     * @throws IndexOutOfBoundsException    Excepción índice fuera de límite
     */
    public void cargarDisfrazEnMapa(int idCelda) throws IndexOutOfBoundsException {
        int fila = Utilidad.calcularFila(idCelda);          // calcula fila de celda a partir de su id
        int columna = Utilidad.calcularColumna(idCelda);    // calcula columna de celda a partir de su id
        this.matriz[fila][columna].setDisfraz(true);        // carga disfraz en celda de mapa correspondiente a fila y columna calculadas
    }
    
    /**
     * Método que establece valor de atributo tipo y carga difraz de espía 
     * en celda de mapa correspondiente a datos de línea de fichero de inicio.
     *
     * @param vDatos                        Vector de cadenas que contiene datos de mapa
     * @throws IndexOutOfBoundsException    Excepción índice fuera de límite
     * @throws NumberFormatException        Excepción formato numérico
     */
    public void cargarDatosMapa(String[] vDatos) throws IndexOutOfBoundsException, NumberFormatException {
        this.tipo = vDatos[1];                          // establece atributo que recoge tamaño de mapa en formato 'FxC'
        int celdaMapa = Integer.parseInt(vDatos[2]);    // obtiene id de celda de disfraz como dato primitivo int
        this.cargarDisfrazEnMapa(celdaMapa);            // carga disfraz en celda de mapa indicada
    }
    
    /**
     * Método que crea militar correspondiente a posición recibida con datos de línea de 
     * fichero de inicio recibidos y lo inserta al final de cola de personajes de mapa.
     * 
     * @param vDatos                        Vector de cadenas que contiene datos de militar
     * @param posicion                      Número int que indica posición numérica de militar en vector de tókens
     * @throws IndexOutOfBoundsException    Excepción índice fuera de límite
     * @throws NumberFormatException        Excepción formato numérico
     */
    public void cargarMilitarEnMapa(String[] vDatos, int posicion) throws IndexOutOfBoundsException, NumberFormatException {
        // Es posible asignar identificador de superclase a objetos descendientes gracias a polimorfismo
        Militar militar = null;
        String nombre = vDatos[1];                                              // obtiene nombre de militar
        int trn = Integer.parseInt(vDatos[2]);                                  // obtiene turno de militar como dato primitivo int
        int idCelda = Integer.parseInt(vDatos[3]);                              // obtiene id de celda de militar como dato primitivo int
        char marca = vDatos[4].charAt(0);                                       // obtiene marca de militar como dato primitivo char
        char[] ruta = vDatos[5].toCharArray();                                  // obtiene ruta de militar como vector de char
        switch (posicion) {
            case 1: // BOINAVERDE
                militar = new BoinaVerde(nombre, trn, idCelda, marca, ruta);    // crea objeto de clase BoinaVerde con datos recopilados
                this.numAliadosMapa++;                                          // incrementa número de aliados de mapa una unidad 
                break;
            case 2: // ESPIA
                militar = new Espia(nombre, trn, idCelda, marca, ruta);         // crea objeto de clase Espia con datos recopilados
                this.numAliadosMapa++;                                          // incrementa número de aliados de mapa una unidad
                break;
            case 3: // ZAPADOR
                militar = new Zapador(nombre, trn, idCelda, marca, ruta);       // crea objeto de clase Zapador con datos recopilados
                this.numAliadosMapa++;                                          // incrementa número de aliados de mapa una unidad
                break;
            case 4: // GENERAL
                militar = new General(nombre, trn, idCelda, marca, ruta);       // crea objeto de clase General con datos recopilados
                break;
            case 5: // SOLDADO
                militar = new Soldado(nombre, trn, idCelda, marca, ruta);      // crea objeto de clase Soldado con datos recopilados
                break;
        }
        this.insertarMilitar(militar);                                         // inserta militar creado en celda de mapa correspondiente según su atributo idCelda
        this.colaPersonajes.add(militar);                                      // añade militar creado al final de cola de personajes de mapa
    }
    
    /**
     * Método que crea obra de arte con datos de línea de fichero de carga 
     * recibidos y lo inserta en la celda correspondiente del mapa.
     * 
     * @param vDatos                        Vector de cadenas que contiene datos de obra de arte
     * @throws IndexOutOfBoundsException    Excepción índice fuera de límite
     * @throws NumberFormatException        Excepción formato numérico
     */
    public void cargarObraEnMapa(String[] vDatos) throws IndexOutOfBoundsException, NumberFormatException {
        int codigo = Integer.parseInt(vDatos[1]);               // obtiene código de obra como dato primitivo int
        int idCelda = Integer.parseInt(vDatos[2]);              // obtiene id de celda de obra como dato primitivo int
        String nombre = vDatos[3];                              // obtiene nombre de obra
        String autor = vDatos[4];                               // obtiene autor de obra
        Obra obra = new Obra(codigo, idCelda, nombre, autor);   // crea objeto de clase Obra con datos recopilados
        this.insertarObra(obra);                                // inserta obra creada en celda de mapa correspondiente según su atributo idCelda
    }
    
    /**
     * Método que devuelve objeto celda que se encuentra en fila y columna
     * especificadas.
     *
     * @param fila      Número de fila de mapa
     * @param columna   Número de columna de mapa
     * @return          Objeto de clase Celda o null si sale fuera del mapa
     */
    public Celda getCelda(int fila, int columna) {
        Celda celda;
        try {
            celda = this.matriz[fila][columna];
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Const.CEL_IOB_EXC);
            celda = null;
        }
        return celda;
    }
    
    /**
     * Método que devuelve tamaño de la cola de personajes.
     * 
     * @return  Número int de militares que hay en la simulación
     */
    public int getNumeroPersonajes() {
        return this.colaPersonajes.size();
    }
    
    /**
     * Método que inserta militar en celda que corresponde según valor de su atributo idCelda.
     *
     * @param militar                       Objeto de clase Militar
     * @throws IndexOutOfBoundsException    Excepción índice fuera de límite
     */
    public void insertarMilitar(Militar militar) throws IndexOutOfBoundsException {
        int idCelda = militar.getIdCelda();                     // obtiene número de celda de militar
        int fila = Utilidad.calcularFila(idCelda);              // calcula fila de celda a partir de su id
        int columna = Utilidad.calcularColumna(idCelda);        // calcula columna de celda a partir de su id
        this.matriz[fila][columna].insertarMilitar(militar);    // inserta militar en celda de mapa correspondiente a fila y columna calculadas
    }

    /**
     * Método que borra militar de celda indicada que ocupa.
     *
     * @param nombre    Nombre de militar
     * @param idCelda   Número de celda
     */
    public void borrarMilitar(String nombre, int idCelda) {
        int fila = Utilidad.calcularFila(idCelda);          // calcula fila de celda a partir de su id
        int columna = Utilidad.calcularColumna(idCelda);    // calcula columna de celda a partir de su id
        this.matriz[fila][columna].borrarMilitar(nombre);   // borra militar de celda de mapa correspondiente a fila y columna calculadas
    }
    
    /**
     * Método que retorna militar de mapa que ocupa una determinada posición en la cola.
     * 
     * @param posicion  Posición numérica de militar en la cola
     * @return          Objeto de clase Militar o null si número está fuera de los límites de la cola   
     */
    public Militar getMilitar(int posicion) {
        Militar militar;
        try {
            militar = this.colaPersonajes.get(posicion);    // obtiene militar que ocupa posición indicada
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Const.COL_IOB_EXC);
            militar = null;
        }
        return militar;
    }
    
    /**
     * Método que inserta una en celda que corresponde según valor de su atributo idCelda.
     *
     * @param obra                          Objeto de clase Obra
     * @throws IndexOutOfBoundsException    Excepción índice fuera de límite
     */
    public void insertarObra(Obra obra) throws IndexOutOfBoundsException {
        int idCelda = obra.getIdCelda();                    // obtiene número de celda de obra
        int fila = Utilidad.calcularFila(idCelda);          // calcula fila de celda a partir de su id
        int columna = Utilidad.calcularColumna(idCelda);    // calcula columna de celda a partir de su id
        this.matriz[fila][columna].insertarObra(obra);      // inserta obra en celda de mapa correspondiente a fila y columna calculadas
    }

    /**
     * Método que borra obra de celda indicada que ocupa.
     *
     * @param obra      Objeto de clase Obra
     * @param idCelda   Número de celda
     */
    public void borrarObra(Obra obra, int idCelda) {
        int fila = Utilidad.calcularFila(idCelda);          // calcula fila de celda a partir de su id
        int columna = Utilidad.calcularColumna(idCelda);    // calcula columna de celda a partir de su id
        this.matriz[fila][columna].borrarObra(obra);        // borra obra de celda de mapa correspondiente a fila y columna calculadas
    }
    
    /**
     * Método que determina si es posible, según configuración de mapa,
     * movimiento desde celda origen a celda destino especificadas.
     *
     * @param celdaOrigen   Número de celda origen
     * @param celdaDestino  Número de celda destino
     * @return              Booleano true si movimiento es posible o false si movimiento no es posible
     */
    public boolean hayCamino(int celdaOrigen, int celdaDestino) {
        boolean hayCamino;
        try {
            hayCamino = this.adyacencia[celdaOrigen][celdaDestino] == 1;    // valor 1 en matriz de adyacencia indica que es posible movimiento entre dos celdas
        } catch (IndexOutOfBoundsException e) {
            hayCamino = false;                                              // movimiento siempre es imposible fuera de límites de mapa 
        }
        return hayCamino;
    }

    /**
     * Método que incrementa número de turno de mapa una unidad.
     */
    public void siguienteTurno() {
        this.turno++;
    }
    
    /**
     * Método que incrementa número de aliados en Punto de Encuentro una unidad.
     */
    public void aliadoEnPunto() {
        this.numAliadosEncuentro++;
    }

    /***************************************************************************************/
    /***************************************************************************************/
    /****************************** MÉTODOS PARA PINTAR MAPA *******************************/
    /***************************************************************************************/
    /***************************************************************************************/
    
    /*  
        Retorna true si la celda NO es accesible (hay un edificio, un árbol, etc). 
        El método es privado porque sólo se utiliza en la clase Mapa.
     */
    private boolean celdaNoAccesible(int idCelda) {
        boolean noAccesible = true;
        int j = 0;
        while ((j < (this.filas * this.columnas)) && (noAccesible)) {
            if (this.adyacencia[idCelda][j] == 1) {
                noAccesible = false;
            }
            j++;
        }
        return noAccesible;
    }

    /*  
        Pinta la primera línea del mapa. 
        El método es privado porque sólo se utiliza en la clase Mapa.
     */
    private void pintarLineaSuperior(int i, PrintWriter pw) {
        int celdaOrigen, celdaDestino, j;
        if (i == 0) {
            // Línea superior. Empezamos dejando un hueco para dejas un primer hueco para pintar la línea izquierda
            pw.print(Const.WS1);
            for (j = 0; j < this.matriz[0].length; j++) {
                pw.print(Const.LINE + Const.WS1);
            }
        } else {
            pw.print(Const.BAR1);
            for (j = 0; j < this.matriz[i].length - 1; j++) {
                // Calculamos celdaOrigen y celdaDestino
                celdaOrigen = (this.columnas * i) + j;
                celdaDestino = celdaOrigen - this.columnas;
                if (this.hayCamino(celdaOrigen, celdaDestino)) {
                    pw.print(Const.WS4);
                } else {
                    pw.print(Const.LINE + Const.WS1);
                }
            }
            // Calculamos celdaOrigen y celdaDestino
            celdaOrigen = (this.columnas * i) + j;
            celdaDestino = celdaOrigen - this.columnas;
            if (this.hayCamino(celdaOrigen, celdaDestino)) {
                pw.print(Const.BAR4);
            } else {
                pw.print(Const.LINE + Const.BAR1);
            }
        }
        // Ponemos el cursor en la siguiente línea
        pw.print(Const.LB);
    }

    /*  
        Pinta la línea inferior del mapa. 
        El método es privado porque sólo se utiliza en la clase Mapa.
     */
    private void pintarLineaInferior(PrintWriter pw) {
        // Línea inferior. Empezamos dejando un hueco para dejas un primer hueco para pintar la línea izquierda
        pw.print(Const.WS1);
        for (Celda celda : this.matriz[0]) {
            pw.print(Const.LINE + Const.WS1);
        }
        // Ponemos el cursor en la siguiente línea
        pw.print(Const.LB);
    }

    /*  
        Pinta la columna 0 de la matriz. 
        El método es privado porque sólo se utiliza en la clase Mapa.
     */
    private void pintarColumnaCero(int celdaOrigen, int celdaDestino, int i, int j, int x, PrintWriter pw) {
        if (this.celdaNoAccesible(celdaOrigen)) {
            pw.print(Const.BAR1 + Const.BLOCK + Const.BAR1);
        } else {
            if (this.hayCamino(celdaOrigen, celdaDestino)) {
                // Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print(Const.BAR5);
                } else {  // Si estamos en la línea central
                    pw.print(Const.BAR2 + this.matriz[i][j].toString() + Const.WS3);
                }
            } else {
                // Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print(Const.BAR6);
                } else {  // Si estamos en la línea central
                    pw.print(Const.BAR2 + this.matriz[i][j].toString() + Const.BAR3);
                }
            }
        }

    }

    /*  
        Pinta las columnas de la matriz (excepto la columna 0). 
        El método es privado porque sólo se utiliza en la clase Mapa.
     */
    private void pintarRestoColumnas(int celdaOrigen, int celdaDestino, int i, int j, int x, PrintWriter pw) {
        if (this.celdaNoAccesible(celdaOrigen)) {
            pw.print(Const.BLOCK + Const.BAR1);
        } else {
            if (this.hayCamino(celdaOrigen, celdaDestino)) {
                // Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print(Const.WS4);
                } else {  // Si estamos en la línea central
                    pw.print(Const.WS2 + this.matriz[i][j].toString() + Const.WS3);
                }
            } else {
                // Si estamos en la primera o última de las 3 líneas
                if (x % 2 == 0) {
                    pw.print(Const.BAR4);
                } else {  // Si estamos en la línea central
                    pw.print(Const.WS2 + this.matriz[i][j].toString() + Const.BAR3);
                }
            }
        }
    }

    /*  
        Pinta la fila 'i' de la matriz. 
        El método es privado porque sólo se utiliza en la clase Mapa.
     */
    private void pintarFila(int i, PrintWriter pw) {
        int j, x;
        int celdaOrigen, celdaDestino;
        for (x = 0; x < 3; x++) {  //Cada celda la dividimos en 3 líneas
            for (j = 0; j < this.matriz[i].length; j++) {  //Columnas
                // Calculamos celdaOrigen y celdaDestino
                celdaOrigen = (this.columnas * i) + j;
                celdaDestino = celdaOrigen + 1;

                // Si estamos en la primera columna 
                if (j == 0) {
                    this.pintarColumnaCero(celdaOrigen, celdaDestino, i, j, x, pw);
                } else {  //Si estamos en cualquier columna del medio
                    this.pintarRestoColumnas(celdaOrigen, celdaDestino, i, j, x, pw);
                }
            }
            // Ponemos el cursor en la siguiente línea
            pw.print(Const.LB);
        }
    }

    // Pinta mapa en el fichero de simulación
    public void pintarMatriz(PrintWriter pw) {
        int i;
        for (i = 0; i < this.matriz.length; i++) {  // Filas
            this.pintarLineaSuperior(i, pw);
            this.pintarFila(i, pw);
        }
        this.pintarLineaInferior(pw);
    }

}
