package football;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainUI extends JFrame {
	
    private JTextField idField;
    private JPasswordField pwdField;
    private JButton loginButton;
    private JLabel statusLabel;

    private String fId = "football";
    private String fPwd = "football";

    private MainUI_admin adui = new MainUI_admin();
    private MainUI_user usui = new MainUI_user();

    public MainUI() {
        setTitle("EPL Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(4, 1));

        idField = new JTextField();
        pwdField = new JPasswordField();
        loginButton = new JButton("Login");
        statusLabel = new JLabel("", JLabel.CENTER);

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Password:"));
        add(pwdField);
        add(loginButton);
        add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pwd = new String(pwdField.getPassword());
                if (fId.equals(id) && fPwd.equals(pwd)) {
                    adui.menu();
                    clearFields();
                } else {
                    statusLabel.setText("Incorrect ID or password!");
                }
            }
        });
    }

    private void clearFields() {
        idField.setText("");
        pwdField.setText("");
        statusLabel.setText("");
    }

    public void menu() {
        JFrame frame = new JFrame("EPL Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 1));

        JLabel welcomeLabel = new JLabel("<html><font color='red'>Welcome to EPL !!!</font></html>", JLabel.CENTER);
        JButton userButton = new JButton("User");
        JButton adminButton = new JButton("Admin");

        frame.add(welcomeLabel);
        frame.add(userButton);
        frame.add(adminButton);

        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usui.menu();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Start();
            }
        });

        frame.setVisible(true);
    }

    public void Start() {
        JFrame loginFrame = new JFrame("EPL Admin Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLayout(new GridLayout(4, 1));

        JLabel idLabel = new JLabel("ID:");
        JLabel pwdLabel = new JLabel("Password:");
        JTextField idField = new JTextField();
        JPasswordField pwdField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginFrame.add(idLabel);
        loginFrame.add(idField);
        loginFrame.add(pwdLabel);
        loginFrame.add(pwdField);
        loginFrame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pwd = new String(pwdField.getPassword());
                if (fId.equals(id) && fPwd.equals(pwd)) {
                    adui.menu();
                    loginFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect ID or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.setVisible(true);
    }
}