package org.example.lazy_calculate.controllers;

import org.example.lazy_calculate.model.LazinessModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputController {
    @FXML private TextField plannedField;
    @FXML private TextField completedField;

    private LazinessModel model;
    private Stage stage;

    public void initModel(LazinessModel model, Stage stage) {
        this.model = model;
        this.stage = stage;

        if (model.plannedTasksProperty().get() > 0) {
            plannedField.setText(String.valueOf(model.plannedTasksProperty().get()));
            completedField.setText(String.valueOf(model.completedTasksProperty().get()));
        }
    }

    @FXML
    protected void onCalculateClick() {
        try {
            int planned = Integer.parseInt(plannedField.getText());
            int completed = Integer.parseInt(completedField.getText());

            if (planned < 0 || completed < 0) {
                showError("Значения не могут быть отрицательными.");
                return;
            }
            if (completed > planned) {
                showError("Сделано больше, чем запланировано? Вы точно не робот? Проверьте данные.");
                return;
            }

            model.updateData(planned, completed);
            stage.close();

        } catch (NumberFormatException e) {
            showError("Пожалуйста, введите целые числа!");
        }
    }

    @FXML
    protected void onPlannedFieldEnter() {
        completedField.requestFocus();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка ввода");
        alert.setHeaderText("Некорректные данные");
        alert.setContentText(message);
        alert.showAndWait();
    }
}