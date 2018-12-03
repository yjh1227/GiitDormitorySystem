package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Control.RegisterControl;
import Model.RegisterModel;
import Operation.ManagerOperation;
import Operation.RegisterOperation;

@SuppressWarnings("serial")
public class ChangePassword extends JFrame {
	private JFrame frame = new JFrame();
	private JPanel jpl = new JPanel();
	JTextField text0;
	JPasswordField text1,text2, text3;
	private JButton ensure = new JButton("ȷ��");
	private JButton exit = new JButton("����");

	@SuppressWarnings("unchecked")
	public ChangePassword() {
		JLabel lab1 = new JLabel("�޸�����");
		lab1.setFont(new Font("���Ĳ���", Font.BOLD, 25));
		lab1.setBounds(270, 5, 150, 50);
		frame.add(lab1);
		
		JLabel lab2 = new JLabel("�˺�:");
		lab2.setFont(new Font("����", Font.BOLD, 20));
		lab2.setBounds(30, 50, 100, 50);
		frame.add(lab2);

		JLabel lab3 = new JLabel("������:");
		lab3.setFont(new Font("����", Font.BOLD, 20));
		lab3.setBounds(30, 130, 100, 50);
		frame.add(lab3);

		JLabel lab4 = new JLabel("������:");
		lab4.setFont(new Font("����", Font.BOLD, 20));
		lab4.setBounds(30, 210, 100, 50);
		frame.add(lab4);

		JLabel lab5 = new JLabel("ȷ������:");
		lab5.setFont(new Font("����", Font.BOLD, 20));
		lab5.setBounds(30, 290, 100, 50);
		frame.add(lab5);

		JLabel lab6 = new JLabel("����Ϊ3~12λ����Ϊ���֡���ĸ");
		lab6.setFont(new Font("����", Font.BOLD, 13));
		lab6.setBounds(300, 290, 300, 50);
		lab6.setForeground(Color.red);
		frame.add(lab6);
		
		text0 = new JTextField();
		text0.setBounds(130, 60, 150, 30);
		text0.setText(Login.text1.getText());
		text0.setEditable(false);
		text0.setFont(new Font("����", Font.BOLD, 18));
		frame.add(text0);

		text1 = new JPasswordField();
		text1.setBounds(130, 140, 150, 30);
		text1.setFont(new Font("", Font.BOLD, 24));
		text1.setEchoChar('*');
		frame.add(text1);

		text2 = new JPasswordField();
		text2.setBounds(130, 220, 150, 30);
		text2.setFont(new Font("", Font.BOLD, 24));
		text2.setEchoChar('*');
		frame.add(text2);

		text3 = new JPasswordField();
		text3.setBounds(130, 300, 150, 30);
		text3.setFont(new Font("", Font.BOLD, 24));
		text3.setEchoChar('*');
		frame.add(text3);

		ensure.setBounds(150, 400, 80, 30); // ���ð�ť��С
		ensure.setBackground(Color.WHITE);
		ensure.setFont(new Font("����", Font.BOLD, 22));
		ensure.setVisible(true);
		ensure.setLayout(null);
		frame.add(ensure);

		ensure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterControl rc = new RegisterControl();
				RegisterModel rm = null;
				boolean b = false;
				RegisterOperation re = new RegisterOperation();

				if (text1.getText().length() == 0 || text2.getText().length() == 0 || text3.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "����Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					try {
						rm = rc.get(Integer.parseInt(text0.getText()));
					
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (rm != null) {
						
						b = rm.getPassword().equals(text1.getText());
						if (text2.getText().length() != 0||text3.getText().length()!=0) {
							RegisterModel regismodel = new RegisterModel();                 //Pattern Ҫ���ϵ�ģʽ��Matcher ������ģʽƥ���ַ���֮������Ľ�������ݣ�
							Pattern ContactPattern = Pattern.compile("[a-zA-Z\\d]{3,12}");   //Pattern��Compile�����ַ���
							Matcher ContactMatcher = ContactPattern.matcher(text2.getText());
							if (ContactMatcher.matches()) {				                     //matches�����Ƿ�ƥ��
								if (text2.getText().equals(text3.getText())) {
									regismodel.setPassword(text3.getText());									
								}
								else {
									JOptionPane.showMessageDialog(null, "���벻һ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}else {
								JOptionPane.showMessageDialog(null, "�����ʽ����ȷ", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
								text2.setText("");
								return;
							}
						}else{
							JOptionPane.showMessageDialog(null, "������������", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
					}
					}
				}
			
				if (b) {
					// ����һ��ȷ����Ϣ��
					String[] options = new String[] { "��", "��" };
					int n = JOptionPane.showOptionDialog(null, "ȷ�ϴ�����?", "��ʾ", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					// ����ǵĻ�
					if (n == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "��������ɹ�������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						
						try {
							re.Update(text3, text0);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						frame.dispose();
						return;
					}
					// �����Ļ�������
					else if (n == JOptionPane.NO_OPTION) {
						frame.dispose();
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "���������󣡣���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		
	});

		exit.setBounds(400, 400, 80, 30); // ���ð�ť��С
		exit.setBackground(Color.WHITE);
		exit.setFont(new Font("����", Font.BOLD, 22));
		exit.setVisible(true);
		exit.setLayout(null);
		frame.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exit) {
					frame.dispose();
				}
			}
		});

		JLabel label = new JLabel(new ImageIcon("image\\Passwordback.jpg"));
		label.setSize(800, 600);
		Image image = this.getToolkit().getImage("image\\icon.png");

		jpl.add(label);
		jpl.setLayout(null);

		frame.add(jpl);
		frame.setIconImage(image);
		frame.setTitle("�������ϵͳ");
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
