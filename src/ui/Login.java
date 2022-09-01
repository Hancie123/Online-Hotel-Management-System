package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

import customer.Customer_Dashboard;
import customer.Customer_Registration;
import libs.CustomerLibs;
import libs.CustomerLibs2;
import libs.CustomerLoginLibs;
import libs.CustomerLoginLibs2;
import libs.Global;
import reception.Reception_Dashboard;

public class Login {
	JFrame frame;
	JTextField UsernameField;
	JPasswordField PasswordField;
	JButton login,register;
	JCheckBox radioBtn;
	
	public Login() {
		
		//------------Main Frame-------------------
		frame=new JFrame();
		frame.setTitle("Login");
		frame.setSize(600,600);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hotel.png")));
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		
//		-----North Panel-------
		JPanel north=new JPanel();
		north.setLayout(null);
		north.setBackground(new Color(0,0,0));
		north.setPreferredSize(new Dimension(100,100));
		frame.add(north, BorderLayout.NORTH);
		
		JLabel title =new JLabel("Customer Login");
		title.setForeground(Color.white);
		title.setBounds(700,30,300,50);
		title.setFont(new Font("Verdana", Font.BOLD, 25));
		north.add(title);
		
		// ---------Center Panel-------
		JPanel center=new JPanel();
		center.setLayout(null);
		center.setBackground(new Color(222, 225, 236));
		center.setPreferredSize(new Dimension(100,100));
		frame.add(center, BorderLayout.CENTER);
		
		
		//-------------Main Panel------------------------
		JPanel main=new JPanel();
		main.setBounds(400,50,800,450);
		main.setLayout(new BorderLayout());
		main.setBackground(new Color(255,255,255));
		center.add(main);
		
		//-------Main Panel North----------
		JPanel north1=new JPanel();
		north1.setLayout(null);
		north1.setBackground(new Color(255,255,255));
		north1.setPreferredSize(new Dimension(50,50));
		main.add(north1, BorderLayout.NORTH);
		
		
		JLabel name = new JLabel("Login");
		name.setBounds(400, 10, 300, 35);
		name.setFont(new Font("Sans Sariff", Font.BOLD, 25));
		north1.add(name);
		
		JLabel image = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("Secure login-rafiki.png")).getImage();
		image.setIcon(new ImageIcon(img));
		image.setPreferredSize(new Dimension(400, 500));
		main.add(image, BorderLayout.WEST);
		
		//-------------Main Panel Inside Main Panel-------------
		JPanel main1=new JPanel();
		main1.setLayout(null);
		main1.setBackground(new Color(255,255,255));
		main.add(main1, BorderLayout.CENTER);
		
		JLabel username = new JLabel("Username: ");
		username.setBounds(10, 90, 300, 35);
		username.setFont(new Font("Sans Sariff", Font.BOLD, 18));
		main1.add(username);
		
		UsernameField = new JTextField(15);
		UsernameField.setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
		String html1 = "<html><p><font color=\"#800080\" " + "size=\"4\" face=\"Verdana\">Please enter your username!"
				+ "</font></p></html>";
		UsernameField.setToolTipText(html1);
		UsernameField.setBounds(130, 90, 250, 35);
		UsernameField.setFont(new Font("Verdana", Font.BOLD, 18));
		main1.add(UsernameField);
		
		
		JLabel password = new JLabel("Password: ");
		password.setBounds(10, 180, 300, 35);
		password.setFont(new Font("Sans Sariff", Font.BOLD, 18));
		main1.add(password);
		
		PasswordField = new JPasswordField(15);
		PasswordField.setBorder(new MatteBorder(0, 0, 2, 0, Color.black));
		String html2 = "<html><p><font color=\"#800080\" " + "size=\"4\" face=\"Verdana\">Please enter your password!"
				+ "</font></p></html>";
		PasswordField.setToolTipText(html2);
		PasswordField.setBounds(130, 180, 250, 35);
		PasswordField.setFont(new Font("Verdana", Font.BOLD, 18));
		main1.add(PasswordField);
		
		radioBtn = new JCheckBox("Show Password");
		radioBtn.setBackground(new Color(255,255,255));
		radioBtn.setFocusable(false);
		radioBtn.setToolTipText("Click here to show your password!");
		UIManager.put("ToolTip.background", Color.ORANGE);
		UIManager.put("ToolTip.foreground", Color.BLACK);
		UIManager.put("ToolTip.font", new Font("Arial", Font.PLAIN, 14));
		radioBtn.setBounds(130, 215, 150, 35);
		radioBtn.setFont(new Font("Verdana", Font.PLAIN, 12));
		radioBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// show password chars
				if (radioBtn.isSelected()) {
					PasswordField.setEchoChar((char) 0);
				}
				// hide password chars
				else {
					PasswordField.setEchoChar('*');
				}
			}
		});
		main1.add(radioBtn);
		
		
		login = new JButton("Login");
		login.setFocusable(false);
		login.setBounds(80, 270, 110, 35);
		login.setBackground(new Color(0,0,0));
		login.setForeground(Color.WHITE);
		login.setFont(new Font("Verdana", Font.BOLD, 15));
		main1.add(login);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (UsernameField.getText().trim().isEmpty() && PasswordField.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter username and password");
				}

				else if (ae.getSource() == login) {
					
					CustomerLibs2 user1 = new CustomerLibs2();
					
					user1.setUsername(UsernameField.getText());
					user1.setPassword(PasswordField.getText());
					user1 = new CustomerLoginLibs2().login(user1);
					
					Global.registrationUser=user1;
					if (user1.getRegistration_ID() > 0) {
						ImageIcon i=new ImageIcon(getClass().getResource("hotel (1).png"));
						JOptionPane.showMessageDialog(null, "Welcome "+user1.getName(),"Login Window",JOptionPane.WIDTH,i);
						
						if (user1.getRole().equals("Manager")) {
							new Customer_Registration();
							frame.dispose();
						} else if (user1.getRole().equals("Receptionist")) {
							new Reception_Dashboard();
							frame.dispose();
						}
						
						else if (user1.getRole().equals("Bar Staff")) {
							new Customer_Registration();
							frame.dispose();
						}
						
						else if (user1.getRole().equals("Restaurant Staff")) {
							new Customer_Registration();
							frame.dispose();
						}
						
						
						
					}
					
					else if (ae.getSource() == login) {

					CustomerLibs user = new CustomerLibs();
					user.setUsername(UsernameField.getText());
					user.setPassword(PasswordField.getText());
					user = new CustomerLoginLibs().login(user);
					
					
					Global.currentUser=user;
					if (user.getCustomer_ID() > 0) {
						ImageIcon i2=new ImageIcon(getClass().getResource("hotel (1).png"));
						JOptionPane.showMessageDialog(null, "Welcome "+user.getName(),"Login Window",JOptionPane.WIDTH,i2);
						
						}  if (user.getRole().equals("Customer")) {																					
							frame.dispose();
							new Customer_Dashboard();
							
						} else if (user.getRole().equals("Corp Client")) {
							new Customer_Registration();
							frame.dispose();
						}
					} else {

						JOptionPane.showMessageDialog(null, "Incorrect Username and window");
					}

				}
			}
		});
		
		register = new JButton("Register");
		register.setFocusable(false);
		register.setBounds(200, 270, 110, 35);
		register.setBackground(new Color(0,0,0));
		register.setForeground(Color.WHITE);
		register.setFont(new Font("Verdana", Font.BOLD, 15));
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				if (ae.getSource() == register) {
					frame.dispose();
					new Customer_Registration();

				}
			}

		});
		main1.add(register);
		
		
		
		
		frame.setVisible(true);
		
		
		
		
		
		
	}

	public static void main(String[] args) {
		new Login();

	}

}
