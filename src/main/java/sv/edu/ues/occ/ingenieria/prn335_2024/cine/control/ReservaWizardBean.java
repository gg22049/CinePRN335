package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.primefaces.event.FlowEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.AbstractFrm;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Controlador de los pasos de ingresar la reserva
 */
@Named
@ViewScoped
public class ReservaWizardBean extends FrmReserva implements Serializable {

    /*
    @Inject
    ReservaBean bean;

    @Inject
    FacesContext fc;

    //private final Reserva reserva = new Reserva(); // Reserva actual que se está procesando

    @Override
    public AbstractDataPersistence<Reserva> getDataPersist() {
        return this.bean;
    }

    @Override
    public FacesContext getFacesContext() {
        return this.fc;
    }

    @Override
    public String getIdObjeto(Reserva object) {
        if (object != null && object.getIdReserva()!=null) {
            return object.getIdReserva().toString();
        }
        return null;
    }

    @Override
    public Reserva getObjeto(String id) {
        if (id!=null && this.modelo != null && this.modelo.getWrappedData() != null) {
            return this.modelo.getWrappedData().stream().filter(r->r.getIdReserva().toString().equals(id)).collect(Collectors.toList()).get(0);
        }
        return null;
    }

    @Override
    public void instanciarRegistro() {
        this.registro = new Reserva();
    }

    @Override
    public String getTituloPagina() {
        return "";
    }
    */

    // Getters y Setters
    /*
    public Reserva getReserva() {
        return reserva;
    }*/

    /**
     * Intentando añadir por defecto valores
     * @param reserva
     */
    /*
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        this.reserva.setEstado("CREADO");
        this.reserva.setFechaReserva(OffsetDateTime.parse("2024-09-19 20:26:00+00"));
    }*/

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Reserva creado correctamente. ID: ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    /**
     * Solo reserva de hoy en adelante
     */
    private Date fechaReservaMinima;
    private Date minDate;

    @PostConstruct
    public void init() {
        minDate = new Date(); // Asigna la fecha actual como la mínima
    }

    // Getters y Setters
    public Date getFechaReservaMinima() {
        return fechaReservaMinima;
    }

    public void setFechaReservaMinima(Date fechaReservaMinima) {
        this.fechaReservaMinima = fechaReservaMinima;
    }

    public Date getMinDate() {
        return minDate;
    }


    /**
     * Lo de henry
     * @param programacion
     * @param tipoReserva
     * @param estado
     * @param observaciones
     */
    /*
    public void crearReserva(Programacion programacion, TipoReserva tipoReserva,
                             String estado, String observaciones){

        Programacion pr = programacion;
        TipoReserva tipo = tipoReserva;
        Date fecha = getMinDate();
        String estadoFinal = estado;
        String observacionesFinal = observaciones;

        // Crear el EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CinePU");
        // Crear el EntityManager
        EntityManager em = emf.createEntityManager();
        // Iniciar una transacción
        em.getTransaction().begin();

        Reserva nuevaReserva = new Reserva();

        nuevaReserva.setIdProgramacion(programacion);
        nuevaReserva.setIdTipoReserva(tipoReserva);
        nuevaReserva.setFechaReserva(setIdTipoReserva());
        nuevaReserva.setEstado(estadoFinal);
        nuevaReserva.setObservaciones(observacionesFinal);

        // Persistir la entidad
        em.persist(nuevaReserva);
        // Confirmar la transacción
        em.getTransaction().commit();
        // Cerrar el
        em.close();
        // Cerrar el EntityManagerFactory
        emf.close();
    }
    */
    public OffsetDateTime setIdTipoReserva() {
        String fecha = getMinDate().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        OffsetDateTime fechaHora = OffsetDateTime.parse(fecha, formatter);
        return fechaHora;
    }


    /**
     * Esto debería poder guardar la fecha
     */
    /*
    private LocalDate fechaReserva; // Cambiar a LocalDate
    private List<Pelicula> peliculas; // Lista para mostrar las películas

    @Inject
    private PeliculaBean peliculaBean;

    // Getters y Setters
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }*/

    /**
     * Controlador de pasos
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
    /*
    // Método que controla el flujo entre pasos
    public String onFlowProcess(FlowEvent event) {
        // Validación de ejemplo: si el campo 'fechaReserva' es nulo, permanece en el paso actual
        if ("fecha".equals(event.getOldStep()) && reserva.getFechaReserva() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una fecha"));
            return event.getOldStep(); // No avanza al siguiente paso
        }
        // Si no hay problema, avanza al siguiente paso
        return event.getNewStep();
    }*/
}