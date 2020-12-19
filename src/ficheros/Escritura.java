package ficheros;

import generico.Const;
import generico.Mapa;
import generico.Obra;
import generico.Utilidad;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import personajes.Aliado;
import personajes.BoinaVerde;
import personajes.Espia;
import personajes.Militar;
import personajes.Zapador;

/**
 * Clase que lleva a cabo funcionalidad de registro de simulación y obras de arte rescatadas por aliados.
 * 
 * @author  Ignacio Cuevas Serrano
 * @see     personajes.Militar
 * @see     personajes.Aliado
 * @see     personajes.BoinaVerde
 * @see     personajes.Espia
 * @see     personajes.Zapador
 * @see     generico.Obra
 * @see     generico.Mapa
 * @see     generico.Utilidad
 * @see     generico.Const
 */
public class Escritura {
    
    /**
     * Método que crea flujo de escritura FileWriter de un fichero de registro y lo devuelve.
     * 
     * @param fichero   Nombre del fichero de simulación
     * @return          Flujo de escritura de clase FileWriter
     */
    public static FileWriter abrirFlujoFW(String fichero) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fichero);
        } catch (IOException e) {
            System.out.println(Const.IO_EXC + e.getMessage());
        } 
        return fw;
    }
    
    /**
     * Método que crea flujo de escritura PrintWriter de un fichero de registro y lo devuelve.
     * 
     * @param fw    Flujo de escritura de clase FileWriter
     * @return      Flujo de escritura de clase PrintWriter
     */
    public static PrintWriter abrirFlujoPW(FileWriter fw) {
        PrintWriter pw = new PrintWriter(fw);
        return pw;
    }
    
    /**
     * Método que cierra flujo de escritura FileWriter de un fichero de registro.
     * 
     * @param fw    Flujo de escritura de clase FileWriter
     */
    public static void cerrarFlujoFW(FileWriter fw) {
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println(Const.IO_EXC + e.getMessage());
            } 
        }
    }
    
    /**
     * Método que cierra flujo de escritura PrintWriter de un fichero de registro.
     * 
     * @param pw    Flujo de escritura de clase PrintWriter
     */
    public static void cerrarFlujoPW(PrintWriter pw) {
        if (pw != null) {
            pw.close();
        }
    }
    
    /**
     * Método que escribe en el fichero de registro la obra restatada por el aliado.
     * 
     * @param obra  Objeto de clase obra o null
     * @param pw    Flujo de escritura de clase PrintWriter para archivo de registro
     */
    public static void escribirObraRescatada(Obra obra, PrintWriter pw) {
        int codigo;
        String nombre, autor, registro;
        if (obra != null) {                                                                         // si obra no es nula
            codigo = obra.getCodigo();                                                              // obtiene código de obra
            nombre = obra.getNombre();                                                              // obtiene nombre de obra
            autor = obra.getAutor();                                                                // obtiene autor de obra
            registro = String.join(Const.CLN_TOKEN, Integer.toString(codigo), nombre, autor);  // concatena datos de obra usando token ':' como separador
            pw.println(Const.RB1 + registro + Const.RB2);
        }
    }
    
    /**
     * Método que escribe en fichero de registro los datos correspondientes 
     * a la obra u obras rescatadas por el aliado recibido por parámetro.
     * 
     * @param aliado    Objeto de clase Aliado
     * @param pw        Flujo de escritura de clase PrintWriter
     */
    public static void registrarObra(Aliado aliado, PrintWriter pw) {
        Iterator<Obra> it;
        BoinaVerde bVerde;
        Espia espia;
        Zapador zapador;
        Obra obra;
        String nombre, marca, cabecera;
        nombre = aliado.getNombre();                                                    // obtiene nombre de aliado                         
        marca = Character.toString(aliado.getMarca());                                  // obtiene marca de aliado
        if (aliado instanceof BoinaVerde) {                                             // si aliado es instancia de Clase BoinaVerde
            bVerde = (BoinaVerde) aliado;                                               // obtiene boina verde mediante casting
            cabecera = String.join(Const.CLN_TOKEN, Const.B, nombre, marca);  // concatena datos de boina verde usando token ':' como separador
            pw.println(Const.RB1 + cabecera + Const.RB2);                     
            it = bVerde.getConjuntoObras().iterator();                                  // iterador apunta a primera obra de arte de conjunto de boina verde 
            while (it.hasNext()) {                                                      // mientras haya obras en el conjunto
                obra = it.next();                                                       // obtiene obra de arte de boina verde y mueve puntero a siguiente
                escribirObraRescatada(obra, pw);                                        // escribe obra en fichero de registro
            }
        } else if (aliado instanceof Espia) {                                           // si aliado es instancia de Clase Espia
            espia = (Espia) aliado;                                                     // obtiene espía mediante casting
            obra = espia.getObra();                                                     // obtiene obra de arte de espía
            cabecera = String.join(Const.CLN_TOKEN, Const.E, nombre, marca);  // concatena datos de espía usando token ':' como separador
            pw.println(Const.RB1 + cabecera + Const.RB2);                     
            escribirObraRescatada(obra, pw);                                            // escribe obra en fichero de registro
        } else {                                                                        // si aliado es instancia de Clase Zapador (aliado restante)
            zapador = (Zapador) aliado;                                                 // obtiene zapador mediante casting
            obra = zapador.getObra();                                                   // obtiene obra de arte de zapador
            cabecera = String.join(Const.CLN_TOKEN, Const.Z, nombre, marca);  // concatena datos de zapador usando token ':' como separador
            pw.println(Const.RB1 + cabecera + Const.RB2);                     
            escribirObraRescatada(obra, pw);                                            // escribe obra en fichero de registro
        }
    }
    
    /**
     * Método que inserta los datos correspondientes en el flujo recibido por parámetro.
     * 
     * @param pw    Flujo de escritura de clase PrintWriter
     */
    public static void insertarDatos(PrintWriter pw) {
        Militar militar;
        Aliado aliado;
        boolean hayAliado = true;
        Mapa mapa = Mapa.getInstancia();                            // carga objeto mapa (patrón Singleton)
        Iterator<Militar> it = mapa.getColaPersonajes().iterator(); // iterador apunta a primer personaje de cola
        while (hayAliado && it.hasNext()) {         
            militar = it.next();
            if (militar instanceof Aliado) {                    
                aliado = (Aliado) militar;
                registrarObra(aliado, pw);
            } else {
                /*
                    Bucle finaliza al llegar a primer nazi porque bloque de elementos aliados es siempre anterior a bloque de elementos nazis en fichero de inicio,
                    por lo que según este orden de carga, aliados ocupan siempre todos juntos posiciones iniciales de cola de personajes
                */
                hayAliado = false; 
            }
        }
    }
    
    /**
     * Método que escribe el registro de las obras de arte que han rescatado los aliados.
     */
    public static void escribirRegistroLog() {
        String ficheroReg;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            ficheroReg = Utilidad.calcularNombreFichero(Const.BASE_REG);
            fw = new FileWriter(ficheroReg);
            pw = new PrintWriter(fw);
            insertarDatos(pw);
        } catch (IOException e) {
            System.out.println(Const.IO_EXC + e.getMessage());
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println(Const.IO_EXC + e.getMessage());
            } 
        }
    }
    
}
