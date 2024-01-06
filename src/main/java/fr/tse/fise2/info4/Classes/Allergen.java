package fr.tse.fise2.info4.Classes;

import java.util.Objects;

public class Allergen {
	private String aName;

	
	   // Constructeur 
    public Allergen(String aName) {
        this.aName = aName;
    }
    
    // Méthodes getters

    public String getaName() {
            return aName;
    }
 
    // Méthodes setters
    public void setaName(String aName) {
        this.aName = aName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allergen allergen = (Allergen) o;
        return Objects.equals(aName, allergen.aName);
    }

}
