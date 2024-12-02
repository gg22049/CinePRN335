package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProgramacionBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class FrmProgramacion extends AbstractFrm<Programacion> implements Serializable {

    @Inject
    ProgramacionBean prnbean;

    @Inject
    FacesContext fc;

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
    public void instanciarRegistro() {

    }

    @Override
    public String getTituloPagina() {
        return "";
    }

    /**
     * Esto sirve para la pantalla de Reserva
     * Para traer las programaciones
     */
    private List<Programacion> programacionesDelDia;

    @PostConstruct
    public void init() {
        //Iniciarlizar valores a usar
        programacionesDelDia = new ArrayList<>();
    }

    public void cargarProgramaciones(LocalDate fechaWizard){
        programacionesDelDia = prnbean.obtenerProgramacionesDelDia(fechaWizard.atStartOfDay().atOffset(ZoneOffset.UTC));
    }

    public List<Programacion> getProgramacionesDelDia() {
        return programacionesDelDia;
    }

}
