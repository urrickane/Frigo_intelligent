package fr.tse.fise2.info4.Classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Front.Interface;
import net.miginfocom.swing.MigLayout;

public class CreationPnlAjoutIngr {
	private JPanel pnlAjoutIngr;
	private JTextField txtboxAjoutIngrNom;
	private JTextField txtboxAjoutIngrJour;
	private JTextField txtboxAjoutIngrMois;
	private JTextField txtboxAjoutIngrAnnee;
	private JTextField txtboxAjoutIngrQte;
	
	public CreationPnlAjoutIngr(JDialog dialog)
	{
		try {
			List<String> ingredients = readIngredients("./src/main/resources/Ing.txt");

			pnlAjoutIngr = new JPanel();
			pnlAjoutIngr.setLayout(new MigLayout("", "[100px][100px][100px][100px][100px][100px]", "[50px][50px][50px][50px][50px][50px][50px][50px][50px][50px]"));

			JLabel lblAjoutIngrTitle = new JLabel("Entrez le nom d'un ingrédient :");
			lblAjoutIngrTitle.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrTitle, "cell 0 0 6 1,grow");

			txtboxAjoutIngrNom = new JTextField();
			txtboxAjoutIngrNom.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(txtboxAjoutIngrNom, "cell 0 1 6 1,grow");
			AutoCompletion.setupAutoComplete(ingredients,txtboxAjoutIngrNom );

			txtboxAjoutIngrNom.setColumns(10);

			JLabel lblAjoutIngrQte = new JLabel("Saisir la quantité :");
			lblAjoutIngrQte.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrQte, "flowx,cell 0 3 3 1,grow");

			JComboBox cmbboxAjoutIngrUnites = new JComboBox();
			cmbboxAjoutIngrUnites.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(cmbboxAjoutIngrUnites, "cell 5 3,grow");
			// *** Ajouter toutes les unités possibles (g, kg, L, ...) à la combobox.
			cmbboxAjoutIngrUnites.setModel(new DefaultComboBoxModel(new String[] {"g", "kg", "L"}));

			JLabel lblAjoutIngrDLdC = new JLabel("Saisissez la date limite de consommation :");
			lblAjoutIngrDLdC.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrDLdC, "cell 0 5 6 1,grow");

			JLabel lblAjoutIngrJour = new JLabel("Jour :");
			lblAjoutIngrJour.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrJour, "cell 0 6,alignx trailing");

			txtboxAjoutIngrJour = new JTextField();
			txtboxAjoutIngrJour.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(txtboxAjoutIngrJour, "cell 1 6,grow");
			txtboxAjoutIngrJour.setColumns(10);

			JLabel lblAjoutIngrMois = new JLabel("Mois :");
			lblAjoutIngrMois.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrMois, "cell 2 6,alignx trailing");

			txtboxAjoutIngrMois = new JTextField();
			txtboxAjoutIngrMois.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(txtboxAjoutIngrMois, "cell 3 6,grow");
			txtboxAjoutIngrMois.setColumns(10);

			JLabel lblAjoutIngrAnnee = new JLabel("Année :");
			lblAjoutIngrAnnee.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrAnnee, "cell 4 6,alignx trailing");

			txtboxAjoutIngrAnnee = new JTextField();
			txtboxAjoutIngrAnnee.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(txtboxAjoutIngrAnnee, "cell 5 6,grow");
			txtboxAjoutIngrAnnee.setColumns(10);

			JLabel lblAjoutIngrErreur = new JLabel("");
			lblAjoutIngrErreur.setForeground(new Color(255, 0, 0));
			lblAjoutIngrErreur.setHorizontalAlignment(SwingConstants.CENTER);
			lblAjoutIngrErreur.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(lblAjoutIngrErreur, "cell 0 8 6 1");

			txtboxAjoutIngrQte = new JTextField();
			txtboxAjoutIngrQte.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(txtboxAjoutIngrQte, "cell 3 3 2 1,grow");
			txtboxAjoutIngrQte.setColumns(10);

			JButton btnAjoutIngrRetour = new JButton("Retour");
			btnAjoutIngrRetour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cleanTxtbox();
					dialog.dispose();
				}
			});
			btnAjoutIngrRetour.setFont(new Font("Calibri", Font.BOLD, 30));
			pnlAjoutIngr.add(btnAjoutIngrRetour, "cell 0 9 3 1,grow");

			JButton btnAjoutIngrValider = new JButton("Valider");
			btnAjoutIngrValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// *** Ajouter une méthode pour ajouter l'ingrédient à la bonne liste (liste de course ou du frigo).
					cleanTxtbox();
					dialog.dispose();
				}
			});
			btnAjoutIngrValider.setFont(new Font("Calibri", Font.BOLD, 30));
			pnlAjoutIngr.add(btnAjoutIngrValider, "cell 3 9 3 1,grow");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public JPanel getPnlAjoutIngr() {
		return pnlAjoutIngr;
	}
	
	public void cleanTxtbox()
	{
		txtboxAjoutIngrAnnee.setText("");
		txtboxAjoutIngrJour.setText("");
		txtboxAjoutIngrMois.setText("");
		txtboxAjoutIngrNom.setText("");
		txtboxAjoutIngrQte.setText("");
	}

	private static List<String> readIngredients(String filePath) throws IOException {
		List<String> ingredients = new ArrayList<>();



		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Ajoutez chaque ligne à la liste
				ingredients.add(line.trim());
			}
		}

		return ingredients;
	}
}
