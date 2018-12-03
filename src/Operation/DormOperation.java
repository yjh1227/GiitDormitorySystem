package Operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.db;
import Model.DormModel;

public class DormOperation {
	public static DormModel live = null;
	String dorm;
	String bed_id;

	public void AddDorm(DormModel adddorm) throws Exception {
		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn.prepareStatement(" insert into dorm " + " values(?,?,?) ");
		// ����
		prepare.setString(1, adddorm.getDorm_id());
		prepare.setInt(2, adddorm.getBed_num());
		prepare.setInt(3, adddorm.getPeople_num());
		prepare.execute();
	}

	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @param dorm
	 *            �����
	 * @param bed_num
	 *            ����
	 * @param people_num
	 *            ����
	 * @throws Exception
	 */

	public void AddStudentDorm(String dorm, String bed_num, Integer people_num) throws Exception {

		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn
				.prepareStatement(" insert into  dorm (dorm_id,bed_num,people_num) " + " values(?,?,?) ");
		// ����
		prepare.setString(1, dorm);
		prepare.setString(2, bed_num);
		prepare.setInt(3, people_num + 1);
		prepare.execute();
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param updatedorm
	 * @throws SQLException
	 */
	public void UpDateDorm(DormModel updatedorm) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn
				.prepareStatement(" update dorm set dorm_id = ? , bed_num=? , people_num = ? where dorm_id = ? ");
		prepare.setString(1, updatedorm.getDorm_id());
		prepare.setInt(2, updatedorm.getBed_num());
		prepare.setInt(3, updatedorm.getPeople_num());
		prepare.execute();
	}

	/**
	 * ������������
	 * 
	 * @param dorm_id
	 *            �����
	 * @throws SQLException
	 */
	public void UpDateoneDorm(String dorm_id) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn
				.prepareStatement(" update dorm set  people_num = people_num + 1   where dorm_id = ? ");
		prepare.setString(1, dorm_id);
		prepare.execute();
	}

	public void UpDateRemoveOneDorm(Object dorm_id) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn
				.prepareStatement(" update dorm set  people_num = people_num - 1   where dorm_id = ? ");
		prepare.setString(1, (String) dorm_id);
		prepare.execute();
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param deletegrade
	 * @throws SQLException
	 */
	public void DeleteDorm(DormModel deletegrade) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn.prepareStatement("delete from dorm where dorm_id = ? ");
		prepare.setString(1, deletegrade.getDorm_id());
		prepare.execute();
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param row
	 *            ��ǰѡ�����е�ĳһ��
	 * @throws SQLException
	 */
	public void DeleteRemove(Object row) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement delete = conn.prepareStatement(" delete from dorm where dorm_id = ? ");
		delete.setInt(1, (int) row);
		delete.execute();
	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DormModel> selectall() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select * from dorm ");
		List<DormModel> live = new ArrayList<DormModel>();
		DormModel select = null;
		while (rs.next()) {
			select = new DormModel();
			select.setDorm_id(rs.getString("dorm_id"));
			select.setBed_num(rs.getInt("bed_num"));
			select.setPeople_num(rs.getInt("people_num"));
			live.add(select);
		}
		return live;

	}

	/**
	 * ��ѯ���������
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<DormModel> selectdorm_id() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select dorm_id from dorm where people_num<>4 ");
		List live = new ArrayList<DormModel>();
		DormModel select = null;
		while (rs.next()) {
			select = new DormModel();
			select.setDorm_id(rs.getString("dorm_id"));
			live.add(select);
		}
		return live;

	}

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @param dorm_id
	 * @return
	 * @throws Exception
	 */
	public static DormModel selectone(String dorm_id) throws Exception {
		Connection conn = db.getConnection();
		PreparedStatement select = conn.prepareStatement(" select * from dorm where dorm_id =  ? ");
		select.setString(1, dorm_id);
		// ���ؽ����
		ResultSet rs = select.executeQuery();

		while (rs.next()) {
			live = new DormModel();
			live.setDorm_id(rs.getString("dorm_id"));
			live.setBed_num(rs.getInt("bed_num"));
			live.setPeople_num(rs.getInt("people_num"));
		}
		return live;
	}
}
