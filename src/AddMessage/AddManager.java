package AddMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Control.ManagerControl;
import Model.ManagersModel;
import Operation.ManagerOperation;

public class AddManager {
	JTextField text1, text2, text3;

	public boolean AddManager(JTextField text1, JTextField text2, JTextField text3) throws Exception {
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		ManagerControl managercontrol = new ManagerControl();
		ManagersModel managermodel = new ManagersModel();
		if (text1.getText().length() != 0) {
			Pattern NamePattren = Pattern.compile("([\u4e00-\u9fa5]{2,4})");
			Matcher NameMatcher = NamePattren.matcher(text1.getText());
			if (NameMatcher.matches()) {
				managermodel.setManager_name(text1.getText());
			} else {
				JOptionPane.showMessageDialog(null, "��������ȷ���ָ�ʽ", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				text1.setText("");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "��������Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		
		if (text2.getText().length() != 0) {
			Pattern NamePattren = Pattern.compile("\\d{6}");
			Matcher NameMatcher = NamePattren.matcher(text2.getText());
			if (NameMatcher.matches()) {
				managermodel.setManager_id(Integer.parseInt(text2.getText()));
			} else {
				JOptionPane.showMessageDialog(null, "Ա������������ȷ��ʽ", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				text2.setText("");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Ա���Ų���Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		for (int i = 0; i < ManagerOperation.manager_id().size(); i++) {
			//System.out.println(ManagerOperation.manager_id().get(i).getManager_id() + "yuangonghao");
			if (ManagerOperation.manager_id().get(i).getManager_id().equals(Integer.parseInt(text2.getText()))) {
				JOptionPane.showMessageDialog(null, "Ա�����Ѵ���", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		

	

		if (text3.getText().length() != 0) {
			Pattern NamePattren = Pattern.compile("(^[1]([3578][0-9]{1})[0-9]{8}$)");
			Matcher NameMatcher = NamePattren.matcher(text3.getText());
			if (NameMatcher.matches()) {
				managermodel.setContact(text3.getText());
			} else {
				JOptionPane.showMessageDialog(null, "��ϵ��ʽ��������ȷ��ʽ", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				text3.setText("");
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "��ϵ��ʽ����Ϊ��", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		for (int i = 0; i < ManagerOperation.contact().size(); i++) {
			//System.out.println(ManagerOperation.contact().get(i).getContact() + "��ϵ");
			if (text3.getText().equals(ManagerOperation.contact().get(i).getContact())) {
				JOptionPane.showMessageDialog(null, "��ϵ��ʽ�Ѵ���", "ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		managercontrol.add(managermodel);
		return true;

	}
}
