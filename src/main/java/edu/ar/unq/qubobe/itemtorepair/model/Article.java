package edu.ar.unq.qubobe.itemtorepair;

import static edu.ar.unq.qubobe.extensions.ObjectValidations.assertIfNoneOrEmpty;

public class Article {
    public static final String NAME_CAN_NOT_BE_EMPTY = "El nombre no puede ser nulo o vacio";
    public static final String BRAND_CAN_NOT_BE_EMPTY = "La marca no puede ser nula o vacía";
    public static final String MODEL_CAN_NOT_BE_EMPTY = "El modelo no puede ser nulo o vacío";
    private final String name;
    private final String brand;
    private final String model;

    private Article(String name, String brand, String model) {
        this.name = name;
        this.brand = brand;
        this.model = model;
    }

    public static Article named(String name, String brand, String model) {
        assertIfNoneOrEmpty(name, NAME_CAN_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(brand, BRAND_CAN_NOT_BE_EMPTY);
        assertIfNoneOrEmpty(model, MODEL_CAN_NOT_BE_EMPTY);
        return new Article(name, brand, model);
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}
