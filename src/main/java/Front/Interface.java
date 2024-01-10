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

import fr.tse.fise2.info4.Classes.*;


import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import fr.tse.fise2.info4.*;
public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
					List<String> allUsers = Database.getAllUsers();
					System.out.println("Liste de tous les utilisateurs : ");
			        for (String user : allUsers) {
			            System.out.println(user);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Variables
	
	String username = "abc";
	private JTextField txtboxInscription;

	/**
	 * Create the frame.
	 */
	public Interface() {
		setMinimumSize(new Dimension(800, 600));
		pack();
		
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
		
        //Recipe(int id,String linkToImage, String title, List<Ingredient> l_ingredients,String summary,int nbPeople,List<String> l_steps,List<Ingredient> l_usedIngredients,List<Ingredient> l_missingIngredients, int cookingTime,int healthScore)
        
		Recipe recette = new Recipe(1,
				"https://spoonacular.com/recipeImages/633852-312x231.jpg", 
				"baked tomatoes",
				l_testIngredients,
                "Baked tomatoes is a <b>gluten free and primal</b> side dish. This recipe serves 4.",
                4,
                l_steps,
                l_testUsedIngredients,
                l_testMissingIngredients,45,7);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		
		// Relation Back-Front matières premières : 
		List<String> allUsers = Database.getAllUsers(); // liste contenant les noms de chaque user
		
		
		//Panels
		
		JPanel pnlMain = new JPanel();
		getContentPane().add(pnlMain, "name_1742971366120500");
								
		JPanel pnlInscription = new JPanel();
		pnlInscription.setVisible(false);
		getContentPane().add(pnlInscription, "name_1742971381735700");
		pnlInscription.setLayout(new MigLayout("flowx, wrap 2", "[10%,grow][300px][5px][50%,grow][5px][300px][10%,grow]", "[:10%:100px,grow][100px][20%,grow][50px][25%,grow][100px][5%,grow]"));
		
		JLabel lblInscriptionMain = new JLabel("Quel bonheur d'accueillir un nouveau chef ! Entrez votre nom.");
		lblInscriptionMain.setFont(new Font("Calibri", Font.BOLD, 30));
		lblInscriptionMain.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInscription.add(lblInscriptionMain, "cell 1 1 5 1,grow");
		
		JLabel lblInscriptionUsername = new JLabel("Nom d'utilisateur :");
		lblInscriptionUsername.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblInscriptionUsername.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInscription.add(lblInscriptionUsername, "cell 1 3,grow");
		
		txtboxInscription = new JTextField();
		txtboxInscription.setFont(new Font("Calibri", Font.PLAIN, 25));
		pnlInscription.add(txtboxInscription, "cell 3 3 3 1,grow");
		txtboxInscription.setColumns(10);
		
		JButton btnInscriptionRetour = new JButton("Retour");
		btnInscriptionRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtboxInscription.setText("");
				pnlInscription.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnInscriptionRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlInscription.add(btnInscriptionRetour, "cell 1 5,grow");
		
		JButton btnInscriptionValider = new JButton("Valider");
		btnInscriptionValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtboxInscription.getText().compareTo("") != 0)
				{
					//Méthode pour créer un utilisateur
					String username_input=txtboxInscription.getText();
					System.out.println("username input is : "+username_input);
					Database.addUser(username_input); // cela donne des erreurs à corriger
					//User newUser = Database.getUser(username_input);
					// On récupère içi l'utilisateur
					
					
					pnlInscription.setVisible(false);
					pnlMain.setVisible(true);
				}
			}
		});
		btnInscriptionValider.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlInscription.add(btnInscriptionValider, "cell 5 5,grow");
						
		JPanel pnlConnexion = new JPanel();
		pnlConnexion.setVisible(false);
		getContentPane().add(pnlConnexion, "name_1742971397572300");
						
		JPanel pnlAccueil = new JPanel();
		getContentPane().add(pnlAccueil, "name_1742971413657900");
				
		JPanel pnlAllergenes = new JPanel();
		pnlAllergenes.setVisible(false);
		getContentPane().add(pnlAllergenes, "name_1742971429427900");
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
		getContentPane().add(pnlRecette, "name_1742971446209000");
		pnlRecette.setLayout(new MigLayout("", "[34%][33%,grow][33%]", "[50px][50px][8%,grow][8%,grow][30%,grow][50%,grow]"));
		
		//Panel Recette (1850;120)
		
		JLabel lblRecetteTitre = new JLabel("\"Titre recette\"");
		lblRecetteTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		lblRecetteTitre.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRecette.add(lblRecetteTitre, "cell 1 0 2 1,grow");
		
		JLabel lblRecetteCookTime = new JLabel("\"Temps\"");
		lblRecetteCookTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecetteCookTime.setFont(new Font("Calibri", Font.PLAIN, 25));
		pnlRecette.add(lblRecetteCookTime, "cell 1 1 2 1,grow");
		
		JButton btnNewButton_1 = new JButton("New button");
		pnlRecette.add(btnNewButton_1, "flowy,cell 1 3");
		
		JLabel lblRecetteIngredients = new JLabel("Ingredients :");
		lblRecetteIngredients.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlRecette.add(lblRecetteIngredients, "cell 1 3,grow");
		
		JPanel pnlRecetteIngredients = new JPanel();
		pnlRecette.add(pnlRecetteIngredients, "cell 2 4,grow");
		pnlRecetteIngredients.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel pnlRecetteSteps = new JPanel();
		pnlRecette.add(pnlRecetteSteps, "cell 0 5 3 1,grow");
		pnlRecetteSteps.setLayout(new GridLayout(3, 0, 0, 0));
		pnlMain.setLayout(new MigLayout("", "[50px:10%,grow][300px][30%,grow][300px][50px:10%,grow]", "[5%,grow][80px][20%,grow][80px][150px][20%,grow]"));
		
		JLabel lblMainBienvenue = new JLabel("Bienvenue dans la meilleure application de recherche de recette à cuisiner !");
		lblMainBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainBienvenue.setFont(new Font("Calibri", Font.BOLD, 35));
		pnlMain.add(lblMainBienvenue, "cell 1 1 3 1,grow");
		
		JLabel lblMainInscription = new JLabel("Nouvel utilisateur ?");
		lblMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		lblMainInscription.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMain.add(lblMainInscription, "cell 1 3,grow");
		
		JLabel lblMainConnexion = new JLabel("Déjà inscrit ?");
		lblMainConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(lblMainConnexion, "cell 3 3,grow");
		
		//Panel Connexion (1850;40)
		
		JLabel lblConnexionMain = new JLabel("Heureux de vous revoir ! Cliquez sur votre compte ci-dessous.");
		lblConnexionMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexionMain.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlConnexion.add(lblConnexionMain, "cell 2 1,grow");
				
		JButton btnConnexionRetour = new JButton("Retour");
		btnConnexionRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlMain.setVisible(true);
				pnlConnexion.setVisible(false);
			}
		});
				
		JPanel pnlConnexionComptes = new JPanel();
		pnlConnexion.add(pnlConnexionComptes, "cell 2 3,grow");
		pnlConnexionComptes.setLayout(new GridLayout(1, 0, 0, 0));
		btnConnexionRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlConnexion.add(btnConnexionRetour, "cell 1 5,grow");
		pnlAccueil.setLayout(new MigLayout("", "[10%,grow][25%,grow][30%,grow][25%,grow][10%,grow]", "[5%,grow][50px][10%,grow][15%,grow][15%,grow][15%,grow][15%,grow][15%,grow]"));
				
		JLabel lblAccueilMain = new JLabel("Bienvenue dans votre cuisine, ... ! Que souhaitez-vous faire ?");
		lblAccueilMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueilMain.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(lblAccueilMain, "cell 1 1 3 1,grow");
						
		JLabel lblRecetteImage = new JLabel("\"Image\"");
		lblRecetteImage.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRecette.add(lblRecetteImage, "cell 0 1 1 4,grow");
		
		//Panel Main (1850;0)
		
		JButton btnMainInscription = new JButton("Inscription");
		btnMainInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlMain.setVisible(false);
				pnlInscription.setVisible(true);
			}
		});
		btnMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(btnMainInscription, "cell 1 4,grow");
		
		JButton btnMainConnexion = new JButton("Connexion");
		btnMainConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Méthode pour récupérer les User
				//InteractionBackFront.remplissagePanelConnexionComptes(pnlConnexionComptes, ); /!\ Ajouter le champ contenant la liste de User
				InteractionBackFront.remplissagePanelConnexionComptes2(pnlConnexionComptes, allUsers);
				pnlConnexion.setVisible(true);
				pnlMain.setVisible(false);
			}
		});
		btnMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(btnMainConnexion, "cell 3 4,grow");
		pnlConnexion.setLayout(new MigLayout("", "[10%,grow][300px][50%,grow][20%,grow]", "[5%,grow][50px][10%,grow][grow][10%,grow][150px][5%,grow]"));
		
		//Panel Accueil (1850;60)
		
		JButton btnAccueilAddIngredient = new JButton("Ajouter un ingrédient");
		btnAccueilAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilAddIngredient.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilAddIngredient, "cell 3 3,grow");
		
		JButton btnAccueilContentFrigo = new JButton("Voir le contenu du frigo");
		btnAccueilContentFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAccueilListeCourses = new JButton("Voir Liste de courses");
		btnAccueilListeCourses.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilListeCourses, "cell 1 4,grow");
		btnAccueilContentFrigo.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilContentFrigo, "cell 3 4,grow");
		
		JButton btnAccueilCheckFavorite = new JButton("Voir vos recettes favorites");
		btnAccueilCheckFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilCheckFavorite.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilCheckFavorite, "cell 3 5,grow");
		
		JButton btnAccueilSearchRecipes = new JButton("Chercher une recette");
		btnAccueilSearchRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlRecette.setLocation(0,0);
				pnlRecette.setVisible(true);
				pnlAccueil.setVisible(false);
				
				InteractionBackFront.actualisationRecette(lblRecetteImage, lblRecetteTitre, lblRecetteCookTime, pnlRecetteIngredients, pnlRecetteSteps, recette);
			}
		});
		btnAccueilSearchRecipes.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilSearchRecipes, "cell 3 6,grow");
		
		JButton btnAccueilAllergenes = new JButton("Voir/Modifier vos allergènes");
		btnAccueilAllergenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccueilAllergenes.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilAllergenes, "cell 1 3,grow");
		
		JLabel lblAccueilExpired = new JLabel("");
		lblAccueilExpired.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueilExpired.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(lblAccueilExpired, "cell 1 7 3 1,grow");
		
		JButton btnAccueilDeconnexion = new JButton("Déconnexion");
		btnAccueilDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAccueil.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnAccueilDeconnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilDeconnexion, "cell 1 6,grow");
		
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
	}
}
