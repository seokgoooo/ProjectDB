package capitals;


import java.sql.SQLException;
public class Test {
	private Manager mg;

	public Test(Manager mg) {
		super();
		this.mg = mg;
	}

	public static void main(String[] args) {
		Manager mg = new Manager();
		try {
			mg.create(101, "독일", "베를린", "유럽");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

//	}
//		try {
//			mg.read();
//			System.out.println("보여줘");
//		} catch (Exception e) {
//			System.out.println("?");
//		}
//		
//		try {
//		mg.update(1, "일본", "도쿄", "아시아");
//		System.out.println("수정완료");
//		} catch(SQLException e) {
//			System.out.println("?");
//		}
//		
//		try {
//			mg.delete(3);
//			System.out.println("삭제?");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
		
		
		
		}

}
