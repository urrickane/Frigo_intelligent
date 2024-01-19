package fr.tse.fise2.info4.Classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private JComboBox cmbboxAjoutIngrJour;
	private JComboBox cmbboxAjoutIngrMois;
	private JComboBox cmbboxAjoutIngrAnnee;
	private JTextField txtboxAjoutIngrQte;

	private JComboBox cmbboxAjoutIngrUnites;

	private JLabel lblAjoutIngrErreur;

	public CreationPnlAjoutIngr(JDialog dialog,User user, boolean fridge)
	{

		pnlAjoutIngr = new JPanel();
		pnlAjoutIngr.setLayout(new MigLayout("", "[100px][100px][100px][100px][100px][100px]", "[50px][50px][50px][50px][50px][50px][50px][50px][50px][50px]"));

		JLabel lblAjoutIngrTitle = new JLabel("Entrez le nom d'un ingrédient :");
		lblAjoutIngrTitle.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrTitle, "cell 0 0 6 1,grow");

		txtboxAjoutIngrNom = new JTextField();
		txtboxAjoutIngrNom.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(txtboxAjoutIngrNom, "cell 0 1 6 1,grow");

		txtboxAjoutIngrNom.setColumns(10);
		if(fridge){
		JLabel lblAjoutIngrQte = new JLabel("Saisir la quantité :");
		lblAjoutIngrQte.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrQte, "flowx,cell 0 3 3 1,grow");

		cmbboxAjoutIngrUnites = new JComboBox();
		cmbboxAjoutIngrUnites.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(cmbboxAjoutIngrUnites, "cell 5 3,grow");
		// *** Ajouter toutes les unités possibles (g, kg, L, ...) à la combobox.
		cmbboxAjoutIngrUnites.setModel(new DefaultComboBoxModel(new String[] {"","cup", "tbsp", "tsp", "servings",  "lb", "oz", "gal", "qt", "pinch", "pt"}));

		JLabel lblAjoutIngrDLdC = new JLabel("Saisissez la date limite de consommation :");
		lblAjoutIngrDLdC.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrDLdC, "cell 0 5 6 1,grow");

		JLabel lblAjoutIngrJour = new JLabel("Jour :");
		lblAjoutIngrJour.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrJour, "cell 0 6,alignx trailing");

			cmbboxAjoutIngrJour = new JComboBox();
			cmbboxAjoutIngrJour.setFont(new Font("Calibri", Font.PLAIN, 30));
			pnlAjoutIngr.add(cmbboxAjoutIngrJour, "cell 1 6,grow");
			cmbboxAjoutIngrJour.setModel(new DefaultComboBoxModel(new String[] {"1","2", "3", "4", "5",  "6", "7", "8", "9", "10", "11", "12", "13", "14", "15","16","17", "18", "19", "20",  "21", "22", "23", "24", "25", "26", "27", "28", "29","30","31"}));

		JLabel lblAjoutIngrMois = new JLabel("Mois :");
		lblAjoutIngrMois.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrMois, "cell 2 6,alignx trailing");


		cmbboxAjoutIngrMois = new JComboBox();
		cmbboxAjoutIngrMois.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(cmbboxAjoutIngrMois, "cell 3 6,grow");
		cmbboxAjoutIngrMois.setModel(new DefaultComboBoxModel(new String[] {"1","2", "3", "4", "5",  "6", "7", "8", "9", "10", "11","12"}));

		JLabel lblAjoutIngrAnnee = new JLabel("Année :");
		lblAjoutIngrAnnee.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrAnnee, "cell 4 6,alignx trailing");

		cmbboxAjoutIngrAnnee = new JComboBox();
		cmbboxAjoutIngrAnnee.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(cmbboxAjoutIngrAnnee, "cell 5 6,grow");
		cmbboxAjoutIngrAnnee.setModel(new DefaultComboBoxModel(new String[] {"2024","2025","2026", "2027", "2028", "2029",  "2030", "2031", "2032", "2033", "2034", "2035"}));

		lblAjoutIngrErreur = new JLabel("");
		lblAjoutIngrErreur.setForeground(new Color(255, 0, 0));
		lblAjoutIngrErreur.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjoutIngrErreur.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrErreur, "cell 0 8 6 1");

		txtboxAjoutIngrQte = new JTextField();
		txtboxAjoutIngrQte.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(txtboxAjoutIngrQte, "cell 3 3 2 1,grow");
		txtboxAjoutIngrQte.setColumns(10);
		}

		JButton btnAjoutIngrRetour = new JButton("Retour");
		btnAjoutIngrRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanTxtbox(fridge);
				dialog.dispose();
			}
		});
		btnAjoutIngrRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAjoutIngr.add(btnAjoutIngrRetour, "cell 0 9 3 1,grow");

		JButton btnAjoutIngrValider = new JButton("Valider");
		btnAjoutIngrValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblAjoutIngrErreur.setText("");

				if(fridge){
					Date date = new Date();
					Date dlc = new Date(Integer.parseInt(cmbboxAjoutIngrAnnee.getSelectedItem().toString())-1900,Integer.parseInt(cmbboxAjoutIngrMois.getSelectedItem().toString())-1,Integer.parseInt(cmbboxAjoutIngrJour.getSelectedItem().toString()));
					if(txtboxAjoutIngrNom.getText().equals("") || txtboxAjoutIngrQte.getText().equals("") || cmbboxAjoutIngrUnites.getSelectedItem().toString().equals("")){
						lblAjoutIngrErreur.setText("Veuillez remplir tous les champs.");
						return;
					}
					else if(dlc.before(date)){
						lblAjoutIngrErreur.setText("Veuillez saisir une date valide.");
						return;
					}
					else if(Double.parseDouble(txtboxAjoutIngrQte.getText())<0){
						lblAjoutIngrErreur.setText("Veuillez saisir une quantité valide.");
						return;
					}

					user.AddIngredient(new Ingredient(dlc.getDay()+"-"+dlc.getMonth()+"-"+dlc.getYear(),txtboxAjoutIngrNom.getText(), Double.parseDouble(txtboxAjoutIngrQte.getText()), cmbboxAjoutIngrUnites.getSelectedItem().toString()));
				}
				else{
					user.addIngredientToShoppingList(new Ingredient("",txtboxAjoutIngrNom.getText(), 0.0, ""));
				}
				cleanTxtbox(fridge);
				dialog.dispose();
			}
		});
		btnAjoutIngrValider.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAjoutIngr.add(btnAjoutIngrValider, "cell 3 9 3 1,grow");

	}
	
	public JPanel getPnlAjoutIngr() {
		return pnlAjoutIngr;
	}
	
	public void cleanTxtbox(boolean fridge)
	{
		if(fridge){
			cmbboxAjoutIngrAnnee.setSelectedIndex(0);
			cmbboxAjoutIngrJour.setSelectedIndex(0);
			cmbboxAjoutIngrMois.setSelectedIndex(0);
			txtboxAjoutIngrNom.setText("");
			txtboxAjoutIngrQte.setText("");
		}
		else{
			txtboxAjoutIngrNom.setText("");
		}
	}


}
