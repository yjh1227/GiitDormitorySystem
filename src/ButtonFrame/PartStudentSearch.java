package ButtonFrame;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Control.StudentsControl;
import Model.StudentsModel;
import Operation.StudentsOperation;

public class PartStudentSearch extends JFrame {
	private JFrame frame = new JFrame();
	private JPanel jpl = new JPanel();
	private JPanel jpbox = new JPanel();
	
	JLabel[] labbox = new JLabel[8];
	String labstr[] = { "����         ","�Ա�         ", "��ϵ��ʽ", "ѧ��         ", "ѧԺ         ","�༶         ", "����¥��", "����         "};

	public static JTextField[] textbox = new JTextField[8];
	JLabel lab1,lab2;
	JTextField text;
	JButton search; 
	
	public PartStudentSearch(){
		Image image = this.getToolkit().getImage("image\\icon.png");
		
		lab1 = new JLabel("ѧ����Ϣ��ѯ");
		lab1.setBounds(255, 0, 150, 30);
		lab1.setFont(new Font("���Ĳ���", Font.BOLD, 23));
		frame.add(lab1);
		
		lab2 = new JLabel("ѧ��:");
		lab2.setBounds(100, 40, 150, 50);
		lab2.setFont(new Font("�����п�", Font.BOLD, 23));
		//frame.add(lab2);
		
		text = new JTextField();
		text.setBounds(170, 50, 150, 30);
		text.setFont(new Font("����", Font.BOLD, 18));
		//frame.add(text);
		
		ImageIcon ic = new ImageIcon("image\\search.png");
		search = new JButton(ic);
		search.setBounds(600,45,80, 25);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentsControl r = new StudentsControl();
				StudentsModel rr = null;
				boolean bb = false;
				if(e.getSource()==search){
					try {
						if(text.getText().length()==0){
							JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ��","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
							return;
						}else {
							rr = r.get(Integer.parseInt(text.getText()));					
						}
						if(rr!=null){
							bb=rr.getStudent_id().equals(Integer.parseInt(text.getText()));
							//System.out.println(rr.getStudent_id()+"���ݿ��е�ѧ��");
							//System.out.println(text.getText()+"�����ѧ��");
						}else {
							JOptionPane.showMessageDialog(null, "ѧ�Ų���ȷ","ϵͳ��ʾ",JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(bb){							
						new StudentsOperation();
						StudentsOperation.selectone(Integer.parseInt(text.getText()));
						textbox[0].setText(StudentsOperation.re.getName());
						textbox[1].setText(StudentsOperation.re.getSex());
						textbox[2].setText(StudentsOperation.re.getContact());
						textbox[3].setText(StudentsOperation.re.getStudent_id()+"");
						textbox[4].setText(StudentsOperation.re.getCollege());
						textbox[5].setText(StudentsOperation.re.getClasses());
						textbox[6].setText(StudentsOperation.re.getDorm_id());
						textbox[7].setText(StudentsOperation.re.getBed_id()+"");	
						return;
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
		
		/**
		 * ѭ����ǩ
		 */
		for (int i = 0; i < 8; i++) {
			labbox[i] = new JLabel(labstr[i]);
			labbox[i].setFont(new Font("�����п�", Font.PLAIN, 18));
		}
		/**
		 * ѭ���ı���
		 */
		for (int i = 0; i < 8; i++) {
			textbox[i] = new JTextField(15);
			textbox[i].setFont(new Font("����", Font.PLAIN, 18));
			textbox[i].setEditable(false);

		}
		
		Box boxVertical =new Box(BoxLayout.Y_AXIS); // �������ϵ��º��Ӳ���	
		Box titleBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		titleBox.add(lab1);
		titleBox.add(Box.createHorizontalStrut(36));
		boxVertical.add(titleBox);
		boxVertical.add(Box.createVerticalStrut(15));
		
		Box studentBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		studentBox.add(lab2);
		studentBox.add(Box.createHorizontalStrut(36));
		studentBox.add(text);
		studentBox.add(Box.createHorizontalStrut(36));
		frame.add(search);
		boxVertical.add(studentBox);
		boxVertical.add(Box.createVerticalStrut(60));
		
		Box nameBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		nameBox.add(labbox[0]);
		nameBox.add(Box.createHorizontalStrut(36));
		nameBox.add(textbox[0]);
		nameBox.add(Box.createHorizontalStrut(36));
		nameBox.add(labbox[1]);
		nameBox.add(Box.createHorizontalStrut(36));
		nameBox.add(textbox[1]);
		boxVertical.add(nameBox);
		boxVertical.add(Box.createVerticalStrut(15));
		
		Box sexBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		sexBox.add(labbox[2]);
		sexBox.add(Box.createHorizontalStrut(36));
		sexBox.add(textbox[2]);
		sexBox.add(Box.createHorizontalStrut(36));
		sexBox.add(labbox[3]);
		sexBox.add(Box.createHorizontalStrut(36));
		sexBox.add(textbox[3]);
		boxVertical.add(sexBox);
		boxVertical.add(Box.createVerticalStrut(15));
		
		Box contactBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		contactBox.add(labbox[4]);
		contactBox.add(Box.createHorizontalStrut(36));
		contactBox.add(textbox[4]);
		contactBox.add(Box.createHorizontalStrut(36));
		contactBox.add(labbox[5]);
		contactBox.add(Box.createHorizontalStrut(36));
		contactBox.add(textbox[5]);
		boxVertical.add(contactBox);
		boxVertical.add(Box.createVerticalStrut(15));
		
		Box collegeBox = Box.createHorizontalBox();// ���������Һ��Ӳ���
		collegeBox.add(labbox[6]);
		collegeBox.add(Box.createHorizontalStrut(36));
		collegeBox.add(textbox[6]);
		collegeBox.add(Box.createHorizontalStrut(36));
		collegeBox.add(labbox[7]);
		collegeBox.add(Box.createHorizontalStrut(36));
		collegeBox.add(textbox[7]);
		boxVertical.add(collegeBox);
		boxVertical.add(Box.createVerticalStrut(15));
		
		jpbox.add(boxVertical);
					
		jpl.setLayout(null);
		jpbox.setVisible(true);
		jpl.add(jpbox);
		frame.add(jpl);
		frame.setIconImage(image);
		frame.setTitle("��ѯ");
		frame.setSize(700, 350);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(jpbox);
		frame.setVisible(true);
		
	}
	public JTextField gettext(){
		return text;
	}
}
