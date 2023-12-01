package view.LogInSignUp;

import interface_adapters.SignUpLogIn.LoginController;
import interface_adapters.SignUpLogIn.LoginState;
import interface_adapters.SignUpLogIn.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    /**
     * The username chosen by the user
     */
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    /**
     * The password
     */
    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton signup;



    /**
     * A window with a title and a JButton.
     */
    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        signup = new JButton(loginViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signup);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );
        signup.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(signup)) {
                    loginController.executeSwitchScreen();
                }
            }
        });

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

}