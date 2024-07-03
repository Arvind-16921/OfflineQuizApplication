import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ConnectionProvider;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private CardLayout cl;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	private String userName;
	private JLabel lblNewLabel_10;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		try {
			cn = ConnectionProvider.getConnection();
			st = cn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 870);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
//		JFrame jf = new JFrame();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		cl = new CardLayout();
		panel.setLayout(cl);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel.add(panel_1, "signIN");
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quiz Application");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 99));
		lblNewLabel.setBounds(10, 30, 1456, 128);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_1.setBounds(345, 312, 289, 37);
		panel_1.add(lblNewLabel_1);

		textField = new JTextField("Enter UserName");
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setForeground(Color.black);
				if (textField.getText().equals("Enter UserName"))
					textField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals("")) {
					textField.setForeground(Color.gray);
					textField.setText("Enter UserName");
				}
			}
		});
		textField.setForeground(Color.gray);
		textField.setFont(new Font("SansSerif", Font.BOLD, 30));
		textField.setBounds(684, 310, 380, 42);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_2.setBounds(345, 431, 303, 37);
		panel_1.add(lblNewLabel_2);

		passwordField = new JPasswordField("Enter Password");
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setForeground(Color.black);
				passwordField.setEchoChar('*');
				if (new String(passwordField.getPassword()).equals("Enter Password"))
					passwordField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (new String(passwordField.getPassword()).equals("")) {
					passwordField.setForeground(Color.gray);
					passwordField.setText("Enter Password");
					passwordField.setEchoChar((char) 0);
				}

			}
		});
		passwordField.setEchoChar((char) 0);
		passwordField.setForeground(Color.gray);
		passwordField.setFont(new Font("SansSerif", Font.BOLD, 30));
		passwordField.setBounds(684, 431, 380, 37);
		panel_1.add(passwordField);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!new String(passwordField.getPassword()).equals("Enter Password")) {
					if (e.getStateChange() == ItemEvent.SELECTED)
						passwordField.setEchoChar((char) 0);
					else
						passwordField.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setFont(new Font("SansSerif", Font.BOLD, 20));
		chckbxNewCheckBox.setBounds(684, 484, 214, 21);
		panel_1.add(chckbxNewCheckBox);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 207, 1456, 2);
		panel_1.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 605, 1456, 9);
		panel_1.add(separator_1);

		JButton btnNewButton = new JButton("SignIn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName = textField.getText();
				String password = new String(passwordField.getPassword());
				try {
					rs = st.executeQuery(
							"select *from user where name ='" + userName + "' and password='" + password + "'");
					if (rs.next()) {
						if (userName.equals("Admin"))
							cl.show(panel, "adminDashboard");
						else
							cl.show(panel, "userDashboard");
						lblNewLabel_10.setText("Welcome "+userName);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Credentials");
					}
					textField.setForeground(Color.gray);
					textField.setText("Enter UserName");
					passwordField.setForeground(Color.gray);
					passwordField.setText("Enter Password");
					passwordField.setEchoChar((char) 0);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton.setBounds(601, 695, 279, 47);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("SignUp");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "signUP");
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_1.setBounds(135, 695, 279, 47);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_2.setBounds(1075, 695, 279, 47);
		panel_1.add(btnNewButton_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel.add(panel_2, "signUP");
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Quiz Application");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 99));
		lblNewLabel_3.setBounds(10, 10, 1456, 128);
		panel_2.add(lblNewLabel_3);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 136, 1456, 2);
		panel_2.add(separator_2);

		JLabel lblNewLabel_4 = new JLabel("User Name");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_4.setBounds(397, 186, 270, 36);
		panel_2.add(lblNewLabel_4);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		textField_1.setBounds(751, 186, 376, 36);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Contact Number");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_5.setBounds(397, 280, 270, 36);
		panel_2.add(lblNewLabel_5);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.BOLD, 30));
		textField_2.setBounds(751, 280, 376, 36);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Email ID");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_6.setBounds(397, 372, 270, 36);
		panel_2.add(lblNewLabel_6);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("SansSerif", Font.BOLD, 30));
		textField_3.setBounds(751, 372, 376, 36);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Password");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_7.setBounds(397, 467, 270, 36);
		panel_2.add(lblNewLabel_7);

		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		passwordField_1.setBounds(751, 467, 376, 36);
		panel_2.add(passwordField_1);

		JLabel lblNewLabel_8 = new JLabel("Confirm Password");
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_8.setBounds(397, 560, 270, 36);
		panel_2.add(lblNewLabel_8);

		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("SansSerif", Font.BOLD, 30));
		passwordField_2.setBounds(751, 560, 376, 36);
		panel_2.add(passwordField_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(10, 662, 1456, 2);
		panel_2.add(separator_2_1);

		JButton btnNewButton_3 = new JButton("SignUP");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField_1.getText();
				String contactNo = textField_2.getText();
				String emailID = textField_3.getText();
				String password = new String(passwordField_1.getPassword());
				String confirmPassword = new String(passwordField_2.getPassword());
				if(password.equals(confirmPassword)) {
					try {
						st.executeUpdate("insert into user values ('"+user+"','"+contactNo+"','"+emailID+"','"+password+"')");
						JOptionPane.showMessageDialog(null, "SignUp successfully");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						passwordField_1.setText("");
						passwordField_2.setText("");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Password and ConfirmPassword are not same");
				}
			}
		});
		btnNewButton_3.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_3.setBounds(563, 730, 258, 36);
		panel_2.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_4.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_4.setBounds(1132, 730, 280, 36);
		panel_2.add(btnNewButton_4);

		JButton btnNewButton_3_1 = new JButton("Clear");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
			}
		});
		btnNewButton_3_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_3_1.setBounds(62, 730, 258, 36);
		panel_2.add(btnNewButton_3_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.CYAN);
		panel.add(panel_3, "adminDashboard");
		panel_3.setLayout(null);

		JLabel lblNewLabel_3_1 = new JLabel("Quiz Application");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 99));
		lblNewLabel_3_1.setBounds(10, 10, 1456, 128);
		panel_3.add(lblNewLabel_3_1);

		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setBounds(10, 155, 1456, 2);
		panel_3.add(separator_2_2);

		JLabel lblNewLabel_9 = new JLabel("Welcome Admin");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_9.setBounds(542, 225, 428, 53);
		panel_3.add(lblNewLabel_9);

		JButton btnNewButton_5 = new JButton("Exit");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_5.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_5.setBounds(81, 714, 306, 41);
		panel_3.add(btnNewButton_5);

		JButton btnNewButton_5_1 = new JButton("Sign out");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "signIN");
			}
		});
		btnNewButton_5_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_5_1.setBounds(1103, 714, 306, 41);
		panel_3.add(btnNewButton_5_1);

		JButton btnNewButton_6 = new JButton("Add Questions");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddQuestions().setVisible(true);
			}
		});
		btnNewButton_6.setFont(new Font("Edwardian Script ITC", Font.BOLD, 99));
		btnNewButton_6.setBounds(467, 396, 569, 150);
		panel_3.add(btnNewButton_6);

		JSeparator separator_2_2_1 = new JSeparator();
		separator_2_2_1.setBounds(10, 670, 1456, 2);
		panel_3.add(separator_2_2_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.CYAN);
		panel.add(panel_4, "userDashboard");
		panel_4.setLayout(null);

		JLabel lblNewLabel_3_1_1 = new JLabel("Quiz Application");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 99));
		lblNewLabel_3_1_1.setBounds(10, 10, 1456, 128);
		panel_4.add(lblNewLabel_3_1_1);

		JSeparator separator_2_2_2 = new JSeparator();
		separator_2_2_2.setBounds(10, 155, 1456, 2);
		panel_4.add(separator_2_2_2);

		lblNewLabel_10 = new JLabel("Welcome "+userName);
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_10.setBounds(476, 240, 544, 35);
		panel_4.add(lblNewLabel_10);

		JSeparator separator_2_2_2_1 = new JSeparator();
		separator_2_2_2_1.setBounds(10, 685, 1456, 2);
		panel_4.add(separator_2_2_2_1);

		JButton btnNewButton_7 = new JButton("Exit");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_7.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_7.setBounds(106, 744, 282, 41);
		panel_4.add(btnNewButton_7);

		JButton btnNewButton_7_1 = new JButton("Sign out");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel, "signIN");
			}
		});
		btnNewButton_7_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_7_1.setBounds(1086, 744, 282, 41);
		panel_4.add(btnNewButton_7_1);
		
		JButton btnNewButton_8 = new JButton("Attempt Quiz");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AttemptTest(userName).setVisible(true);
			}
		});
		btnNewButton_8.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 99));
		btnNewButton_8.setBounds(476, 381, 544, 164);
		panel_4.add(btnNewButton_8);
	}
}
