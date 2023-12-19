package Front;

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
import javax.swing.ImageIcon;
import java.util.List;
import java.util.ArrayList;

import java.net.*;

import Classes.Ingredient;
import Classes.Recipe;
import Classes.InteractionBackFront;

public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtInscriptionUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Variables
	
	String username = "abc";
	String password = "123";
	private JTextField txtConnexionUsername;
	private JPasswordField pswdConnexion;
	private JPasswordField pswdInscription;
	private JPasswordField pswdInscriptionConfirm;
	private JTextField nomIng;

	/**
	 * Create the frame.
	 */
	public Interface() {
		setResizable(false);
		setMinimumSize(new Dimension(1920, 1060));
		getContentPane().setMinimumSize(new Dimension(1920, 1080));
		getContentPane().setLayout(null);
		
		// Variables
		
		Ingredient ingredient1 = new Ingredient("2023-01-01", "Tomato", null,null);
        Ingredient ingredient2 = new Ingredient("2023-02-15", "Milk", null,null);
        
        List<Ingredient> l_ingredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testUsedIngredients = new ArrayList<Ingredient>();
        List<Ingredient> l_testMissingIngredients = new ArrayList<Ingredient>();
        List<String> l_steps = new ArrayList<String>();
        
        l_steps.add("Preheat your oven to 450F.");
        l_steps.add("Mix goat cheese, parmesan cheese and basil in a small bowl.");
        l_steps.add("Cut tomatoes in half, drizzle each half with olive oil and sprinkle with salt & pepper.");
        l_steps.add("Top each tomato half with the cheese mix, equally dividing the mixture between the four halves.");
        l_steps.add("Bake the tomatoes for about 20 minutes, or until softened and slightly browned.");
        l_steps.add("Serve hot or at room temperature.");
        
        Ingredient testIngredient1 = new Ingredient(null, "tomatoes", 2.0,"");
        Ingredient testIngredient2 = new Ingredient(null, "unripened goat's milk cheese", 61.0,"ml");
        Ingredient testIngredient3 = new Ingredient(null, "parmesan", 2.0,"Tbsps");
        Ingredient testIngredient5 = new Ingredient(null, "basil", 1.0,"Tbsp");
        Ingredient testIngredient6 = new Ingredient(null, "salt & pepper", 1.0,"pinch");
        Ingredient testIngredient7 = new Ingredient(null, "olive oil", 4.0,"servings");
        
        Ingredient testUsedIngredient1 = new Ingredient(null, "tomatoes", 2.0,"");
        Ingredient testUsedIngredient2 = new Ingredient(null, "unripened goat's milk cheese", 0.25,"cup");
        Ingredient testMissingIngredient1 = new Ingredient(null, "parmesan", 2.0,"tbsp");
        Ingredient testMissingIngredient2 = new Ingredient(null, "basil", 1.0,"tbsp");
        
        l_ingredients.add(ingredient1);
        l_ingredients.add(ingredient2);
        
        l_testIngredients.add(testIngredient1);
        l_testIngredients.add(testIngredient2);
        l_testIngredients.add(testIngredient3);
        l_testIngredients.add(testIngredient5);
        l_testIngredients.add(testIngredient6);
        l_testIngredients.add(testIngredient7);
        
        l_testUsedIngredients.add(testUsedIngredient1);
        l_testUsedIngredients.add(testUsedIngredient2);
        
        l_testMissingIngredients.add(testMissingIngredient1);
        l_testMissingIngredients.add(testMissingIngredient2);
		
		Recipe recette = new Recipe("https://spoonacular.com/recipeImages/633852-312x231.jpg", 
				"baked tomatoes",
				l_testIngredients,
                "Baked tomatoes is a <b>gluten free and primal</b> side dish. This recipe serves 4.",
                4,
                l_steps,
                l_testUsedIngredients,
                l_testMissingIngredients,45);
		
		//Panels
		
		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(800, 800, 1920, 1080);
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
		pnlAccueil.setBounds(1850, 60, 1920, 1080);
		getContentPane().add(pnlAccueil);
		pnlAccueil.setLayout(null);
				
		JPanel pnlAllergenes = new JPanel();
		pnlAllergenes.setVisible(false);
		pnlAllergenes.setBounds(1850, 80, 1920, 1080);
		getContentPane().add(pnlAllergenes);
		pnlAllergenes.setLayout(null);
				
		JPanel pnlAllergenesTitle = new JPanel();
		pnlAllergenesTitle.setBounds(0, 0, 1920, 100);
		pnlAllergenes.add(pnlAllergenesTitle);
		pnlAllergenesTitle.setLayout(null);
				
		JPanel pnlAllergenesButtonsAllergenes = new JPanel();
		pnlAllergenesButtonsAllergenes.setBounds(0, 100, 1920, 730);
		pnlAllergenes.add(pnlAllergenesButtonsAllergenes);
		pnlAllergenesButtonsAllergenes.setLayout(new GridLayout(1, 0, 0, 0));
				
		JPanel pnlAllergenesRetourAdd = new JPanel();
		pnlAllergenesRetourAdd.setBounds(0, 830, 1920, 200);
		pnlAllergenes.add(pnlAllergenesRetourAdd);
		pnlAllergenesRetourAdd.setLayout(null);
		
		JPanel pnlRecette = new JPanel();
		pnlRecette.setVisible(false);
		pnlRecette.setBounds(1850, 100, 1920, 1080);
		getContentPane().add(pnlRecette);
		pnlRecette.setLayout(null);
		
		JPanel pnlRecetteIngredients = new JPanel();
		pnlRecetteIngredients.setBounds(520, 250, 1380, 200);
		pnlRecette.add(pnlRecetteIngredients);
		pnlRecetteIngredients.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel pnlRecetteSteps = new JPanel();
		pnlRecetteSteps.setBounds(50, 450, 1850, 570);
		pnlRecette.add(pnlRecetteSteps);
		pnlRecetteSteps.setLayout(new GridLayout(3, 0, 0, 0));
		
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
		
		//Panel Recette (1850;120)
		
		JLabel lblRecetteTitre = new JLabel("\"Titre recette\"");
		lblRecetteTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		lblRecetteTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecetteTitre.setBounds(500, 0, 1400, 50);
		pnlRecette.add(lblRecetteTitre);
				
		JLabel lblRecetteCookTime = new JLabel("\"Temps\"");
		lblRecetteCookTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecetteCookTime.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblRecetteCookTime.setBounds(500, 110, 1400, 50);
		pnlRecette.add(lblRecetteCookTime);
				
		JLabel lblRecetteSummary = new JLabel("\"Résumé\"");
		lblRecetteSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecetteSummary.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblRecetteSummary.setBounds(500, 30, 1400, 100);
		pnlRecette.add(lblRecetteSummary);
				
		JLabel lblRecetteImage = new JLabel("\"Image\"");
		lblRecetteImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecetteImage.setBounds(0, 0, 500, 450);
		pnlRecette.add(lblRecetteImage);
				
		JLabel lblRecetteIngredients = new JLabel("Ingredients :");
		lblRecetteIngredients.setFont(new Font("Calibri", Font.BOLD, 30));
		lblRecetteIngredients.setBounds(520, 200, 1380, 50);
		pnlRecette.add(lblRecetteIngredients);
		
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
				pnlRecette.setLocation(0,0);
				pnlRecette.setVisible(true);
				pnlAccueil.setVisible(false);
				
				InteractionBackFront.actualisationRecette(lblRecetteImage, lblRecetteTitre, lblRecetteSummary, lblRecetteCookTime, pnlRecetteIngredients, pnlRecetteSteps, recette);
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
		
		//Panel Allergenes (1850;80)
		
		JLabel lblAllergenesMain = new JLabel("Vos allergènes :");
		lblAllergenesMain.setFont(new Font("Calibri", Font.BOLD, 30));
		lblAllergenesMain.setBounds(100, 20, 1820, 50);
		pnlAllergenesTitle.add(lblAllergenesMain);
		
		JButton btnAllergenesRetour = new JButton("Retour");
		btnAllergenesRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAllergenesRetour.setBounds(100, 25, 300, 150);
		pnlAllergenesRetourAdd.add(btnAllergenesRetour);
		
		JButton btnAllergenesAdd = new JButton("");
		btnAllergenesAdd.setIcon(new ImageIcon(".\\src\\main\\resources\\btnAllergenesAdd.png"));
		btnAllergenesAdd.setBounds(1720, 50, 100, 100);
		pnlAllergenesRetourAdd.add(btnAllergenesAdd);
		
		JPanel panlAjoutIng = new JPanel();
		panlAjoutIng.setBounds(0, 0, 10, 10);
		getContentPane().add(panlAjoutIng);
		
		nomIng = new JTextField();
		nomIng.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nomIng.setBounds(750, 500, 325, 55);
		getContentPane().add(nomIng);
		nomIng.setColumns(10);
		
		JButton btnAjIng = new JButton("Ajouter");
		btnAjIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomIngStr=nomIng.getText();
				nomIng.setText("");
			}
		});
		btnAjIng.setForeground(new Color(255, 255, 255));
		btnAjIng.setBackground(new Color(111, 221, 0));
		btnAjIng.setBounds(750, 570, 325, 38);
		getContentPane().add(btnAjIng);
	}
}
