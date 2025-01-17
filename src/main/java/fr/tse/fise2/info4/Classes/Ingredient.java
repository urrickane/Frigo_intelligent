package fr.tse.fise2.info4.Classes;

import java.util.Objects;

public class Ingredient {
	private String expDate;
	private String name;

    private Number quantity;

    private String unit;




    // Constructeur
    public Ingredient(String expDate, String name, Double quantity, String unit) {
        this.expDate = expDate;
        this.name = name;
        this.quantity = Objects.requireNonNullElse(quantity, 0.0);
        this.unit = unit;
    }

    // Méthodes getters
    public String getExpDate() {
        return expDate;
    }

    public String getName() {
        return name;
    }

    // Méthodes setters
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return (double) quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(expDate, that.expDate) && Objects.equals(name, that.name) && Objects.equals(quantity, that.quantity) && Objects.equals(unit, that.unit);
    }

}
	

	

