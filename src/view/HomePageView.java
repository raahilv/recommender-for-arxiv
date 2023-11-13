package view;

import interface_adapters.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends JPanel implements ActionListener{
    public final String viewName = "home page";

    private final JButton signUp;

    private final JButton login;

    private final ViewManagerModel viewManagerModel;

    public HomePageView(ViewManagerModel viewManagerModel){

        this.viewManagerModel = viewManagerModel;
        JPanel buttons = new JPanel();
        signUp = new JButton("Sign up");
        buttons.add(signUp);
        login = new JButton("Log in");
        buttons.add(login);
        JLabel title = new JLabel("Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            viewManagerModel.setActiveView("sign up");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        login.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            viewManagerModel.setActiveView("log in");
                            viewManagerModel.firePropertyChanged();

                        }
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);

    }
    public void actionPerformed(ActionEvent evt) {
        // Fix this.
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
}


