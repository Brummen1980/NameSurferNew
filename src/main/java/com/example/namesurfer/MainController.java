package com.example.namesurfer;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.Formatter;
import java.util.Random;

public class MainController {
    @FXML private TextField nameTextField;
//    @FXML private Button graphButton;
//    @FXML private Button clearButton;
    @FXML private LineChart<Integer, Integer> graphChart;

    @FXML protected void onNameTextFieldEnter() {
        Formatter f = new Formatter();
        Utils.ShowSimpleMessage(f.format("Введен текст: \n\"%s\"", nameTextField.getText()).toString());
    }

    @FXML protected void onGraphButtonClick(){

/*        graphChart.getData().clear();
        Random rnd = new Random();
        for (int j = 1; j <= 5; j++){
            XYChart.Series<Integer, Integer> series = new XYChart.Series();
            ObservableList<XYChart.Data<Integer, Integer>> data = series.getData();
            for (int i = 0; i < 100; i+=10) data.add(new XYChart.Data<>(i, rnd.nextInt(100)));
            series.setData(data);
            series.setName("Graph #" + j);
            graphChart.getData().add(series);
        }*/

        NSDataEntre nsde = NSApplication.nData.GetEntre(nameTextField.getText());
        if (nsde == null) Utils.ShowSimpleMessage(nameTextField.getText() + " не найдено");
        XYChart.Series<Integer, Integer> series = new XYChart.Series();
        ObservableList<XYChart.Data<Integer, Integer>> data = series.getData();
        for (int i = 0; i < 12; i++) data.add(new XYChart.Data<>(i, nsde.GetValue(i)));
        series.setData(data);
        series.setName(nsde.GetName());
        graphChart.getData().add(series);

    }
    @FXML protected void onClearButtonClick(){
        graphChart.getData().clear();
    }
}