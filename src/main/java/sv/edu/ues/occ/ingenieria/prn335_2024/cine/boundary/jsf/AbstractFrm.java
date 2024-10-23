package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractFrm<T> implements Serializable {

    //Metodos Abstractos
    public abstract AbstractDataPersistence<T> getDataPersist();
    public abstract FacesContext getFacesContext();
    public abstract String getIdObjeto(T object);
    public abstract T getObjeto(String id);
    public abstract void instanciarRegistro();
    public abstract String getTituloPagina();

    //Instancias
    protected LazyDataModel<T> modelo;
    protected T registro=null;
    protected ESTADO_CRUD estado = ESTADO_CRUD.NINGUNO;
    protected String titulo =  getTituloPagina();

    @PostConstruct
    public void init() {
        this.modelo = new LazyDataModel<T>() {

            @Override
            public int count(Map<String, FilterMeta> map) {
                AbstractDataPersistence<T> dataBean = getDataPersist();
                int resultado=0;
                if (dataBean != null) {
                    try{
                        resultado = dataBean.count();

                    }catch(Exception e){
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                }
                return resultado;
            }

            @Override
            public List<T> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                AbstractDataPersistence<T> dataBean = getDataPersist();
                List<T> resultado = null;
                    try {
                        resultado = dataBean.findRange(desde, max);
                    }catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        return List.of();
                    }
                return resultado;
            }

            @Override
            public String getRowKey(T object) {
                if (object != null) {
                    return getIdObjeto(object);
                }else{
                    return null;
                }
            }
            @Override
            public T getRowData(String rowKey) {
                if (rowKey != null) {
                    return getObjeto(rowKey);
                }
                return null;
            }
        };
    }

    //Getter & Setter
    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

//Botones CRUD v2
    public void selecionarRegistro(){
        this.estado = ESTADO_CRUD.MODIFICAR;
    }

    public void btnCancelarHandler(ActionEvent actionEvent) {
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }

    public void btnNuevoHandler(ActionEvent actionEvent) {
        try{
           this.instanciarRegistro();
           this.estado = ESTADO_CRUD.CREAR;
        }catch(Exception e) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al crear el nuevo registro" + e.getMessage());
            fc.addMessage(null, mensaje);
        }
    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            try {
                AbstractDataPersistence<T> dataBean = getDataPersist();
                dataBean.create(registro);
                this.registro = null;
                this.estado = ESTADO_CRUD.NINGUNO;
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                mensaje.setSummary("Registro guardado exitosamente");
                fc.addMessage(null, mensaje);
            }catch(Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al guardar el nuevo registro");
                fc.addMessage(null, mensaje);
            }
        }
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            try{
                AbstractDataPersistence<T> dataBean= getDataPersist();
                dataBean.update(registro);
                this.registro = null;
                this.estado = ESTADO_CRUD.NINGUNO;
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                mensaje.setSummary("El registro se actualizo exitosamente");
                fc.addMessage(null, mensaje);
            }catch(Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("El registro no debe ser nulo para modificar");
                fc.addMessage(null, mensaje);
            }
        }
    }

    public void btnEliminarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            try{
                AbstractDataPersistence<T> dataBean= getDataPersist();
                dataBean.delete(registro);
                this.registro = null;
                this.estado = ESTADO_CRUD.NINGUNO;
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                mensaje.setSummary("El registro se actualizo exitosamente");
                fc.addMessage(null, mensaje);
            }catch(Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("Error al eliminar el registro");
                fc.addMessage(null, mensaje);
            }
        }
    }

}
