package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.StudentBean;

public class StudentDao {
	//①DBアクセスに必要な情報の定数を定義

	//接続先DBのURL(jdbc:mysql://[ホスト名orIPアドレス]:[ポート番号]/[データベース名]?serverTimezone=JST)
	private static final String url = "jdbc:mysql://localhost:3306/jdbcsample?serverTimezone=JST";
	//ユーザ
	private static final String user = "root";
	//パスワード
	private static final String pw = "pass";

	//INSERT文を実行するメソッドのサンプル
	//引数は登録したい情報が格納されたBean
	public static void insertStudent(StudentBean s){
		//②アクセスに必要な変数の初期化
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			//③JDBCドライバをロードする
			Class.forName("com.mysql.cj.jdbc.Driver");

			//④データベースと接続する(コネクションを取ってくる)
			//第1引数→接続先URL
			//第2引数→ユーザ名
			//第3引数→パスワード
			con = DriverManager.getConnection(url, user, pw);

			//⑤SQL文の元を作成する
			//?をプレースホルダと言います。
			//後の手順で?に値を設定します。
			String sql = "INSERT INTO students(name, age) VALUES(?,?)";

			//⑥SQLを実行するための準備(構文解析)
			pstmt = con.prepareStatement(sql);

			//⑦プレースホルダに値を設定
			//第1引数→何番目の?に設定するか(1から数える)
			//第2引数→?に設定する値
			pstmt.setString(1, s.getName());
			pstmt.setInt(2, s.getAge());

			//⑧SQLを実行し、DBから結果を受領する
			int result = pstmt.executeUpdate();
			System.out.println(result + "件登録されました。");

			//おまけ：⑥の準備が完了すれば?の値を更新して
			//続けてINSERTすることができる。
			pstmt.setString(1, "takahashi");
			pstmt.setInt(2, 20);
			pstmt.executeUpdate();


		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			//⑨DBとの切断処理
			try {
				//nullかチェックしないとNullPointerExceptionが
				//発生してしまうためチェックしている。
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	//DELETE文を実行するメソッドのサンプル
	//引数は削除する学生のID
	public static void deleteStudent(int no){
		//②アクセスに必要な変数の初期化
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			//③JDBCドライバをロードする
			Class.forName("com.mysql.cj.jdbc.Driver");

			//④データベースと接続する(コネクションを取ってくる)
			//第1引数→接続先URL
			//第2引数→ユーザ名
			//第3引数→パスワード
			con = DriverManager.getConnection(url, user, pw);

			//⑤SQL文の元を作成する
			//?をプレースホルダと言います。
			//後の手順で?に値を設定します。
			String sql = "DELETE FROM students WHERE id = ?";

			//⑥SQLを実行するための準備(構文解析)
			pstmt = con.prepareStatement(sql);

			//⑦プレースホルダに値を設定
			//第1引数→何番目の?に設定するか(1から数える)
			//第2引数→?に設定する値
			pstmt.setInt(1, no);

			//⑧SQLを実行し、DBから結果を受領する
			int result = pstmt.executeUpdate();
			System.out.println(result + "件削除されました。");

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			//⑨DBとの切断処理
			try {
				//nullかチェックしないとNullPointerExceptionが
				//発生してしまうためチェックしている。
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	//UPDATE文を実行するメソッドのサンプル
	//引数は変更する学生のBean
	public static void updateStudent(StudentBean s){
		//②アクセスに必要な変数の初期化
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			//③JDBCドライバをロードする
			Class.forName("com.mysql.cj.jdbc.Driver");

			//④データベースと接続する(コネクションを取ってくる)
			//第1引数→接続先URL
			//第2引数→ユーザ名
			//第3引数→パスワード
			con = DriverManager.getConnection(url, user, pw);

			//⑤SQL文の元を作成する
			//?をプレースホルダと言います。
			//後の手順で?に値を設定します。
			String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

			//⑥SQLを実行するための準備(構文解析)
			pstmt = con.prepareStatement(sql);

			//⑦プレースホルダに値を設定
			//第1引数→何番目の?に設定するか(1から数える)
			//第2引数→?に設定する値
			pstmt.setString(1, s.getName());
			pstmt.setInt(2, s.getAge());
			pstmt.setInt(3, s.getId());

			//⑧SQLを実行し、DBから結果を受領する
			int result = pstmt.executeUpdate();
			System.out.println(result + "件更新されました。");

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			//⑨DBとの切断処理
			try {
				//nullかチェックしないとNullPointerExceptionが
				//発生してしまうためチェックしている。
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

}
