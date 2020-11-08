package ui;

import java.awt.EventQueue;
import exceptions.DukeException;
import exceptions.MissDescException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ScrollPaneConstants;
import java.awt.Insets;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSend;
	private static JTextPane txtChatRecord;
	private static StyledDocument doc;
	private static boolean isYourTurn = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws DukeException
	 */
	public GUI() throws DukeException {
		setTitle("Duke Chatting System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSend = new JButton("Send");
		btnSend.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		btnSend.setContentAreaFilled(false);
		btnSend.setOpaque(false);
		btnSend.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(Color.WHITE);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					guiInput(txtSend.getText().trim());
					txtSend.setText("");
				} catch (DukeException | IOException | MissDescException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSend.setBounds(287, 411, 66, 39);
		contentPane.add(btnSend);

		txtSend = new JTextField();
		txtSend.setForeground(Color.BLUE);
		txtSend.setBackground(new Color(255, 255, 255));
		txtSend.setBorder(new LineBorder(new Color(255, 240, 245), 3));
		txtSend.setBounds(10, 411, 270, 39);
		contentPane.add(txtSend);
		txtSend.setColumns(10);

		JLabel lblNewLabel = new JLabel("Chat with Duke");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Corbel", Font.BOLD, 21));
		lblNewLabel.setBounds(31, 10, 167, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u0E51\u2032\u1D17\u2035\u0E51");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(287, 8, 66, 34);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 47, 343, 338);
		contentPane.add(scrollPane);

		txtChatRecord = new JTextPane();
		txtChatRecord.setEditable(false);
		txtChatRecord.setDisabledTextColor(Color.BLACK);
		txtChatRecord.setMargin(new Insets(10, 10, 10, 10));
		scrollPane.setViewportView(txtChatRecord);

		doc = txtChatRecord.getStyledDocument();

	}

	public static void guiInput(String input)
			throws DukeException, IOException, MissDescException, BadLocationException {
		assert (input!=null):"blank input!";
		if (isYourTurn) {
			Style styleYou = txtChatRecord.addStyle("", null);
			StyleConstants.setForeground(styleYou, Color.blue);
			StyleConstants.setBold(styleYou, true);
			StyleConstants.setAlignment(styleYou, StyleConstants.ALIGN_RIGHT);
			doc.insertString(doc.getLength(), "\n\nYou: ", styleYou);
			isYourTurn = false;
		}
		Style style = txtChatRecord.addStyle("", null);
		StyleConstants.setForeground(style, Color.blue);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
		doc.insertString(doc.getLength(), input + "\n", style);
		Chat.processChat(input, true);
	}

	public static void guiOutput(String output) throws DukeException, BadLocationException {
		if (!isYourTurn) {
			Style style = txtChatRecord.addStyle("", null);
			StyleConstants.setBold(style, true);
			doc.insertString(doc.getLength(), "\nDuke: ", style);
			isYourTurn = true;
		}
		doc.insertString(doc.getLength(), "\n" + output, null);
	}

	public static void guiOutputWarning(String output) throws DukeException, BadLocationException {
		if (!isYourTurn) {
			Style styleDuke = txtChatRecord.addStyle("", null);
			StyleConstants.setForeground(styleDuke, Color.red);
			StyleConstants.setBold(styleDuke, true);
			doc.insertString(doc.getLength(), "\nDuke: ", styleDuke);
		}
		Style style = txtChatRecord.addStyle("", null);
		StyleConstants.setForeground(style, Color.red);
		doc.insertString(doc.getLength(), "\n" + output, style);
	}
}
