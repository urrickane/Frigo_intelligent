package fr.tse.fise2.info4.Classes;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.tse.fise2.info4.Database;
import fr.tse.fise2.info4.Classes.*;
import fr.tse.fise2.info4.*;

public class InteractionBackFront {
	public static void actualisationRecette(JLabel recipeImage, JLabel recipeTitle, JLabel recipeTime, JPanel recipeIngredients, JPanel recipeSteps, Recipe recipe)
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
	
	public static void remplissagePanelConnexionComptes(JPanel pnl, List<User> comptes)
	{
		int i = 0;
		while(i < comptes.size())
		{
			JButton button = new JButton();
			button.setText(comptes.get(i).getName());
			button.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnl.add(button);
			i++;
		}
	}
	
	
	
	public static void remplissagePanelConnexionComptes2(JPanel pnl, List<String> comptes ,JPanel pnlConnexion,JPanel pnlAccueil)
	{
		int i = 0;
		while(i < comptes.size())
		{
			JButton button = new JButton();
			String name =comptes.get(i);
			button.setText(name);
			button.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnl.add(button);
			i++;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("L'utilisateur choisit est : "+name);
					pnlConnexion.setVisible(false);
					pnlAccueil.setVisible(true);
					
					
					//userSelected = Database.getUser(name);
				}
			});
		}
	}

	public static void remplissagePanelConnexionComptes3(JPanel pnl, List<String> comptes,
			JPanel pnlConnexion, JPanel pnlAccueil, AtomicReference<String> usernameRef) {
		// TODO Auto-generated method stub
		int i = 0;
		while(i < comptes.size())
		{
			JButton button = new JButton();
			String name =comptes.get(i);
			button.setText(name);
			button.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnl.add(button);
			i++;
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					System.out.println("L'utilisateur choisit est : "+name);
					//username=name;
					usernameRef.set(name);
					pnlConnexion.setVisible(false);
					pnlAccueil.setVisible(true);
					
					
					
					
					//userSelected = Database.getUser(name);
				}
			});
		}
		
	}
}
