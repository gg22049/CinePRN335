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

/**
 *Generalizacion de los metodos para los frm
 * @param <T> parametro generico que se sustituye para utilizar la clase
 */
public abstract class AbstractFrm<T> implements Serializable {

    /**
     * Metodo abstracto para obtener bean de operaciones crud
     * @return AbstractDataPersistence<T> generico del que se opte
     */
    public abstract AbstractDataPersistence<T> getDataPersist();

    /**
     * Metodo abstracto para obtener un Faces Context para los mensajes
     * @return FacesContext
     */
    public abstract FacesContext getFacesContext();

    /**
     * Conversor de objeto a id en texto para el lazy data model
     * @param object objeto del que obtendra el id
     * @return String de la id obtenida
     */
    public abstract String getIdObjeto(T object);

    /**
     * Metodo para convertir el string del id a objeto
     * @param id en texto para obtener el objeto de devuelta
     * @return objeto recuperado
     */
    public abstract T getObjeto(String id);

    /**
     * Inicializa el registro para crear nuevos.
     */
    public abstract void instanciarRegistro();

    /**
     * Regresa el titulo para la pagina en base al nombre de la clase
     * @return regresa el titulo
     */
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
                int resultado=0;
                    try{
                        resultado = contar();
                    }catch(Exception e){
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

                    }
                return resultado;
            }

            @Override
            public List<T> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                List<T> resultado = null;
                    try {
                        resultado = cargarDatos(desde, max);
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

    /**
     * Metodo para obtener los registros de una table de la base de datos y sustentar el modelo
     * @param first Numero desde el cual inicia la recuperacion
     * @param max Numero hasta el cual llega la recuperacion
     * @return Retorna una lista de los registros obtenidos
     */
    public List<T> cargarDatos( int first, int max) {
        AbstractDataPersistence<T> dataBean = getDataPersist();
        try {
            return dataBean.findRange(first,max);
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al cargar los datos" + e.getMessage());
            fc.addMessage(null, mensaje);
        }
        return null;
    }

    /**
     * Metodo para obtener la cantidad de registros de una tabla de la base de datos y sustentar el modelo
     * @return Retorna un entero
     */
    public int contar(){
        AbstractDataPersistence<T> dataBean = getDataPersist();
    if (dataBean != null) {
        try {
            return dataBean.count();
        }catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Error al contar los registros" + e.getMessage());
            fc.addMessage(null, mensaje);
        }
    }
        return 0;
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
    /**
     * Metodo para asignar el estado de modificacion con el registro seleccionado
     */
    public void selecionarRegistro(){
        this.estado = ESTADO_CRUD.MODIFICAR;
    }

    /**
     * Metodo que da funcionalidad al boton para cancelar el estado de seleccion y volver registro nulo
     * @param actionEvent parametro que indica el uso del boton
     */
    public void btnCancelarHandler(ActionEvent actionEvent) {
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }

    /**
     * Metodo que da funcionalidad al boton que instancia el registro
     * @param actionEvent Parametro que indica el uso del boton
     */
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

    /**
     * Metodo que da funcionalidad al boton para persistir el registro instanciado
     * @param actionEvent Parametro que indica el uso del boton
     */
    public void btnGuardarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            try {
                AbstractDataPersistence<T> dataBean = getDataPersist();
                System.out.println(registro.getClass().toString());
                dataBean.create(registro);
                this.estado = ESTADO_CRUD.NINGUNO;
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

    /**
     * Metodo que da funcionalidad al boton para actualizar registros desde el formulario
     * @param actionEvent Parametro que indica el uso del boton
     */
    public void btnModificarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            try{
                AbstractDataPersistence<T> dataBean= getDataPersist();
                dataBean.update(registro);
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

    /**
     * Metodo que da funcionalidad al boton para eliminar un registro desde el formulario
     * @param actionEvent Parametro que indica el uso del boton
     */
    public void btnEliminarHandler(ActionEvent actionEvent) {
        if (registro != null) {
            FacesContext fc = getFacesContext();
            FacesMessage mensaje = new FacesMessage();
            try{
                AbstractDataPersistence<T> dataBean= getDataPersist();
                dataBean.delete(registro);
                this.estado = ESTADO_CRUD.NINGUNO;
                mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
                mensaje.setSummary("El registro se elimino exitosamente");
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
