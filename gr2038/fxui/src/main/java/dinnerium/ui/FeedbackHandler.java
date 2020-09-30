package dinnerium.ui;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class FeedbackHandler {
    public static final char ERROR = 'E';
    public static final char WARNING = 'W';
    public static final char MESSAGE = 'M';

    public void showMessage(Pane pane, String msg, char type) {
        pane.setVisible(true);

        pane.getChildren().addAll(new Text().setText(msg));
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            pane.setVisible(false);
        });
        System.err.println(msg);
        System.out.println(msg);

    }

}
