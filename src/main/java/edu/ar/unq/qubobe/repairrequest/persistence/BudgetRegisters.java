package edu.ar.unq.qubobe.repairrequest.persistence;

import edu.ar.unq.qubobe.repairrequest.model.Budget;

import java.util.List;

public interface BudgetRegisters {
    void add(Budget budget);

    List<Budget> getAll();
}
