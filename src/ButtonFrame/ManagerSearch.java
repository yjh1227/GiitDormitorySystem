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

import Control.ManagerControl;
import Model.ManagersModel;
import Operation.ManagerOperation;
import Operation.StudentsOperation;

public class ManagerSearch extends JFrame {
	JTextField text, text1, text2, text3;
	JLabel lab, lab0;
	JPanel jp = new JPanel();
	JButton ok, search, change;

	public ManagerSearch() {
		JLabel label = new JLabel(new ImageIcon("image\\background.jpg"));
		label.setSize(640, 480);
		Image image = this.getToolkit().getImage("image\\icon.png");

		lab0 = new JLabel("�޹���Ϣ��ѯ");
		lab0.setBounds(140, 0, 150, 30);
		lab0.setFont(new Font("���Ĳ���", Font.BOLD, 23));
		this.add(lab0);

		lab = new JLabel("Ա����:");
		lab.setBounds(20, 40, 150, 50);
		lab.setFont(new Font("�����п�", Font.BOLD, 23));
		this.add(lab);

		text = new JTextField();
		text.setBounds(120, 50, 150, 30);
		text.setFont(new Font("����", Font.BOLD, 18));
		this.add(text);

		// ��ǩ
		JLabel lab1 = new JLabel("��    ��:");
		jp.add(lab1);
		lab1.setBounds(20, 150, 100, 50);
		lab1.setFont(new Font("����", Font.BOLD, 20));

		JLabel label2 = new JLabel("Ա �� ��:");
		label2.setBounds(20, 210, 100, 50);
		label2.setFont(new Font("����", Font.BOLD, 20));
		this.add(label2);

		JLabel label3 = new JLabel("��ϵ��ʽ:");
		label3.setBounds(20, 270, 100, 50);
		label3.setFont(new Font("����", Font.BOLD, 20));
		this.add(label3);

		// ����
		text1 = new JTextField();
		text1.setBounds(120, 165, 150, 30);
		text1.setFont(new Font("����", Font.BOLD, 18));
		text1.setEditable(false);
		this.add(text1);

		// Ա����
		text2 = new JTextField();
		text2.setBounds(120, 220, 150, 30);
		text2.setFont(new Font("����", Font.BOLD, 18));
		text2.setEditable(false);
		this.add(text2);

		// ��ϵ��ʽ
		text3 = new JTextField();
		text3.setBounds(120, 280, 150, 30);
		text3.setFont(new Font("����", Font.BOLD, 18));
		text3.setEditable(false);
		this.add(text3);

		ImageIcon ichange = new ImageIcon("image\\change.png");
		change = new JButton(ichange);
		change.setBounds(220, 370, 80, 25);
		change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text1.setEditable(true);
				text3.setEditable(true);
			}
		});
		this.add(change);

		ImageIcon iok = new ImageIcon("image\\OK.png");
		ok = new JButton(iok);
		ok.setBounds(100, 370, 80, 25);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerOperation update = new ManagerOperation();
				try {
					update.UpdateOneManager(text.getText(), text1, text2, text3);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				text1.setEditable(false);
				text3.setEditable(false);
			}
		});

		ImageIcon isearch = new ImageIcon("image\\search.png");
		search = new JButton(isearch);
		search.setBounds(300, 52, 80, 25);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerControl r = new ManagerControl();
				ManagersModel rr = null;
				boolean bb = false;
				if (e.getSource() == search) {
					try {
						if (text.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "Ա���Ų���Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							rr = r.get(Integer.parseInt(text.getText()));
						}
						if (rr != null) {
							bb = rr.getManager_id() == Integer.parseInt(text.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Ա���Ų���ȷ", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
						}
						if (bb) {
							new ManagerOperation();
							ManagerOperation.get(Integer.parseInt(text.getText()));
							//System.out.println(Integer.parseInt(text.getText()) + "Ա����");
							text1.setText(ManagerOperation.managers.getManager_name());
							text2.setText(ManagerOperation.managers.getManager_id() + "");
							text3.setText(ManagerOperation.managers.getContact());
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					//System.out.println("��ѯ");

				}

			}
		});
		this.add(search);

		// ���
		jp.setLayout(null);
		jp.add(ok);
		jp.add(label);

		// ����
		this.add(jp);
		this.setIconImage(image);
		// this.setTitle("");
		this.setSize(400, 480);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		ActionListener ok = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		};
	}

	public JTextField gettext() {
		return text;
	}
}
