package edu.ar.unq.qubobe.repairrequest.persistence;

import edu.ar.unq.qubobe.repairrequest.model.Budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetRegistersTransient implements BudgetRegisters {
    private final List<Budget> budgets;

    public BudgetRegistersTransient() {
        budgets = new ArrayList<>();
    }

    @Override
    public void add(Budget budget) {
        budgets.add(budget);
    }

    @Override
    public List<Budget> getAll() {
        return budgets;
    }
}
