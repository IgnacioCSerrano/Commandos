package generico;

/**
 * Clase que representa un militar aliado.
 *
 * @author  Ignacio Cuevas Serrano
 * @see     Const
 */
public class Obra {

    /**
     * Código de obra de arte.
     */
    private int codigo;

    /**
     * Número de celda de obra de arte.
     */
    private int idCelda;

    /**
     * Nombre de obra de arte.
     */
    private String nombre;

    /**
     * Autor de obra de arte.
     */
    private String autor;

    /**
     * Método constructor por defecto.
     */
    public Obra() {
        this.codigo = 0;
        this.idCelda = 0;
        this.nombre = Const.EMP_STR;
        this.autor = Const.EMP_STR;
    }

    /**
     * Método constructor parametrizado.
     *
     * @param codigo    Código de obra de arte
     * @param idCelda   Número de celda de obra de arte
     * @param nombre    Nombre de obra de arte
     * @param autor     Autor de obra de arte
     */
    public Obra(int codigo, int idCelda, String nombre, String autor) {
        this.codigo = codigo;
        this.idCelda = idCelda;
        this.nombre = nombre;
        this.autor = autor;
    }

    /**
     * Método que devuelve código de obra de arte.
     *
     * @return  Número int de obra de arte
     */
    public int getCodigo() {
        return this.codigo;
    }

    /**
     * Método que devuelve número de celda de obra de arte.
     *
     * @return  Número int de celda de obra de arte
     */
    public int getIdCelda() {
        return this.idCelda;
    }

    /**
     * Método que devuelve nombre de obra de arte.
     *
     * @return  Nombre String de obra de arte
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Método que devuelve autor de obra de arte.
     *
     * @return  Autor String de obra de arte
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * Método que establece código de obra de arte.
     *
     * @param codigo    Número de obra de arte
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Método que establece número de celda de obra de arte.
     *
     * @param idCelda   Número de celda de obra de arte
     */
    public void setIdCelda(int idCelda) {
        this.idCelda = idCelda;
    }

    /**
     * Método que establece nombre de obra de arte.
     *
     * @param nombre    Nombre de obra de arte
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que establece autor de obra de arte.
     *
     * @param autor Autor de obra de arte
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Método que muestra por pantalla información de nombre y autor de obra de arte.
     */
    public void mostrarObra() {
        System.out.println(Const.TAB + getNombre() + Const.RESC5 + getAutor() + Const.PRD);
    }

}
