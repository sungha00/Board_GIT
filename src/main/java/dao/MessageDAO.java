package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MessageDTO;

public class MessageDAO {

	private static MessageDAO instance =null;

	public synchronized static MessageDAO getInstance(){  

		if(instance==null) {
			instance = new MessageDAO();
		}
		return instance;
	}
	private MessageDAO() {}

	private Connection getConnection() throws Exception{
		Context iCtx = new InitialContext(); 
		DataSource ds=(DataSource)iCtx.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();


	}


	public int insertMessage(String writer, String message) throws Exception {
		String sql = "insert into message values(message_seq.nextval,?,?)";
		try (Connection con = this.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(1, writer);
			stmt.setString(2, message);
			int result = stmt.executeUpdate();
			con.commit();
			return result;
		}
	}

	public List<MessageDTO> selectMessage() throws Exception {
		String sql = "select * from message order by 1";
		try (Connection con = this.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			List<MessageDTO> result = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String message = rs.getString("messages");
				MessageDTO dto = new MessageDTO(id, writer, message);
				result.add(dto);
			}
			return result;
		}
	}

	public int deleteMessage(int delID) throws Exception {
		String sql = "delete from message where id=?";

		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setInt(1, delID);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

	public int updateMessage(int id, String writer, String message) throws Exception{
		String sql = "update message set writer = ?, messages = ? where id = ?";

		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, writer);
			pstat.setString(2, message);
			pstat.setInt(3, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
}
}
