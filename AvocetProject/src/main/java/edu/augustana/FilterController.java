package edu.augustana;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class FilterController {
    public static List<Card> cardGridHandler(ComboBox<String> eventFilterComboBox, ComboBox<String> genderFilterComboBox, ComboBox<String> levelFilterComboBox, TextField searchBox){
        return searchFunction(levelFunction(genderFunction(eventFunction(eventFilterComboBox), genderFilterComboBox), levelFilterComboBox), searchBox);
    }
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

    private static List<Card> genderFunction(List<Card> tempList, ComboBox<String> genderFilterComboBox) {
        List<Card> genderOutputList = new ArrayList<>();
        String keywords = genderFilterComboBox.getValue();
        if (keywords == null || keywords.equals("All Genders")) {
            genderOutputList = tempList;
        } else {
            for (Card myCard : tempList) {
                if (myCard.getGender().contains(keywords)) {
                    genderOutputList.add(myCard);
                }
            }
        }
        return genderOutputList;
    }

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
        }
    }
}