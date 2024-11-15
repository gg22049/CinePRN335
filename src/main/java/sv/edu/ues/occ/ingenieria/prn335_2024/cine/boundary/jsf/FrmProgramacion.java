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
import java.util.List;

@Named
@ViewScoped
public class FrmProgramacion extends AbstractFrm<Programacion> implements Serializable {

    @Inject
    ProgramacionBean bean;

    @Inject
    FacesContext fc;

    @Override
    public AbstractDataPersistence<Programacion> getDataPersist() {
        return this.bean;
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
     * Para traer las programaciones
     */
    private List<Programacion> programacionesDelDia;

    @PostConstruct
    public void init() {
        // Obtener los tipos de reserva únicos
        programacionesDelDia = bean.obtenerProgramacionesDelDia();
    }

    public List<Programacion> getProgramacionesDelDia() {
        return programacionesDelDia;
    }

    /**
     * Detalles de las pelicula del dia
     */
    /*
    private String sinopsisProgramacion;

    public String getSinopsisProgramacion() {
        // Aquí deberías obtener la sinopsis de la programación seleccionada.
        return sinopsisProgramacion;
    }

    public void setSinopsisProgramacion(String sinopsisProgramacion) {
        this.sinopsisProgramacion = sinopsisProgramacion;
    }*/


}
