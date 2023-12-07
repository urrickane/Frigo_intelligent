import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.GridLayout;

public class Prototype1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtInscriptionUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype1 frame = new Prototype1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Variables
	
	String username;
	String password;
	private JTextField txtConnexionUsername;
	private JPasswordField pswdConnexion;
	private JPasswordField pswdInscription;
	private JPasswordField pswdInscriptionConfirm;

	/**
	 * Create the frame.
	 */
	public Prototype1() {
		setResizable(false);
		setMinimumSize(new Dimension(1920, 1060));
		getContentPane().setMinimumSize(new Dimension(1920, 1080));
		getContentPane().setLayout(null);
		
		JPanel pnlAllergenes = new JPanel();
		pnlAllergenes.setBounds(0, 0, 1920, 1080);
		getContentPane().add(pnlAllergenes);
		pnlAllergenes.setLayout(null);
		
		JPanel pnlAllergenesTitle = new JPanel();
		pnlAllergenesTitle.setBounds(0, 0, 1920, 100);
		pnlAllergenes.add(pnlAllergenesTitle);
		pnlAllergenesTitle.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vos allergènes :");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setBounds(100, 20, 1820, 50);
		pnlAllergenesTitle.add(lblNewLabel);
		
		JPanel pnlAllergenesButtonsAllergenes = new JPanel();
		pnlAllergenesButtonsAllergenes.setBounds(0, 100, 1920, 730);
		pnlAllergenes.add(pnlAllergenesButtonsAllergenes);
		pnlAllergenesButtonsAllergenes.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel pnlAllergenesRetourAdd = new JPanel();
		pnlAllergenesRetourAdd.setBounds(0, 830, 1920, 200);
		pnlAllergenes.add(pnlAllergenesRetourAdd);
		pnlAllergenesRetourAdd.setLayout(null);
		
		JButton btnAllergenesRetour = new JButton("Retour");
		btnAllergenesRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAllergenesRetour.setBounds(100, 25, 300, 150);
		pnlAllergenesRetourAdd.add(btnAllergenesRetour);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(600, 89, 100, 100);
		pnlAllergenesRetourAdd.add(btnNewButton);
		
		//Panels
		
		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(1850, 0, 1920, 1080);
		getContentPane().add(pnlMain);
		pnlMain.setLayout(null);
				
		JPanel pnlInscription = new JPanel();
		pnlInscription.setVisible(false);
		pnlInscription.setBounds(1850, 20, 1920, 1080);
		getContentPane().add(pnlInscription);
		pnlInscription.setLayout(null);
		
		JPanel pnlConnexion = new JPanel();
		pnlConnexion.setVisible(false);
		pnlConnexion.setBounds(1850, 40, 1920, 1080);
		getContentPane().add(pnlConnexion);
		pnlConnexion.setLayout(null);
		
		JPanel pnlAccueil = new JPanel();
		pnlAccueil.setVisible(false);
		pnlAccueil.setBounds(1850, 60, 1920, 1080);
		getContentPane().add(pnlAccueil);
		pnlAccueil.setLayout(null);
		
		//Panel Main (1850;0)
		
		JButton btnMainInscription = new JButton("Inscription");
		btnMainInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlInscription.setLocation(0, 0);
				pnlMain.setVisible(false);
				pnlInscription.setVisible(true);
			}
		});
		btnMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		btnMainInscription.setBounds(510, 550, 300, 150);
		pnlMain.add(btnMainInscription);
				
		JButton btnMainConnexion = new JButton("Connexion");
		btnMainConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlConnexion.setLocation(0,0);
				pnlConnexion.setVisible(true);
				pnlMain.setVisible(false);
			}
		});
		btnMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		btnMainConnexion.setBounds(1110, 550, 300, 150);
		pnlMain.add(btnMainConnexion);
				
		JLabel lblMainBienvenue = new JLabel("Bienvenue dans la meilleure application de recherche de recette à cuisiner !");
		lblMainBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainBienvenue.setFont(new Font("Calibri", Font.BOLD, 40));
		lblMainBienvenue.setBounds(310, 250, 1300, 50);
		pnlMain.add(lblMainBienvenue);
				
		JLabel lblMainInscription = new JLabel("Nouvel utilisateur ?");
		lblMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		lblMainInscription.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainInscription.setBounds(510, 500, 300, 50);
		pnlMain.add(lblMainInscription);
				
		JLabel lblMainConnexion = new JLabel("Déjà inscrit ?");
		lblMainConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		lblMainConnexion.setBounds(1110, 500, 300, 50);
		pnlMain.add(lblMainConnexion);
		
		//Panel Inscription (1850;20)
		
		JLabel lblInscripitionMain = new JLabel("Quel bonheur d'accueillir un nouveau chef ! Entrez vos informations ci-dessous puis cliquez sur \"Valider\".");
		lblInscripitionMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblInscripitionMain.setFont(new Font("Calibri", Font.BOLD, 30));
		lblInscripitionMain.setBounds(260, 200, 1400, 50);
		pnlInscription.add(lblInscripitionMain);
				
		txtInscriptionUserName = new JTextField();
		txtInscriptionUserName.setFont(new Font("Calibri", Font.PLAIN, 30));
		txtInscriptionUserName.setHorizontalAlignment(SwingConstants.LEFT);
		txtInscriptionUserName.setBounds(810, 400, 700, 50);
		pnlInscription.add(txtInscriptionUserName);
		txtInscriptionUserName.setColumns(10);
		
		JLabel lblInscriptionUserName = new JLabel("Nom d'utilisateur :");
		lblInscriptionUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblInscriptionUserName.setFont(new Font("Calibri", Font.BOLD, 30));
		lblInscriptionUserName.setBounds(410, 400, 400, 50);
		pnlInscription.add(lblInscriptionUserName);
				
		JLabel lblInscriptionPassword = new JLabel("Mot de passe :");
		lblInscriptionPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInscriptionPassword.setFont(new Font("Calibri", Font.BOLD, 30));
		lblInscriptionPassword.setBounds(410, 500, 400, 50);
		pnlInscription.add(lblInscriptionPassword);
				
		JLabel lblInscriptionPasswordConfirm = new JLabel("Confirmer mot de passe :");
		lblInscriptionPasswordConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblInscriptionPasswordConfirm.setFont(new Font("Calibri", Font.BOLD, 30));
		lblInscriptionPasswordConfirm.setBounds(410, 600, 400, 50);
		pnlInscription.add(lblInscriptionPasswordConfirm);
				
		JButton btnInscriptionValider = new JButton("Valider");
		btnInscriptionValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInscriptionUserName.getText().compareTo("") != 0 && pswdInscription.getText().compareTo("") != 0 && pswdInscriptionConfirm.getText().compareTo("") != 0)
				{
					if(pswdInscription.getText().compareTo(pswdInscriptionConfirm.getText()) == 0)
					{
						username = txtInscriptionUserName.getText();
						password = pswdInscription.getText();
						txtInscriptionUserName.setText("");
						pswdInscription.setText("");
						pswdInscriptionConfirm.setText("");
						pnlInscription.setVisible(false);
						pnlMain.setVisible(true);
					}
				}
			}
		});
		btnInscriptionValider.setFont(new Font("Calibri", Font.BOLD, 30));
		btnInscriptionValider.setBounds(1520, 830, 300, 150);
		pnlInscription.add(btnInscriptionValider);
				
		JButton btnInscriptionRetour = new JButton("Retour");
		btnInscriptionRetour.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				pnlInscription.setVisible(false);
				pnlMain.setVisible(true);
				txtInscriptionUserName.setText("");
				pswdInscription.setText("");
				pswdInscriptionConfirm.setText("");
			}
		});
		btnInscriptionRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		btnInscriptionRetour.setBounds(100, 830, 300, 150);
		pnlInscription.add(btnInscriptionRetour);		
				
		pswdInscription = new JPasswordField();
		pswdInscription.setHorizontalAlignment(SwingConstants.LEFT);
		pswdInscription.setFont(new Font("Calibri", Font.PLAIN, 30));
		pswdInscription.setBounds(810, 500, 700, 50);
		pnlInscription.add(pswdInscription);
				
		pswdInscriptionConfirm = new JPasswordField();
		pswdInscriptionConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		pswdInscriptionConfirm.setFont(new Font("Calibri", Font.PLAIN, 30));
		pswdInscriptionConfirm.setBounds(810, 600, 700, 50);
		pnlInscription.add(pswdInscriptionConfirm);
				
		//Panel Connexion (1850;40)
		
		JLabel lblConnexionMain = new JLabel("Heureux de vous revoir ! Entrez vos paramètres de connexion ci-dessous.");
		lblConnexionMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexionMain.setFont(new Font("Calibri", Font.BOLD, 30));
		lblConnexionMain.setBounds(460, 200, 1000, 50);
		pnlConnexion.add(lblConnexionMain);
		
		JLabel lblConnexionUsername = new JLabel("Nom d'utilisateur :");
		lblConnexionUsername.setFont(new Font("Calibri", Font.BOLD, 30));
		lblConnexionUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexionUsername.setBounds(460, 400, 300, 50);
		pnlConnexion.add(lblConnexionUsername);
		
		txtConnexionUsername = new JTextField();
		txtConnexionUsername.setFont(new Font("Calibri", Font.PLAIN, 30));
		txtConnexionUsername.setHorizontalAlignment(SwingConstants.LEFT);
		txtConnexionUsername.setBounds(760, 400, 700, 50);
		pnlConnexion.add(txtConnexionUsername);
		txtConnexionUsername.setColumns(10);
		
		JLabel lblConnexionPassword = new JLabel("Mot de passe :");
		lblConnexionPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexionPassword.setFont(new Font("Calibri", Font.BOLD, 30));
		lblConnexionPassword.setBounds(460, 500, 300, 50);
		pnlConnexion.add(lblConnexionPassword);
		
		pswdConnexion = new JPasswordField();
		pswdConnexion.setFont(new Font("Calibri", Font.PLAIN, 30));
		pswdConnexion.setHorizontalAlignment(SwingConstants.LEFT);
		pswdConnexion.setBounds(760, 500, 700, 50);
		pnlConnexion.add(pswdConnexion);
		
		JButton btnConnexionRetour = new JButton("Retour");
		btnConnexionRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtConnexionUsername.setText("");
				pswdConnexion.setText("");
				pnlMain.setVisible(true);
				pnlConnexion.setVisible(false);
			}
		});
		btnConnexionRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		btnConnexionRetour.setBounds(100, 830, 300, 150);
		pnlConnexion.add(btnConnexionRetour);
		
		JLabel lblAccueilMain = new JLabel("Bienvenue dans votre cuisine, ... ! Que souhaitez-vous faire ?");
		lblAccueilMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueilMain.setFont(new Font("Calibri", Font.BOLD, 30));
		lblAccueilMain.setBounds(60, 100, 1800, 50);
		pnlAccueil.add(lblAccueilMain);
		
		JButton btnConnexionValider = new JButton("Valider");
		btnConnexionValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtConnexionUsername.getText().compareTo(username) == 0 && pswdConnexion.getText().compareTo(password) == 0)
				{
					txtConnexionUsername.setText("");
					pswdConnexion.setText("");
					lblAccueilMain.setText("Bienvenue dans votre cuisine, " + username + "! Que souhaitez-vous faire ?");
					pnlAccueil.setLocation(0,0);
					pnlAccueil.setVisible(true);
					pnlConnexion.setVisible(false);
				}
			}
		});
		btnConnexionValider.setFont(new Font("Calibri", Font.BOLD, 30));
		btnConnexionValider.setBounds(1520, 830, 300, 150);
		pnlConnexion.add(btnConnexionValider);
		
		//Panel Accueil (1850;60)
		
		JButton btnAccueilAddIngredient = new JButton("Ajouter un ingrédient");
		btnAccueilAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilAddIngredient.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilAddIngredient.setBounds(1240, 250, 400, 100);
		pnlAccueil.add(btnAccueilAddIngredient);
		
		JButton btnAccueilContentFrigo = new JButton("Voir le contenu du frigo");
		btnAccueilContentFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilContentFrigo.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilContentFrigo.setBounds(1240, 400, 400, 100);
		pnlAccueil.add(btnAccueilContentFrigo);
		
		JButton btnAccueilCheckFavorite = new JButton("Voir vos recettes favorites");
		btnAccueilCheckFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilCheckFavorite.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilCheckFavorite.setBounds(1240, 550, 400, 100);
		pnlAccueil.add(btnAccueilCheckFavorite);
		
		JButton btnAccueilSearchRecipes = new JButton("Chercher une recette");
		btnAccueilSearchRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilSearchRecipes.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilSearchRecipes.setBounds(1240, 700, 400, 100);
		pnlAccueil.add(btnAccueilSearchRecipes);
		
		JButton btnAccueilAllergenes = new JButton("Voir/Modifier vos allergènes");
		btnAccueilAllergenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilAllergenes.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilAllergenes.setBounds(280, 250, 400, 100);
		pnlAccueil.add(btnAccueilAllergenes);
		
		JLabel lblAccueilExpired = new JLabel("");
		lblAccueilExpired.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueilExpired.setFont(new Font("Calibri", Font.BOLD, 30));
		lblAccueilExpired.setBounds(460, 880, 1000, 50);
		pnlAccueil.add(lblAccueilExpired);
		
		JButton btnAccueilSettings = new JButton("Modifer les paramètres");
		btnAccueilSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilSettings.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilSettings.setBounds(280, 550, 400, 100);
		pnlAccueil.add(btnAccueilSettings);
		
		JButton btnAccueilDeconnexion = new JButton("Déconnexion");
		btnAccueilDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAccueil.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnAccueilDeconnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilDeconnexion.setBounds(280, 700, 400, 100);
		pnlAccueil.add(btnAccueilDeconnexion);
	}
}
