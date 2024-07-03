import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ConnectionProvider;

public class AddQuestions extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Connection cn;
	private Statement st;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddQuestions frame = new AddQuestions();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddQuestions() {
		try {
			cn = ConnectionProvider.getConnection();
			st = cn.createStatement();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1635, 841);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Questions");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 70));
		lblNewLabel.setBounds(10, 10, 1517, 66);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 74, 1517, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 423, 1517, 2);
		contentPane.add(separator_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 86, 1507, 327);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel_1 = new JLabel("Problem Statement");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		scrollPane.setColumnHeaderView(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField.setBounds(20, 442, 1507, 36);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(20, 506, 1507, 36);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(20, 573, 1507, 36);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField_3.setColumns(10);
		textField_3.setBounds(20, 638, 1507, 36);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(20, 709, 1507, 36);
		contentPane.add(textField_4);

		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton.setBounds(20, 779, 208, 36);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statement = textArea.getText();
				String option1 = textField.getText();
				String option2 = textField_1.getText();
				String option3 = textField_2.getText();
				String option4 = textField_3.getText();
				String answer = textField_4.getText();
				try {
					st.executeUpdate("insert into questions values('"+statement+"','"+option1+"','"+option2+"','"+option3+"','"+option4+"','"+answer+"');");
					JOptionPane.showMessageDialog(null, "Question inserted successfully");
					textArea.setText("");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_1.setBounds(652, 779, 208, 36);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Close");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton_1_1.setBounds(1319, 779, 208, 36);
		contentPane.add(btnNewButton_1_1);
	}
}
