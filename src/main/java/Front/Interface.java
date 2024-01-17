package Front;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.ArrayList;
import java.awt.image.ImageObserver;

import java.net.*;

import fr.tse.fise2.info4.API.API;
import fr.tse.fise2.info4.Database;
import fr.tse.fise2.info4.Classes.*;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

public class Interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtboxInscription;
	private JTextField txtboxAjoutIngrNom;
	private JTextField txtboxAjoutIngrJour;
	private JTextField txtboxAjoutIngrMois;
	private JTextField txtboxAjoutIngrAnnee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					
					// Récupérer la taille de l'écran
			        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			        int screenWidth = gd.getDisplayMode().getWidth();
			        int screenHeight = gd.getDisplayMode().getHeight();

			        // Définir la taille de la JFrame à la taille de l'écran
			        frame.setSize(screenWidth, screenHeight);

			        // Mettre la JFrame en plein écran
			        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			        
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		setMinimumSize(new Dimension(800, 600));
		pack();
		
		// Variables
		API api = new API();
		User user = new User(1,"",null,null,null);
		List<String> allUsers = Database.getAllUsers(); // liste contenant les noms de chaque user
		AtomicReference<String> usernameRef = new AtomicReference<>();
		
		
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
		
		Recipe recette = new Recipe(12,
				"https://spoonacular.com/recipeImages/633852-312x231.jpg", 
				"baked tomatoes",
				l_testIngredients,
                4,
                l_steps,
                l_testUsedIngredients,
                l_testMissingIngredients,45,96);
		getContentPane().setLayout(new CardLayout(0, 0));

		
		//Panels
		
		ImageIcon imageIcon = new ImageIcon(Interface.class.getResource("/frigoferme.png"));
        Image backgroundImageFrigoFerme = imageIcon.getImage();
        
        imageIcon = new ImageIcon(Interface.class.getResource("/frigoouvert.png"));
        Image backgroundImageFrigoOuvert = imageIcon.getImage();
		
		JPanel pnlMain = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessinez l'arrière-plan avec l'image
                g.drawImage(backgroundImageFrigoFerme, 0, 0, getWidth(), getHeight(), this);
            }
        };
		getContentPane().add(pnlMain, "name_1742971366120500");
								
		JPanel pnlInscription = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessinez l'arrière-plan avec l'image
                g.drawImage(backgroundImageFrigoFerme, 0, 0, getWidth(), getHeight(), this);
            }
        };
		pnlInscription.setVisible(false);
		getContentPane().add(pnlInscription, "name_1742971381735700");
		pnlInscription.setLayout(new MigLayout("flowx, wrap 2", "[10%,grow][300px][5px][50%,grow][5px][300px][10%,grow]", "[:10%:100px,grow][50px][20%,grow][50px][25%,grow][100px][5%,grow]"));
		
		JPanel pnlConnexion = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessinez l'arrière-plan avec l'image
                g.drawImage(backgroundImageFrigoFerme, 0, 0, getWidth(), getHeight(), this);
            }
        };
		pnlConnexion.setVisible(false);
		getContentPane().add(pnlConnexion, "name_1742971397572300");
						
		JPanel pnlAccueil = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dessinez l'arrière-plan avec l'image
                g.drawImage(backgroundImageFrigoOuvert, 0, 0, getWidth(), getHeight(), this);
            }
        };
		getContentPane().add(pnlAccueil, "name_1742971413657900");
				
		JPanel pnlAllergenes = new JPanel();
		pnlAllergenes.setVisible(false);
		getContentPane().add(pnlAllergenes, "name_1742971429427900");
		pnlAllergenes.setLayout(new MigLayout("", "[20%,grow][60%,grow][20%,grow]", "[50px][70%][25%,grow]"));
		
		JPanel pnlAllergenesDisplayAllergenes = new JPanel();
		pnlAllergenes.add(pnlAllergenesDisplayAllergenes, "cell 1 1,grow");
		pnlAllergenesDisplayAllergenes.setLayout(new BoxLayout(pnlAllergenesDisplayAllergenes, BoxLayout.Y_AXIS));
				
		JPanel pnlAllergenesRetourAdd = new JPanel();
		pnlAllergenes.add(pnlAllergenesRetourAdd, "cell 0 2 3 1,grow");
		
		JPanel pnlRecetteInfos = new JPanel();
		pnlRecetteInfos.setVisible(false);
		getContentPane().add(pnlRecetteInfos, "name_1742971446209000");
		pnlRecetteInfos.setLayout(new MigLayout("", "[100px:25%:300px,grow][10%,grow][200px:40%,grow][100px:25%:300px,grow]", "[50px:15%:100px][50px:15%:100px][50%,grow][50px:15%:100px,grow]"));
		
		JPanel pnlRecetteSteps = new JPanel();
		getContentPane().add(pnlRecetteSteps, "name_238947168100");
		pnlRecetteSteps.setLayout(new CardLayout(0, 0));
		
		JPanel pnlConnexionComptes = new JPanel();
		pnlConnexionComptes.setOpaque(false);
		
		JPanel pnlFavoris = new JPanel();
		getContentPane().add(pnlFavoris, "name_33645632673100");
		pnlFavoris.setLayout(new MigLayout("", "[150px:30%:300px,grow][70%,grow]", "[50px][80%,grow][100px:15%:150px,grow]"));
		
		JPanel pnlChercherRecette = new JPanel();
		getContentPane().add(pnlChercherRecette, "name_34762517307000");
		pnlChercherRecette.setLayout(new MigLayout("", "[150px:30%:300px,grow][40%,grow][150px:30%:300px,grow]", "[50px][80%,grow][100px:15%:150px,grow]"));
		
		JPanel pnlVoirFrigo = new JPanel();
		getContentPane().add(pnlVoirFrigo, "name_35636612101900");
		pnlVoirFrigo.setLayout(new MigLayout("", "[150px:30%:300px,grow][55%,grow][100px:15%:150px,grow]", "[50px][80%,grow][100px:15%:150px,grow]"));
		
		JPanel pnlListeCourse = new JPanel();
		getContentPane().add(pnlListeCourse, "name_36666886837500");
		pnlListeCourse.setLayout(new MigLayout("", "[150px:30%:300px,grow][55%,grow][100px:15%:150px,grow]", "[50px][80%,grow][100px:15%:150px,grow]"));
		
		JPanel pnlAjoutIngr = new JPanel();
		getContentPane().add(pnlAjoutIngr, "name_37639865607000");
		pnlAjoutIngr.setLayout(new MigLayout("", "[100px][100px][100px][100px][100px][100px]", "[50px][50px][50px][50px][50px][50px][50px][50px][50px][50px]"));
		
		//Panel Inscription
		
		JLabel lblInscriptionTitre = new JLabel("Quel bonheur d'accueillir un nouveau chef ! Entrez votre nom.");
		lblInscriptionTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		lblInscriptionTitre.setBackground(Color.LIGHT_GRAY);
		lblInscriptionTitre.setOpaque(true);
		lblInscriptionTitre.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInscription.add(lblInscriptionTitre, "cell 1 1 5 1,grow");
		
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
					// *** Méthode pour créer un utilisateur à partir du champ Text de txtboxInscription
					Database.addUser(txtboxInscription.getText());
					allUsers.add(txtboxInscription.getText());
					
					pnlInscription.setVisible(false);
					pnlMain.setVisible(true);
					txtboxInscription.setText("");
				}
			}
		});
		btnInscriptionValider.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlInscription.add(btnInscriptionValider, "cell 5 5,grow");
		
		//Panel Recette
		
		JLabel lblRecetteTitre = new JLabel("\"Titre recette\"");
		lblRecetteTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		lblRecetteTitre.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRecetteInfos.add(lblRecetteTitre, "cell 2 0,grow");
		
		JButton btnRecetteFavoris = new JButton("Favoris");
		btnRecetteFavoris.setFont(new Font("Calibri", Font.BOLD, 30));
		btnRecetteFavoris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *** Ajouter la recette aux favoris
			}
		});
		pnlRecetteInfos.add(btnRecetteFavoris, "cell 3 0,grow");
		
		JLabel lblRecetteIngredients = new JLabel("Ingredients :");
		lblRecetteIngredients.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlRecetteInfos.add(lblRecetteIngredients, "cell 2 1 2 1,grow");
		
		JPanel pnlRecetteIngredients = new JPanel();
		pnlRecetteInfos.add(pnlRecetteIngredients, "cell 2 2 2 1,grow");
		pnlRecetteIngredients.setLayout(new GridLayout(2, 0, 0, 0));
		pnlMain.setLayout(new MigLayout("", "[50px:10%,grow][300px][30%,grow][300px][50px:10%,grow]", "[5%,grow][80px][20%,grow][80px][150px][20%,grow]"));
		
		JLabel lblRecetteImage = new JLabel("\"Image\"");
		lblRecetteImage.setHorizontalAlignment(SwingConstants.CENTER);
		pnlRecetteInfos.add(lblRecetteImage, "cell 0 0 2 3,grow");
		
		JButton btnRecetteRetour = new JButton("Retour");
		btnRecetteRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlRecetteInfos.setVisible(false);
				pnlAccueil.setVisible(true);
			}
		});
		btnRecetteRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlRecetteInfos.add(btnRecetteRetour, "cell 0 3,grow");
		
		JButton btnRecetteIngredientsManquants = new JButton("Acheter les ingrédients manquants");
		btnRecetteIngredientsManquants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *** Méthode pour ajouter les ingrédients manquants à la liste de course
			}
		});
		btnRecetteIngredientsManquants.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlRecetteInfos.add(btnRecetteIngredientsManquants, "cell 2 3,grow");
		
		JButton btnRecetteValider = new JButton("Valider");
		btnRecetteValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InteractionBackFront.actualisationRecetteEtapes(pnlRecetteSteps, recette, pnlRecetteInfos, pnlAccueil, user);
				pnlRecetteInfos.setVisible(false);
				pnlRecetteSteps.setVisible(true);
			}
		});
		btnRecetteValider.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlRecetteInfos.add(btnRecetteValider, "cell 3 3,grow");
		
		//Panel Main
		
		JLabel lblMainTitre = new JLabel("Bienvenue dans la meilleure application de recherche de recette à cuisiner !");
		lblMainTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainTitre.setFont(new Font("Calibri", Font.BOLD, 35));
		lblMainTitre.setBackground(Color.LIGHT_GRAY);
		lblMainTitre.setOpaque(true);
		pnlMain.add(lblMainTitre, "cell 1 1 3 1,grow");
		
		JLabel lblMainInscription = new JLabel("Nouvel utilisateur ?");
		lblMainInscription.setFont(new Font("Calibri", Font.BOLD, 30));
		lblMainInscription.setHorizontalAlignment(SwingConstants.CENTER);
		pnlMain.add(lblMainInscription, "cell 1 3,grow");
		
		JLabel lblMainConnexion = new JLabel("Déjà inscrit ?");
		lblMainConnexion.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(lblMainConnexion, "cell 3 3,grow");
		pnlAccueil.setLayout(new MigLayout("", "[10%,grow][25%,grow][30%,grow][25%,grow][10%,grow]", "[5%,grow][50px][10%,grow][15%,grow][15%,grow][15%,grow][15%,grow][15%,grow]"));
		
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
				// *** Méthode pour récupérer les User
				pnlConnexionComptes.removeAll();
				// ***  Appeler InteractionBackFront.remplissagePanelConnexionComptes(pnlConnexionComptes, ); /!\ Ajouter le champ contenant la liste de User
				InteractionBackFront.remplissagePanelConnexionComptes2(pnlConnexionComptes, allUsers, pnlAccueil, pnlConnexion,user);
				pnlConnexion.setVisible(true);
				pnlMain.setVisible(false);
			}
		});
		btnMainConnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlMain.add(btnMainConnexion, "cell 3 4,grow");
		pnlConnexion.setLayout(new MigLayout("", "[100px:30%:300px,grow][70%,grow]", "[50px][70%,grow][50px:20%:150px,grow]"));
		
		//Panel Accueil
				
		JLabel lblAccueilTitre = new JLabel("Bienvenue dans votre cuisine, ... ! Que souhaitez-vous faire ?");
		lblAccueilTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccueilTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		lblAccueilTitre.setBackground(Color.LIGHT_GRAY);
		lblAccueilTitre.setOpaque(true);
		pnlAccueil.add(lblAccueilTitre, "cell 1 1 3 1,grow");
		
		JButton btnAccueilAddIngredient = new JButton("Ajouter un ingrédient");
		btnAccueilAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAccueil.setVisible(false);
				pnlAjoutIngr.setVisible(true);
			}
		});
		btnAccueilAddIngredient.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilAddIngredient, "cell 3 3,grow");
		
		JButton btnAccueilContentFrigo = new JButton("Voir le contenu du frigo");
		btnAccueilContentFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pnlAccueil.setVisible(false);
				pnlVoirFrigo.setVisible(true);
			}
		});
		
		JButton btnAccueilListeCourses = new JButton("Voir Liste de courses");
		btnAccueilListeCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAccueil.setVisible(false);
				pnlListeCourse.setVisible(true);
			}
		});
		btnAccueilListeCourses.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilListeCourses, "cell 1 4,grow");
		btnAccueilContentFrigo.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilContentFrigo, "cell 3 4,grow");
		
		JButton btnAccueilCheckFavorite = new JButton("Voir vos recettes favorites");
		btnAccueilCheckFavorite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAccueil.setVisible(false);
				pnlFavoris.setVisible(true);
			}
		});
		btnAccueilCheckFavorite.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilCheckFavorite, "cell 3 5,grow");

		JList listChercherRecettes = new JList();
		listChercherRecettes.setFont(new Font("Calibri", Font.PLAIN, 18));
		pnlChercherRecette.add(listChercherRecettes, "cell 1 1,grow");

		JButton btnAccueilSearchRecipes = new JButton("Chercher une recette");
		btnAccueilSearchRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChercherRecette.setVisible(true);
				pnlAccueil.setVisible(false);
			}
		});
		btnAccueilSearchRecipes.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilSearchRecipes, "cell 3 6,grow");
		
		JButton btnAccueilAllergenes = new JButton("Voir/Modifier vos allergènes");
		btnAccueilAllergenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAccueil.setVisible(false);
				pnlAllergenes.setVisible(true);
				InteractionBackFront.remplissagePanelAllergenes(user.getAllergies(), pnlAllergenesDisplayAllergenes, user);
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
				user.copy(new User(1,"",null,null,null));
				pnlAccueil.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnAccueilDeconnexion.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAccueil.add(btnAccueilDeconnexion, "cell 1 6,grow");
		pnlAllergenesRetourAdd.setLayout(new MigLayout("", "[100px:30%:300px,grow][55%,grow][50px:15%:150px]", "[150px]"));
		
		//Panel Allergènes
		
		JLabel lblAllergenesTitre = new JLabel("Vos allergènes :");
		lblAllergenesTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllergenesTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAllergenes.add(lblAllergenesTitre, "cell 0 0 3 1,alignx center");
		
		JButton btnAllergenesAdd = new JButton("+");
		btnAllergenesAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAllergenesAdd.setFont(new Font("Calibri", Font.BOLD, 97));
		btnAllergenesAdd.setForeground(Color.WHITE);
		btnAllergenesAdd.setBackground(Color.GREEN);
		btnAllergenesAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Afficher une boîte de texte
                String input = JOptionPane.showInputDialog(pnlAllergenes, "Entrez un allergène :");

                // Vérifier si l'utilisateur a cliqué sur OK ou Annuler
                if (input != null) {
                    Allergen newAllergen = new Allergen(input);
                    user.addAllergy(newAllergen);
                }
				
				InteractionBackFront.remplissagePanelAllergenes(user.getAllergies(), pnlAllergenesDisplayAllergenes, user);
			}
		});
		
		JButton btnAllergenesRetour = new JButton("Retour");
		btnAllergenesRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAllergenesRetourAdd.add(btnAllergenesRetour, "cell 0 0,grow");
		btnAllergenesRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAllergenes.setVisible(false);
				pnlAccueil.setVisible(true);
			}
		});
		btnAllergenesAdd.setIcon(new ImageIcon(".\\src\\main\\resources\\btnAllergenesAdd.png"));
		pnlAllergenesRetourAdd.add(btnAllergenesAdd, "cell 2 0,grow");
		
		//Panel Connexion
		
		JLabel lblConnexionTitre = new JLabel("Heureux de vous revoir ! Sélectionnez votre compte :");
		lblConnexionTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexionTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		lblConnexionTitre.setBackground(Color.LIGHT_GRAY);
		lblConnexionTitre.setOpaque(true);
		pnlConnexion.add(lblConnexionTitre, "cell 0 0 2 1,grow");
		
		JButton btnConnexionRetour = new JButton("Retour");
		btnConnexionRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlConnexion.setVisible(false);
				pnlMain.setVisible(true);
			}
		});
		btnConnexionRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		
		pnlConnexion.add(pnlConnexionComptes, "cell 0 1 2 1,growx,aligny center");
		pnlConnexion.add(btnConnexionRetour, "cell 0 2,grow");
		
		//Panel Favoris
		
		JLabel lblFavorisTitre = new JLabel("Vos recettes favorites :");
		lblFavorisTitre.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlFavoris.add(lblFavorisTitre, "cell 1 0,alignx left,growy");
		
		JList listFavorisRecettes = new JList();
		listFavorisRecettes.setFont(new Font("Calibri", Font.PLAIN, 18));
		listFavorisRecettes.setBackground(new Color(255, 255, 128));
		pnlFavoris.add(listFavorisRecettes, "cell 1 1,grow");
		
		JButton btnFavorisRetour = new JButton("Retour");
		btnFavorisRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlFavoris.setVisible(false);
				pnlAccueil.setVisible(true);
			}
		});
		btnFavorisRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlFavoris.add(btnFavorisRetour, "cell 0 2,grow");
		
		//Panel Chercher Recette
		
		JLabel lblChercherTitle = new JLabel("Sélectionnez une recette dans la liste ci-dessous :");
		lblChercherTitle.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlChercherRecette.add(lblChercherTitle, "cell 1 0");
		

		
		JButton btnChercherRetour = new JButton("Retour");
		btnChercherRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChercherRecette.setVisible(false);
				pnlAccueil.setVisible(true);
			}
		});
		btnChercherRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlChercherRecette.add(btnChercherRetour, "cell 0 2,grow");
		
		JButton btnChercherValider = new JButton("Valider");
		btnChercherValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *** Ajouter méthode pour récupérer la recette sur laquelle on va cliquer dans la liste pour la mettre dans la fonction suivante.
				InteractionBackFront.actualisationRecettePresentation(lblRecetteImage, lblRecetteTitre, pnlRecetteIngredients, recette);
				pnlChercherRecette.setVisible(false);
				pnlRecetteInfos.setVisible(true);
			}
		});
		btnChercherValider.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlChercherRecette.add(btnChercherValider, "cell 2 2,grow");
		
		//Panel Voir Frigo
		
		JLabel lblVoirFrigoTitle = new JLabel("Voilà les ingrédients contenus dans votre frigo :");
		lblVoirFrigoTitle.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlVoirFrigo.add(lblVoirFrigoTitle, "cell 1 0");
		
		JList listVoirFrigo = new JList();
		listVoirFrigo.setFont(new Font("Calibri", Font.PLAIN, 18));
		pnlVoirFrigo.add(listVoirFrigo, "cell 1 1,grow");
		
		JButton btnVoirFrigoRetour = new JButton("Retour");
		btnVoirFrigoRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlVoirFrigo.setVisible(false);
				pnlAccueil.setVisible(true);
			}
		});
		btnVoirFrigoRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlVoirFrigo.add(btnVoirFrigoRetour, "cell 0 2,grow");
		
		JButton btnVoirFrigoAddIngr = new JButton("+");
		btnVoirFrigoAddIngr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAjoutIngr.setVisible(true);
				pnlVoirFrigo.setVisible(false);
			}
		});
		btnVoirFrigoAddIngr.setVerticalAlignment(SwingConstants.BOTTOM);
		btnVoirFrigoAddIngr.setFont(new Font("Calibri", Font.BOLD, 97));
		btnVoirFrigoAddIngr.setForeground(Color.WHITE);
		btnVoirFrigoAddIngr.setBackground(Color.GREEN);
		pnlVoirFrigo.add(btnVoirFrigoAddIngr, "cell 2 2,grow");
		
		//Panel Liste Course
		
		JLabel lblListeCourseTitle = new JLabel("Voici votre liste de courses :");
		lblListeCourseTitle.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlListeCourse.add(lblListeCourseTitle, "cell 1 0");
		
		JList listListeCourse = new JList();
		listListeCourse.setFont(new Font("Calibri", Font.PLAIN, 18));
		listListeCourse.setBackground(new Color(255, 255, 128));
		listListeCourse.setForeground(new Color(0, 0, 0));
		pnlListeCourse.add(listListeCourse, "cell 1 1,grow");
		
		JButton btnListeCourseRetour = new JButton("Retour");
		btnListeCourseRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlListeCourse.setVisible(false);
				pnlAccueil.setVisible(true);
			}
		});
		btnListeCourseRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlListeCourse.add(btnListeCourseRetour, "cell 0 2,grow");
		
		JButton btnListeCourseAdd = new JButton("+");
		btnListeCourseAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		btnListeCourseAdd.setFont(new Font("Calibri", Font.BOLD, 97));
		btnListeCourseAdd.setForeground(Color.WHITE);
		btnListeCourseAdd.setBackground(Color.GREEN);
		pnlListeCourse.add(btnListeCourseAdd, "cell 2 2,grow");
		
		//Panel Ajout Ingrédient
		
		JLabel lblAjoutIngrTitle = new JLabel("Entrez le nom d'un ingrédient :");
		lblAjoutIngrTitle.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrTitle, "cell 0 0 6 1,grow");
		
		txtboxAjoutIngrNom = new JTextField();
		txtboxAjoutIngrNom.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(txtboxAjoutIngrNom, "cell 0 1 6 1,grow");
		txtboxAjoutIngrNom.setColumns(10);
		
		JLabel lblAjoutIngrQte = new JLabel("Saisir la quantité :");
		lblAjoutIngrQte.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(lblAjoutIngrQte, "flowx,cell 0 3 3 1,grow");
		
		JComboBox cmbboxAjoutIngrUnites = new JComboBox();
		cmbboxAjoutIngrUnites.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(cmbboxAjoutIngrUnites, "cell 5 3,grow");
		
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
		
		JButton btnAjoutIngrRetour = new JButton("Retour");
		btnAjoutIngrRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlAjoutIngr.setVisible(false);
				pnlVoirFrigo.setVisible(true);
			}
		});
		btnAjoutIngrRetour.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAjoutIngr.add(btnAjoutIngrRetour, "cell 0 9 3 1,grow");
		
		JButton btnAjoutIngrValider = new JButton("Valider");
		btnAjoutIngrValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// *** Ajouter une méthode pour récupérer les infos entrées et créer un ingrédient avec.
				pnlAjoutIngr.setVisible(false);
				pnlVoirFrigo.setVisible(true);
			}
		});
		btnAjoutIngrValider.setFont(new Font("Calibri", Font.BOLD, 30));
		pnlAjoutIngr.add(btnAjoutIngrValider, "cell 3 9 3 1,grow");
		
		JFormattedTextField txtboxAjoutIngrQte = new JFormattedTextField();
		txtboxAjoutIngrQte.setFont(new Font("Calibri", Font.PLAIN, 30));
		pnlAjoutIngr.add(txtboxAjoutIngrQte, "cell 3 3 2 1,grow");
	}
}
