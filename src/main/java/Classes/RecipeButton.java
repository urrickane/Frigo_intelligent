package Classes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class RecipeButton {
	private JLabel lblImageRecette;
	private JButton btnNomRecette;
	private JButton btnSuppr;
	private Recipe recipeClicked;
	
	/*
	 * Constructeur qui crée la structure Bouton+Label pour afficher et supprimer un allergène.
	 */
	public RecipeButton(Recipe recipe)
	{
		//Cr&ation d'un label et d'un bouton. Le premier va contenir l'image de la recette et le second va permettre de la sélectionner en appuyant dessus.		
		lblImageRecette = new JLabel();
		
		try {
            URL url = new URL(recipe.getLinkToImage());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            lblImageRecette.setIcon(imageIcon);
            lblImageRecette.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
		lblImageRecette.setPreferredSize(new Dimension(100, 100));
		lblImageRecette.setMaximumSize(new Dimension(100, 100));
		
		btnNomRecette = new JButton(recipe.getTitle());
		btnNomRecette.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNomRecette.setPreferredSize(new Dimension(500, 100));
		btnNomRecette.setMaximumSize(new Dimension(500, 100));
		
		btnSuppr = new JButton("X");
		btnSuppr.setFont(new Font("Calibri", Font.BOLD, 25));
		btnSuppr.setPreferredSize(new Dimension(100, 100));
		btnSuppr.setMaximumSize(new Dimension(100, 100));
		btnSuppr.setBackground(Color.RED);
		btnSuppr.setForeground(Color.WHITE);
		
		btnNomRecette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipeClicked = recipe;
			}
		});
		
		btnSuppr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Suppression du label du panel contenant les favoris.
            	Container parentComponent = lblImageRecette.getParent();
            	parentComponent.remove(lblImageRecette);
                
                //Suppression des boutons du panel contenant les favoris.
            	parentComponent = btnNomRecette.getParent();
            	parentComponent.remove(btnNomRecette);
            	parentComponent = btnSuppr.getParent();
            	parentComponent.remove(btnSuppr);
            	
            	//Actualisation de la fenêtre
            	parentComponent.revalidate();
                parentComponent.repaint();
                
                // *** Ajouter un moyen de supprimer la recette de la liste des favoris.
            }
        });
	}
	
	public JLabel getLblImageRecette() {
		return lblImageRecette;
	}
	
	public JButton getBtnNomRecette() {
		return btnNomRecette;
	}
	
	public JButton getBtnSuppr() {
		return btnSuppr;
	}
	
	public Recipe getRecipeClicked() {
		return recipeClicked;
	}
}
