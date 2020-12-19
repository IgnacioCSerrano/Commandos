package ficheros;

import generico.Const;
import generico.Mapa;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Clase que lleva a cabo funcionalidad de carga de datos en simulación a partir de fichero de inicio.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     generico.Mapa
 * @see     generico.Const
 */
public class Lectura {
    
    /**
     * Método que determina qué tipo de elemento corresponde a la línea 
     * de fichero pasada por parámetro.
     * 
     * @param elemento  Cadena String de objeto a ser creado
     * @return          Número int que indica posición numérica de objeto en vector de tókens
     */
    public static int queElemento(String elemento) {
        int posicion = Arrays.asList(Const.VTOKENS).indexOf(elemento.toUpperCase()); // obtiene posición en vector de tókens de elemento pasado como parámetro
        return posicion;
    }
    
    /**
     * Método que lee los datos de fichero de inicio y, en función de estos,
     * crea y carga los objetos correspondientes en el mapa.
     * 
     * @param br            Flujo de lectura de clase BufferedReader
     * @throws IOException  Excepción Entrada/Salida
     * @return              Booleano true si proceso ha resultado correcto y false si ha habido algún fallo (objeto inexistente)
     */
    public static boolean cargarDatos(BufferedReader br) throws IOException {
        int posicion;
        String[] vDatos;
        boolean correcto = true;
        Mapa mapa = Mapa.getInstancia();                            // carga objeto mapa (patrón Singleton)                          
        String linea = br.readLine();                               // recoge primera línea de fichero
        while (linea != null && correcto) {                         // mientras no se llegue a final de fichero y no haya habido fallo
            if (!linea.startsWith(Const.COMMENT)) {            // si línea NO es comentario
                vDatos = linea.split(Const.NS_TOKEN);          // obtiene array de cadenas divididas por el patrón '#'
                posicion = queElemento(vDatos[0]);                  // obtiene posición en array de tókens de elemento detallado en línea actual
                switch (posicion) {
                    case 0:                                         // si información de línea se refiere a objeto mapa
                        mapa.cargarDatosMapa(vDatos);               // carga datos de tamaño de mapa y celda de disfraz
                        break;
                    case 1:                                         // si información de línea se refiere a objeto militar
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        mapa.cargarMilitarEnMapa(vDatos, posicion); // crea objeto militar correspondiente a posición y lo inserta en mapa
                        break;
                    case 6:                                         // si información de línea se refiere a objeto obra
                        mapa.cargarObraEnMapa(vDatos);              // crea objeto obra y lo inserta en mapa
                        break;
                    default:
                        System.out.println(Const.LOAD_ERR + Const.CLN + Const.LOAD_ERR1);
                        correcto = false;
                }
            }
            linea = br.readLine();                                  // recoge siguiente línea de fichero
        }
        return correcto;
    }
    
    /**
     * Método que lee los datos del fichero de inicio y carga los datos de la simulación.
     * 
     * @param fichero   Nombre del fichero de carga de datos
     * @return          Booleano true si proceso ha resultado correcto y false si ha habido algún fallo (excepción)
     */
    public static boolean cargarFichero(String fichero) {
        boolean correcto = false;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(fichero);
            br = new BufferedReader(fr);
            correcto = cargarDatos(br);
        } catch (FileNotFoundException e) {
            System.out.println(Const.FNF_EXC + Const.CLN + e.getMessage());
        } catch (IOException e) {
            System.out.println(Const.IO_EXC + Const.CLN + e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Const.LOAD_ERR + Const.CLN + Const.LOAD_ERR2);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println(Const.IO_EXC + Const.CLN + e.getMessage());
                correcto = false;
            }
        }
        return correcto;
    }
    
}
