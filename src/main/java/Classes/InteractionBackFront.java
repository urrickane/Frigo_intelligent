package Classes;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InteractionBackFront {
	public static void actualisationRecette(JLabel recipeImage, JLabel recipeTitle, JLabel recipeSummary, JLabel recipeTime, JPanel recipeIngredients, JPanel recipeSteps, Recipe recipe)
	{
		// Affichage de l'image
        try {
            URL url = new URL(recipe.getLinkToImage());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(500, 450, Image.SCALE_DEFAULT));
            recipeImage.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Affichage du titre
        recipeTitle.setText(recipe.getTitle());
        recipeTitle.setFont(new Font("Calibri", Font.BOLD, 40));
        
        // Affichage du résumé
        recipeSummary.setText("<html>" + recipe.getSummary() + "</html>");
        recipeSummary.setFont(new Font("Calibri", Font.PLAIN, 30));
        
        // Affichage du temps
        recipeTime.setText("Cooking time: " + Integer.toString(recipe.getCookingTime()) + " min");
        recipeTime.setFont(new Font("Calibri", Font.PLAIN, 30));
        
        // Affichage des ingrédients
        int i = 0;
        while(i < recipe.getL_ingredients().size())
        {
        	JLabel label = new JLabel();
        	label.setText("<html><div style='white-space: pre-wrap;'> -" + recipe.getL_ingredients().get(i).getName() + ": " + recipe.getL_ingredients().get(i).getQuantity() + " " + recipe.getL_ingredients().get(i).getUnit() + "</div></html>");
        	label.setFont(new Font("Calibri", Font.PLAIN, 30));
            recipeIngredients.add(label);
            i++;
        }
        
        // Affichage des étapes
        i = 0;
        while(i < recipe.getL_steps().size())
        {
        	JLabel label = new JLabel();
        	label.setText("<html><div style='white-space: pre-wrap;'>" + Integer.toString(i+1) + ". " + recipe.getL_steps().get(i) + "</div></html>");
        	label.setFont(new Font("Calibri", Font.PLAIN, 30));
            recipeSteps.add(label);
            i++;
        }
	}
}
