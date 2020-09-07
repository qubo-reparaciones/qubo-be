package edu.ar.unq.qubobe.itemtorepair;

import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertIfNone;
import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertIfNoneOrEmpty;

public class ItemToRepair {
    public static final String ARTICLE_NOT_BE_EMPTY = "Debe tener un artículo";
    public static final String SERIAL_NUMBER_NOT_BE_EMPTY = "El número de serie no puede ser nulo o vacío";
    private final Article article;
    private final String serialNumber;

    private ItemToRepair(Article article, String serialNumber) {
        this.article = article;
        this.serialNumber = serialNumber;
    }

    public static ItemToRepair create(Article article, String nroserie) {
        assertIfNone(article, ARTICLE_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(nroserie, SERIAL_NUMBER_NOT_BE_EMPTY);
        return new ItemToRepair(article, nroserie);
    }

    public Article getArticle() {
        return article;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
