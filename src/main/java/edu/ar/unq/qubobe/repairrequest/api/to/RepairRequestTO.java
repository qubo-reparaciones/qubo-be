package edu.ar.unq.qubobe.repairrequest.api.to;

import edu.ar.unq.qubobe.itemtorepair.api.ItemToRepairTO;

public class RepairRequestTO {
    private String customer; //acá va el nombre por ahora, pero luego id y lo mismo el resto
    private ItemToRepairTO itemToRepair; // lo mismo, aca iria id, pero va nombre
    private RepairRequestCreationInfoTO creationInfo;

    public RepairRequestTO(String customer, ItemToRepairTO itemToRepairTO, RepairRequestCreationInfoTO creationInfoTO) {
        this.customer = customer;
        this.itemToRepair = itemToRepairTO;
        this.creationInfo = creationInfoTO;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public RepairRequestCreationInfoTO getCreationInfo() {
        return creationInfo;
    }

    public void setCreationInfo(RepairRequestCreationInfoTO creationInfo) {
        this.creationInfo = creationInfo;
    }

    public ItemToRepairTO getItemToRepair() {
        return itemToRepair;
    }

    public void setItemToRepair(ItemToRepairTO itemToRepair) {
        this.itemToRepair = itemToRepair;
    }
}
