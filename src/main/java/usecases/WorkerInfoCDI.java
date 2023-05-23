package usecases;

import entities.Equipment;
import entities.WorkOrder;
import entities.Worker;
import lombok.Getter;
import lombok.Setter;
import persistence.EquipmentDAO;
import persistence.WorkOrderDAO;
import persistence.WorkerDAO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Named
@RequestScoped
public class WorkerInfoCDI {

    @Inject
    private WorkerDAO workerDAO;

    @Inject
    private EquipmentDAO equipmentDAO;

    @Inject
    private WorkOrderDAO workOrderDAO;

    @Getter
    private Worker worker;

    @Inject
    private JobAssigner jobAssigner;

    @Getter @Setter
    private String input;

    @Getter @Setter
    private String equipmentId;

    @Getter @Setter
    private String workId;

    public void getSpecificWorker() {
        try {
            Integer id = Integer.parseInt(input);
            worker = this.workerDAO.findOne(id);

        } catch (Exception ex) {
            System.out.println("Could not parse integer");
        }
    }


    @Transactional
    public void assignTools() {
        try {
            Integer toolId = Integer.parseInt(equipmentId);
            Equipment eq = this.equipmentDAO.findOne(toolId);
            Integer id = Integer.parseInt(input);
            worker = this.workerDAO.findOne(id);

            System.out.println("DBG: " + worker.EquipmentAccess.toString());
            System.out.println("DBG " + eq);
            if (eq != null) {
                worker.EquipmentAccess.add(eq);
                this.workerDAO.update(worker);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

//    @Transactional
//    public void assignOrders() {
//        try {
//            Integer orderId = Integer.parseInt(workId);
//            Integer workderId = Integer.parseInt(input);
//
//            System.out.println(String.format("DEBUG: Order: %s worker: %s"
//            ,orderId, workderId));
//
//
//            return CompletableFuture.supplyAsync(() -> jobAssigner.assignJob(orderId, workderId));
////            jobAssigner.assignJob(orderId, workderId);
//
//        } catch (Exception ex) {
//            System.out.println("Error: " + ex.getMessage());
//        }
//
//    }

    @Transactional
    public void assignOrders() {
        try {
            Integer orderId = Integer.parseInt(workId);
            Integer workerId = Integer.parseInt(input);

            System.out.println(String.format("DEBUG: Order: %s worker: %s", orderId, workerId));

            CompletableFuture.runAsync(() -> jobAssigner.assignJob(orderId, workerId))
                    .thenAcceptAsync(result -> {
                    });
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
