package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AbstractFrm<T> implements Serializable {

    @Inject
    FacesContext facesContext;

    protected LazyDataModel<T> modelo;
    protected T registro;
    protected ESTADO_CRUD estado;

    public abstract int getCount();
    public abstract List<T> llenarModelo(int desde,int max);
    public abstract String obtenerID(T objeto);

    @PostConstruct
    public void inicializar() {
        FacesMessage mensaje = new FacesMessage();
        modelo = new LazyDataModel<T>() {

            @Override
            public int count(Map<String, FilterMeta> map) {
                return getCount();
            }

            @Override
            public List<T> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                if (desde >=0 || max >=desde) {
                    return llenarModelo(desde, max);
                }else {
                    mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                    mensaje.setSummary("No se pudo llenar los registros");
                    facesContext.addMessage(null, mensaje);
                    return List.of();
                }
            }

            @Override
            public String getRowKey(T object) {
                if (object != null && obtenerID(object) != null) {
                    return obtenerID(object);
                }else{
                    return null;
                }
            }
            @Override
            public T getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    return getWrappedData().stream().filter(r->rowKey.equals(obtenerID(r))).findFirst().orElse(null);
                }else{
                    mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                    mensaje.setSummary("Error al obtener la rowData");
                }
                return null;
            }
        };
    }

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

//Botones CRUD

    public abstract void createNew();
    public abstract void saveNew();
    public abstract void updateNew();
    public abstract void deleteNew();

    public void btnNuevoHandler(ActionEvent actionEvent) {
        try{
            createNew();
            estado = ESTADO_CRUD.CREAR;
        }catch(Exception e) {
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al crear el nuevo registro" + e.getMessage());
            facesContext.addMessage(null, mensaje);
        }
    }

    public void btnGuardarHandler(ActionEvent actionEvent) {
    if (registro != null) {
        try {
            saveNew();
            registro = null;
            this.estado = ESTADO_CRUD.NINGUNO;
        }catch(Exception e) {
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al guardar el nuevo registro" + e.getMessage());
            facesContext.addMessage(null, mensaje);
        }
    }
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            try{
                updateNew();
                registro = null;
                this.estado = ESTADO_CRUD.NINGUNO;
            }catch(Exception e) {
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("El registro no debe ser nulo para modificar" + e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }
    }
    public void btnEliminarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            try{
                deleteNew();
                registro = null;
                this.estado = ESTADO_CRUD.NINGUNO;
            }catch(Exception e) {
                FacesMessage mensaje = new FacesMessage();
                mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                mensaje.setSummary("El registro no debe ser nulo para eliminar" + e.getMessage());
                facesContext.addMessage(null, mensaje);
            }
        }
    }

    public void btnCancelarHandler(ActionEvent actionEvent) {
        registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }
}
