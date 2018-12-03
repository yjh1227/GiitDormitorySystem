package Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import JDBC.db;
import Model.ManagersModel;
import Model.StudentsModel;

public class ManagerOperation {
/**
 * ��Ӧ����
 * @param m
 * @throws Exception
 */
	public static ManagersModel managers = null;

	JTextField  text1,text2,text3;
	String manager_id;
	public void AddManager(ManagersModel m) throws Exception {
		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn.prepareStatement(" insert into managers " + " values(?,?,?) ");
		// ����
		prepare.setString(1, m.getManager_name());
		prepare.setInt(2, m.getManager_id());
		prepare.setString(3, m.getContact());
		prepare.execute();

	}

	public void UpdateManager(ManagersModel m) throws SQLException {
		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn.prepareStatement(" update managers set contact=? where manager_id=? ");
		// ����
		prepare.setString(1, m.getContact());
		prepare.setInt(2, m.getManager_id());
		prepare.execute();
	}
	//�����޹���Ϣ
	public void UpdateOneManager(String manager_id,JTextField text1, JTextField text2, JTextField text3) throws SQLException {
		this.manager_id=manager_id;
		this.text1=text1;
		this.text2=text2;
		this.text3=text3;
		Connection conn = db.getConnection();
		PreparedStatement prepareupdate = conn.prepareStatement(
				" update managers set manager_name = ? , contact = ?    where manager_id = ? ");
		prepareupdate.setString(1, text1.getText());
		prepareupdate.setString(2, text3.getText());
		prepareupdate.setString(3,text2.getText() );
		prepareupdate.execute();
	}

	public void DeleteManager(Integer manager_id) throws SQLException {
		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn.prepareStatement(" delete from managers where manager_id = ? ");
		// ����
		prepare.setInt(1, manager_id);
		prepare.execute();
	}
	public void DeleteRemove(Object row) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement delete = conn.prepareStatement(" delete from managers where manager_id = ? ");
		delete.setInt(1, (int) row);
		delete.execute();
	}

	public List<ManagersModel> query() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select * from managers ");
		List<ManagersModel> manag = new ArrayList<ManagersModel>();
		ManagersModel m = null;
		while (rs.next()) {
			m = new ManagersModel();
			m.setManager_name(rs.getString("manager_name"));
			m.setManager_id(rs.getInt("manager_id"));
			m.setContact(rs.getString("contact"));
			manag.add(m);
		}
		return manag;
	}
	/**
	 * ����Ա��ϵ��ʽ
	 * @return
	 * @throws Exception
	 */
	public static List<ManagersModel> contact() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select * from managers ");
		List<ManagersModel> manag = new ArrayList<ManagersModel>();
		ManagersModel m = null;
		while (rs.next()) {
			m = new ManagersModel();		
			m.setContact(rs.getString("contact"));
			manag.add(m);
		}
		return manag;
	}

	
	/**
	 * ����ԱԱ����
	 * @return
	 * @throws Exception
	 */
	public static List<ManagersModel> manager_id() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select * from managers ");
		List<ManagersModel> manag = new ArrayList<ManagersModel>();
		ManagersModel m = null;
		while (rs.next()) {
			m = new ManagersModel();		
			m.setManager_id(Integer.parseInt(rs.getString("manager_id")));
			manag.add(m);
		}
		return manag;
	}
	/*
	 * statementִ�в��������ļ�SQL��䣨����SQL��䣩 PreparedStatement�ӿڼ̳���Statement
	 */
	public static ManagersModel get(Integer manager_id) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement psm = conn.prepareStatement(" select * from managers where manager_id = ? ");
		psm.setInt(1, manager_id);
		// ���ؽ����
		ResultSet rs = psm.executeQuery();
		while (rs.next()) {
			managers = new ManagersModel();
			managers.setManager_name(rs.getString("manager_name"));
			managers.setManager_id(rs.getInt("Manager_id"));
			managers.setContact(rs.getString("contact"));
		}
		return managers;

	}
	public ManagersModel mode(){
		return managers;
		
	}

}
