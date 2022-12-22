package com.example.namesurfer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;


public class MainController{
    public MainController() {
    }

    @FXML void initialize()
    {
        nameTextField.textProperty().addListener((obj, oldVal, newVal) -> graphButton.setDisable(newVal.trim().isEmpty()));
        NSData.getDrawnEntries().getOnCountChanged().addListener(this::onDrawnEntriesCountChanged);

    }
    @FXML
    private TextField nameTextField;
    @FXML
    private LineChart<Integer, Integer> graphChart;

    @FXML
    private Button graphButton;

    @FXML
    private Button clearButton;

    private static final FileChooser fileChooser = new FileChooser();
    private String lastFolder = "assets/";

    @FXML
    protected void onGraphButtonClick() {
        String name = nameTextField.getText().trim();
        if (name.isEmpty()) {nameTextField.selectAll(); return;}
        NSDataEntry nsde = NSData.getLoadedEntries().getEntre(name);
        if (nsde == null) {
            SimpleAlert.showMessage(Alert.AlertType.ERROR,
                    String.format("The name: \"%s\" are not found in the \"%s\" data file",
                    name, NSData.getLoadedEntries().getDataFileName()));
            nameTextField.selectAll();
            return;
        }
        try {
            NSData.getDrawnEntries().add(nsde);
            XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<Integer, Integer>> data = series.getData();
            for (int i = 0; i < NSDataEntry.VALUES_COUNT; i++)
                data.add(new XYChart.Data<>(NSLoadedEntries.DATE_START + NSLoadedEntries.DATE_STEP * i, nsde.GetValue(i)));
            series.setData(data);
            series.setName(nsde.GetName());
            graphChart.getData().add(series);
            nameTextField.clear();
            //clearButton.setDisable(false);
        } catch (IllegalArgumentException e) {
            SimpleAlert.showMessage(Alert.AlertType.ERROR, e.getMessage());
            nameTextField.selectAll();
        }

    }

    @FXML
    protected void onClearButtonClick() {
        Optional<ButtonType> clearAnswer = SimpleAlert.showMessage(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to clear charts");
        if (clearAnswer.isPresent() && clearAnswer.get() == ButtonType.OK)
        {
            NSData.getDrawnEntries().clear();
            graphChart.getData().clear();
            //clearButton.setDisable(true);
        }

    }

    @FXML
    protected void onOpenMenuAction() {
        fileChooser.setTitle("Open data file...");
        fileChooser.getExtensionFilters().clear();
        fileChooser.setInitialDirectory(new File(lastFolder));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Data files", "*.txt"));
        File newDataFile = fileChooser.showOpenDialog(NSApplication.getStage());
        if (newDataFile != null) {
            lastFolder = newDataFile.getParent();
            if(NSData.getLoadedEntries().loadDataFromFile(newDataFile.getPath())){
                NSData.getDrawnEntries().clear();
                graphChart.getData().clear();
                //clearButton.setDisable(true);
            }
        }

    }

    @FXML protected void onSaveMenuAction(){}
    @FXML
    protected void onCloseMenuAction() {
        Optional<ButtonType> exitAnswer = SimpleAlert.showMessage(
                Alert.AlertType.CONFIRMATION,"Are you sure you want to close the application");

        if (exitAnswer.isPresent() && exitAnswer.get() == ButtonType.OK) NSApplication.getStage().close();

    }

    protected void onDrawnEntriesCountChanged(Object source, int eventArgs){
        clearButton.setDisable(eventArgs == 0);
    }


}