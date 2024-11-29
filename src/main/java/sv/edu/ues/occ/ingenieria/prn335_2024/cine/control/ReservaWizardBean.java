package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmProgramacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmTipoReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


/**
 * Controlador de los pasos de ingresar la reserva
 */
@Named
@ViewScoped
public class ReservaWizardBean implements Serializable {

    //@ManagedProperty(value = "#{frmReserva}")
    //private FrmReserva frmReserva;

    @Inject
    FrmReserva frmReserva;

    @Inject
    FrmTipoReserva frmTipoReserva;

    @Inject
    FrmProgramacion frmProgramacion;

    @Inject
    FacesContext fc;

    /**
     * Dato de ejemplo en el wizard
     */
    String textoEjemplo;

    public String getTextoEjemplo() {
        return textoEjemplo;
    }

    public void setTextoEjemplo(String textoEjemplo) {
        this.textoEjemplo = textoEjemplo;
    }

    /**
     * Para obtener la fecha del día
     * Primefaces no soporta offsetDataTime así que se obtiene en LocalDate
     * Después se asigna en la entidad con un conversor
     * Formato de fecha si se necesita mostrar
     */
    LocalDate fechaReservaSeleccionada;

    public LocalDate getFechaReservaSeleccionada() {
        return fechaReservaSeleccionada;
    }

    public void setFechaReservaSeleccionada(LocalDate fechaReservaSeleccionada) {
        this.fechaReservaSeleccionada = fechaReservaSeleccionada;
    }

    //No funciona
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");

    public DateTimeFormatter getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(DateTimeFormatter formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    /**
     * Para obtener id Tipo Reserva
     * Luego lo buscamos por id
     */

    private Integer idTipoReservaSeleccionado;

    public Integer getIdTipoReservaSeleccionado() {
        return idTipoReservaSeleccionado;
    }

    public void setIdTipoReservaSeleccionado(Integer idTipoReservaSeleccionado) {
        this.idTipoReservaSeleccionado = idTipoReservaSeleccionado;
    }

    /**
     * Obtener la Programación
     */
    private Long idProgramacionSeleccionada;

    public Long getIdProgramacionSeleccionada() {
        return idProgramacionSeleccionada;
    }

    public void setIdProgramacionSeleccionada(Long idProgramacionSeleccionada) {
        this.idProgramacionSeleccionada = idProgramacionSeleccionada;
    }

    /**
     * Asientos seleccionados
     */

    private List<Asiento> asientosSeleccionados;

    public List<Asiento> getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(List<Asiento> asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }

    /**
     * Controlador de pasos
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        //Paso 0 Iniciar el registro
        if ("Indicaciones".equals(event.getOldStep())) {
            try {
                //Creamos el registro para instanciar una reserva
                frmReserva.btnNuevoHandler(null);
                System.out.println("Registro instanciado");
            } catch (Exception e) {
                System.out.println("Error al crear registro: " + e.getMessage());
            }

        }else if ("fechaStep".equals(event.getOldStep())) {
            //Validaciones Paso 1
            if(fechaReservaSeleccionada != null && idTipoReservaSeleccionado!=null && idTipoReservaSeleccionado>0) {
                try {
                    //Guardamos la fecha con el formato correcto
                    System.out.println(idTipoReservaSeleccionado);
                    frmReserva.getRegistro().setFechaReserva(fechaReservaSeleccionada.atStartOfDay().atOffset(ZoneOffset.UTC));
                    System.out.println("fecha guardado: " + frmReserva.getRegistro().getFechaReserva());
                    //Guardamos la entidad idTipoSala seleccionada
                    frmReserva.getRegistro().setIdTipoReserva(frmTipoReserva.getDataPersist().findById(idTipoReservaSeleccionado));
                    System.out.println("idTipoReserva guardado: " + frmReserva.getRegistro().getIdTipoReserva().getNombre());
                    return event.getNewStep();
                } catch (Exception e) {
                    FacesContext fc = frmReserva.getFacesContext();
                    FacesMessage mensaje = new FacesMessage();
                    mensaje.setSeverity(FacesMessage.SEVERITY_FATAL);
                    mensaje.setSummary("Error al asignar: " + e.getMessage());
                    System.out.println("Error al guardar. " + e.getMessage());
                }
            } else {
                System.out.println("No se pueden guardar los campos:"+ fechaReservaSeleccionada + ", " + idTipoReservaSeleccionado);
                return event.getOldStep();
            }

        } else if ("funcionStep".equals(event.getOldStep())) {
            //Validaciones Paso 2
            if(idProgramacionSeleccionada!=null && idProgramacionSeleccionada>0) {
                try {
                    //Guardamos la programación seleccionada
                    frmReserva.getRegistro().setIdProgramacion(frmProgramacion.getDataPersist().findById(idProgramacionSeleccionada));
                    System.out.println("Programación guardada:" + frmReserva.getRegistro().getIdProgramacion().getIdProgramacion());
                    return event.getNewStep();
                } catch (Exception e) {
                    FacesContext fc = frmReserva.getFacesContext();
                    FacesMessage mensaje = new FacesMessage();
                    mensaje.setSeverity(FacesMessage.SEVERITY_FATAL);
                    mensaje.setSummary("Error al asignar: " + e.getMessage());
                }
            } else {
                System.out.println("No se pueden guardar los campos:" + idProgramacionSeleccionada);
                return event.getOldStep();
            }
        } else if ("asientosStep".equals(event.getOldStep())) {
            if(asientosSeleccionados != null && !asientosSeleccionados.isEmpty()) {


                return event.getNewStep();
            } else {
                System.out.println("No se pueden guardar los campos:" + asientosSeleccionados);
                return event.getOldStep();
            }

        }
        return event.getNewStep();
    }

    /**
     * Guardar
     */
    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Reserva creado correctamente. ID: ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

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
    /*
    public OffsetDateTime setIdTipoReserva() {
        String fecha = getMinDate().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        OffsetDateTime fechaHora = OffsetDateTime.parse(fecha, formatter);
        return fechaHora;
    }*/


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