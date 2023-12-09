package edu.augustana;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages filtering of cards based on user selections.
 */
public class FilterController {

    /**
     * Filters cards based on user selections and search input.
     *
     * @param eventFilterComboBox   The ComboBox for event filtering.
     * @param genderFilterComboBox  The ComboBox for gender filtering.
     * @param levelFilterComboBox   The ComboBox for level filtering.
     * @param modelFilterComboBox   The ComboBox for model filtering.
     * @param searchBox             The TextField for search input.
     * @return                      The list of cards after applying filters.
     */
    public static List<Card> cardGridHandler(ComboBox<String> eventFilterComboBox, ComboBox<String> genderFilterComboBox, ComboBox<String> levelFilterComboBox, ComboBox<String> modelFilterComboBox, TextField searchBox){
        return searchFunction(modelFunction(levelFunction(genderFunction(eventFunction(eventFilterComboBox), genderFilterComboBox), levelFilterComboBox), modelFilterComboBox), searchBox);
    }

    /**
     * Filters cards based on the selected event.
     *
     * @param eventFilterComboBox   The ComboBox for event filtering.
     * @return                      The list of cards after event filtering.
     */
    private static List<Card> eventFunction(ComboBox<String> eventFilterComboBox) {
        List<Card> eventOutputList = new ArrayList<>();
        String keywords = eventFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Events")) {
            eventOutputList = App.getCardCollection();
        } else {
            for (Card myCard : App.cardCollection) {
                if (myCard.getEvent().contains(keywords)) {
                    eventOutputList.add(myCard);
                }
            }
        }
        return eventOutputList;
    }

    /**
     * Filters cards based on the selected gender.
     *
     * @param tempList              The list of cards after previous filtering.
     * @param genderFilterComboBox  The ComboBox for gender filtering.
     * @return                      The list of cards after gender filtering.
     */
    private static List<Card> genderFunction(List<Card> tempList, ComboBox<String> genderFilterComboBox) {
        List<Card> genderOutputList = new ArrayList<>();
        String keywords = genderFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Genders")) {
            genderOutputList = tempList;
        } else {
            for (Card myCard : tempList) {
                if (myCard.getGender().contains(keywords) || myCard.getGender().contains("N")) {
                    genderOutputList.add(myCard);
                }
            }
        }
        return genderOutputList;
    }

    /**
     * Filters cards based on the selected level.
     *
     * @param tempList              The list of cards after previous filtering.
     * @param levelFilterComboBox   The ComboBox for level filtering.
     * @return                      The list of cards after level filtering.
     */
    private static List<Card> levelFunction(List<Card> tempList, ComboBox<String> levelFilterComboBox) {
        List<Card> levelOutputList = new ArrayList<>();
        String keywords = levelFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Levels") || keywords.equals("ALL")) {
            levelOutputList = tempList;
        } else {
            for (Card myCard : tempList) {
                String[] cardLevels = myCard.getLevelList();

                for (String level : cardLevels) {
                    if (keywords.equals(level)) {
                        levelOutputList.add(myCard);
                    }
                }
            }
        }
        return levelOutputList;
    }

    /**
     * Filters cards based on the selected model.
     *
     * @param tempList              The list of cards after previous filtering.
     * @param modelFilterComboBox   The ComboBox for model filtering.
     * @return                      The list of cards after model filtering.
     */
    private static List<Card> modelFunction(List<Card> tempList, ComboBox<String> modelFilterComboBox) {
        List<Card> modelOutputList = new ArrayList<>();
        String keywords = modelFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Models")) {
            modelOutputList = tempList;
        } else {
            for (Card myCard : tempList) {
                if (myCard.getModelSex().contains(keywords)) {
                    modelOutputList.add(myCard);
                }
            }
        }
        return modelOutputList;
    }

    /**
     * Filters cards based on the search input.
     *
     * @param tempList   The list of cards after previous filtering.
     * @param searchBox  The TextField for search input.
     * @return           The list of cards after search filtering.
     */
    private static List<Card> searchFunction(List<Card> tempList, TextField searchBox) {
        String searchText = searchBox.getText().toLowerCase();
        List<Card> searchOutputList = new ArrayList<>();
        for (Card myCard : tempList) {
            if (myCard.matchesSearchText(searchText)) {
                searchOutputList.add(myCard);
            }
        }
        return searchOutputList;
    }

    /**
     * Initializes ComboBox options based on the specified category.
     *
     * @param comboBox  The ComboBox to be initialized.
     * @param category  The category for ComboBox initialization.
     */
    public static void comboBoxInitializer(ComboBox<String> comboBox, String category) {
        if (category.equals("event")) {
            comboBox.getItems().add("All Events");
            for (Card c : App.cardCollection) {
                if (!comboBox.getItems().contains(c.getEvent())) {
                    comboBox.getItems().add(c.getEvent());
                }
            }
        } else if (category.equals("gender")) {
            comboBox.getItems().add("All Genders");
            for (Card c : App.cardCollection) {
                if (!comboBox.getItems().contains(c.getGender())) {
                    comboBox.getItems().add(c.getGender());
                }
            }
        } else if (category.equals("level")) {
            comboBox.getItems().add("All Levels");
            for (Card c : App.cardCollection) {
                String[] lvl = c.getLevelList();
                for (String tempLevel : lvl) {
                    if (!comboBox.getItems().contains(tempLevel)) {
                        comboBox.getItems().add(tempLevel);
                    }
                }
            }
        } else if (category.equals("model")) {
            comboBox.getItems().add("All Models");
            for (Card c : App.cardCollection) {
                if (!comboBox.getItems().contains(c.getModelSex())) {
                    comboBox.getItems().add(c.getModelSex());
                }
            }
        }
    }
}
