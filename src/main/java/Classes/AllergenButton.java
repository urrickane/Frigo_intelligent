package Classes;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllergenButton {
	private JLabel lblAllergen;
	private JButton btnSuppr;
	
	/*
	 * Constructeur qui crée la structure Bouton+Label pour afficher et supprimer un allergène.
	 */
	public AllergenButton(String allergenName)
	{
		//Cr&ation d'un label et d'un bouton. Le premier va contenir le nom de l'allergène et le second va permettre de le supprimer en appuyant dessus.
		lblAllergen = new JLabel();
		lblAllergen.setText(allergenName);
		lblAllergen.setPreferredSize(new Dimension(150, 50));
		lblAllergen.setMaximumSize(new Dimension(150, 100));
		lblAllergen.setFont(new Font("Calibri", Font.PLAIN, 30));
		
		btnSuppr = new JButton("X");
		btnSuppr.setFont(new Font("Calibri", Font.BOLD, 25));
		btnSuppr.setPreferredSize(new Dimension(50, 50));
		btnSuppr.setMaximumSize(new Dimension(50, 50));
		btnSuppr.setBackground(Color.RED);
		btnSuppr.setForeground(Color.WHITE);
		
		btnSuppr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Suppression du label du panel contenant les allergènes.
            	Container parentComponent = lblAllergen.getParent();
            	parentComponent.remove(lblAllergen);
                
                //Suppression du bouton du panel contenant les allergènes.
            	parentComponent = btnSuppr.getParent();
            	parentComponent.remove(btnSuppr);
            	
            	//Actualisation de la fenêtre
            	parentComponent.revalidate();
                parentComponent.repaint();
                
                // *** Ajouter un moyen de supprimer l'allergène de la liste via le champ Text de lblAllergen qui contient le nom de l'allergène.
            }
        });
	}
	
	public JLabel getLblAllergen() {
		return lblAllergen;
	}
	
	public JButton getBtnSuppr() {
		return btnSuppr;
	}
}
