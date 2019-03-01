package gestion.torneos.ui;

/**
 * Clase que representa el resultado de la validación de los datos de un nuevo
 * aspirante.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class ResultadoValidacionNuevoAspirante {

    // true si los datos ingresados pasaron la validación, false en otro caso...
    private boolean nuevoAspiranteOk = true;

    private boolean nombreOk;

    private boolean apellidoOk;

    private boolean fechaNacimientoOk;

    private boolean fechaNacimientoFormatoOk;

    private boolean fechaIngresadaMenorQueActualOk;

    private boolean rangoDeEdadOk;

    private boolean dniOk;

    private boolean dniSoloNumerosOk;

    private boolean dniExistenteOk;

    private boolean dniLongitudOk;

    public boolean isNuevoAspiranteOk() {
        return nuevoAspiranteOk;
    }

    public void setNuevoAspiranteOk(boolean nuevoAspiranteOk) {
        this.nuevoAspiranteOk = nuevoAspiranteOk;
    }

    public boolean isNombreOk() {
        return nombreOk;
    }

    public void setNombreOk(boolean nombreOk) {
        this.nombreOk = nombreOk;
        if (!this.nombreOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isApellidoOk() {
        return apellidoOk;
    }

    public void setApellidoOk(boolean apellidoOk) {
        this.apellidoOk = apellidoOk;
        if (!this.apellidoOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isFechaNacimientoOk() {
        return fechaNacimientoOk;
    }

    public void setFechaNacimientoOk(boolean fechaNacimientoOk) {
        this.fechaNacimientoOk = fechaNacimientoOk;
        if (!this.fechaNacimientoOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isFechaNacimientoFormatoOk() {
        return fechaNacimientoFormatoOk;
    }

    public void setFechaNacimientoFormatoOk(boolean fechaNacimientoFormatoOk) {
        this.fechaNacimientoFormatoOk = fechaNacimientoFormatoOk;
        if (!this.fechaNacimientoFormatoOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isDniOk() {
        return dniOk;
    }

    public void setDniOk(boolean dniOk) {
        this.dniOk = dniOk;
        if (!this.dniOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isDniSoloNumerosOk() {
        return dniSoloNumerosOk;
    }

    public void setDniSoloNumerosOk(boolean dniSoloNumerosOk) {
        this.dniSoloNumerosOk = dniSoloNumerosOk;
        if (!this.dniSoloNumerosOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isDniExistenteOk() {
        return dniExistenteOk;
    }

    public void setDniExistenteOk(boolean dniExistenteOk) {
        this.dniExistenteOk = dniExistenteOk;
        if (!this.dniExistenteOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isFechaIngresadaMenorQueActualOk() {
        return fechaIngresadaMenorQueActualOk;
    }

    public void setFechaIngresadaMenorQueActualOk(boolean fechaIngresadaMenorQueActualOk) {
        this.fechaIngresadaMenorQueActualOk = fechaIngresadaMenorQueActualOk;
        if (!this.fechaIngresadaMenorQueActualOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isRangoDeEdadOk() {
        return rangoDeEdadOk;
    }

    public void setRangoDeEdadOk(boolean rangoDeEdadOk) {
        this.rangoDeEdadOk = rangoDeEdadOk;
        if (!this.rangoDeEdadOk) {
            setNuevoAspiranteOk(false);
        }
    }

    public boolean isDniLongitudOk() {
        return dniLongitudOk;
    }

    public void setLongitudDniOk(boolean dniLongitudOk) {
        this.dniLongitudOk = dniLongitudOk;
        if (!dniLongitudOk) {
            setNuevoAspiranteOk(false);
        }
    }
}
