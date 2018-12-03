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
import Model.LivedModel;
import Model.ManagersModel;
import Model.RegisterModel;

public class LivedOperation {

	public void AddLived(LivedModel addlive) throws Exception {
		// ������ݿ�����
		Connection conn = db.getConnection();
		// Ԥ���루��SQL�����ص�ָ�������в���ֱ��ִ��ֱ������execute��ִ���ã���ռλ������ʾ��
		PreparedStatement prepare = conn.prepareStatement(" insert into lived " + " values(?,?,?,?) ");
		// ����
		prepare.setInt(1, addlive.getStudent_id());
		prepare.setString(2, addlive.getDorm_id());
		prepare.setInt(3, addlive.getBed_num());
		prepare.setDate(4, new Date(addlive.getLivingdate().getTime()));
		prepare.execute();
	}
/**
 * ������ס
 * @param updatelived
 * @throws SQLException
 */
	public void UpDateLived(LivedModel updatelived) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn.prepareStatement(" update lived set dorm_id = ? , bed_num=? , livingdate = ? where student_id = ? ");
		prepare.setString(1, updatelived.getDorm_id());
		prepare.setInt(2, updatelived.getBed_num());
		prepare.setDate(3, new Date(updatelived.getLivingdate().getTime()));
		prepare.setInt(4, updatelived.getStudent_id());
		prepare.execute();
	}
/**
 * ɾ������
 * @param deletelived
 * @throws SQLException
 */
	public void DeleteLived(LivedModel deletelived) throws SQLException {
		Connection conn = db.getConnection();
		PreparedStatement prepare = conn.prepareStatement("delete from lived where student_id = ? ");
		prepare.setInt(1, deletelived.getStudent_id());
		prepare.execute();
	}

	public List<LivedModel> selectall() throws Exception {
		Connection conn = db.getConnection();
		Statement sme = conn.createStatement();
		ResultSet rs = sme.executeQuery(" select * from lived ");
		List<LivedModel> live = new ArrayList<LivedModel>();
		LivedModel select = null;
		while (rs.next()) {
			select = new LivedModel();
			select.setStudent_id(rs.getInt("student_id"));
			select.setDorm_id(rs.getString("dorm_id"));
			select.setBed_num(rs.getInt("bed_num"));
			select.setLivingdate(rs.getDate("livingdate"));
			live.add(select);
		}
		return live;
	}

	public LivedModel selectone(Integer student_id) throws Exception {
		Connection conn = db.getConnection();
		PreparedStatement select = conn.prepareStatement(" select * from lived where student_id =  ? ");
		select.setInt(1, student_id);
		// ���ؽ����
		ResultSet rs = select.executeQuery();
		LivedModel live = null;
		while (rs.next()) {
			live = new LivedModel();
			live.setStudent_id(rs.getInt("student_id"));
			live.setDorm_id(rs.getString("dorm_id"));
			live.setBed_num(rs.getInt("bed_num"));
			live.setLivingdate(rs.getDate("livingdate"));
		}
		return live;
	}
}
