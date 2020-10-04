package dinnerium.ui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Class for error handling and other messages to the user.
 */
public class FeedbackHandler {

  public static final char ERROR = 'E';
  public static final char WARNING = 'W';
  public static final char MESSAGE = 'M';

  /**
   * This method displays the chosen message.
   *
   * @param pane Which pane to display the message. Usually msgPane.
   * @param msg   Which message to display.
   * @param type  What type of message. 'E' = Error, 'W' = Warning, 'M' = Message.
   *
   */
  public static void showMessage(Pane pane, String msg, char type) {
    pane.getChildren().clear();
    pane.setVisible(true);
    Text t = new Text(msg);
    t.setFill(Color.WHITE);
    pane.getChildren().addAll(t);
    if (type == ERROR) {
      pane.getStyleClass().add("error");
    }
    if (type == WARNING) {
      pane.getStyleClass().add("warning");
    }
    if (type == MESSAGE) {
      pane.getStyleClass().add("msg");
    }

    CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
      t.setText("");
      pane.getStyleClass().clear();
      pane.setVisible(false);
    });
    System.err.println(msg);
    System.out.println(msg);

  }

}
