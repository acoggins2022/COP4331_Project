/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cop4331.gui;

import cop4331.SharedViews.TaskBarController;
import cop4331.System.PlannerSystem;
import cop4331.SharedModels.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the user profile window for the application.
 * It displays user information and allows for changing credentials or deleting the account.
 *
 * @author cashhollister, andrewcogins
 */
public class UserProfileController {
    private JPanel userProfilePanel;
    private JFrame frame;
    PlannerSystem systemInstance = PlannerSystem.getInstance();
    private UserAccount userAccount = systemInstance.getUserAccount();

    /**
     * Constructs the UserProfileWindow with the given username and password.
     */
    public UserProfileController() {
        frame = new JFrame();
        frame.setTitle("User Profile Information");
        frame.setLayout(new BorderLayout());
        
        // Add the taskbar panel to the west of the frame
        TaskBarController taskbarPanel = new TaskBarController(frame);
        frame.add(taskbarPanel, BorderLayout.WEST);
        
        // Initialize the userProfilePanel
        userProfilePanel = new JPanel();
        userProfilePanel.setLayout(new GridLayout(3, 1));

        // Create and set up header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(4, 1));
        
        // header panel components
        JLabel usernameLabel = new JLabel("Username: " + userAccount.getUsername());
        JLabel passwordLabel = new JLabel("Password: " + userAccount.getPassword());
        
        // add componenets to header panel
        headerPanel.add(usernameLabel);
        headerPanel.add(passwordLabel);

        
        // Create and set up field panel
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        
        // Create and set up separate field panels
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
        
        // field panel components
        JLabel newUsernameLabel = new JLabel("New Username: ");
        JTextField usernameField = new JTextField();
        JLabel newPasswordLabel = new JLabel("New Password: ");
        JTextField passwordField = new JPasswordField(); 
        
        // add componenets to field panel
        usernamePanel.add(newUsernameLabel);  
        usernamePanel.add(usernameField);
        passwordPanel.add(newPasswordLabel);
        passwordPanel.add(passwordField);
        
        // add separate panels to field panel
        fieldPanel.add(usernamePanel);
        fieldPanel.add(passwordPanel);
        
        // Set maximum size
        Dimension fieldSize = new Dimension(400, 30);
        usernameField.setMaximumSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);
        
        
        // Create and set up button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton changeCredentialsButton = new JButton("Change Credentials");
        buttonPanel.add(changeCredentialsButton); //Name changed for clarity
        
        JButton deleteAccountButton = new JButton("Delete Account");
        buttonPanel.add(deleteAccountButton); //Name changed for clarity
        
        // Change UserAccount credentials based on text field input
        changeCredentialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change account credentials
                String newUsername = usernameField.getText();
                String newPassword = passwordField.getText();
                systemInstance.changeUserCredentials(newUsername, newPassword);
                frame.dispose();
                new UserProfileController();
                JOptionPane.showMessageDialog(userProfilePanel, "New Username:Password is " + newUsername  + ":" + newPassword, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Delete UserAccount
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                systemInstance.deleteAccount();
                frame.dispose();
                new LoginController();
            }
        });
        // Add panels to the main panel
        userProfilePanel.add(headerPanel);
        userProfilePanel.add(fieldPanel);
        userProfilePanel.add(buttonPanel);
        
        // frame settings
        frame.setSize(800, 450);
        frame.add(userProfilePanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}