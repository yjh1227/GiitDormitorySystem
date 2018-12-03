
/*���¹�����
 *��Ŀ���������ϵͳ
 *���ߣ����γ�����Ө����Mr.Robot
 *�汾��DS1.1
 *���ڣ�����
 *���ȣ�
 */
package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Control.RegisterControl;
import Model.RegisterModel;
import Operation.StudentsOperation;
import TableStudent.PersonInformation;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

	Managers managers;
	public static JTextField text1;
	JPasswordField text2 = new JPasswordField();

	private JButton enter = new JButton("��   ¼");
	Font butfont = new Font("����", Font.BOLD, 20);

	private JFrame frame = new JFrame();
	private JPanel jpl = new JPanel();
	JComboBox comboBox;

	@SuppressWarnings("unchecked")
	public Login() {

		JLabel label0 = new JLabel("�������ϵͳ");
		label0.setBounds(80, 25, 150, 50);
		label0.setFont(new Font("���Ĳ���", Font.BOLD, 23));
		frame.add(label0);

		JLabel lab1 = new JLabel("���:");
		jpl.add(lab1);
		lab1.setBounds(20, 75, 100, 50);
		lab1.setFont(new Font("����", Font.BOLD, 18));

		comboBox = new JComboBox();
		comboBox.setBounds(70, 87, 150, 30);
		comboBox.addItem("����Ա");
		comboBox.addItem("�޹�");
		comboBox.addItem("ѧ��");
		comboBox.setFont(new Font("�����п�", Font.PLAIN, 20));
		jpl.add(comboBox);
		// ��ǩ
		JLabel label2 = new JLabel("�˺�:");
		label2.setBounds(20, 125, 100, 50);
		label2.setFont(new Font("����", Font.BOLD, 18));
		frame.add(label2);

		JLabel label3 = new JLabel("����:");
		label3.setBounds(20, 175, 100, 50);
		label3.setFont(new Font("����", Font.BOLD, 18));
		frame.add(label3);

		JLabel label4 = new JLabel();
		label4.setText("<HTML><U>�������룿</U></HTMl>");
		label4.setBounds(225, 175, 100, 50);
		label4.setForeground(Color.BLUE);
		label4.setFont(new Font("����", Font.BOLD, 13));
		label4.setToolTipText("���ǲ���ɵ���������붼����������");
		label4.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "�뵽������������", "������ʾ", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		frame.add(label4);

		// �˺�
		text1 = new JTextField();
		text1.setBounds(70, 137, 150, 30);
		text1.setFont(new Font("����", Font.BOLD, 18));
		frame.add(text1);

		// ����
		text2.setBounds(70, 185, 150, 30);
		text2.setFont(new Font("", Font.BOLD, 24));
		text2.setEchoChar('*');
		frame.add(text2);
		// ����
		JLabel label = new JLabel(new ImageIcon("image\\background.jpg"));
		label.setSize(300, 565);
		Image image = this.getToolkit().getImage("image\\icon.png");

		jpl.setLayout(null);
		jpl.add(enter);
		jpl.add(label);

		// ����
		frame.add(jpl);
		frame.setIconImage(image);
		frame.setTitle("��¼");
		frame.setSize(300, 565);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		// ��¼
		enter.setBounds(85, 300, 120, 40); // ���ð�ť��С
		enter.setBackground(Color.WHITE);
		enter.setVisible(true);
		enter.setLayout(null);
		enter.setFont(butfont);
		enter.addActionListener(new ButtonListener());
		enter.registerKeyboardAction(new ButtonListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

	}

	public static void main(String[] args) {
		new Login();

	}

	// ��¼����
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RegisterControl r = new RegisterControl();
			RegisterModel rr = null;
			boolean bb = false;
			if (text1.getText().length() == 0 || text2.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�˺Ż�����Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				try {
					rr = r.get(Integer.parseInt(text1.getText()));
				} catch (NumberFormatException e1) {
				} catch (Exception e1) {

					return;
				}
				if (rr != null) {
					bb = rr.getPassword().equals(text2.getText());
					//System.out.println(rr.getPassword() + "���ݿ��е�����");
				} else {
					JOptionPane.showMessageDialog(null, "�˺���������", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
					text1.setText(null);
					text2.setText(null);
					return;
				}
				if (bb) {
//					System.out.println(comboBox.getSelectedItem().toString().equals(rr.getIdentity()));
					if (comboBox.getSelectedIndex()==rr.getIdentity()){
						frame.dispose();

						if (comboBox.getSelectedIndex()==2) {
							new Students();
							PersonInformation.jpl.setVisible(true);

						} else if (comboBox.getSelectedIndex()==1) {
							new Managers();
						} else {
							new Administrator();
						}
						return;
					} else {
						JOptionPane.showOptionDialog(managers, "������󣡣���", "��ʾ", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, null, null);
						text2.setText(null);
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "�˺���������", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
			text1.setText(null);
			text2.setText(null);
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

}
