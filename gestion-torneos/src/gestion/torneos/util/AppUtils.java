package gestion.torneos.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que contiene métodos estáticos utilitarios.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class AppUtils {

    /**
     * Determina si la fecha argumentada en formato String es válida y con
     * formato dd/mm/aaaa.
     *
     *
     * @param date Fecha en formato String.
     * @return true si es válida la fecha y con formato dd/mm/aaaa, false en
     * otro caso.
     */
    public static boolean esFormatoFechaValido(String date) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Determina si la fecha argumentada es menor a la fecha actual.
     *
     * @param fechaIngresada - Fecha en formato Date
     * @return true si la fecha ingresada es menor a la actual, false en otro
     * caso.
     */
    public static boolean esFechaIngresadaMenorQueFechaActual(Date fechaIngresada) {
        Date fechaActual = new Date();
        return fechaIngresada != null && fechaIngresada.before(fechaActual);
    }

    /**
     * Determina si el String argumentado es solo numérico, es decir, si
     * representa a un número válido.
     *
     * @param numeroStr Número en formato String.
     * @return true si es un número válido, false en otro caso.
     */
    public static boolean esNumeroValido(String numeroStr) {
        try {
            Integer.parseInt(numeroStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Convierte el String argumentado a un objeto de tipo Date con formato
     * dd/mm/aaaa.
     *
     * @param dateAsString - Fecha en formato String.
     * @return Fecha en formato Date.
     */
    public static Date stringToDate(String dateAsString) {
        Date outputDate = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            outputDate = df.parse(dateAsString);
            return outputDate;
        } catch (ParseException e) {
            return null;
        }
    }
}
