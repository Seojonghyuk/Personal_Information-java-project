package information;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
	private Connection connection = null;

	public void connect() {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:/202301javaworkspace/information/db.properties");
			properties.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream error" + e.getStackTrace());
		} catch (IOException e) {
			System.out.println("Properties.load error" + e.getStackTrace());
		}

		try {
			Class.forName(properties.getProperty("driverName"));
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("password"));
		} catch (ClassNotFoundException e) {
			System.err.println("[데이터베이스 로드 오류]" + e.getStackTrace());
		} catch (SQLException e) {
			System.err.println("[데이터베이스 연결 오류]" + e.getStackTrace());
		}
	}

	// 데이터 삽입
	public int insert(Personalinformation s) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "insert into personalTBL values(null, ? , ? , ?, ?, ?, ?, ?, ?,?)";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, s.getName());
			ps.setInt(2, s.getAge());
			ps.setString(3, s.getGender());
			ps.setInt(4, s.getHeight());
			ps.setInt(5, s.getWeight());
			ps.setString(6, s.getCity());
			ps.setString(7, s.getBloodtype());
			ps.setString(8, s.getPhoneNumber());
			ps.setString(9, s.getBirth());
			// 삽입성공하면 1 리턴
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally
		return returnValue;
	}

	// 선택 select statement
	public ArrayList<Personalinformation> select() {

		ArrayList<Personalinformation> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from personaltbl;";
		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String city = rs.getString("city");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				String birth = rs.getString("birth");
				list.add(new Personalinformation(id, name, age, gender, height, weight, city, bloodtype, phoneNumber,birth));
			}

		} catch (Exception e) {
			System.out.println("select 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally

		return list;
	}

	public ArrayList<Personalinformation> analizeSelect() {
		ArrayList<Personalinformation> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select id,name,age,gender,height,weight,bloodtype,Phonenumber from personaltbl";

		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				list.add(new Personalinformation(id, name, age, gender, height, weight, null, bloodtype, phoneNumber,null));
			}

		} catch (Exception e) {
			System.out.println("select 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally

		return list;

	}

	public ArrayList<Personalinformation> nameSearchSelect(String dataName) {

		ArrayList<Personalinformation> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from personaltbl where name like ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%" + dataName + "%");
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String city = rs.getString("city");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				String birth = rs.getString("birth");
				list.add(new Personalinformation(id, name, age, gender, height, weight, city, bloodtype, phoneNumber, birth));
			}

		} catch (Exception e) {
			System.out.println("select 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} // end of finally
		return list;
	}

	public Personalinformation selectId(int dataId) {
		Personalinformation person = null;
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from personaltbl where id = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, dataId);
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String city = rs.getString("city");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				String birth = rs.getString("birth");
				person = new Personalinformation(id, name, age, gender, height, weight, city,bloodtype,phoneNumber,birth);
			}

		} catch (Exception e) {
			System.out.println("select id 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally
		return person;
	}

	public int update(Personalinformation s) {

		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "update personaltbl set age = ?, height = ?, weight = ?, city = ? , bloodtype =?, phonenumber =?  where id = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, s.getAge());
			ps.setInt(2, s.getHeight());
			ps.setInt(3, s.getWeight());
			ps.setString(4, s.getCity());
			ps.setString(5, s.getBloodtype());
			ps.setString(6, s.getPhoneNumber());
			ps.setInt(7, s.getId());
			// 삽입성공하면 1 리턴
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("update 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally
		return returnValue;
	}

	public ArrayList<Personalinformation> sortHeight() {
		ArrayList<Personalinformation> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from personaltbl order by height desc";

		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String city = rs.getString("city");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				String birth = rs.getString("birth");
				list.add(new Personalinformation(id, name, age, gender, height, weight, city , bloodtype , phoneNumber,birth));
			}

		} catch (Exception e) {
			System.out.println("select 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally

		return list;
	}

	public int delete(int deleteid) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "delete from personaltbl where id = ?;";

		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, deleteid);
			// 삽입성공하면 1 리턴
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally
		return returnValue;
	}

	public ArrayList<Personalinformation> sortWeight() {
		ArrayList<Personalinformation> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from personaltbl order by weight desc";

		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String city = rs.getString("city");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				String birth = rs.getString("birth");
				list.add(new Personalinformation(id, name, age, gender, height, weight, city , bloodtype , phoneNumber,birth));
			}

		} catch (Exception e) {
			System.out.println("select 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}

		} // end of finally
		return list;
	}
	public ArrayList<Personalinformation> sortAge() {
		ArrayList<Personalinformation> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from personaltbl order by age desc";
		
		try {
			ps = connection.prepareStatement(query);
			// select 성공하면 ResultSet 실패하면 null
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				int height = rs.getInt("height");
				int weight = rs.getInt("weight");
				String city = rs.getString("city");
				String bloodtype = rs.getString("bloodtype");
				String phoneNumber = rs.getString("phoneNumber");
				String birth = rs.getString("birth");
				list.add(new Personalinformation(id, name, age, gender, height, weight, city , bloodtype , phoneNumber,birth));
			}
			
		} catch (Exception e) {
			System.out.println("select 오류 발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
			
		} // end of finally
		
		return list;
		
	}
}




