package gestion.torneos.model;

/**
 * Clase que representa una categoría.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Categoria implements java.io.Serializable {

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private Genero genero;
    private int edadDesde;
    private int edadHasta;

    /**
     * Constructor por defecto, sin parámetros.
     */
    public Categoria() {

    }

    /**
     * Constructor que recibe los siguientes datos de la categoría:
     * <li>Id de la categoría</li>
     * <li>Nombre de la categoría</li>
     * <li>Género de la categoría</li>
     *
     * @param idCategoria Id de la categoría.
     * @param nombre Nombre de la categoría.
     * @param genero Género de la categoría.
     */
    public Categoria(int idCategoria, String nombre, Genero genero) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.genero = genero;
    }

    /**
     * Constructor que recibe los siguientes datos de la categoría:
     * <li>Id de la categoría</li>
     * <li>Nombre de la categoría</li>
     * <li>Descripción de la categoría</li>
     * <li>Género de la categoría</li>
     * <li>Edad mínima de la categoría</li>
     * <li>Edad máxima de la categoría</li>
     *
     * @param idCategoria Id de la categoría.
     * @param nombre Nombre de la categoría.
     * @param descripcion Descripción de la categoría.
     * @param genero Genero de la categoría.
     * @param edadDesde Edad mínima de la categoría.
     * @param edadHasta Edad máxima de la categoría.
     */
    public Categoria(int idCategoria, String nombre, String descripcion, Genero genero, int edadDesde, int edadHasta) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.edadDesde = edadDesde;
        this.edadHasta = edadHasta;
    }

    /**
     * Retorna el Id de la categoría.
     *
     * @return int id
     */
    public int getIdCategoria() {
        return this.idCategoria;
    }

    /**
     * Setea el Id de la categoría.
     *
     * @param idCategoria El Id de la categoría.
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Retorna el nombre de la categoría.
     *
     * @return String - El nombre de la categoría.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre de la categoría.
     *
     * @param nombre El nombre de la categoría.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripción de la categoría.
     *
     * @return String - La descripción de la categoría.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Setea la descripción de la categoría.
     *
     * @param descripcion La descripción de la categoría.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna el género de la categoría.
     *
     * @return Genero - El género de la categoría.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Setea el género de la categoría.
     *
     * @param genero El género de la categoría.
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Retorna la edad mínima de la categoría.
     *
     * @return int - La edad mínima de la categoría.
     */
    public int getEdadDesde() {
        return edadDesde;
    }

    /**
     * Setea la edad mínima de la categoría.
     *
     * @param edadDesde La edad mínima de la categoría.
     */
    public void setEdadDesde(int edadDesde) {
        this.edadDesde = edadDesde;
    }

    /**
     * Retorna la edad máxima de la categoría.
     *
     * @return int - La edad máxima de la categoría.
     */
    public int getEdadHasta() {
        return edadHasta;
    }

    /**
     * Setea la edad máxima de la categoría.
     *
     * @param edadHasta La edad máxima de la categoría.
     */
    public void setEdadHasta(int edadHasta) {
        this.edadHasta = edadHasta;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.idCategoria != other.idCategoria) {
            return false;
        }
        return true;
    }
}
