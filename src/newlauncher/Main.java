package newlauncher;

import control.ControlException;
import control.Router;
import control.daos.connection.Connection;
import newviews.NewView;


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
