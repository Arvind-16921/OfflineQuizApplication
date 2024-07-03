import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dao.ConnectionProvider;

public class AttemptTest extends JFrame {
	private String userName;
	private JPanel contentPane;
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	private ButtonGroup bg;
	private String userAnswer;
	private String dbAnswer;

	private double score = 0;
	private double penalty = 0.25;
	private int timerLimit = 10;
	private int timer = timerLimit;

	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	private JLabel lblNewLabel_2;
	private JTextArea textArea;
	private Thread t;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AttemptTest frame = new AttemptTest("User");
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
	public AttemptTest(String userName) {
		try {
			cn = ConnectionProvider.getConnection();
			st = cn.createStatement();
			rs = st.executeQuery("select *from questions;");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		this.userAnswer = userName;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1745, 849);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 93, 1516, 9);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 463, 1516, 9);
		contentPane.add(separator_1);

		JLabel lblNewLabel = new JLabel(userName);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 27, 325, 40);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Time Remaining : ");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1.setBounds(1168, 28, 179, 38);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel(timer + "");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_2.setBounds(1368, 27, 32, 40);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Seconds");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_3.setBounds(1403, 28, 123, 38);
		contentPane.add(lblNewLabel_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 1506, 327);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel_4 = new JLabel("Question");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 20));
		scrollPane.setColumnHeaderView(lblNewLabel_4);

		rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		rdbtnNewRadioButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
		rdbtnNewRadioButton.setBounds(10, 491, 1506, 38);
		contentPane.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		rdbtnNewRadioButton_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		rdbtnNewRadioButton_1.setBounds(10, 557, 1506, 38);
		contentPane.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		rdbtnNewRadioButton_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		rdbtnNewRadioButton_2.setBounds(10, 628, 1506, 38);
		contentPane.add(rdbtnNewRadioButton_2);

		rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAnswer = e.getActionCommand();
			}
		});
		rdbtnNewRadioButton_3.setFont(new Font("SansSerif", Font.PLAIN, 20));
		rdbtnNewRadioButton_3.setBounds(10, 701, 1506, 38);
		contentPane.add(rdbtnNewRadioButton_3);

		JButton btnNewButton = new JButton("End Test");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Your score is : " + score);
				setVisible(false);
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnNewButton.setBounds(10, 779, 189, 40);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bg.clearSelection();
				userAnswer = "";
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnNewButton_1.setBounds(670, 779, 189, 40);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Next");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextQuestions();
			}
		});
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnNewButton_2.setBounds(1327, 779, 189, 40);
		contentPane.add(btnNewButton_2);

		bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_3);

		try {
			if (rs.next()) {
				textArea.setText(rs.getString(1));
				rdbtnNewRadioButton.setText(rs.getString(2));
				rdbtnNewRadioButton_1.setText(rs.getString(3));
				rdbtnNewRadioButton_2.setText(rs.getString(4));
				rdbtnNewRadioButton_3.setText(rs.getString(5));
				dbAnswer = rs.getString(6);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		t = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						Thread.sleep(1000);
						timer--;
						lblNewLabel_2.setText(timer + "");
						if (timer <= 0)
							nextQuestions();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		t.start();
	}

	private void nextQuestions() {
		timer = timerLimit;
		if (dbAnswer.equals(userAnswer))
			score += 1.0;
		else if (!userAnswer.equals(""))
			score = score - penalty;
		bg.clearSelection();
		try {
			if (rs.next()) {
				textArea.setText(rs.getString(1));
				rdbtnNewRadioButton.setText(rs.getString(2));
				rdbtnNewRadioButton_1.setText(rs.getString(3));
				rdbtnNewRadioButton_2.setText(rs.getString(4));
				rdbtnNewRadioButton_3.setText(rs.getString(5));
				dbAnswer = rs.getString(6);
			}
			else {
				JOptionPane.showMessageDialog(null, "Your score is : "+score);
				this.setVisible(false);
				t.stop();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
