package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * Controlador de los pasos de ingresar la reserva
 */
@Named
@ViewScoped
public class ReservaWizardBean implements Serializable {

    private Reserva reserva = new Reserva(); // Reserva actual que se está procesando

    // Getters y Setters
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Reserva creado correctamente. ID: " + reserva.getIdReserva());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Solo reserva de hoy en adelante
     */
    private Date fechaReservaMinima;
    private Date minDate;

    @PostConstruct
    public void init() {
        minDate = new Date(); // Asigna la fecha actual como la mínima
    }

    // Getters y Setters
    public Date getFechaReservaMinima() {
        return fechaReservaMinima;
    }

    public void setFechaReservaMinima(Date fechaReservaMinima) {
        this.fechaReservaMinima = fechaReservaMinima;
    }

    public Date getMinDate() {
        return minDate;
    }

    /**
     * Esto debería poder guardar la fecha
     */
    /*
    private LocalDate fechaReserva; // Cambiar a LocalDate
    private List<Pelicula> peliculas; // Lista para mostrar las películas

    @Inject
    private PeliculaBean peliculaBean;

    // Getters y Setters
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }*/

    /**
     * Controlador de pasos
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
    /*
    // Método que controla el flujo entre pasos
    public String onFlowProcess(FlowEvent event) {
        // Validación de ejemplo: si el campo 'fechaReserva' es nulo, permanece en el paso actual
        if ("fecha".equals(event.getOldStep()) && reserva.getFechaReserva() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una fecha"));
            return event.getOldStep(); // No avanza al siguiente paso
        }
        // Si no hay problema, avanza al siguiente paso
        return event.getNewStep();
    }*/
}