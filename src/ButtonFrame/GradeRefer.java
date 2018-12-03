package ButtonFrame;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import AddMessage.AddGrade;
import Control.GradeControl;
import Model.GradeModel;
import Operation.DormOperation;
import System.Login;
import TableAdministrator.GradeTable;

public class GradeRefer extends JFrame {
	JFrame Add_Frame;
	JButton Add_OkButton;
	JLabel[] lab = new JLabel[7];
	String labstr[] = { "�����", "���ɷ�", "���ڷ�", "������", "�ܷ�", "����ʱ��", "������" };

	JTextField[] text = new JTextField[8];
	JComboBox dorm;

	JPanel jp;

	public GradeRefer() {
		Image image = this.getToolkit().getImage("image\\icon.png");
		Add_Frame = new JFrame();
		Add_Frame.setIconImage(image);
		Add_Frame.setSize(220, 360);
		Add_Frame.setResizable(false);

		/**
		 * ѭ����ǩ
		 */
		for (int i = 0; i < 7; i++) {
			lab[i] = new JLabel(labstr[i]);
		}
		/**
		 * ѭ���ı���
		 */
		for (int i = 0; i < 7; i++) {
			text[i] = new JTextField(10);

		}
		Add_Frame.setVisible(true);
		Add_Frame.setLocationRelativeTo(null);

		Box boxVertical = new Box(BoxLayout.Y_AXIS); // �������ϵ��º��Ӳ���

		Box dorm_idBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		dorm_idBox.add(lab[0]);
		dorm_idBox.add(Box.createHorizontalStrut(23));
		dorm = new JComboBox();

		try {
			for (int i = 0; i < DormOperation.selectdorm_id().size(); i++) {

				dorm.addItem(DormOperation.selectdorm_id().get(i).getDorm_id());

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dorm_idBox.add(dorm);
		boxVertical.add(dorm_idBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box disciplineBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		disciplineBox.add(lab[1]);
		disciplineBox.add(Box.createHorizontalStrut(23));
		disciplineBox.add(text[1]);
		boxVertical.add(disciplineBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box checkBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		checkBox.add(lab[2]);
		checkBox.add(Box.createHorizontalStrut(23));
		checkBox.add(text[2]);
		boxVertical.add(checkBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box healthBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		healthBox.add(lab[3]);
		healthBox.add(Box.createHorizontalStrut(23));
		healthBox.add(text[3]);
		boxVertical.add(healthBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box gradeBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		gradeBox.add(lab[4]);
		gradeBox.add(Box.createHorizontalStrut(36));
		gradeBox.add(text[4]);
		boxVertical.add(gradeBox);
		boxVertical.add(Box.createVerticalStrut(15));
		text[4].setEditable(false);
		text[4].addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
				if (text[1].getText().length()==0||text[2].getText().length()==0 || text[3].getText().length()==0) {
					JOptionPane.showMessageDialog(null, "�������ֵ������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int num1= Integer.parseInt(text[1].getText()); 
				int num2 = Integer.parseInt(text[2].getText()); 
				int num3 = Integer.parseInt(text[3].getText()); 
				text[4].setText(String.valueOf(num1 + num2+num3));	
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
			}
		});

		Box datesBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		datesBox.add(lab[5]);
		datesBox.add(Box.createHorizontalStrut(10));
		datesBox.add(text[5]);
		boxVertical.add(datesBox);
		boxVertical.add(Box.createVerticalStrut(15));
		Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//����ʱ����ʾ��ʽ
		String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		text[5].setText(str);
		text[5].setEditable(false);

		Box managers_idBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		managers_idBox.add(lab[6]);
		managers_idBox.add(Box.createHorizontalStrut(23));
		managers_idBox.add(text[6]);
		boxVertical.add(managers_idBox);
		boxVertical.add(Box.createVerticalStrut(15));
		text[6].setText(Login.text1.getText());
		text[6].setEditable(false);

		Box addokBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		Add_OkButton = new JButton("ȷ��");
		Add_OkButton.setVisible(true);
		Add_OkButton.addActionListener(AddOKButton);

		addokBox.add(Add_OkButton);
		addokBox.add(Box.createHorizontalStrut(0));
		boxVertical.add(addokBox);
		boxVertical.add(Box.createVerticalStrut(15));
		Add_Frame.setLayout(new FlowLayout());
		Add_Frame.add(boxVertical);

	}

	ActionListener AddOKButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				new AddGrade(text, dorm);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			TableManager.GradeTable.jt.setModel(querygrade());
		}
	};
	public Object data[][] = null;
	private DefaultTableModel model;
	Object columnNames[] = { "���", "�����", "����", "����", "����", "�ܷ�", "����ʱ��", "������" };
	public DefaultTableModel querygrade() {
		GradeControl dormcontrol = new GradeControl();
		List<GradeModel> result;
		try {
			result = dormcontrol.query();
			data = new Object[result.size()][11];
			int j = 0;
			for (int i = 0; i < result.size(); i++) {
				data[i][j] = result.get(i).getGrade_id();
				j++;
				data[i][j] = result.get(i).getDorm_id();
				j++;
				data[i][j] = result.get(i).getDiscipline();
				j++;
				data[i][j] = result.get(i).getChecks();
				j++;
				data[i][j] = result.get(i).getHealth();
				j++;
				data[i][j] = result.get(i).getGrade();
				j++;
				data[i][j] = result.get(i).getDates();
				j++;
				data[i][j] = result.get(i).getManagers_id();
				j = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

}
