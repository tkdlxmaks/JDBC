import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestCount {

	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			/* 1. 드라이버 로딩 */
			Class.forName("oracle.jdbc.driver.OracleDriver");

			/* 연결 얻어오기 */
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String password = "hr";

			/* 2. 드라이버 연결 */
			conn = DriverManager.getConnection(url, user, password);
			// System.out.println("연결성공"); // 등장하지 않는다면

			/* 3. statement 생성 */
			stmt = conn.createStatement();

			/* 4. sql문 실행 */
			String sql = "select count(*) from employees";
			rs = stmt.executeQuery(sql);
			while (rs.next() == true) {
				int count = rs.getInt(1);
				System.out.println("전체 row의 수는 " + count + "개의 row가 있습니다.");
			}
			/* 드라이버 로딩 실패시 */
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
			/* SQL 연결 실패시 */
		} catch (SQLException sqlE) {
			System.out.println("sql 에러: " + sqlE);
		} finally {
			// 역순으로 종료 시켜줘라.
			try {
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException sqlE) {
				System.out.println("sql 에러: " + sqlE);
			}
		}
	}

}
