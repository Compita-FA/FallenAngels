package com.example.fallenangels.adoption.dogObject;

public class Dogs {

    private String Breed;
    private String DOB;
    private String Gender;
    private String History;
    private String ID;
    private String Intake;
    private String Name;
    private String Picture;
    private String Suit;
    private String Nature;

    public Dogs() {}

    public Dogs(String breed, String DOB, String gender, String history,
                String ID, String intake, String name, String imageID, String suit, String nature) {
        this.Breed = breed;
        this.DOB = DOB;
        this.Gender = gender;
        this.History = history;
        this.ID = ID;
        this.Intake = intake;
        this.Name = name;
        this.Picture = imageID;
        this.Suit = suit;
        this.Nature = nature;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        this.Breed = breed;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getHistory() {
        return History;
    }

    public void setHistory(String history) {
        this.History = history;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIntake() {
        return Intake;
    }

    public void setIntake(String intake) {
        this.Intake = intake;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        this.Picture = picture;
    }

    public String getSuit() {
        return Suit;
    }

    public void setSuit(String suit) {
        this.Suit = suit;
    }

    public String getNature() {
        return Nature;
    }

    public void setNature(String nature) {
        Nature = nature;
    }

}
