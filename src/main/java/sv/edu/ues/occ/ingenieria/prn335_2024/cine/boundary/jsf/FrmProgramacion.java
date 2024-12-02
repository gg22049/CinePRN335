package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@Dependent
public class FrmProgramacion extends AbstractFrm<Programacion> implements Serializable {

    //Injecciones
    @Inject
    ProgramacionBean prnbean;

    @Inject
    FacesContext fc;

    //Instancias
    protected Sala salaSeleccionada;
    protected ScheduleModel programacionModel;
    protected List<Programacion> programaciones;
    private List<Programacion> programacionesDelDia;

    //Metodos Abstractos
    @Override
    @PostConstruct
    public void init() {
        super.init();
        programacionesDelDia = bean.obtenerProgramacionesDelDia();
    }

    @Override
    public AbstractDataPersistence<Programacion> getDataPersist() {
        return this.prnbean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Programacion object) {
        return "";
    }

    @Override
    public Programacion getObjeto(String id) {
        return null;
    }

    @Override
    public void instanciarRegistro() {}

    @Override
    public String getTituloPagina() {
        return "";
    }

    //Metodos Propios
    public void cargarProgramacionBySala() {
        if (salaSeleccionada != null && bean!=null) {
            try{
                programaciones = bean.programacionesByIdSala(salaSeleccionada.getIdSala());
            }catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
            }
        }
    }

    public void cargarProgramaciones() {
        cargarProgramacionBySala();
        if (programaciones!=null && programaciones.size()>0) {
            for (Programacion p : programaciones) {
                DefaultScheduleEvent<?> dse = DefaultScheduleEvent.builder()
                        .title(p.getIdPelicula().getNombre())
                        .startDate(p.getDesde().toLocalDateTime())
                        .endDate(p.getHasta().toLocalDateTime())
                        .build();
            }
        }
    }

    public void cargarProgramaciones(LocalDate fechaWizard){
        programacionesDelDia = prnbean.obtenerProgramacionesDelDia(fechaWizard.atStartOfDay().atOffset(ZoneOffset.UTC));
    }

    //Getter && Setter
    public List<Programacion> getProgramacionesDelDia() {
        return programacionesDelDia;
    }

    public Sala getSalaSeleccionada() {
        return salaSeleccionada;
    }

    public void setSalaSeleccionada(Sala salaSeleccionada) {
        this.salaSeleccionada = salaSeleccionada;
    }

    public ScheduleModel getProgramacionModel() {
        return programacionModel;
    }

    public void setProgramacionModel(ScheduleModel programacionModel) {
        this.programacionModel = programacionModel;
    }
}
