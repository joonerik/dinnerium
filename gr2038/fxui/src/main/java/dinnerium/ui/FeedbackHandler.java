package dinnerium.ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FeedbackHandler {
    public static final char ERROR = 'E';
    public static final char WARNING = 'W';
    public static final char MESSAGE = 'M';

    public static void showMessage(Pane pane, String msg, char type) {
        pane.setVisible(true);
        Text t = new Text(msg);
        t.setFill(Color.WHITE);
        pane.getChildren().addAll(t);
        if (type == 'E') {
            pane.getStyleClass().add("error");
        }
        if (type == 'W') {
            pane.getStyleClass().add("warning");
        }
        if (type == 'M') {
            pane.getStyleClass().add("msg");
        }

        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            pane.getStyleClass().clear();
            pane.getChildren().clear();
            pane.setVisible(false);
        });
        System.err.println(msg);
        System.out.println(msg);

    }

}
