package edu.augustana;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

import java.awt.*;

public class Card{
    private String code;
    private String event;
    private String category;
    private String title;
    private String imageFileName;
    private transient Image imageThumbnail;
    private String gender;
    private String modelSex;
    private String level;
    private String equipment;
    private String keywords;
    private String packName;

    private Boolean isFavorite;

    public Card(String code, String event, String category, String title, String imgFileName, String gender, String modelSex, String level, String equipment, String keywords, String packName) {
        this.code = code;
        this.event = event;
        this.category = category;
        this.title = title;
        this.imageFileName = imgFileName;
        this.imageThumbnail = new Image("file:packs/" + packName + "/thumbs/" + imgFileName + ".jpg");
        this.gender = gender;
        this.modelSex = modelSex;
        this.level = level;
        this.equipment = equipment;
        this.keywords = keywords;
        this.packName = packName;
        this.isFavorite = false;


    }

    public boolean matchesSearchText(String searchText) {
        searchText = searchText.toLowerCase();

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

    public String getImageFileName() {
        return imageFileName;
    }

    public Image getImageThumbnail() { return imageThumbnail; }

    public String getModelSex() {return modelSex;}

    public String getLevel() {return level;}

    public String getEquipment() {return equipment;}

    public String getKeywords() {return keywords;}

    public String getGender() {return gender;}

    public String getPackName() {return packName;}

    public String[] getLevelList() {
        String[] levels = level.split("[,\\s]+");
        return levels;
    }
    public void setFavoriteStatus(Boolean status) {
        isFavorite = status;
    }
    public boolean isFavorite() {
        return isFavorite;
    }


}
