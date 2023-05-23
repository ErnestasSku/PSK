package usecases;

import entities.WorkOrder;
import entities.Worker;
import persistence.WorkOrderDAO;
import persistence.WorkerDAO;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Alternative
@RequestScoped
public class ComplexJobAssign implements JobAssigner{

    @Inject
    private WorkerDAO workerDAO;

    @Inject
    private WorkOrderDAO workOrderDAO;

    @Override
    @Transactional
    public void assignJob(int jobId, int workId) {
        System.out.println("DEBUG: Complex assign");
        WorkOrder workOrder = workOrderDAO.findOne(jobId);
        Worker worker = workerDAO.findOne(workId);
        worker.workOder.add(workOrder);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }

        workerDAO.update(worker);
    }
}
