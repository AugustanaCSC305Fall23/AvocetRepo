package edu.augustana;

public class Card {
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
