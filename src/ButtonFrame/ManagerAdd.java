package ButtonFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import AddMessage.AddManager;
import Model.ManagersModel;
import Operation.RegisterOperation;

@SuppressWarnings("serial")
public class ManagerAdd extends JFrame {
	JTextField text1, text2, text3;
	JFrame frame = new JFrame();
	JPanel jp = new JPanel();
	JButton ok;

	public ManagerAdd() {
		// ����
		JLabel label = new JLabel(new ImageIcon("image\\background.jpg"));
		label.setSize(500, 500);
		Image image = this.getToolkit().getImage("image\\icon.png");

		// ��ǩ
		JLabel lab1 = new JLabel("��    ��:");
		jp.add(lab1);
		lab1.setBounds(20, 50, 100, 50);
		lab1.setFont(new Font("����", Font.BOLD, 20));

		JLabel label2 = new JLabel("Ա �� ��:");
		label2.setBounds(20, 110, 100, 50);
		label2.setFont(new Font("����", Font.BOLD, 20));
		frame.add(label2);

		JLabel label3 = new JLabel("��ϵ��ʽ:");
		label3.setBounds(20, 160, 100, 50);
		label3.setFont(new Font("����", Font.BOLD, 20));
		frame.add(label3);

		// ����
		text1 = new JTextField();
		text1.setBounds(120, 60, 150, 30);
		text1.setFont(new Font("����", Font.BOLD, 18));
		frame.add(text1);

		// Ա����
		text2 = new JTextField();
		text2.setBounds(120, 120, 150, 30);
		text2.setFont(new Font("����", Font.BOLD, 18));
		frame.add(text2);

		// ��ϵ��ʽ
		text3 = new JTextField();
		text3.setBounds(120, 172, 150, 30);
		text3.setFont(new Font("����", Font.BOLD, 18));
		frame.add(text3);

		ok = new JButton("ȷ��");
		ok.setBounds(100, 280, 100, 40);
		ok.setFont(new Font("����", Font.CENTER_BASELINE, 23));
		ok.setBackground(Color.WHITE);
		ok.setVisible(true);
		ok.setLayout(null);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (new AddManager().AddManager(text1, text2, text3) == false) {
						return;
					}
					new RegisterOperation().AddManagerRegister(text2.getText());
					frame.dispose();
				} catch (Exception e1) {
					//System.out.println("ada ");
					e1.printStackTrace();
				}
			}
		});

		jp.setLayout(null);
		jp.add(ok);
		jp.add(label);

		// ����
		frame.add(jp);
		frame.setIconImage(image);
		frame.setSize(300, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
