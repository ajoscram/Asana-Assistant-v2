package asana_assistant_2.launcher;

import asana_assistant_1.control.ControlException;
import asana_assistant_1.control.Router;
import asana_assistant_1.control.daos.connection.Connection;
import asana_assistant_2.view.NewView;


public class Main {

    public static void main(String[] args) {
        try {
            Connection.connect();
            new NewView(new Router()).display();
        } catch(ControlException ex) {
            NewView.displayError(null, ex);
        }
    }

}
