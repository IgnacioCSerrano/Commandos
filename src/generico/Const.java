package generico;

/**
 * Clase que contiene las constantes del proyecto.
 * 
 * @author Ignacio Cuevas Serrano
 */
public class Const {
    
    // CLASE PRINCIPAL
    
    public static final String WELCOME = "¡BIENVENIDO A LA SIMULACIÓN DE \"COMMANDOS: BEHIND MONUMENTS MEN\"!";
    public static final String PED_OPC = "Elija la opción deseada: ";
    public static final String OPC = "Opción: ";
    public static final String MENU1 = "1.- Simulación 3x3.";
    public static final String MENU2 = "2.- Simulación 5x5.";
    public static final String MENU3 = "3.- Simulación 8x8.";
    public static final String MENU4 = "4.- Salir.";
    public static final String EXIT = "Simulación terminada.";
    public static final String OPC_ERR = "Opción incorrecta.";
    
    public static final String TUR = "TURNO: ";
    
    public static final String SIM_SUC1 = "Simulación de mapa ";
    public static final String SIM_SUC2 = " ejecutada correctamente.";
    public static final String SIM_ERR = "Simulación ha fallado por errores en la carga del fichero de inicio.";
    
    // LECTURA Y ESCRITURA
    
    public static final String TRESPORTRES = "inicio3x3.txt";
    public static final String CINCOPORCINCO = "inicio5x5.txt";
    public static final String OCHOPOROCHO = "inicio8x8.txt";
    public static final String BASE_SIM = "simulacion.log";
    public static final String BASE_REG = "registro.log";
    public static final String LOG_EXT = ".log";
    
    public static final String M = "MAPA";
    public static final String B = "BOINAVERDE";
    public static final String E = "ESPIA";
    public static final String Z = "ZAPADOR";
    public static final String G = "GENERAL";
    public static final String S = "SOLDADO";
    public static final String O = "OBRAARTE";
    
    public static final String[] VTAM = {TRESPORTRES, CINCOPORCINCO, OCHOPOROCHO};
    public static final String[] VTOKENS = {M, B, E, Z, G, S, O};
    
    public static final String COMMENT = "--";
    public static final String NS_TOKEN = "#";
    public static final String CLN_TOKEN = ":";
    public static final String US = "_";
    public static final String DIR_SEP = "\\";
    public static final String TIM_SEP = "T";
    
    public static final String LOAD_ERR = "Error al evaluar elemento en fichero de inicio";
    public static final String LOAD_ERR1 = "Objeto inexistente.";
    public static final String LOAD_ERR2 = "Secuencia de datos incorrecta.";
    public static final String CEL_IOB_EXC = "Error: Celda fuera de límites de mapa.";
    public static final String FNF_EXC = "Error - Fichero no encontrado";
    public static final String IO_EXC = "Error - Entrada/Salida";
    
    // MAPA
    
    public static final int TRES = 3;
    public static final int CINCO = 5;
    public static final int OCHO = 8;
    
    public static final char NORTH = 'N';
    public static final char SOUTH = 'S';
    public static final char EAST = 'E';
    public static final char WEST = 'O';
    
    public static final String CERO = "0";
    public static final String INTERROGACION = "?";
    public static final String WS1 = " ";
    public static final String WS2 = "   ";
    public static final String WS3 = "    ";
    public static final String WS4 = "        ";
    public static final String LINE = "-------";
    public static final String BLOCK = "///////";
    public static final String BAR1 = "|";
    public static final String BAR2 = "|   ";
    public static final String BAR3 = "   |";
    public static final String BAR4 = "       |";
    public static final String BAR5 = "|        ";
    public static final String BAR6 = "|       |";
    
    public static final String MAP_ERR = "Mapa ya está creado.";
    public static final String IMP_MOV1 = "¡NO PUEDO! ";
    public static final String IMP_MOV2 = "¡NO TENGO RUTA!";
    public static final String COL_IOB_EXC = "Error: Posición fuera de los límites de cola de personajes.";
    
    // CELDA
    
    public static final String CON_IOB_EXC = "Error: Posición fuera de los límites de conjunto de militares.";
    
    // ALIADOS
    
    public static final String DISG = "Disfraz recogido.";
    
    public static final String RESC1 = " recuperado.";
    public static final String RESC2 = "Imposible recuperar ";
    public static final String RESC3 = "Yo, ";
    public static final String RESC4 = ", he rescatado: ";
    public static final String RESC5 = ", de ";
    public static final String RESC6 = " no ha rescatado ninguna obra todavía.";
    
    public static final String PNA = "Perímetro NO asegurado.";
    public static final String PE = " está en Punto de Encuentro.";
    public static final String CAP = "¡CAPTURADO!";
    
    // NAZIS
    
    public static final String MISLED = "¡ENGAÑADO!";
    public static final String REQU = " requisada.";
    
    // MISCELÁNEA
    
    public static final String LB = "\n";
    public static final String TAB = "\t";
    public static final String QM = "\"";
    public static final String ZP = "%02d";
    public static final String CLN = ": ";
    public static final String PRD = ".";
    public static final String SB1 = "[";
    public static final String SB2 = "]";
    public static final String RB1 = "(";
    public static final String RB2 = ")";
    public static final String EMP_STR = "";
    public static final char EMP_CHA = ' ';
    
}
