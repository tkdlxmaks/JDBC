package kr.ac.sungkyul.bookmall.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.sungkyul.bookmal.vo.BookVo;
import kr.ac.sungkyul.bookmall.dao.BookDao;

public class BookDaoTest {

	public static void main(String[] args) {
		testBookDaoGetList();
	}

	public static void testBookDaoGetList() {
		BookDao dao = new BookDao();

		List<BookVo> list = dao.getBookList();

		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}
	public List<BookVo> getBookList() {
		List<BookVo> list = new ArrayList<BookVo>();

		Connection conn = null;
		ResultSet rs = null;
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

			String sql = "select * from Book";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				Long rate = rs.getLong(3);
				Long authorNo = rs.getLong(4);

				BookVo vo = new BookVo();

				vo.setNo(no);
				vo.setName(name);
				vo.setRate(rate);
				vo.setAuthorNo(authorNo);

				list.add(vo);
			}

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
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlE) {
				System.out.println("sql 에러: " + sqlE);
			}
		}
		return list;
	}
}
