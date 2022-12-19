package com.example.namesurfer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Formatter;

public class MainController {
    @FXML private TextField nameTextField;
    @FXML private LineChart<Integer, Integer> graphChart;

    @FXML protected void onGraphButtonClick(){
        NSDataEntre nsde = NSApplication.nData.getEntre(nameTextField.getText());
        if (nsde == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Formatter f = new Formatter();
            alert.setHeaderText(f.format("The name: \"%s\" are not found in the \"%s\" data file",
                    nameTextField.getText(), NSApplication.nData.getDataFileName()).toString());
            alert.showAndWait();
            nameTextField.selectAll();
            return;
        }
        XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<Integer, Integer>> data = series.getData();
        for (int i = 0; i < NSDataEntre.VALUES_COUNT; i++)
            data.add(new XYChart.Data<>(NSData.DATE_START + NSData.DATE_STEP * i, nsde.GetValue(i)));
        series.setData(data);
        series.setName(nsde.GetName());
        graphChart.getData().add(series);
        nameTextField.clear();
    }
    @FXML protected void onClearButtonClick(){
        graphChart.getData().clear();
    }
}