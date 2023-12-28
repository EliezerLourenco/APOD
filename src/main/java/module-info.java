module com.example.apod {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.apod to javafx.fxml;
    exports com.example.apod;
}