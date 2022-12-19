package com.example.namesurfer;

import com.almasb.fxgl.cutscene.dialogue.DialogueNodeType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class Utils {

    public  static  void ShowSimpleMessage(String message){
        Dialog<String> dlg = new Dialog<>();
        dlg.setTitle("Information");
        dlg.setContentText(message);
        ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dlg.getDialogPane().getButtonTypes().add(btnType);
        dlg.showAndWait();    }

    public static void ShowErrorMessage(String message)
    {
        Dialog dlg = new Dialog();
        dlg.setTitle("Error");
        dlg.setContentText(message);
        ButtonType btnType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dlg.getDialogPane().getButtonTypes().add(btnType);
        dlg.showAndWait();
    }

    public static String Capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}
