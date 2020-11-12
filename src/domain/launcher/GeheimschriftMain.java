package domain.launcher;

import javafx.application.Application;
import javafx.stage.Stage;

public class GeheimschriftMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GeheimschriftModel model = new GeheimschriftModel();

        GeheimschriftController geheimschriftController = new GeheimschriftController(model);
        GeheimschriftView geheimschriftView = new GeheimschriftView(geheimschriftController);

        EncryptHistoryController encryptHistoryController = new EncryptHistoryController(model);
        EncryptHistoryView encryptHistoryView = new EncryptHistoryView(encryptHistoryController);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
