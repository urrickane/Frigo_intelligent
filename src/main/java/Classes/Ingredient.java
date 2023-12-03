package Classes;

public class Ingredient {
	private String expDate;
	private String name;
	private boolean isAlergen;



    // Constructeur 
    public Ingredient(String expDate, String name, boolean isAlergen) {
        this.expDate = expDate;
        this.name = name;
        this.isAlergen = isAlergen;
    }

    // Méthodes getters
    public String getExpDate() {
        return expDate;
    }

    public String getName() {
        return name;
    }

    public boolean isAllergen() {
        return isAlergen;
    }

    // Méthodes setters
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAllergen(boolean isAlergen) {
        this.isAlergen = isAlergen;
    }
}
	

	

