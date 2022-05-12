package userInterface;

import exception.DBException;
import exception.SingletonConnectionException;

import javax.swing.*;
import java.awt.*;

public class ByPromoPanel extends JPanel {
    Container container;


    public ByPromoPanel (int minvalue, int maxValue, Container container){
        this.setLayout(new BorderLayout());
        this.container = container;
        //try{

        //}
        /*catch (DBException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
