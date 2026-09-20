package org.example.lazy_calculate.controllers;

import org.example.lazy_calculate.Main;
import org.example.lazy_calculate.model.LazinessModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML private Label coeffLabel;
    @FXML private Label levelLabel;
    @FXML private Label phraseLabel;

    private LazinessModel model;

    public void initModel(LazinessModel model) {
        this.model = model;

        coeffLabel.textProperty().bind(model.lazinessCoefficientProperty().asString("Коэффициент лени: %.1f%%"));
        levelLabel.textProperty().bind(model.procrastinationLevelProperty().concat(" уровень"));
        phraseLabel.textProperty().bind(model.phraseProperty());
    }

    @FXML
    protected void onEnterDataClick() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/input-view.fxml"));
            Stage inputStage = new Stage();
            inputStage.setTitle("Ввод данных");
            inputStage.setScene(new Scene(loader.load(), 400, 350));
            inputStage.initModality(Modality.APPLICATION_MODAL);

            InputController controller = loader.getController();
            controller.initModel(model, inputStage);

            inputStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}