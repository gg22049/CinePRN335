package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named("frmTipoSala")
@ViewScoped
public class FrmTipoSala implements Serializable {
    @Inject
    TipoSalaBean tsBean;

    @Inject
    FacesContext facesContext;

    List<TipoSala> registros;

    ESTADO_CRUD estado;

    TipoSala registro;

    @PostConstruct
    public void init() {
        estado=ESTADO_CRUD.NINGUNO;
        if (tsBean != null){
            try {
                registros = tsBean.findByRange(0,1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<TipoSala> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoSala> registros) {
        this.registros = registros;
    }


    public TipoSala getRegistro() {return registro;}

    public void setRegistro(TipoSala registro) {this.registro = registro;}

    public Integer getSeleccionadp (){
        if (registro == null) {return null;}
        return registro.getIdTipoSala();
    }

    public void setSeleccionado(Integer seleccionado) {
        this.registro = this.registros.stream().filter(r->r.getIdTipoSala() == seleccionado).collect(Collectors.toList()).getFirst();
        if (registro == null) {this.estado=ESTADO_CRUD.MODIFICAR;}
            }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

            //BOTONES
    public void btnSeleccionarRegistroHandler(final Integer idTipoSala) {
        if(idTipoSala!=null) {
            this.registro = this.registros.stream().filter(r->idTipoSala.equals(r.getIdTipoSala())).findFirst().orElse(null);
            this.estado=ESTADO_CRUD.MODIFICAR;
            return;
        }
        this.registro = null;
    }

    public void btnCancelarHandler() {
        this.estado=ESTADO_CRUD.NINGUNO;
        this.registro = null;
    }

    public void btnNuevoHandler() {
        this.registro = new TipoSala();
        registro.setActivo(true);
        registro.setExpresionRegular(".");
        this.estado=ESTADO_CRUD.CREAR;
    }

    public void btnGuardarHandler() {
//        if (registro != null) {
//            try {
//                this.tsBean.create(registro);
//                this.estado=ESTADO_CRUD.NINGUNO;
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        this.registro = null;
//        this.registros = tsBean.findByRange(0,1000);
        this.tsBean.create(registro);
        this.registro = null;
    }

    public void btnModificarHandler() {
        TipoSala actualizado = tsBean.update(registro);
        FacesMessage mensaje = new FacesMessage();
        if (actualizado != null) {
            this.registro = null;
            this.estado=ESTADO_CRUD.NINGUNO;
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Actualizado exitosamente");
        }else{
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al actualizar");
        }
        facesContext.addMessage(null, mensaje);
    }

    public void btnEliminarHandler(final Integer idTipoSala) {
        FacesMessage mensaje = new FacesMessage();
        try {
            tsBean.delete(idTipoSala);
            this.registro = null;
            this.estado=ESTADO_CRUD.MODIFICAR;
            this.registros = tsBean.findByRange(0,1000);
            mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
            mensaje.setSummary("Eliminado exitosamente");
        }catch (Exception e){
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al eliminar "+e.getMessage());
        }
        facesContext.addMessage(null, mensaje);
    }
}