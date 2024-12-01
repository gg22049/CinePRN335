package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.enterprise.context.Dependent;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@Dependent
public class FrmAsiento extends AbstractFrm<Asiento> implements Serializable {

    @Inject
    AsientoBean bean;

    @Inject
    FacesContext fc;

    @Inject
    FrmAsientoCaracteristica frmAsientoCaracteristica;

    Long idSala;

    @Override
    public AbstractDataPersistence<Asiento> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Asiento object) {
        if (object != null && object.getIdAsiento()!=null) {
            return object.getIdAsiento().toString();
        }
        return null;
    }

    @Override
    public Asiento getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdAsiento().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Asiento();
    }

    @Override
    public String getTituloPagina() {
        return Asiento.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    public FrmAsientoCaracteristica getFrmAsientoCaracteristica() {
        return frmAsientoCaracteristica;
    }

    @Override
    public int contar(){
        try {
            if (idSala!=null && bean!=null) {
                return bean.countAsiento(this.idSala);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    @Override
    public List<Asiento> cargarDatos(int findFrist, int findMax){
        try {
            if (idSala!=null && bean!=null) {
                return bean.caracteristicaSelected(this.idSala, findFrist, findMax);
            }
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return null;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }
}