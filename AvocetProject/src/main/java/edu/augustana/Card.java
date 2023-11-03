package edu.augustana;

public class Card{
    private String code;
    private String event;
    private String category;
    private String title;
    private String img;
    private String gender;



    public Card(String code, String event, String category, String title, String img, String gender) {
        this.code = code;
        this.event = event;
        this.category = category;
        this.title = title;
        this.img = img;
        this.gender = gender;

    }

    public boolean matchesSearchText(String searchText) {
        searchText = searchText.toLowerCase(); // Convert search text to lowercase for case-insensitive search

        // Check if any of the card's properties contain the search text
        return code.toLowerCase().contains(searchText)
                || event.toLowerCase().contains(searchText)
                || category.toLowerCase().contains(searchText)
                || title.toLowerCase().contains(searchText)
                || gender.toLowerCase().contains(searchText);
    }

    public String getCode() {
        return code;
    }

    public String getEvent() {
        return event;
    }
    public String getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public String getImg() {
        return img;
    }

    public String getGender() {
        return gender;
    }
}
