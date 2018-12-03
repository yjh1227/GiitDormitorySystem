package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import Model.StudentsModel;
import Operation.RegisterOperation;

@SuppressWarnings("serial")
public class ResetPassword extends JFrame {
	private JFrame frame = new JFrame();
	private JPanel jpl = new JPanel();
	JTextField text1;
	JPasswordField text2 = new JPasswordField();
	private JButton ensure = new JButton("ȷ��");
	private JButton exit = new JButton("����");
	JLabel lab1, lab2, lab3, lab4, lab5;

	public ResetPassword() {

		lab1 = new JLabel("��������");
		lab1.setFont(new Font("���Ĳ���", Font.BOLD, 25));
		lab1.setBounds(270, 20, 150, 50);
		frame.add(lab1);

		lab2 = new JLabel("���:");
		jpl.add(lab2);
		lab2.setBounds(30, 120, 100, 50);
		lab2.setFont(new Font("����", Font.BOLD, 20));
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 130, 150, 30);
		comboBox.addItem("����Ա");
		comboBox.addItem("��    ��");
		comboBox.addItem("ѧ    ��");
		comboBox.setFont(new Font("�����п�", Font.PLAIN, 20));
		jpl.add(comboBox);
		jpl.setLayout(null);

		lab3 = new JLabel("�˺�:");
		lab3.setFont(new Font("����", Font.BOLD, 20));
		lab3.setBounds(30, 220, 100, 50);
		frame.add(lab3);

		lab4 = new JLabel("������:");
		lab4.setFont(new Font("����", Font.BOLD, 20));
		lab4.setBounds(30, 320, 100, 50);
		frame.add(lab4);

		lab5 = new JLabel("����3-12λ����Ϊ���֡���ĸ");
		lab5.setFont(new Font("����", Font.BOLD, 13));
		lab5.setBounds(290, 320, 300, 50);
		lab5.setForeground(Color.red);
		lab5.setVisible(false);
		frame.add(lab5);

		text1 = new JTextField();
		text1.setBounds(120, 230, 150, 30);
		text1.setFont(new Font("����", Font.BOLD, 18));
		frame.add(text1);

		text2 = new JPasswordField();
		text2.setBounds(120, 330, 150, 30);
		text2.setFont(new Font("", Font.BOLD, 24));
		text2.setEchoChar('*');
		text2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				lab5.setVisible(true);
			}
		});

		frame.add(text2);

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

				if (text1.getText().length() == 0 || text2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "�˺Ż�����Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					try {
						rm = rc.get(Integer.parseInt(text1.getText()));

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (rm != null) {
						b = rm.getAccountt().equals(Integer.parseInt(text1.getText()));
						if (text2.getText().length() != 0) {
							RegisterModel regismodel = new RegisterModel();                 //Pattern Ҫ���ϵ�ģʽ��Matcher ������ģʽƥ���ַ���֮������Ľ�������ݣ�
							Pattern ContactPattern = Pattern.compile("[a-zA-Z\\d]{3,12}");   //Pattern��Compile�����ַ���
							Matcher ContactMatcher = ContactPattern.matcher(text2.getText());
							if (ContactMatcher.matches()) {				                     //matches�����Ƿ�ƥ��
								regismodel.setPassword(text2.getText());
							} else {
								JOptionPane.showMessageDialog(null, "�����ʽ����ȷ", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
								text2.setText("");
								return;
							}
								
							}else{
								JOptionPane.showMessageDialog(null, "����������", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
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
								re.Update(text2, text1);
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
						}
					}
					JOptionPane.showMessageDialog(null, "������󣡣���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		exit.setBounds(400, 400, 80, 30); // ���ð�ť��С
		exit.setBackground(Color.WHITE);
		exit.setFont(new Font("����", Font.BOLD, 22));
		exit.setVisible(true);
		exit.setLayout(null);
		frame.add(exit);
		exit.addActionListener(new ActionListener() {
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
