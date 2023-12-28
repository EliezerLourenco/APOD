package com.example.apod;

/**
 * Represents the data for an astronomy picture. This class encapsulates details such as the URL, title,
 * explanation, date, and the media type of the astronomy picture.
 */

public class AstronomyPictureData {

    private String url;
    private String title;
    private String explanation;
    private String date;
    private String mediaType;

    // Constructor
    public AstronomyPictureData(String url, String title, String explanation, String date, String mediaType) {
        this.url = url;
        this.title = title;
        this.explanation = explanation;
        this.date = date;
        this.mediaType = mediaType; // mediaType is used here because the API can eventually return video content instead of an image
    }

    // Getters
    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getDate() {
        return date;
    }

    public String getMediaType() {
        return mediaType;
    }


}
