package edu.augustana;

import javafx.scene.image.Image;


/**
 * Represents a card in the application.
 */
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

    /**
     * Constructs a Card object with the specified parameters.
     *
     * @param code        The code of the card.
     * @param event       The event associated with the card.
     * @param category    The category of the card.
     * @param title       The title of the card.
     * @param imgFileName The image file name of the card.
     * @param gender      The gender associated with the card.
     * @param modelSex    The model sex associated with the card.
     * @param level       The level of the card.
     * @param equipment   The equipment associated with the card.
     * @param keywords    The keywords associated with the card.
     * @param packName    The name of the pack to which the card belongs.
     */
    public Card(String code, String event, String category, String title, String imgFileName, String gender, String modelSex,String level, String equipment, String keywords, String packName) {
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
    }

    /**
     * Checks if the card matches the given search text.
     *
     * @param searchText The search text to match against.
     * @return True if the card matches the search text, false otherwise.
     */
    public boolean matchesSearchText(String searchText) {
        searchText = searchText.toLowerCase();

        return code.toLowerCase().contains(searchText)
                || event.toLowerCase().contains(searchText)
                || category.toLowerCase().contains(searchText)
                || title.toLowerCase().contains(searchText)
                || gender.toLowerCase().contains(searchText);
    }

    /**
     * Gets the code of the card.
     *
     * @return The code of the card.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the event associated with the card.
     *
     * @return The event associated with the card.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Gets the category of the card.
     *
     * @return The category of the card.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the title of the card.
     *
     * @return The title of the card.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the image file name of the card.
     *
     * @return The image file name of the card.
     */
    public String getImageFileName() {
        return imageFileName;
    }

    /**
     * Gets the image thumbnail of the card.
     *
     * @return The image thumbnail of the card.
     */
    public Image getImageThumbnail() { return imageThumbnail; }

    /**
     * Gets the model sex associated with the card.
     *
     * @return The model sex associated with the card.
     */
    public String getModelSex() {return modelSex;}

    /**
     * Gets the level of the card.
     *
     * @return The level of the card.
     */
    public String getLevel() {return level;}

    /**
     * Gets the equipment associated with the card.
     *
     * @return The equipment associated with the card.
     */
    public String getEquipment() {return equipment;}

    /**
     * Gets the keywords associated with the card.
     *
     * @return The keywords associated with the card.
     */
    public String getKeywords() {return keywords;}

    /**
     * Gets the gender associated with the card.
     *
     * @return The gender associated with the card.
     */
    public String getGender() {return gender;}

    /**
     * Gets the name of the pack to which the card belongs.
     *
     * @return The name of the pack to which the card belongs.
     */
    public String getPackName() {return packName;}

    /**
     * Gets the levels associated with the card as an array.
     *
     * @return The levels associated with the card as an array.
     */
    public String[] getLevelList() {
        String[] levels = level.split("[,\\s]+");
        return levels;
    }
}
