package gestion.torneos.main;

import gestion.torneos.controller.GestorInscripcion;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Clase principal de la aplicación. Desde esta clase inicia la ejecución del
 * sistema.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Main {

    private static final Logger _logger = Logger.getLogger(Main.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Se carga configuración de log4j...
        cargarLog4j();
        
        // Se carga lookAndFeel...
        cargarLookAndFeel();
        
        // Se inicia gestor de inscripciones...
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new GestorInscripcion().iniciarProcesoDeInscripcion();
            } catch (Exception ex) {
                String errorMessage = "Error al iniciar la aplicación: " + ex.getMessage();
                _logger.error(errorMessage, ex);
                JOptionPane.showMessageDialog(new JFrame(), 
                        errorMessage, 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        });

    }

    private static void cargarLookAndFeel() {
        try {
            // Configuración de lookAndFeel...
            _logger.debug("Intentando cargar Nimbus lookAndFeel");
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    NimbusLookAndFeel laf = (NimbusLookAndFeel) UIManager.getLookAndFeel();
                    UIDefaults defaults = laf.getDefaults();
                    defaults.put("List[Selected].textForeground",
                            laf.getDerivedColor("nimbusLightBackground", 0.0f, 0.0f, 0.0f, 0, false));
                    defaults.put("List[Selected].textBackground",
                            laf.getDerivedColor("nimbusSelectionBackground", 0.0f, 0.0f, 0.0f, 0, false));
                    defaults.put("List[Disabled+Selected].textBackground",
                            laf.getDerivedColor("nimbusSelectionBackground", 0.0f, 0.0f, 0.0f, 0, false));
                    defaults.put("List[Disabled].textForeground",
                            laf.getDerivedColor("nimbusDisabledText", 0.0f, 0.0f, 0.0f, 0, false));
                    defaults.put("List:\"List.cellRenderer\"[Disabled].background",
                            laf.getDerivedColor("nimbusSelectionBackground", 0.0f, 0.0f, 0.0f, 0, false));

                    _logger.debug("El lookAndFeel fue cargado correctamente");
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            _logger.error("Error al cargar Nimbus lookAndFeel: " + ex.getMessage(), ex);
        }
    }

    private static void cargarLog4j() {
        Properties props = new Properties();
        try {
            props.load(Main.class.getResourceAsStream("/resources/log4j.properties"));
            PropertyConfigurator.configure(props);
        } catch (Exception ex) {
            BasicConfigurator.configure();
            _logger.error("Error al cargar archivo log4j.properties: " + ex.getMessage(), ex);
            _logger.error("Se inicializa log4j por defecto");
        }
    }

}
