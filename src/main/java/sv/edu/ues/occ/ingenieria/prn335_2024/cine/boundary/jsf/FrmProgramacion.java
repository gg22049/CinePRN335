package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class FrmProgramacion implements Serializable {

    @Inject
    ProgramacionBean bean;

    @Inject
    FacesContext fc;

    protected Long idSala;
    protected Programacion registro;
    protected LazyScheduleModel scheduleModel;

    @PostConstruct
    public void init() {
        scheduleModel = new LazyScheduleModel() {
            @Override
            public void loadEvents(LocalDateTime start, LocalDateTime end) {
                try {
                    cargarEventos(start,end);
                }catch(Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    FacesMessage mensaje = new FacesMessage();
                    mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                    mensaje.setSummary("Error al cargar los datos" + e.getMessage());
                    fc.addMessage(null, mensaje);
                }
            }
        };
    }

    public void cargarEventos(LocalDateTime start, LocalDateTime end) {
        List<Programacion> eventos = bean.programacionList(idSala, Date.from(start.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(end.atZone(ZoneId.systemDefault()).toInstant()));

        for (Programacion e : eventos) {
            DefaultScheduleEvent event = new DefaultScheduleEvent().builder()
                    .title(e.getIdPelicula().getNombre())
                    .startDate(e.getDesde().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .endDate(e.getHasta().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                    .build();
            scheduleModel.addEvent(event);
        }
    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesMessage mensaje = new FacesMessage();
            try {
                bean.create(registro);
                this.registro = new Programacion();
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                mensaje.setSummary("Registro guardado exitosamente");
                fc.addMessage(null, mensaje);
            }catch(Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al guardar el nuevo registro xd");
                fc.addMessage(null, mensaje);
            }
        }
    }

    public void onDateSelect(SelectEvent event) {
        registro = (Programacion) event.getObject();
    }

    public LazyScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public Programacion getRegistro() {
        return registro;
    }

    public void setRegistro(Programacion registro) {
        this.registro = registro;
    }
}