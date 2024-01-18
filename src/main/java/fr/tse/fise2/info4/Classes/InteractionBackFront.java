package fr.tse.fise2.info4.Classes;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.tse.fise2.info4.Database;
import net.miginfocom.swing.MigLayout;

public class InteractionBackFront {
	/*
	 * Méthode qui affiche les informations de la recette sur le panel pnlRecettePresentation.
	 */
	public static void actualisationRecettePresentation(JLabel recipeImage, JLabel recipeTitle, JPanel recipeIngredients, Recipe recipe)
	{
		// Affichage de l'image
        try {
            URL url = new URL(recipe.getLinkToImage());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
            recipeImage.setIcon(imageIcon);
            recipeImage.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Affichage du titre
        recipeTitle.setText(recipe.getTitle() + " (" + recipe.getCookingTime() + " min)");
        recipeTitle.setFont(new Font("Calibri", Font.BOLD, 40));
        
        // Affichage des ingrédients
        int i = 0;
        while(i < recipe.getL_ingredients().size())
        {
        	JLabel label = new JLabel();
        	label.setText("<html><div style='white-space: pre-wrap;'> -" + recipe.getL_ingredients().get(i).getName() + ": " + recipe.getL_ingredients().get(i).getQuantity() + " " + recipe.getL_ingredients().get(i).getUnit() + "</div></html>");
        	label.setFont(new Font("Calibri", Font.PLAIN, 30));
            recipeIngredients.add(label);
            i++;
        }
	}
	
	/*
	 * Méthode qui affiche les différentes étapes de la recette sur différents panels que l'on peut parcourir avec des boutons Suivant et Précedent.
	 */
	public static void actualisationRecetteEtapes(JPanel pnlRecetteSteps, Recipe recipe, JPanel pnlRecetteInfos, JPanel pnlAccueil,User user)
	{
		// Affichage des étapes
        int i = 0;
        String pnlNameCreation;
        
        //Création d'une structure de données qui relie un String à un JPanel.
        Map<String, JPanel> panelMap = new HashMap<>();
        
        //Tant qu'il reste des étapes de la recette dans la liste...
        while(i < recipe.getL_steps().size())
        {           
        	//On crée un nouveau panel
        	pnlNameCreation = "pnlRecetteStep" + (i + 1);
            JPanel pnlStepx = new JPanel();
            pnlRecetteSteps.add(pnlStepx);
    		pnlStepx.setLayout(new MigLayout("", "[100px:25%:300px,grow][12%,grow][100px:25%:300px,grow][12%,grow][100px:25%:300px,grow]", "[50px][75%,grow][50px:20%:150px,grow]"));
    		pnlStepx.setName(pnlNameCreation);
    		//On ajoute le panel ainsi que son nom dans la structure de donnée décrite ligne 55.
    		panelMap.put(pnlNameCreation, pnlStepx);
    		
    		//On crée un nouveau label qui va contenir le numéro de l'étape et qu'on va placer en haut du panel.
    		JLabel lblStepNb = new JLabel("Step " + (i + 1));
    		lblStepNb.setFont(new Font("Calibri", Font.BOLD, 40));
    		lblStepNb.setHorizontalAlignment(SwingConstants.CENTER);
    		pnlStepx.add(lblStepNb, "cell 0 0 5 1,grow");
    		
    		//On crée un nouveau label qui va contenir le texte de l'étape et on le place au milieur du panel.
    		JLabel lblStep = new JLabel();
    		lblStep.setText("<html><div style='white-space: pre-wrap;'>" + recipe.getL_steps().get(i) + "</div></html>");
        	lblStep.setFont(new Font("Calibri", Font.PLAIN, 40));
        	lblStep.setHorizontalAlignment(SwingConstants.CENTER);
        	pnlStepx.add(lblStep, "cell 0 1 5 1,grow");
    		
        	//Si on ne se trouve pas à l'étape 1...
    		if(i != 0)
    		{
    			//On crée un bouton qui va nous permettre de revenir à l'étape précédente par rapport à l'étape actuelle.
    			JButton btnEtapePrec = new JButton("Etape précédente");
    			btnEtapePrec.setFont(new Font("Calibri", Font.BOLD, 30));
    			pnlStepx.add(btnEtapePrec, "cell 0 2,grow");
    			
    			//Quand on clique sur le bouton...
        		btnEtapePrec.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				//On récupère le nom du panel auquel est rattaché le bouton.
        				String pnlNameGathering = btnEtapePrec.getParent().getName();
        				
        				//On récupère le dernier caractère de son nom (pnlRecetteStepX avec X >= 1) qui contient le numéro du panel.
        				int numPanel = (int) (pnlNameGathering.charAt(pnlNameGathering.length() - 1) - '0');
        				
        				//On ferme le panel actuel et on ouvre le précédent.
        				panelMap.get("pnlRecetteStep" + (numPanel - 1)).setVisible(true);
        				panelMap.get("pnlRecetteStep" + numPanel).setVisible(false);
        			}
        		});
    		}
    		
    		//On crée un bouton Retour qui nous permet de quitter la recette.
    		JButton btnEtapeRetour = new JButton("Retour");
    		btnEtapeRetour.setFont(new Font("Calibri", Font.BOLD, 30));
    		pnlStepx.add(btnEtapeRetour, "cell 2 2,grow");
    		btnEtapeRetour.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				pnlRecetteSteps.setVisible(false); 
    				pnlRecetteInfos.setVisible(true);
    				pnlRecetteSteps.removeAll();
    			}
    		});
    		
    		//Si on ne se trouve pas à la dernière étape...
    		if(i < recipe.getL_steps().size() - 1)
    		{
    			//On crée un bouton qui va nous permettre d'aller à l'étape suivante par rapport à l'étape actuelle.
    			JButton btnEtapeSuiv = new JButton("Etape suivante");
    			btnEtapeSuiv.setFont(new Font("Calibri", Font.BOLD, 30));
    			pnlStepx.add(btnEtapeSuiv, "cell 4 2,grow");
    			
    			//Quand on clique sur le bouton
        		btnEtapeSuiv.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				//On récupère le nom du panel auquel est rattaché le bouton.
        				String pnlNameGathering = btnEtapeSuiv.getParent().getName();
        				
        				//On récupère le dernier caractère de son nom (pnlRecetteStepX avec X >= 1) qui contient le numéro du panel.
        				int numPanel = (int) (pnlNameGathering.charAt(pnlNameGathering.length() - 1) - '0');
        				
        				//On ferme le panel actuel et on ouvre le suivant.
        				panelMap.get("pnlRecetteStep" + (numPanel + 1)).setVisible(true);
        				panelMap.get("pnlRecetteStep" + numPanel).setVisible(false);
        			}
        		});
    		}
    		else
    		{
    			//Sinon on crée un bouton qui va permettre de quitter la recette et d'enlever les ingrédients utilisés.
    			JButton btnTerminerRecette = new JButton("Terminer");
    			btnTerminerRecette.setFont(new Font("Calibri", Font.BOLD, 30));
    			pnlStepx.add(btnTerminerRecette, "cell 4 2,grow");
    			
    			//Quand on clique sur le bouton
        		btnTerminerRecette.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {

						for (Ingredient ingredient : recipe.getL_usedIngredients()){
							user.RemoveIngredient(ingredient);
						}

        				// *** Afficher le panel après l'affichage de la recette
        				pnlRecetteSteps.setVisible(false);
        				pnlAccueil.setVisible(true);
        				pnlRecetteSteps.removeAll();
        			}
        		});
    		}
    		
    		i++;
        }
	}
	
	/*
	 * Méthode qui affiche tous les comptes créés sur le panel Connexion.
	 */
	public static void remplissagePanelConnexionComptes(JPanel pnlConnexionComptes, List<User> comptes, JPanel pnlAccueil, JPanel pnlConnexion)
	{
		int i = 0;
		while(i < comptes.size())
		{
			JButton button = new JButton();
			button.setText(comptes.get(i).getName());
			button.setFont(new Font("Calibri", Font.PLAIN, 30));
			button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				// *** Méthode pour récupérer un User à partir du champ Text du bouton qui contient le nom du User.
    				pnlAccueil.setVisible(true);
    				pnlConnexion.setVisible(false);
    			}
			});
			pnlConnexionComptes.add(button);
			i++;
		}
	}
	
	/*
	 * A supprimer
	 */
	public static void remplissagePanelConnexionComptes2(JPanel pnlConnexionComptes, List<String> comptes, JPanel pnlAccueil, JPanel pnlConnexion, User user)
	{
		int i = 0;
		while(i < comptes.size())
		{
			JButton button = new JButton();
			String name =comptes.get(i);
			button.setText(comptes.get(i));
			button.setFont(new Font("Calibri", Font.BOLD, 30));
			button.setPreferredSize(new Dimension(300, 150));
			button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				// *** Méthode pour récupérer un User à partir du champ Text du bouton qui contient le nom du User.
    				System.out.println("L'utilisateur choisit est : "+name);
					user.copy(Database.getUser(name));
    				pnlAccueil.setVisible(true);
    				pnlConnexion.setVisible(false);
    				
    			}
			});
			pnlConnexionComptes.add(button);
			i++;
		}
	}
	
	/*
	 * Méthode qui affiche tous les allergènes créés dans le panel pnlAllergenesDisplayAllergenes
	 */
	public static void remplissagePanelAllergenes(List<Allergen> l_allergen, JPanel pnlAllergenes,User user) {
		int i = 0;
		AllergenButton btnAllergen;
		
		//On supprime les éléments déjà présents.
		pnlAllergenes.removeAll();
		pnlAllergenes.revalidate();
		pnlAllergenes.repaint();
		
		//On crée et on ajoute les allergènes au panel tant que la liste n'est pas vide.
		while(i < l_allergen.size())
		{
			btnAllergen = new AllergenButton(l_allergen.get(i).getaName(),user);
			
			// Créer un Box pour chaque paire JLabel et JButton
            Box box = Box.createHorizontalBox();
            box.add(btnAllergen.getLblAllergen());
            box.add(btnAllergen.getBtnSuppr());

            // Ajouter le Box au panneau principal
            pnlAllergenes.add(box);
			i++;
		}
	}

	/*
	 * Méthode qui affiche tous les ingrédients créés dans les panels pnlListeCourse et pnlVoirFrigo
	 */
	public static void remplissagePanelIngredientButton(List<Ingredient> l_ingredients, JPanel pnlIngredientButton) {
		int i = 0;
		IngredientButton btnIngredient;

		//On supprime les éléments déjà présents.
		pnlIngredientButton.removeAll();
		pnlIngredientButton.revalidate();
		pnlIngredientButton.repaint();

		//On crée et on ajoute les allergènes au panel tant que la liste n'est pas vide.
		while(i < l_ingredients.size())
		{
			btnIngredient = new IngredientButton(l_ingredients.get(i), pnlIngredientButton);

			// Créer un Box pour chaque paire JLabel et JButton
			Box box = Box.createHorizontalBox();
			box.add(btnIngredient.getLblIngredient());
			box.add(btnIngredient.getBtnModif());

			// Ajouter le Box au panneau principal
			pnlIngredientButton.add(box);
			i++;
		}
	}
	public static void remplissagePanelFavoris(List<Recipe> l_favoris, JPanel pnlFavorisRecettes, JLabel lblRecetteImage,JLabel lblRecetteTitre, JPanel pnlRecetteIngredients,JPanel pnlRecetteInfos,Recipe recipeToReturn,User user) {
		int i = 0;
		RecipeButton btnRecipe;

		//On supprime les éléments déjà présents.
		pnlFavorisRecettes.removeAll();
		pnlFavorisRecettes.revalidate();
		pnlFavorisRecettes.repaint();

		//On crée et on ajoute les allergènes au panel tant que la liste n'est pas vide.
		while(i < l_favoris.size())
		{
			btnRecipe = new RecipeButton(l_favoris.get(i),user,lblRecetteImage,lblRecetteTitre,pnlRecetteIngredients,pnlFavorisRecettes,pnlRecetteInfos,recipeToReturn);

			// Créer un Box pour chaque paire JLabel et JButton
			Box box = Box.createHorizontalBox();
			box.add(btnRecipe.getLblImageRecette());
			box.add(btnRecipe.getBtnNomRecette());
			box.add(btnRecipe.getBtnSuppr());

			// Ajouter le Box au panneau principal
			pnlFavorisRecettes.add(box);
			i++;
		}
	}

	public static void remplissagePanelRecherche(List<Recipe> l_recipe, JPanel pnlChercherRecettes, JLabel lblRecetteImage,JLabel lblRecetteTitre, JPanel pnlRecetteIngredients,JPanel pnlRecetteInfos,Recipe recipeToReturn, User user) {
		int i = 0;
		RecipeButton btnRecipe;

		//On supprime les éléments déjà présents.
		pnlChercherRecettes.removeAll();
		pnlChercherRecettes.revalidate();
		pnlChercherRecettes.repaint();

		//On crée et on ajoute les allergènes au panel tant que la liste n'est pas vide.
		while(i < l_recipe.size())
		{
			btnRecipe = new RecipeButton(l_recipe.get(i),user,lblRecetteImage,lblRecetteTitre,pnlRecetteIngredients,pnlChercherRecettes,pnlRecetteInfos,recipeToReturn);

			// Créer un Box pour chaque paire JLabel et JButton
			Box box = Box.createHorizontalBox();
			box.add(btnRecipe.getLblImageRecette());
			box.add(btnRecipe.getBtnNomRecette());

			// Ajouter le Box au panneau principal
			pnlChercherRecettes.add(box);
			i++;
		}
	}
}





