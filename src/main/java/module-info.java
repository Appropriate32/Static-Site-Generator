module com.ssg {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.ssg to javafx.fxml;
    exports com.ssg;
}
