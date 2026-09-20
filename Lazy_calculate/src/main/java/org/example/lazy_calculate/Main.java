package org.example.lazy_calculate;

import org.example.lazy_calculate.model.LazinessModel;
import org.example.lazy_calculate.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LazinessModel model = new LazinessModel();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

        MainController controller = fxmlLoader.getController();
        controller.initModel(model);

        stage.setTitle("Калькулятор лени");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}