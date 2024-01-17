package Classes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class IngredientButton {
	private JLabel lblIngredient;
	private JButton btnModif;
	private double Qte;
	private String Nom;
	private String Unite;
	
	/*
	 * Constructeur qui crée la structure Bouton+Label pour afficher et supprimer un allergène.
	 */
	public IngredientButton(Ingredient ingredient, JPanel pnlIngredientButton)
	{
		Qte = ingredient.getQuantity();
		Nom = ingredient.getName();
		Unite = ingredient.getUnit();
		
		//Cr&ation d'un label et d'un bouton. Le premier va contenir le nom de l'allergène et le second va permettre de le supprimer en appuyant dessus.
		lblIngredient = new JLabel();
		if(Unite != null)
		{
			lblIngredient.setText(Nom + " (" + Qte + " " + Unite + ")");
		}
		else
		{
			lblIngredient.setText(Nom + " (" + Qte + ")");
		}
		lblIngredient.setPreferredSize(new Dimension(500, 50));
		lblIngredient.setMaximumSize(new Dimension(500, 50));
		lblIngredient.setFont(new Font("Calibri", Font.PLAIN, 30));
		
		btnModif = new JButton("Modifier...");
		btnModif.setFont(new Font("Calibri", Font.BOLD, 25));
		btnModif.setPreferredSize(new Dimension(50, 50));
		btnModif.setMaximumSize(new Dimension(50, 50));
		btnModif.setBackground(Color.BLUE);
		btnModif.setForeground(Color.WHITE);
		
		btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Container parentComponent = new Container();
            	
            	// Afficher une boîte de texte
                String input = JOptionPane.showInputDialog(pnlIngredientButton, "Entrez la nouvelle quantité de " + Nom + ":");

                // Vérifier si l'utilisateur a cliqué sur OK ou Annuler
                if (input != null) {
                	modifierQte(Double.parseDouble(input));
                	
                	//Si la quantité est inférieure ou égale à 0, c'est qu'il n'y en a plus donc on supprime l'IngredientButton et l'ingrédient dans la BDD.
                	if(Qte <= 0)
                	{
                		//Suppression du label du panel contenant les allergènes.
                    	parentComponent = lblIngredient.getParent();
                    	parentComponent.remove(lblIngredient);
                        
                        //Suppression du bouton du panel contenant les allergènes.
                    	parentComponent = btnModif.getParent();
                    	parentComponent.remove(btnModif);
                    	
                    	// *** Ajouter un moyen de supprimer un ingredient dans la BDD.
                	}
                	//Sinon on actualise simplement l'affichage et on modifie l'ingrédient dans la BDD.
                	else
                	{
                		//Actualisation du label affichant l'ingrédient dans la liste.
                		actualiserIngredientButton();
                		
                		// *** Ajouter un moyen de modifier la quantité d'un ingredient dans la BDD.
                	}
                }
            	
            	//Actualisation de la fenêtre
            	parentComponent.revalidate();
                parentComponent.repaint();
            }
        });
	}
	
	public void actualiserIngredientButton() {
		if(Unite != null)
		{
			lblIngredient.setText(Nom + " (" + Qte + " " + Unite + ")");
		}
		else
		{
			lblIngredient.setText(Nom + " (" + Qte + ")");
		}
	}
	
	public void modifierQte(double newQte) {
		Qte = newQte;
	}
	
	public JLabel getLblIngredient() {
		return lblIngredient;
	}
	
	public JButton getBtnModif() {
		return btnModif;
	}
}
