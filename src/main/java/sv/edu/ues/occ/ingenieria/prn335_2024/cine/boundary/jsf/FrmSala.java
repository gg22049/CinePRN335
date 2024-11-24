package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.Message;
import org.primefaces.event.TabChangeEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class FrmSala extends AbstractFrm<Sala> implements Serializable {

    @Inject
    FacesContext fc;

    @Inject
    SalaBean bean;

    @Inject
    FrmSalaCaracteristica frmSalaCaracteristica;

    @Inject
    FrmAsiento frmAsiento;

    @Inject
    FrmProgramacion frmProgramacion;

    @Inject
    SucursalBean sBean;

    protected List<Sucursal> sucursalList;

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

    @Override
    public void instanciarRegistro() {
        this.registro = new Sala();
    }

    @Override
    public String getTituloPagina() {
        return Sala.class.getSimpleName().replaceAll("([a-z])([A-Z])", "$1 de $2");
    }

    @PostConstruct
    @Override
    public void init(){
        super.init();
        try {
            sucursalList = sBean.findRange(0, Integer.MAX_VALUE);
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al buscar las Sucursales");
            fc.addMessage(null, mensaje);
        }
    }

    @Override
    public void btnCancelarHandler(ActionEvent actionEvent) {
        super.btnCancelarHandler(actionEvent);
        frmAsiento.btnCancelarHandler(actionEvent);
        frmSalaCaracteristica.btnCancelarHandler(actionEvent);
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

    public List<Sucursal> getSucursalList() {
        return sucursalList;
    }

    public void setSucursalList(List<Sucursal> sucursalList) {
        this.sucursalList = sucursalList;
    }

    public Integer getIdSucursalSeleccionado() {
        if (this.registro!= null && this.registro.getIdSala()!=null) {
            return this.registro.getIdSucursal().getIdSucursal();
        }
        return 0;
    }

    public void setIdSucursalSeleccionado(Integer idSucursal) {
        if (idSucursal != null && idSucursal>0 && sucursalList!=null && !sucursalList.isEmpty()) {
            this.registro.setIdSucursal(this.sucursalList.stream().filter(r->r.getIdSucursal().equals(idSucursal)).findFirst().orElse(null));
        }
    }

    //Tab
    public void cambiarTab(TabChangeEvent tce){
        if (tce.getTab().getTitle().equals("Caracteristicas")){
            if(this.registro!=null && this.frmSalaCaracteristica !=null){
                this.frmSalaCaracteristica.setIdSala(this.registro.getIdSala().longValue());
            }
        } else if (tce.getTab().getTitle().equals("Asientos")){
            if(this.registro!=null && this.frmAsiento !=null){
                this.frmAsiento.setIdSala(this.registro.getIdSala().longValue());
            }
        }else if (tce.getTab().getTitle().equals("Programacion")){
            if(this.registro!=null && this.frmProgramacion !=null){
                this.frmProgramacion.setIdSala(this.registro.getIdSala().longValue());
            }
        }
    }
}