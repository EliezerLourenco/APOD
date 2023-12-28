package com.example.apod;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Controller class for the NASA Space Image Viewer. This class handles user interactions,
 * processes data requests, and updates the UI accordingly.
 */

public class Controller {
    @FXML
    private Button getApodButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label explanationLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private DatePicker datePicker;

    /**
     * Event handler for the DatePicker. Fetches the astronomy picture of the day for the selected date.
     */
    @FXML
    private void handleGetImageForDate() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            getAPOD(selectedDate.format(DateTimeFormatter.ISO_DATE));
        } else {
            titleLabel.setText("Please select a date first.");
            explanationLabel.setText("");
            imageView.setImage(null);
        }
    }

    /**
     * Event handler for the "Get Today's APOD" button. Fetches today's astronomy picture of the day.
     */
    @FXML
    private void getTodaysApod() {
        getAPOD(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    }

    /**
     * Fetches the Astronomy Picture of the Day (APOD) from NASA's API based on the provided date.
     * Updates the UI with the fetched data.
     *
     * @param date The date for which to fetch the APOD in ISO format (YYYY-MM-DD).
     */
    private void getAPOD(String date) {
        String apiKey = "rmWoHfkWKUchgKHduydsWYI8oksoSQ6ASjLZHecA";
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey + "&date=" + date;

        try {
            String jsonResponse = NasaApiUtil.fetchDataFromAPI(apiUrl);
            AstronomyPictureData apodData = NasaApiUtil.parseJson(jsonResponse);
            titleLabel.setText(apodData.getTitle());
            explanationLabel.setText(apodData.getExplanation());

            if ("image".equals(apodData.getMediaType())) {
                Image image = new Image(apodData.getUrl());
                imageView.setImage(image);
            } else {
                imageView.setImage(null);
                titleLabel.setText("Today's APOD is not an image.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            titleLabel.setText("Error fetching APOD data");
            explanationLabel.setText("");
        }
    }
}
