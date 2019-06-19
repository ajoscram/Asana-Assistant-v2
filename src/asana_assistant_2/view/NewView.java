package asana_assistant_2.view;

import asana_assistant_1.control.ControlException;
import asana_assistant_1.control.IRouter;
import asana_assistant_1.control.daos.connection.Connection;
import asana_assistant_1.view.View;
import javax.swing.JFrame;

public class NewView extends View{
    
    public NewView(IRouter router){
        super(router);
    }
    
    public void display(){
        LoginFrame frame = new LoginFrame(this);
        frame.setLocationRelativeTo(null); //centers the screen
        frame.setVisible(true);
    }
    
    public int dispose(){
        try {
            Connection connection = Connection.getInstance();
            if(connection != null)
                connection.close();
            System.exit(0);
        } catch (ControlException ex) {
            ex.printStackTrace();
        }
        return JFrame.EXIT_ON_CLOSE;
    }
}