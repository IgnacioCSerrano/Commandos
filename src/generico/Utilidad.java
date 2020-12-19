package generico;

import java.io.File;
import java.time.LocalDateTime;

/**
 * Clase que lleva a cabo operaciones de utilidad.
 * 
 * @author Ignacio Cuevas Serrano
 */
public class Utilidad {
    
    /**
     * Método que devuelve fila de celda de mapa identificada por su número pasado como parámetro.
     * 
     * @param idCelda   Número de celda
     * @return          Número int de fila
     */
    public static int calcularFila(int idCelda) {   // salto de número de celda a siguiente fila viene determinado por número de columnas de mapa
        Mapa mapa = Mapa.getInstancia();            // carga objeto mapa (patrón Singleton)
        int fila = idCelda / mapa.getColumnas();    // fila se calcula como divión entre número de celda y número de columnas de mapa
        return fila;
    }
    
    /**
     * Método que devuelve columna de celda de mapa identificada por su número pasado como parámetro.
     * 
     * @param idCelda   Número de celda
     * @return          Número int de columna
     */
    public static int calcularColumna(int idCelda) {    // salto de número de celda a siguiente fila viene determinado por número de columnas de mapa
        Mapa mapa = Mapa.getInstancia();                // carga objeto mapa (patrón Singleton)
        int columna = idCelda % mapa.getColumnas();     // columna se calcula como resto de divión entre número de celda y número de columnas de mapa
        return columna;
    }
    
    /**
     * Método que completa el nombre del fichero con los parámetros del día y mes de ejecución.
     * 
     * @param base  Nombre base de fichero
     * @return      Número int de columna
     */
    public static String calcularNombreFichero(String base) {
        LocalDateTime ahora = LocalDateTime.now();                          // obtiene fecha actual
        String anno = String.format(Const.ZP, ahora.getYear());        // obtiene año actual con relleno de cero a izquierda si necesario para formar dos cifras
        String mes = String.format(Const.ZP, ahora.getMonthValue());   // obtiene mes actual con relleno de cero a izquierda si necesario para formar dos cifras
        String dia = String.format(Const.ZP, ahora.getDayOfMonth());   // obtiene día actual con relleno de cero a izquierda si necesario para formar dos cifras
        String hora = String.format(Const.ZP, ahora.getHour());        // obtiene hora actual con relleno de cero a izquierda si necesario para formar dos cifras
        String min = String.format(Const.ZP, ahora.getMinute());       // obtiene minuto actual con relleno de cero a izquierda si necesario para formar dos cifras
        String seg = String.format(Const.ZP, ahora.getSecond());       // obtiene segundo actual con relleno de cero a izquierda si necesario para formar dos cifras
        String fecha = anno.substring(anno.length() - 2) + mes + dia + Const.TIM_SEP + hora + min + seg;
        Mapa mapa = Mapa.getInstancia();                                    // carga objeto mapa (patrón Singleton)
        String tam = mapa.getTipo();                                        // obtiene tamaño de mapa en formato 'FxC'
        String carpeta = Const.M + tam;                                // obtiene nombre de carpeta concatenando el tamaño de mapa
        File directorio = new File(carpeta);                                // crea objeto de clase File
        directorio.mkdir();                                                 // crea directorio (carpeta)
        // Construye nombre de fichero como subcadena de base cortada desde inicio hasta último punto, o como cadena base si esta no incluye extensión (no hay punto)
        String file = base.contains(Const.PRD) ? base.substring(0, base.lastIndexOf(Const.PRD)) : base;
        // Construye extensión de fichero como subcadena de base cortada desde último punto hasta final, o como '.log' si esta no incluye extensión (no hay punto)
        String extension = base.contains(Const.PRD) ? base.substring(base.lastIndexOf(Const.PRD), base.length()) : Const.LOG_EXT;
        // Construye nombre final del fichero (ruta compuesta por directorio de carpeta y archivo)
        String filename = carpeta + Const.DIR_SEP + file + tam + Const.US + fecha + extension;  
        return filename;
    }
    
}
