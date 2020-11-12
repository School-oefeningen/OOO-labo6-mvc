package domain.launcher;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EncryptHistoryView {
    private Stage stage = new Stage();
    private Scene scene;
    private Pane root;
    private Label header = new Label("Encrypt history\n-----------------\n");
    private Label encryptHistory = new Label("");

    public EncryptHistoryView(EncryptHistoryController controller) {
        root = createNodeHierarchy();
        scene = new Scene(root,250,400);
        stage.setScene(scene);
        stage.setTitle("Watcher");
        stage.setResizable(false);
        stage.setX(400);
        stage.setY(200);
        stage.show();
        controller.setView(this);
    }

    public Pane root() {
        return root;
    }

    public void updateDisplay(String s) {
        encryptHistory.setText(encryptHistory.getText() + s + "\n");
    }

    private Pane createNodeHierarchy() {
        VBox p = new VBox(8);
        p.setPadding(new Insets(10));
        p.getChildren().addAll(header, encryptHistory);
        return p;
    }
}
