package edu.ar.unq.qubobe.itemtorepair.api;

import ar.com.kfgodel.nary.api.optionals.Optional;

public class ItemToRepairTO {
    private String article;
    private String serialNumber;
    private ItemSecurityTO itemSecurity;

    public ItemToRepairTO(String article, String serialNumber, ItemSecurityTO itemSecurity) {
        this.article = article;
        this.serialNumber = serialNumber;
        this.itemSecurity = itemSecurity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Optional<ItemSecurityTO> getItemSecurity() {
        return Optional.ofNullable(itemSecurity);
    }

    public void setItemSecurity(ItemSecurityTO itemSecurity) {
        this.itemSecurity = itemSecurity;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
