package usecases;

import entities.WorkOrder;
import entities.Worker;
import persistence.WorkOrderDAO;
import persistence.WorkerDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class FastJobAssign implements JobAssigner{

    @Inject
    private WorkerDAO workerDAO;

    @Inject
    private WorkOrderDAO workOrderDAO;


    @Override
    @Transactional
    public void assignJob(int jobId, int workerId) {
        System.out.println("DEBUG: Fast assign");
        System.out.println(String.format("jobid: %s workder id %s", jobId, workerId));
        WorkOrder workOrder = workOrderDAO.findOne(jobId);
        Worker worker = workerDAO.findOne(workerId);
        System.out.println(String.format("%s | %s", worker.getName(), workOrder.getTitle()));
        worker.workOder.add(workOrder);
        System.out.println("DEBUG: update");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        workerDAO.update(worker);
    }
}
