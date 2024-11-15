package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;

import java.io.Serializable;


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
    }
}