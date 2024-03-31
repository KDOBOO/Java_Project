 package subject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;


// 회원관리 화면 UI
public class MemberUI {
	private int num = 100001; // 회원번호 초기값        //바이트 기반의 입력 스트림(System.in)을 문자 기반의 입력 스트림으로 변환
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// ㄴ 사용자의 입력을 문자열로 읽어올 수 있음
	private Member member = new MemberImpl();

	// 회원관리 메뉴
	public void menu() {
		int ch;

		while (true) {
			try {
				do {
					System.out.println("\t\t[회원 관리]");
					System.out.println("---------------------------------------------------------");
					System.out.println("1.회원등록 2.회원정보 수정 3.회원삭제 4.회원 전체조회 5.회원검색 6.뒤로가기\n");
					System.out.println("선택 => ");
					
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 6);

				if (ch == 6) {
					return;
				}

				switch (ch) {
				case 1: insert(); break;
				case 2: update(); break;
				case 3: delete(); break;
				case 4: list(); break;
				case 5: search(); break;
				// case 6 : // 돌아가기
				}

			} catch (NumberFormatException e) { 
				// Integer.parseInt()에서 발생될 경우 예외처리
				System.out.println("잘못 입력하셨습니다.\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 회원 등록
	public void insert() { 
		String tel;
		System.out.println("\n[회원 등록]");

		MemberVO vo = new MemberVO(); //새로운 회원 정보를 저장할 MemberVO타입의 vo객체를 생성
		try {

			System.out.print("이름 => ");
			vo.setName(br.readLine());

			System.out.print("주소 => ");
			vo.setAddress(br.readLine());

			// 전화번호
			// 전화번호가 000-0000-0000 의 형식과 일치하지 않으면 return
			System.out.print("전화번호[예: 010-0000-0000] => ");
			tel = br.readLine();
			if (!tel.matches("^\\d{3}-\\d{4}-\\d{4}$")) {
				System.out.println("형식을 잘못 입력하셨습니다.\n");
				return;
			} else {
				vo.setTel(tel);
			}

			// 등록일
			// 시스템 날짜 입력
			Calendar cal = Calendar.getInstance();
			String s = String.format("%tF", cal);
			vo.setRegDate(s);

			// 회원번호
			// 초기값(100001)부터 1씩 증가시키면서 회원번호 생성
			String id = Integer.toString(num++);
			vo.setId(id);

			member.insertMember(vo);
			System.out.println("\n" + vo.getName() + "님의 회원 정보가 등록되었습니다.");
			System.out.println("회원번호 : " + vo.getId() + "\n");

		} catch (PatternException e) {
			// 이름을 한글 또는 영문자로 입력하지 않은 경우 예외처리
			System.out.println("형식을 잘못 입력하셨습니다. 한글 혹은 영어로 입력하세요.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원정보 수정
	public void update() {
		System.out.println("\n[회원 수정]");
		String id, tel;
		int ch;

		try {
			System.out.print("회원번호 => ");
			id = br.readLine();

			// 회원번호가 여섯자리의 숫자가 아닌 경우
			if (!id.matches("\\d{6}")) {
				System.out.println("회원번호 여섯자리 숫자를 입력해주세요.\n");
				return;
			}

			MemberVO vo = member.findById(id);

			// 회원번호가 존재하지 않는 경우
			if (vo == null) {
				System.out.println("존재하지 않는 회원입니다.\n");
				return;
			}

			// 수정할 항목 선택
			do {
				System.out.println("\n[수정할 항목 선택]");
				System.out.print("1.이름 2.주소 3.전화번호 => ");
				ch = Integer.parseInt(br.readLine());
			} while (ch < 1 || ch > 3);

			switch (ch) {
			case 1:
				System.out.print("이름 => ");
				vo.setName(br.readLine());
				break;

			case 2:
				System.out.print("주소 => ");
				vo.setAddress(br.readLine());
				break;

			case 3:
				// 전화번호가 000-0000-0000 의 형식과 일치하지 않으면 return
				System.out.print("전화번호[예: 010-0000-0000] => ");
				tel = br.readLine();
				if (!tel.matches("^\\d{3}-\\d{4}-\\d{4}$")) {
					System.out.println("형식을 잘못 입력하셨습니다. [예: 010-1234-1234]\n");
					return;
				} else {
					vo.setTel(tel);
				}
				break;
			}

			System.out.println("\n" + vo.getName() + "님의 정보 수정이 완료되었습니다.\n");

		} catch (subject.PatternException e) {
			// 이름을 한글 또는 영문자로 입력하지 않은 경우 예외처리
			System.out.println("형식을 잘못 입력하셨습니다. 한글 혹은 영어로 입력하세요.\n");
		} catch (NumberFormatException e) {
			System.out.println("잘못 입력하셨습니다.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원 삭제
	public void delete() {
		System.out.println("\n[회원 삭제]");
		String id;

		try {
			System.out.print("삭제할 회원번호 => ");
			id = br.readLine();

			// 회원번호가 여섯자리의 숫자가 아닌 경우
			if (!id.matches("\\d{6}")) {
				System.out.println("회원번호 여섯자리 숫자를 입력해주세요.\n");
				return;
			}

			// 회원번호가 일치하면 삭제
			boolean b = member.deleteMember(id);
			if (b) {
				System.out.println("회원 정보가 삭제되었습니다.\n");
			} else {
				System.out.println("존재하지 않는 회원입니다.\n");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 전체 리스트
	public void list() {
		System.out.println("\n[회원 전체리스트]");

		List<MemberVO> list = member.listMember();

		System.out.println("이름\t주소\t전화번호\t등록일\t회원번호");
		for (MemberVO vo : list) {
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAddress() + "\t");
			System.out.print(vo.getTel() + "\t");
			System.out.print(vo.getRegDate() + "\t");
			System.out.println(vo.getId());
		}
		System.out.println();

	}

	// 회원 검색
	public void search() {
		System.out.println("\n[회원 검색]");
		String id;

		try {
			System.out.print("회원번호 => ");
			id = br.readLine();

			// 회원번호가 여섯자리의 숫자가 아닌 경우
			if (!id.matches("\\d{6}")) {
				System.out.println("회원번호 여섯자리 숫자를 입력해주세요.\n");
				return;
			}

			MemberVO vo = member.findById(id);
			if (vo == null) {
				System.out.println("존재하지 않는 회원번호입니다.");
				return;
			}

			System.out.println("이름\t주소\t전화번호\t\t등록일\t\t회원번호");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAddress() + "\t");
			System.out.print(vo.getTel() + "\t");
			System.out.print(vo.getRegDate() + "\t");
			System.out.println(vo.getId() + "\n");

		} catch (Exception e) {
		}
	}
}
