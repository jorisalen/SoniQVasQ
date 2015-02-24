package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import domain.Comment;
import domain.Member;
import domain.PlayList;
import domain.Record;

public class PostgreSQL implements Database {

	Connection dbConnection = null;

	public PostgreSQL() throws DbException {

		try {
			Class.forName("org.postgresql.Driver");
			dbConnection = DriverManager.getConnection(ConnectionData.url,
					ConnectionData.user, ConnectionData.password);
			dbConnection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}

	}

	public void addMember(String naam, String vnaam, String pw, String email,
			Date geb_datum) throws DbException {

		try {
			PreparedStatement addMember = dbConnection
					.prepareStatement("insert into users(userId, idFacebook, idSoundcloud, idGoogleplus, naam, voornaam, email) values(?,?,?,?,?,?,?)");
			addMember.setString(1, naam);
			addMember.setString(2, vnaam);
			addMember.setString(3, pw);
			addMember.setString(4, email);
			addMember.setString(5, email);
			addMember.setString(6, email);
			addMember.setString(7, email);

			if (addMember.executeUpdate() < 1) {
				throw new DbException("failed to add member");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public void addComment(String content, String userId, int recordId)
			throws DbException {

		try {
			PreparedStatement addComment = dbConnection
					.prepareStatement("insert into comment(content, userId, recordId, timestamp) values(?,?,?,now())");
			addComment.setString(1, content);
			addComment.setString(2, userId);
			addComment.setInt(3, recordId);

			if (addComment.executeUpdate() < 1) {
				throw new DbException("failed to add comment");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public void addRecord(Record record) throws DbException {

		try {
			PreparedStatement addRecord = dbConnection
					.prepareStatement("insert into record(recordId, link, duration) values(?,?,?)");
			addRecord.setLong(1, record.getRecordId());
			addRecord.setString(2, record.getLink());
			addRecord.setInt(3, record.getDuration());

			if (addRecord.executeUpdate() < 1) {
				throw new DbException("failed to add record");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	public Member getMember(String id) throws DbException {
		Member user = null;
		try {
			PreparedStatement getUser = dbConnection
					.prepareStatement("select * from public.users where userid = ?");
			getUser.setString(1, id);
			ResultSet result = getUser.executeQuery();

			if (result.next()) {
				user = convertToUser(result);
			} else {
				throw new DbException("geen user gevonden met dit id");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return user;
	}

	private Member convertToUser(ResultSet result) throws DbException {
		// String naam, String vnaam, String pw, String email, Date geb_datum
		Member user = null;
		try {
			user = new Member(result.getString("naam"), "pw",
					result.getString("voornaam"), result.getString("email"),
					null);

		} catch (SQLException e) {
			throw new DbException("user convertion went wrong", e);
		}
		return user;
	}

	private Comment convertToComment(ResultSet result) throws DbException {
		// String content,String userId,int recordId
		Comment comment = null;
		try {

			comment = new Comment(result.getString("content"),
					result.getString("userid"), result.getInt("recordid"));
			// comment.setNaam(result.getString("voornaam") + " " +
			// result.getString("naam"));

		} catch (SQLException e) {
			throw new DbException("comment convertion went wrong", e);
		}
		return comment;
	}

	public Comment getComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Record getRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPlayList(int userId, String naam) {
		// TODO Auto-generated method stub

	}

	public void sharePlayList(String username, int playListId) {
		// TODO Auto-generated method stub

	}

	public ArrayList<PlayList> getPlayListsForUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Comment> convertToCommentList(ResultSet result)
			throws DbException {
		ArrayList<Comment> messages = new ArrayList<Comment>();
		try {
			while (result.next()) {
				messages.add(convertToComment(result));
			}
		} catch (SQLException e) {
			throw new DbException("comment to list convertion went wrong", e);
		}
		return messages;
	}

	public ArrayList<Comment> getCommentsForRecord(int recordId)
			throws DbException {
		// TODO Auto-generated method stub

		ArrayList<Comment> comments = null;
		try {
			PreparedStatement getCommentsForRecord = dbConnection
					.prepareStatement(""
							+ "select * from comment where recordid = ?");
			getCommentsForRecord.setInt(1, recordId);
			ResultSet result = getCommentsForRecord.executeQuery();
			comments = convertToCommentList(result);
			// No check needed for users can have an empty messagebox
		} catch (SQLException e) {
			throw new DbException("Error while getting comments for record", e);
		}
		return comments;
	}


	public ArrayList<Record> getRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	public Member login(String email, String pass) {
		return null;
	}

	public boolean isUserAuthorized(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void upvote(int recordId) {
		// TODO Auto-generated method stub

	}

	public void downvote(int recordId) {
		// TODO Auto-generated method stub

	}

	public void addPlayList(String userId, int playlistId) throws DbException {
		try {
			PreparedStatement addComment = dbConnection
					.prepareStatement("insert into userplaylist(userid, playlistid) values(?,?");
			addComment.setString(1, userId);
			addComment.setInt(2, playlistId);

			if (addComment.executeUpdate() < 1) {
				throw new DbException("failed to add Playlist");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}


	@Override
	public void addRecordToPlayList(int recordId, int playlistId)
			throws DbException {
		// TODO Auto-generated method stub
		try {
			PreparedStatement addRecord = dbConnection
					.prepareStatement("insert into playlistrecord(recordid, playlistid) values(?,?)");
			addRecord.setLong(1, recordId);
			addRecord.setInt(2, playlistId);
			if (addRecord.executeUpdate() < 1) {
				throw new DbException("failed to add record");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
