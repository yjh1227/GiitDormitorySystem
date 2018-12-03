package Operation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.db;
import Model.GradeModel;
import Model.LivedModel;

public class GradeOperation {

	public void AddGrade(GradeModel addgrade) throws Exception {
		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn
				.prepareStatement(" insert into grade (dorm_id,discipline,checks,health,grade,dates,managers_id ) "
						+ " values(?,?,?,?,?,?,?) ");
		// ����		
		prepare.setString(1, addgrade.getDorm_id());
		prepare.setInt(2, addgrade.getDiscipline());
		prepare.setInt(3, addgrade.getChecks());
		prepare.setInt(4, addgrade.getHealth());
		prepare.setInt(5, addgrade.getGrade());
		prepare.setDate(6, new Date(addgrade.getDates().getTime()));
		prepare.setInt(7, addgrade.getManagers_id());
		prepare.execute();
	}
/**
 * ������������
 * @param updategrade
 * @throws SQLException
 */
	public void UpDateGrade(GradeModel updategrade) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn.prepareStatement(
				" update grade set grade_id = ? , dorm_id=? , discipline = ?, check = ? , health = ? ,  grade = ? , dates = ? , managers_id = ?  where dorm_id = ? ");
		prepare.setInt(1, updategrade.getGrade_id());
		prepare.setString(2, updategrade.getDorm_id());
		prepare.setInt(3, updategrade.getDiscipline());
		prepare.setInt(4, updategrade.getChecks());
		prepare.setInt(5, updategrade.getHealth());
		prepare.setInt(6, updategrade.getGrade());
		prepare.setDate(7, new Date(updategrade.getDates().getTime()));
		prepare.setInt(8, updategrade.getManagers_id());
		prepare.execute();
	}
/**
 * ɾ���������ȼ�¼
 * @param deletegrade  ����ţ����������ɾ����������
 * @throws SQLException
 */
	public void DeleteLived(GradeModel deletegrade) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn.prepareStatement("delete from grade where dorm_id = ? ");
		prepare.setString(1, deletegrade.getDorm_id());
		prepare.execute();
	}
/**
 * ɾ������
 * @param row ����ID ɾ���������ȵ�����¼
 * @throws SQLException
 */
	public void DeleteRemove(int row) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement delete = conn.prepareStatement(" delete from grade where grade_id = ? ");
		delete.setInt(1, row);
		delete.execute();
	}
/**
 * ��ѯ�������м�¼
 * @return
 * @throws Exception
 */
	public List<GradeModel> selectall() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select * from grade ");
		List<GradeModel> live = new ArrayList<GradeModel>();
		GradeModel select = null;
		while (rs.next()) {
			select = new GradeModel();
			select.setGrade_id(rs.getInt("grade_id"));
			select.setDorm_id(rs.getString("dorm_id"));
			select.setDiscipline(rs.getInt("discipline"));
			select.setChecks(rs.getInt("checks"));
			select.setHealth(rs.getInt("health"));
			select.setGrade(rs.getInt("grade"));
			select.setDates(rs.getDate("dates"));
			select.setManagers_id(rs.getInt("managers_id"));
			live.add(select);
		}
		return live;

	}
/**
 * ��ѯ��������������Ϣ
 * @param dorm_id
 * @return
 * @throws Exception
 */
	public GradeModel selectone(Integer dorm_id) throws Exception {
		Connection conn = db.getConnection();
		PreparedStatement select = conn.prepareStatement(" select * from grade where dorm_id =  ? ");
		select.setInt(1, dorm_id);
		// ���ؽ����
		ResultSet rs = select.executeQuery();
		GradeModel live = null;
		while (rs.next()) {
			live = new GradeModel();
			live.setGrade_id(rs.getInt("grade_id"));
			live.setDorm_id(rs.getString("dorm_id"));
			live.setDiscipline(rs.getInt("discipline"));
			live.setChecks(rs.getInt("check"));
			live.setHealth(rs.getInt("health"));
			live.setGrade(rs.getInt("grade"));
			live.setDates(rs.getDate("dates"));
			live.setManagers_id(rs.getInt("managers_id"));
		}
		return live;
	}
/**
 * ��ѯ������¼
 * @param dorm_id
 * @return
 * @throws Exception
 */
	public GradeModel select(Integer dorm_id) throws Exception {
		Connection conn = db.getConnection();
		PreparedStatement select = conn.prepareStatement(" select * from grade where dorm_id =  ? ");
		select.setInt(1, dorm_id);
		// ���ؽ����
		ResultSet rs = select.executeQuery();
		GradeModel live = null;
		while (rs.next()) {
			live = new GradeModel();
			live.setGrade_id(rs.getInt("grade_id"));
			live.setDorm_id(rs.getString("dorm_id"));
			live.setDiscipline(rs.getInt("discipline"));
			live.setChecks(rs.getInt("check"));
			live.setHealth(rs.getInt("health"));
			live.setGrade(rs.getInt("grade"));
			live.setDates(rs.getDate("dates"));
			live.setManagers_id(rs.getInt("managers_id"));
		}
		return live;
	}
}
