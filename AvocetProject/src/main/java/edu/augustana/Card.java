package edu.augustana;

public class Card{
    private String code;
    private String event;
    private String category;
    private String title;
    private String img;
    private String gender;
    private String modelSex;
    private String level;
    private String equipment;
    private String keywords;
    private String packName;



    public Card(String code, String event, String category, String title, String img, String gender, String modelSex,String level, String equipment, String keywords, String packName) {
        this.code = code;
        this.event = event;
        this.category = category;
        this.title = title;
        this.img = img;
        this.gender = gender;
        this.modelSex = modelSex;
        this.level = level;
        this.equipment = equipment;
        this.keywords = keywords;
        this.packName = packName;

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

    public String getModelSex() {return modelSex;}

    public String getLevel() {return level;}

    public String getEquipment() {return equipment;}

    public String getKeywords() {return keywords;}

    public String getGender() {
        return gender;
    }

    public String getPackName() {
        return packName;
    }


//    public String[] getLevelList() {
//        String[] levels;
//        levels = level.split(" ");
//        return levels;
//    }

    public String[] getLevelList() {
        // Split the level string based on spaces and commas
        String[] levels = level.split("[,\\s]+");
        return levels;
    }


}
