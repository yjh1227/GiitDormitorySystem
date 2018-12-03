package ButtonFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import AddMessage.AddStudent;
import Control.StudentsControl;
import Model.StudentsModel;
import Operation.DormOperation;
import Operation.RegisterOperation;
import Operation.StudentsOperation;
import Operation.TableFitColumn;
import TableAdministrator.DormTable;
import TableAdministrator.StudentTable;

public class StudentAdd extends JFrame {
	JFrame Add_Frame;
	JButton Add_OkButton;
	Integer a = 0;
	JLabel[] lab = new JLabel[13];
	String[] labstr = { "����", "�Ա�", "��������", "��ַ", "��ϵ��ʽ", "ѧ��", "ѧԺ", "רҵ", "�༶", "����¥��", "����", "״̬" };

	JTextField[] text = new JTextField[13];

	ButtonGroup group = new ButtonGroup();

	JRadioButton Add_sexboy, Add_sexgirl;
	JPanel jp;

	JComboBox CollegeBox;
	String[] CollegeMajor = { "��Ϣ����ѧԺ", "������ѧѧԺ", "���ù���ѧԺ", "����ѧԺ" };

	JComboBox MajorBox;
	String[] InformationMajor = { "�������ѧ�뼼��", "����������", "��Ϣ����", "ͨ�Ź���", "��������ѧ" };

	String[] LifeMajor = { "�����ѧ", "���＼��", "���﹤��" };

	String[] IncomeMajor = { "���ѧ", "����ѧ", "�г�Ӫ��" };

	String[] SpaceFlight = { "���������칤��", "��е��Ƽ����Զ���", "��ؼ���������" };

	public static JComboBox DormitoryBox;
	String[] Dormitory = null;

	public static JComboBox Bed_idBox;
	String[] bed_id = { "1", "2", "3", "4" };

	public StudentAdd() {
		Image image = this.getToolkit().getImage("image\\icon.png");
		JLabel label = new JLabel(new ImageIcon("image\\background.jpg"));
		label.setSize(300, 500);
		Add_Frame = new JFrame();
		Add_Frame.setIconImage(image);
		Add_Frame.setSize(260, 550);
		Add_Frame.setResizable(false);

		/**
		 * ѭ����ǩ
		 */
		for (int i = 0; i < 12; i++) {
			lab[i] = new JLabel(labstr[i]);

		}
		/**
		 * ѭ���ı���
		 */
		for (int i = 0; i < 12; i++) {
			text[i] = new JTextField(10);

		}

		/**
		 * ѧԺ�����б�����
		 */
		CollegeBox = new JComboBox();
		for (int i = 0; i < 4; i++) {
			CollegeBox.addItem(CollegeMajor[i]);
		}

		/**
		 * ���������б���
		 */
		Bed_idBox = new JComboBox();
		Bed_idBox.setEditable(true);

		for (int i = 0; i < 4; i++) {
			Bed_idBox.addItem(bed_id[i]);
		}

		/**
		 * ���������б�����
		 */
		DormitoryBox = new JComboBox();
		try {
			for (int i = 0; i < DormOperation.selectdorm_id().size(); i++) {
				try {
					DormitoryBox.addItem(DormOperation.selectdorm_id().get(i).getDorm_id());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DormitoryBox.setEditable(true);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Add_Frame.setVisible(true);
		Add_Frame.setLocationRelativeTo(null);

		Box boxVertical = new Box(BoxLayout.Y_AXIS); // �������ϵ��º��Ӳ���
		Box nameBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		nameBox.add(lab[0]);
		nameBox.add(Box.createHorizontalStrut(36));
		nameBox.add(text[0]);
		boxVertical.add(nameBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box sexBox = Box.createHorizontalBox();
		sexBox.add(lab[1]);
		JPanel sexPanel = new JPanel();
		sexPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// �����ְ�ť��ˮƽ�ݷ�
		sexBox.add(Box.createHorizontalStrut(30));
		Add_sexboy = new JRadioButton();
		Add_sexboy.setText("��");
		Add_sexgirl = new JRadioButton("Ů");
		Add_sexboy.setSelected(true);
		group.add(Add_sexboy);
		group.add(Add_sexgirl);
		sexPanel.add(Add_sexboy);
		sexPanel.add(Add_sexgirl);
		sexBox.add(sexPanel);
		boxVertical.add(sexBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box birthdayBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		birthdayBox.add(lab[2]);
		birthdayBox.add(Box.createHorizontalStrut(10));
		birthdayBox.add(text[2]);
		boxVertical.add(birthdayBox);
		boxVertical.add(Box.createVerticalStrut(15));

		text[2].setText("��:19960101");
		text[2].setForeground(Color.RED);
		text[2].addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				text[2].setForeground(Color.BLACK);
				text[2].setText("");
			}
		});

		Box addresssBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		addresssBox.add(lab[3]);
		addresssBox.add(Box.createHorizontalStrut(36));
		addresssBox.add(text[3]);
		boxVertical.add(addresssBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box contactBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		contactBox.add(lab[4]);
		contactBox.add(Box.createHorizontalStrut(10));
		contactBox.add(text[4]);
		boxVertical.add(contactBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box student_idBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		student_idBox.add(lab[5]);
		student_idBox.add(Box.createHorizontalStrut(36));
		student_idBox.add(text[5]);
		boxVertical.add(student_idBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box collegeBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		collegeBox.add(lab[6]);
		collegeBox.add(Box.createHorizontalStrut(36));
		collegeBox.add(CollegeBox);
		boxVertical.add(collegeBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box majorBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		majorBox.add(lab[7]);
		majorBox.add(Box.createHorizontalStrut(36));
		MajorBox = new JComboBox(InformationMajor);
		majorBox.add(MajorBox);
		CollegeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (CollegeBox.getSelectedItem().equals("������ѧѧԺ")) {
					MajorBox.removeAllItems();
					for (int i = 0; i < 3; i++) {
						MajorBox.addItem(LifeMajor[i]);
					}
				} else if (CollegeBox.getSelectedItem().equals("���ù���ѧԺ")) {
					MajorBox.removeAllItems();
					for (int i = 0; i < 3; i++) {
						MajorBox.addItem(IncomeMajor[i]);
					}

				} else {
					MajorBox.removeAllItems();
					for (int i = 0; i < 3; i++) {
						MajorBox.addItem(SpaceFlight[i]);
					}

				}

			}
		});
		boxVertical.add(majorBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box classesBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		classesBox.add(lab[8]);
		classesBox.add(Box.createHorizontalStrut(36));
		classesBox.add(text[8]);
		boxVertical.add(classesBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box dorm_idBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		dorm_idBox.add(lab[9]);
		dorm_idBox.add(Box.createHorizontalStrut(10));
		dorm_idBox.add(DormitoryBox);
		boxVertical.add(dorm_idBox);
		boxVertical.add(Box.createVerticalStrut(15));
		/**
		 * ���������б�ļ���,��ʾ����ѡ��Ĵ���
		 */
		DormitoryBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Bed_idBox.removeAllItems();
					Bed_idBox.addItem(bed_id[0]);
					Bed_idBox.addItem(bed_id[1]);
					Bed_idBox.addItem(bed_id[2]);
					Bed_idBox.addItem(bed_id[3]);
					for (int i = 0; i < StudentsOperation.selectdorm(DormitoryBox.getSelectedItem().toString())
							.size(); i++) {

//						System.out.println(
//								StudentsOperation.selectdorm(DormitoryBox.getSelectedItem().toString()).size() + "��С");
//						System.out.println(StudentsOperation.selectdorm(DormitoryBox.getSelectedItem().toString())
//								.get(i).getBed_id() + "ѧ������");
//						System.out.println(bed_id[i] + "����");
//						System.out.println(bed_id[i].equals(StudentsOperation
//								.selectdorm(DormitoryBox.getSelectedItem().toString()).get(i).getBed_id()));
						if (bed_id[i].equals(StudentsOperation.selectdorm(DormitoryBox.getSelectedItem().toString())
								.get(i).getBed_id())) {
						} else {
							Bed_idBox.removeItem(bed_id[i]);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Box bed_idBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		bed_idBox.add(lab[10]);
		bed_idBox.add(Box.createHorizontalStrut(36));
		bed_idBox.add(Bed_idBox);
		boxVertical.add(bed_idBox);
		boxVertical.add(Box.createVerticalStrut(15));

		Box statusBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		statusBox.add(lab[11]);
		statusBox.add(Box.createHorizontalStrut(36));
		statusBox.add(text[11]);
		boxVertical.add(statusBox);
		boxVertical.add(Box.createVerticalStrut(15));
		lab[11].setVisible(false);
		text[11].setText("0");
		text[11].setEditable(false);
		text[11].setVisible(false);

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

	/**
	 * ���ѧ����ȷ�ϰ�ť
	 */

	ActionListener AddOKButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				//System.out.println(DormitoryBox.getSelectedItem().toString() + "����¥��");
				if (new AddStudent().AddStudent(text, Add_sexboy, Add_sexgirl, CollegeBox, DormitoryBox,
						MajorBox) == false) {
					return;
				}
				boolean b = true;
				for (int i = 0; i < DormOperation.selectdorm_id().size(); i++) {
					// System.out.println(DormitoryBox.getSelectedItem().toString()
					// .equals(DormOperation.selectdorm_id().get(i).getDorm_id()));
					// System.out.println(DormOperation.selectdorm_id().get(i).getDorm_id());
					if (DormitoryBox.getSelectedItem().equals(DormOperation.selectdorm_id().get(i).getDorm_id())) {
						new DormOperation().UpDateoneDorm(DormitoryBox.getSelectedItem().toString());
						new RegisterOperation().AddStudentRegister(text[5].getText());
						
						// System.out.println("ִ����");
						b = false;
						break;
					}
				}
				if (b) {
					new DormOperation().AddStudentDorm(DormitoryBox.getSelectedItem().toString(), "4", a);
					}
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			StudentTable.jt.setModel(querystudent());
			TableFitColumn.fitTableColumns(StudentTable.jt);// ����Ӧ�п�
		}
	};
	Object columnNames[] = { "����", "�Ա�", "��������", "��ַ", "��ϵ��ʽ", "ѧ��", "ѧԺ", "רҵ", "�༶", "�����", "����" };
	public Object data[][] = null;
	public static DefaultTableModel model;
	public DefaultTableModel querystudent() {
		StudentsControl studentcontrol = new StudentsControl();
		List<StudentsModel> result;
		try {
			result = studentcontrol.all();
			data = new Object[result.size()][11];
			int j = 0;
			for (int i = 0; i < result.size(); i++) {
				data[i][j] = result.get(i).getName();
				j++;
				data[i][j] = result.get(i).getSex();
				j++;
				data[i][j] = StudentsOperation.getfromunix((String) result.get(i).getBirthday());
				;
				j++;
				data[i][j] = result.get(i).getAddress();
				j++;
				data[i][j] = result.get(i).getContact();
				j++;
				data[i][j] = result.get(i).getStudent_id();
				j++;
				data[i][j] = result.get(i).getCollege();
				j++;
				data[i][j] = result.get(i).getMajor();
				j++;
				data[i][j] = result.get(i).getClasses();
				j++;
				data[i][j] = result.get(i).getDorm_id();
				j++;
				data[i][j] = result.get(i).getBed_id();
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
