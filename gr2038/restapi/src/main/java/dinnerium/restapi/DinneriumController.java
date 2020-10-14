package dinnerium.restapi;

import static spark.Spark.*;

public class DinneriumController {

    public DinneriumController() {
        get("/users/:name", (req, res) -> "hei");
    }

    public static void main(String[] args) {
        DinneriumController dc = new DinneriumController();
    }
}
