package kr.ac.sungkyul.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmal.vo.AuthorVo;

public class AuthorDao {

	/* 전체 리스트 가져오기 */
	public List<AuthorVo> getList() {
		List<AuthorVo> list = new ArrayList<AuthorVo>(); // 리스트 객체 생성

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			/* 1. 드라이버 로딩 */
			Class.forName("oracle.jdbc.driver.OracleDriver");

			/* 연결 얻어오기 */
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "skudb";
			String password = "skudb";

			/* 2. 드라이버 연결 */
			conn = DriverManager.getConnection(url, user, password);
			// System.out.println("연결성공"); // 등장하지 않는다면

			/* 3. statement 생성 */
			stmt = conn.createStatement();

			/* 4. sql문 실행 */
			String sql = "select no, name, description from author"; 
			rs = stmt.executeQuery(sql);
			
			while (rs.next() == true) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				
				AuthorVo vo = new AuthorVo();
				
				
				vo.setNo(no);
				vo.setName(name);
				vo.setDescription(description);
				
				list.add(vo);
			}
			conn.close();
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

		return list;
	}
	
	public int insert(AuthorVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			/* 1. 드라이버 로딩 */
			Class.forName("oracle.jdbc.driver.OracleDriver");

			/* 연결 얻어오기 */
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "skudb";
			String password = "skudb";

			/* 2. 드라이버 연결 */
			conn = DriverManager.getConnection(url, user, password);
			// System.out.println("연결성공"); // 등장하지 않는다면

			/* 4. sql문 준비 - insert */
			String sql = "insert into author values ( seq_author.nextval, ?,?)";			
			
			/* 3. statement 준비 */ 
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getDescription());
			
			count = pstmt.executeUpdate(); //preparedStatement에 넣는 경우 동적이기에  이 실행문은 에러
			System.out.println(count + "개의 row가 입력되었습니다.");

			/* 드라이버 로딩 실패시 */
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
			/* SQL 연결 실패시 */
		} catch (SQLException sqlE) {
			System.out.println("sql 에러: " + sqlE);
		} finally {
			// 역순으로 종료 시켜줘라.
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException sqlE) {
				System.out.println("sql 에러: " + sqlE);
			}
		}
		return count;
	}
}
