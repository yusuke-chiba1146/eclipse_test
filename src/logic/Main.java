package logic;

import bean.StudentBean;
import dao.StudentDao;

public class Main {

	public static void main(String[] args) {

		//StudentBeanクラスのインスタンス生成
		StudentBean student = new StudentBean("大坪",20);

		//生成したインスタンスを引数にDBアクセスロジックを呼び出す
		//staticなメソッドなのでクラスから直接呼出し可能
		StudentDao.insertStudent( student );

		//削除のサンプル
//		StudentDao.deleteStudent(1);

		//更新のサンプル
//		StudentBean s2 = new StudentBean(2,"田中", 30);
//		StudentDao.updateStudent(s2);

	}

}
