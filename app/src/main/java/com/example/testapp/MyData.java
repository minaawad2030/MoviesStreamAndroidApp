package com.example.testapp;

class MyData {
    private String imageURL;
    private String description;
    private String name;
    private int id;
    private String videoURL;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public MyData(String imageURL, String description, int id,String name,String videoURL) {
        this.imageURL = imageURL;
        this.description = description;
        this.id = id;
        this.name=name;
        this.videoURL=videoURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
