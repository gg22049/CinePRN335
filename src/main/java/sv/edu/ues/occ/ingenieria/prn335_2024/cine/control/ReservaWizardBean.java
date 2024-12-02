package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmAsiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmProgramacion;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf.FrmTipoReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Controlador de los pasos de ingresar la reserva
 */
@Named
@ViewScoped
public class ReservaWizardBean implements Serializable {

    /**
     * Inyección de Dependencias:
     * frReserva para crear una instancias de reserva usando el modelo mediante el botón del abstractfrm
     * frmTipoReserva para traer los TipoReserva disponibles
     * frmProgramación para traer las Programaciones disponibles según fechaReserva
     * frmAsiento para traer los asientos disponibles
     * FacesContext para poder mostrar mensajes al cliente
     */

    @Inject
    FrmReserva frmReserva;

    @Inject
    FrmTipoReserva frmTipoReserva;

    @Inject
    FrmProgramacion frmProgramacion;

    @Inject
    FrmAsiento frmAsiento;

    @Inject
    FacesContext fc;

    /**
     * Inicialización de depencias esenciales
     * createMinDate para obtener la fecha de hoy
     * frmReserva.btnNuevoHandler para crear un registro Reserva
     *
     */
    @PostConstruct
    public void init() {
        //fc = FacesContext.getCurrentInstance();
        //fc.getExternalContext().getFlash().setKeepMessages(true);
        createMinDate(); //Obtener fecha de hoy
        frmReserva.btnNuevoHandler(null);//Creamos el registro para instanciar una reserva
    }

    /**
     * Para obtener la fecha del día
     * Primefaces no soporta offsetDataTime así que se obtiene en LocalDate
     * Después se asigna en la entidad con un conversor
     * Cambiar el formato de fecha si se necesita mostrar
     */
    LocalDate fechaReservaSeleccionada;

    public LocalDate getFechaReservaSeleccionada() {
        return fechaReservaSeleccionada;
    }

    public void setFechaReservaSeleccionada(LocalDate fechaReservaSeleccionada) {
        this.fechaReservaSeleccionada = fechaReservaSeleccionada;
    }

    /**
     * Solo permitir reservar de hoy en adelante
     * minDate asigna la fecha de hoy
     * fechaReservaMinima para fechas especiales
     */
    private LocalDate fechaReservaMinima;

    //Obtener día de hoy
    private void createMinDate(){
        fechaReservaMinima = LocalDate.now(); // Asigna la fecha actual como la mínima
    }

    // Getters y Setters
    public LocalDate getFechaReservaMinima() {
        return fechaReservaMinima;
    }

    public void setFechaReservaMinima(LocalDate fechaReservaMinima) {
        this.fechaReservaMinima = fechaReservaMinima;
    }

    /**
     * Obtener id Tipo Reserva
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
     * Luego lo buscamos por id
     */
    private Long idProgramacionSeleccionada;

    public Long getIdProgramacionSeleccionada() {
        return idProgramacionSeleccionada;
    }

    public void setIdProgramacionSeleccionada(Long idProgramacionSeleccionada) {
        this.idProgramacionSeleccionada = idProgramacionSeleccionada;
    }

    /**
     * Obtener los Asientos seleccionados
     * Luego lo buscamos por id
     */
    private List<Long> asientosDisponibles = new ArrayList<>();
    private List<Long> asientosSeleccionados = new ArrayList<>();
    private DualListModel<Long> asientoDualListModel;

    public List<Long> getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(List<Long> asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public List<Long> getAsientosSeleccionados() {
        return asientosSeleccionados;
    }

    public void setAsientosSeleccionados(List<Long> asientosSeleccionados) {
        this.asientosSeleccionados = asientosSeleccionados;
    }

    public DualListModel<Long> getAsientoDualListModel() {
        return asientoDualListModel;
    }

    public void setAsientoDualListModel(DualListModel<Long> asientoDualListModel) {
        this.asientoDualListModel = asientoDualListModel;
    }

    /**
     * Lista del orden de pasos a seguir
     */
    private static final List<String> pasosOrdenados = Arrays.asList(
            "fechaStep", "funcionStep", "asientoStep", "confirmacionStep"
    );

    /**
     * Controlador de pasos:
     * @param event recibe el paso actual: event.getOldStep() y el siguiente: event.getNewStep()
     * pasosOrdenados permite evaluar en que dirección se está moviendo
     * @return después de validar se devuelve la indicación si se permite moverse o se debe quedar donde está
     */
    public String onFlowProcess(FlowEvent event) {
        String currentStep = event.getOldStep(); // Paso actual
        String nextStep = event.getNewStep();   // Paso al que el usuario intenta ir

        /**
         * Los pasos están ordenados en una lista, y la lógica de avanzar es:
         * 'fechaStep' -> 'funcionStep' -> 'asientosStep' -> 'confirmacionStep'
         */

        int currentStepIndex = pasosOrdenados.indexOf(currentStep);//Paso actual
        int nextStepIndex = pasosOrdenados.indexOf(nextStep);//Paso al que se desea ir

        System.out.println("Evaluando transición de paso: " + currentStep + " -> " + nextStep);

        if (currentStepIndex < nextStepIndex) {
            System.out.println("El usuario está avanzando del paso " + currentStep + " al paso " + nextStep);

            switch (currentStep) {
                //Paso 1 asignar la fecha
                case "fechaStep":

                    if (fechaReservaSeleccionada == null || fechaReservaSeleccionada.isBefore(fechaReservaMinima)) {
                        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Selecciona una fecha válida.", "");
                        FacesContext.getCurrentInstance().addMessage(null, mensaje);
                        return currentStep; // No avanzamos si la fecha no es válida
                    } else if (idTipoReservaSeleccionado==null || idTipoReservaSeleccionado<=0) {
                        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Selecciona una Tipo de Reserva válido.", "");
                        FacesContext.getCurrentInstance().addMessage(null, mensaje);
                        return currentStep; // No avanzamos si el idTipoReserva no es válido
                    }
                    //Bucamos las programaciones para el paso 2, sino hay no avanzamos
                    frmProgramacion.cargarProgramaciones(fechaReservaSeleccionada);

                    if (frmProgramacion.getProgramacionesDelDia().isEmpty()) {
                        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Selecciona un día con programaciones.", "");
                        FacesContext.getCurrentInstance().addMessage(null, mensaje);
                        return currentStep; // No avanzamos si no hay programaciones ese día
                    }

                    //Guardamos la fecha con el formato correcto
                    //--------------------------------------------------
                    frmReserva.getRegistro().setFechaReserva(fechaReservaSeleccionada.atStartOfDay().atOffset(ZoneOffset.UTC));

                    //Guardamos la entidad idTipoSala seleccionada
                    //--------------------------------------------------
                    frmReserva.getRegistro().setIdTipoReserva(frmTipoReserva.getDataPersist().findById(idTipoReservaSeleccionado));

                    //Debug
                    System.out.println("Se guardó correctamente:");
                    System.out.println("fechaReserva: " + frmReserva.getRegistro().getFechaReserva());
                    System.out.println("idTipoReserva: " + frmReserva.getRegistro().getIdTipoReserva().getNombre());

                    break;

                //Paso 2 asignar la programacion
                case "funcionStep":

                    if (idProgramacionSeleccionada==null || idProgramacionSeleccionada<=0){
                        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Selecciona una programacion válida.", "");
                        FacesContext.getCurrentInstance().addMessage(null, mensaje);
                        return currentStep; // No avanzamos si la fecha no es válida
                    }

                    //Guardamos la programación seleccionada
                    //--------------------------------------------------
                    frmReserva.getRegistro().setIdProgramacion(frmProgramacion.getDataPersist().findById(idProgramacionSeleccionada));

                    //Debug
                    System.out.println("Se guardó correctamente:");
                    System.out.println("idProgramación:" + frmReserva.getRegistro().getIdProgramacion().getIdProgramacion());

                    //Traer los asientos
                    frmAsiento.cargarAsientosDisponibles(frmReserva.getRegistro().getIdProgramacion().getIdSala().getIdSala());
                    asientosDisponibles = frmAsiento.getAsientosDisponiblesID();
                    for (Object obj : asientosDisponibles) {
                        System.out.println("Asiento: " + obj.getClass().getName());
                    }
                    System.out.println("Revisados los asientos en asientosDisponibles");
                    asientoDualListModel = new DualListModel<>(asientosDisponibles, asientosSeleccionados);
                    System.out.println("Array en la lista" + Arrays.toString(getAsientoDualListModel().getSource().toArray()));
                    System.out.println("añadidos al listdatamodel");


                    try {
                        for (Object obj : getAsientoDualListModel().getSource()) {
                            System.out.println(obj.getClass().getName());
                            System.out.println();
                        }
                        System.out.println("Revisados en el listdatamodel");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }


                    System.out.println("aD: " + asientosDisponibles);
                    System.out.println("aS: " + asientosSeleccionados);
                    try {
                        System.out.println("asientosD:" + asientosDisponibles.getClass());
                        System.out.println("asientosS:" + asientosSeleccionados.getClass());
                        System.out.println("primeraLista" + asientoDualListModel.getSource().getClass());
                        System.out.println("segundaLista" + asientoDualListModel.getTarget().getClass());
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Saliendo de Funcion!!");

                    break;
                //Paso 3
                case "asientoStep":
                    System.out.println("Entró en el stepAsientos");
                    try {
                        System.out.println("Asientos disponibles: " + asientoDualListModel.getSource().size());
                        System.out.println("Asientos seleccionados: " + asientoDualListModel.getTarget().size());

                        System.out.println("revisando asientos en asientoStep");
                        for (Object obj : getAsientoDualListModel().getSource()) {
                            System.out.println(obj.getClass().getName());
                            System.out.println();
                        }
                        System.out.println("Salió del for");
                        //System.out.println("afuera del for: " + getAsientoDualListModel().getSource().get(0).getClass().getName());
                        System.out.println("segundaLista: " + asientoDualListModel.getTarget().size());
                        //
                        if (getAsientoDualListModel().getTarget().isEmpty()) {
                            System.out.println("Entro en el if: No se seleccionaron asientos");
                            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error: seleccione al menos un asiento", "");
                            FacesContext.getCurrentInstance().addMessage(null, mensaje);

                            // Resetear el modelo de asientos

                            //FacesContext.getCurrentInstance().renderResponse(); // Asegura que no se siga el flujo de navegación
                            return currentStep; // No avanzamos si la lista está vacía
                        }

                        FacesMessage msg = new FacesMessage("Asientos agregados correctamente", "Asientos agregados correctamente");
                        FacesContext.getCurrentInstance().addMessage(null, msg);

                        System.out.println("Validación correcta");
                        System.out.println("Asientos que no se reservaron: " + asientoDualListModel.getSource());
                        System.out.println("Asientos reservados: " + asientoDualListModel.getTarget());

                        // Si se llega aquí, avanzamos al siguiente paso
                        return nextStep; // Aquí avanzamos, si la validación pasó
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                default:
                    return "fechaStep"; // Si no hay coincidencia, regresamos al primer paso
            }

        } else {
            // El usuario está retrocediendo al paso anterior
            System.out.println("El usuario está retrocediendo del paso " + currentStep + " al paso " + nextStep);
            return event.getNewStep(); // Permitir el retroceso sin validación
        }

        // Si no hay validaciones bloqueando, avanzamos al siguiente paso
        return nextStep;
    }

    /**
     * Botón del paso final en ConfimStep
     * Guardar el registro(Esto devería invocar al save de nuestro AbtractFrm)
     */
    public void save() {
        //Aquí la lógica de guardar
        FacesMessage msg = new FacesMessage("Successful", "Reserva creado correctamente. ID: ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Volviendo al paso uno:");
    }

}