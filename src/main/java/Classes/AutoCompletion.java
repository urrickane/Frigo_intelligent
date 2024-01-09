package Classes;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;

public class AutoCompletion {	
	
	public static void setupAutoComplete(List<String> words, JTextField textField) {
        AutoCompleteDocumentListener listener = new AutoCompleteDocumentListener(words, textField);
        textField.getDocument().addDocumentListener(listener);
    }

    private static class AutoCompleteDocumentListener implements DocumentListener {

        private static List<String> baseWordList;
        private static List<String> filteredWordList;
        private static JTextField textField;

        /*
         * Constructeur de base
         */
        public AutoCompleteDocumentListener(List<String> wordsList, JTextField textBox) {
        	baseWordList = wordsList;
        	filteredWordList = new ArrayList();
            textField = textBox;
        }
        
        /*
         * Surcharge du constructeur en cas de besoin
         */
        public AutoCompleteDocumentListener(List<String> wordsList, JTextField textBox, List<String> alteredWordList) {
        	baseWordList = wordsList;
        	filteredWordList = alteredWordList;
            textField = textBox;
        }

        /*
         * Méthode appelée en cas d'insertion d'un caractère dans la boite de texte
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
        	filterAutoCompletionAltered();
        }

        /*
         * Méthode appelée en cas de suppression d'un caractère dans la boite de texte
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
        	filterAutoCompletionBase();
        }
        
        /*
         * Méthode en cas de changement du contenu de la boite de texte. Si c'est uniquement un changement de la longueur de la chaine, les deux méthodes au-dessus seront appelées.
         * Celle-ci ne sera utilisée qu'en cas de changement de police ou de casse par exemple, ce qui ne devrait pas arriver.
         */
        @Override
		public void changedUpdate(DocumentEvent e) {
			// Normalement inutile dans notre cas mais obligatoire pour la classe AutoCompleteDocumentListener
		}
        
        /*
         * Méthode remplissant filteredWordList à partir de la liste de mots de base donnée par le back
         */
        public static void filterAutoCompletionBase(){
    		List<String> newWordList = new ArrayList();
    		int i = 0;
    		
    		// On parcourt l'intégralité de la liste de mots et on récupère uniquement ceux qui commencent par les mêmes lettres que le contenu de la boite de texte dans newWordList
    		while(i < baseWordList.size())
    		{
    			if(baseWordList.get(i).toLowerCase().startsWith(textField.getText().toLowerCase()))
    			{
    				newWordList.add(baseWordList.get(i));
    			}
    			i++;
    		}
    		filteredWordList = newWordList;
    	}
        
        /*
         * Méthode remplissant filteredWordList à partir de la filteredWordList précédemment modifiée
         */
        public static void filterAutoCompletionAltered(){
    		List<String> newWordList = new ArrayList();
    		int i = 0;
    		
    		// On parcourt l'intégralité de la liste de mots et on récupère uniquement ceux qui commencent par les mêmes lettres que le contenu de la boite de texte dans newWordList
    		while(i < filteredWordList.size())
    		{
    			if(filteredWordList.get(i).toLowerCase().startsWith(textField.getText().toLowerCase()))
    			{
    				newWordList.add(filteredWordList.get(i));
    			}
    			i++;
    		}
    		filteredWordList = newWordList;
    	}
        
        /*
         * Méthode retournant filteredWordList
         */
        public static List<String> getFilteredWordList()
        {
        	return filteredWordList;
        }
    }
}
