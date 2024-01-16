package Front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

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
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;

public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtInscriptionUserName;
	
	//Les modèles de listes d'affichage
	private DefaultListModel<Ingredient> listModelIng = new DefaultListModel<>();
	private DefaultListModel<Ingredient> listModelShopping = new DefaultListModel<>();
	private DefaultListModel<Recipe> listModelRecipe = new DefaultListModel<>();
	private DefaultListModel<Recipe> listModelRecipeFav = new DefaultListModel<>();
	
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
	private JTextField txtAjoutIngNomIng;
	private JTextField dateJour;
	private JTextField dateMois;
	private JTextField dateAnnee;

	/**
	 * Create the frame.
	 */
	public Interface() {
		setResizable(false);
		setMinimumSize(new Dimension(1920, 1060));
		getContentPane().setMinimumSize(new Dimension(1920, 1080));
		
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
		getContentPane().setLayout(new CardLayout(0, 0));

		
		//Panels
		
		JPanel pnlMain = new JPanel();
		getContentPane().add(pnlMain, "name_1260978277190400");
								
		JPanel pnlInscription = new JPanel();
		getContentPane().add(pnlInscription, "name_1260978321775600");
		pnlInscription.setLayout(null);
		pnlInscription.setVisible(false);
						
		JPanel pnlConnexion = new JPanel();
		getContentPane().add(pnlConnexion, "name_1260978362757500");
		pnlConnexion.setLayout(null);
		pnlConnexion.setVisible(false);
						
		JPanel pnlAccueil = new JPanel();
		getContentPane().add(pnlAccueil, "name_1260978407053000");
		pnlAccueil.setLayout(null);
		//pnlAccueil.setVisible(false);
				
		JPanel pnlAllergenes = new JPanel();
		getContentPane().add(pnlAllergenes, "name_1260978451111300");
		pnlAllergenes.setLayout(null);
		pnlAllergenes.setVisible(false);
				
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
		getContentPane().add(pnlRecette, "name_1260978495132100");
		pnlRecette.setLayout(null);
		
		JPanel pnlRecetteIngredients = new JPanel();
		pnlRecetteIngredients.setVisible(false);
		pnlRecetteIngredients.setBounds(520, 250, 1380, 200);
		pnlRecette.add(pnlRecetteIngredients);
		pnlRecetteIngredients.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel pnlRecetteSteps = new JPanel();
		pnlRecetteSteps.setBounds(50, 450, 1850, 570);
		pnlRecette.add(pnlRecetteSteps);
		pnlRecetteSteps.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel pnlRecettesFav = new JPanel();
		getContentPane().add(pnlRecettesFav, "name_1260978536942300");
		pnlRecettesFav.setLayout(null);
		pnlRecettesFav.setVisible(false);
		
		JPanel pnlChercherRecette = new JPanel();
		getContentPane().add(pnlChercherRecette, "name_1260978577235600");
		pnlChercherRecette.setLayout(null);
		pnlChercherRecette.setVisible(false);
		
		JPanel pnlVoirFrigo = new JPanel();
		getContentPane().add(pnlVoirFrigo, "name_1260978616199300");
		pnlVoirFrigo.setLayout(null);
		pnlVoirFrigo.setVisible(false);
		
		JPanel pnlListeCourse = new JPanel();
		getContentPane().add(pnlListeCourse, "name_1260978656552900");
		pnlListeCourse.setLayout(null);
		pnlListeCourse.setVisible(false);
		
		JPanel pnlAjoutIng = new JPanel();
		getContentPane().add(pnlAjoutIng, "name_1260978699163300");
		pnlAjoutIng.setLayout(null);
		pnlAjoutIng.setVisible(false);
		
		
		// Listes d'affichage
		
		JList listListeCourse = new JList();
		listListeCourse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listListeCourse.setBounds(44, 38, 853, 581);
		listListeCourse.setModel(listModelShopping);
		listListeCourse.setBackground(new Color(238, 232, 170));
		pnlListeCourse.add(listListeCourse);

		JList listChercherRecette = new JList();
		listChercherRecette.setModel(listModelRecipe);
		listChercherRecette.setFont(new Font("Tahoma", Font.PLAIN, 18));
		listChercherRecette.setBounds(15, 15, 800, 800);
		pnlChercherRecette.add(listChercherRecette);
		
		JButton btnFavRecette = new JButton("Mettre la recette sélectionnée en favori");
		btnFavRecette.setForeground(new Color(255, 255, 255));
		btnFavRecette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = listChercherRecette.getSelectedIndex();
				Recipe selectedRecipe;
			    if (selectedIndex != -1) {
			    	selectedRecipe=listModelRecipeFav.get(selectedIndex);
			    	selectedRecipe.setIsFav(true);
			    }
			}
		});
		btnFavRecette.setBackground(new Color(255, 210, 0));
		btnFavRecette.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFavRecette.setBounds(825, 20, 500, 50);
		pnlChercherRecette.add(btnFavRecette);
		
		JList listRecettesFav = new JList();
		listRecettesFav.setBounds(15, 15, 800, 800);
		listRecettesFav.setModel(listModelRecipeFav);
		listRecettesFav.setFont(new Font("Tahoma", Font.PLAIN, 18));
		listRecettesFav.setBackground(new Color(240, 230, 140));
		pnlRecettesFav.add(listRecettesFav);
		
		JList listVoirFrigoIngredients = new JList();
		listVoirFrigoIngredients.setBounds(50, 15, 800, 800);
		listVoirFrigoIngredients.setModel(listModelIng);
		listModelIng.addElement(new Ingredient("12122024","chocolat",8.2,"grammes"));
		listModelIng.addElement(testIngredient1);
		pnlVoirFrigo.add(listVoirFrigoIngredients);
		
		//Panel Main (1850;0)
		
		JButton btnMainInscription = new JButton("Inscription");
		btnMainInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlInscription.setLocation(0, 0);
				pnlMain.setVisible(false);
				pnlInscription.setVisible(true);
			}
		});
		pnlMain.setLayout(new MigLayout("", "[810px][300px][810px]", "[300px][200px][50px][510px]"));
		btnMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(btnMainInscription, "cell 0 3,alignx right,aligny top");
				
		JButton btnMainConnexion = new JButton("Connexion");
		btnMainConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlConnexion.setLocation(0,0);
				pnlConnexion.setVisible(true);
				pnlMain.setVisible(false);
			}
		});
		btnMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(btnMainConnexion, "cell 2 3,alignx left,aligny top");
				
		JLabel lblMainBienvenue = new JLabel("Bienvenue dans la meilleure application de recherche de recette à cuisiner !");
		lblMainBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainBienvenue.setFont(new Font("Calibri", Font.BOLD, 40));
		pnlMain.add(lblMainBienvenue, "cell 0 0 3 1,alignx center,aligny bottom");
				
		JLabel lblMainInscription = new JLabel("Nouvel utilisateur ?");
		lblMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		lblMainInscription.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMain.add(lblMainInscription, "cell 0 2,alignx right,growy");
				
		JLabel lblMainConnexion = new JLabel("Déjà inscrit ?");
		lblMainConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(lblMainConnexion, "cell 2 2,alignx left,growy");
		
		JLabel imageBgFrigoFerme = new JLabel("");
		imageBgFrigoFerme.setIcon(new ImageIcon(Interface.class.getResource("/Front/frigoferme.png")));
		pnlMain.add(imageBgFrigoFerme, "cell 0 0 3 4,grow");
		
		
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
		lblAccueilMain.setBackground(new Color(234, 234, 234));
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
				pnlAjoutIng.setBounds(0,0,1920,1060);
				pnlAjoutIng.setVisible(true);
				pnlAccueil.setVisible(false);
			}
		});
		btnAccueilAddIngredient.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilAddIngredient.setBounds(1240, 250, 400, 100);
		pnlAccueil.add(btnAccueilAddIngredient);
		
		JButton btnAccueilContentFrigo = new JButton("Voir le contenu du frigo");
		btnAccueilContentFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlVoirFrigo.setBounds(0,0,1920,1060);
				pnlVoirFrigo.setVisible(true);
				pnlAccueil.setVisible(false);
			}
		});
		btnAccueilContentFrigo.setFont(new Font("Calibri", Font.BOLD, 30));
		btnAccueilContentFrigo.setBounds(1240, 400, 400, 100);
		pnlAccueil.add(btnAccueilContentFrigo);
		
		JButton btnAccueilCheckFavorite = new JButton("Voir vos recettes favorites");
		btnAccueilCheckFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlRecettesFav.setBounds(0,0,1920,1060);
				pnlRecettesFav.setVisible(true);
				pnlAccueil.setVisible(false);
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
		
		JLabel lblImgFrigoOuvert = new JLabel("");
		lblImgFrigoOuvert.setIcon(new ImageIcon(Interface.class.getResource("/Front/frigoouverrs.png")));
		lblImgFrigoOuvert.setBounds(0, 0, 1920, 1060);
		pnlAccueil.add(lblImgFrigoOuvert);
		
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
		
		// Panel chercherRecettes
		JButton btnVoirFrigoSupprIng = new JButton("Supprimer l'ingrédient selectionné");
		btnVoirFrigoSupprIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = listVoirFrigoIngredients.getSelectedIndex();

			    if (selectedIndex != -1) {
			    	listModelIng.removeElementAt(selectedIndex);
			    }
			}
		});
		btnVoirFrigoSupprIng.setBackground(new Color(178, 34, 34));
		btnVoirFrigoSupprIng.setForeground(new Color(255, 255, 255));
		btnVoirFrigoSupprIng.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVoirFrigoSupprIng.setBounds(900, 20, 400, 80);
		pnlVoirFrigo.add(btnVoirFrigoSupprIng);
		
		
		
		
		//Panel ajoutIng
		
		txtAjoutIngNomIng = new JTextField();
		txtAjoutIngNomIng.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAjoutIngNomIng.setBounds(750, 440, 325, 55);
		pnlAjoutIng.add(txtAjoutIngNomIng);
		txtAjoutIngNomIng.setColumns(10);
		
		JComboBox cbBoxAjoutIngChoixUnite = new JComboBox();
		cbBoxAjoutIngChoixUnite.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbBoxAjoutIngChoixUnite.setModel(new DefaultComboBoxModel(new String[] {"Grammes", "Litres", "Unités"}));
		cbBoxAjoutIngChoixUnite.setBounds(750, 540, 325, 55);
		pnlAjoutIng.add(cbBoxAjoutIngChoixUnite);
		
		JFormattedTextField txtAjoutIngQtt = new JFormattedTextField();
		txtAjoutIngQtt.setBounds(1000, 500, 50, 30);
		pnlAjoutIng.add(txtAjoutIngQtt);
		
		JLabel lblAjoutIngSaisirQtt = new JLabel("Saisir la quantité: ");
		lblAjoutIngSaisirQtt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAjoutIngSaisirQtt.setBounds(750, 505, 500, 20);
		pnlAjoutIng.add(lblAjoutIngSaisirQtt);
		
		JLabel lblAjoutIngMsg = new JLabel("");
		lblAjoutIngMsg.setBackground(new Color(127, 255, 0));
		lblAjoutIngMsg.setForeground(new Color(0, 0, 0));
		lblAjoutIngMsg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAjoutIngMsg.setBounds(750, 750, 1000, 50);
		lblAjoutIngMsg.setVisible(false);
		pnlAjoutIng.add(lblAjoutIngMsg);
		
		dateJour = new JTextField();
		dateJour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateJour.setBounds(750, 635, 96, 19);
		pnlAjoutIng.add(dateJour);
		dateJour.setColumns(10);
		
		dateMois = new JTextField();
		dateMois.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateMois.setBounds(856, 635, 96, 19);
		pnlAjoutIng.add(dateMois);
		dateMois.setColumns(10);
		
		dateAnnee = new JTextField();
		dateAnnee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateAnnee.setBounds(962, 635, 96, 19);
		pnlAjoutIng.add(dateAnnee);
		dateAnnee.setColumns(10);
		
		JButton btnAjoutIng = new JButton("Ajouter");
		btnAjoutIng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ajouterMsg="Ingrédient ajouté";
				String nomIngStr=txtAjoutIngNomIng.getText();
				String qttIngStr=txtAjoutIngQtt.getText();
				double qttIngDouble = 0;
				int dateJourInt;
				int dateMoisInt;
				int dateAnneeInt;
				try {
					qttIngDouble=Double.parseDouble(qttIngStr);
				}catch(Exception ee) {
					ajouterMsg="Erreur. Saisir un nombre.";
				}
				try {
					if(dateJour.getText().length()<2) {
						dateJour.setText("0"+dateJour.getText());
					}
					if(dateMois.getText().length()<2) {
						dateMois.setText("0"+dateMois.getText());
					}
					dateJourInt=Integer.parseInt(dateJour.getText());
					dateMoisInt=Integer.parseInt(dateMois.getText());
					dateAnneeInt=Integer.parseInt(dateAnnee.getText());
					
					if(((dateJourInt<=0)&& (dateJourInt>31)) ) {
						ajouterMsg= "Jour invalide.";
					}
					else if(((dateMoisInt<=0)&& (dateJourInt>12)) ) {
						ajouterMsg= "Mois invalide.";
					}
					
					else if(((dateAnneeInt<2024)) ) {
						ajouterMsg= "Annee invalide.";
					}
					else {
						
					}
				}catch(Exception ee) {
					if(ajouterMsg.equals("Erreur. Saisir un entier pour la quantité.")) {
						ajouterMsg=ajouterMsg+" Date non valide.";
					}
					else {
						ajouterMsg=" Date non valide";
					}
					
				}
				lblAjoutIngMsg.setText(ajouterMsg);
				lblAjoutIngMsg.setVisible(true);
				if(ajouterMsg.equals("Ingrédient ajouté")) {
					Ingredient newIng= new Ingredient(dateJour.getText()+dateMois.getText()+dateAnnee.getText(),nomIngStr, qttIngDouble,cbBoxAjoutIngChoixUnite.getSelectedItem().toString());
					listModelIng.addElement(newIng);
					System.out.print(listModelIng.lastElement());
					txtAjoutIngNomIng.setText("");
					dateAnnee.setText("");
					dateMois.setText("");
					dateJour.setText("");
					txtAjoutIngQtt.setText("");
				}
				
		}
		});
		btnAjoutIng.setForeground(new Color(255, 255, 255));
		btnAjoutIng.setBackground(new Color(111, 221, 0));
		btnAjoutIng.setBounds(750, 700, 325, 38);
		pnlAjoutIng.add(btnAjoutIng);
		
		JLabel labelAjoutIngDatePeremption = new JLabel("Date limite de consommation: ");
		labelAjoutIngDatePeremption.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelAjoutIngDatePeremption.setBounds(750, 605, 300, 20);
		pnlAjoutIng.add(labelAjoutIngDatePeremption);

	}
}
