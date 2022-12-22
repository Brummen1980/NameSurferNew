package com.example.namesurfer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class SimpleAlert {
    private static final Alert alert = new Alert(Alert.AlertType.INFORMATION);

    static {
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(NSApplication.appIcon);
    }
    public static Optional<ButtonType> showMessage(Alert.AlertType alertType, String titleMessage){
        return showMessage(alertType, titleMessage, "");
    }

    public static Optional<ButtonType> showMessage(Alert.AlertType alertType, String headMessage, String titleMessage){
        alert.setHeaderText(headMessage);
        alert.setContentText(titleMessage);
        if (alert.getAlertType() != alertType) alert.setAlertType(alertType);
        return alert.showAndWait();
    }
}
