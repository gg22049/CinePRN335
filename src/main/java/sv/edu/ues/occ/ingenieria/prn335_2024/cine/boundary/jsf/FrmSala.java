package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmSala extends AbstractFrm<Sala> implements Serializable {

    @Inject
    SalaBean bean;

    @Inject
    FacesContext fc;

    @Inject
    FrmSalaCaracteristica frmSalaCaracteristica;

    @Inject
    FrmAsiento frmAsiento;

    @Inject
    FrmProgramacion frmProgramacion;

    @Override
    public AbstractDataPersistence<Sala> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Sala object) {
        if (object != null && object.getIdSala()!=null) {
            return object.getIdSala().toString();
        }
        return null;
    }

    @Override
    public Sala getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdSala().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;    }

    /**
     * Para crear nueva sala
     */
    @Override
    public void instanciarRegistro() {
        this.registro = new Sala();
    }

    @Override
    public String getTituloPagina() {
        return Sala.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    public FrmSalaCaracteristica getFrmSalaCaracteristica() {
        return frmSalaCaracteristica;
    }

    public FrmProgramacion getFrmProgramacion() {
        return frmProgramacion;
    }

    public FrmAsiento getFrmAsiento() {
        return frmAsiento;
    }

    public void cambiarTab(TabChangeEvent tce){
        if (tce.getTab().getTitle().equals("Tipos")){
            if(this.registro!=null && this.frmSalaCaracteristica !=null){
                this.frmSalaCaracteristica.setIdSala(this.registro.getIdSala().longValue());
            }
        } else if (tce.getTab().getTitle().equals("Asientos")){
            if(this.registro!=null && this.frmAsiento !=null){
                this.frmAsiento.setIdSala(this.registro.getIdSala().longValue());
            }
        }else if (tce.getTab().getTitle().equals("Programacion")){
//            if(this.registro!=null && this.frmProgramacion !=null){
//                this.frmProgramacion.setIdSala(this.registro.getIdSala().longValue());
//            }
        }
    }
}