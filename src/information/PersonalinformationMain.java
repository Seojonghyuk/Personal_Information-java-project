package information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalinformationMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int INPUT = 1, PRINT = 2, ANLYZE = 3, SEARCH = 4, UPDATE = 5, SORT = 6, DELETE = 7, EXIT = 8;

	public static void main(String[] args) {
		boolean run = true;
		int no = 0;
		ArrayList<Personalinformation> list = new ArrayList<>();
		DBConnection dbCon = new DBConnection();
		while (run) {
			System.out.println("=====================|개인신상정보 프로그램|=========================");
			System.out.println("1.입력| 2.출력| 3.분석| 4.검색| 5.수정| 6.정렬| 7.삭제| 8.종료");
			System.out.println("==============================================================");
			System.out.print(">>");
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case INPUT:
				Personalinformation person = inputDataperson();
				// 데이타베이스입력
				int rValue = dbCon.insert(person);
				if (rValue == 1) {
					System.out.println("삽입성공");
				} else {
					System.out.println("삽입실패");
				}
				break;

			case PRINT:
				ArrayList<Personalinformation> list2 = dbCon.select();
				if (list2 == null) {
					System.out.println("선택실패");
				} else {
					printperson(list2);
				}
				break;
			case ANLYZE:
				ArrayList<Personalinformation> list3 = dbCon.analizeSelect();
				if (list3 == null) {
					System.out.println("분석선택실패");
				}
				printAnalyze(list3);
				break;
			case SEARCH:
				String dataName = searchPerson();
				ArrayList<Personalinformation> list4 = dbCon.nameSearchSelect(dataName);
				if (list4.size() >= 1) {
					printperson(list4);
				} else {
					System.out.println("이름 검색오류");
				}
				break;
			case UPDATE:

				int updateReturnteValue = 0;
				int id = inputId();
				Personalinformation per = dbCon.selectId(id);
				if (per == null) {
					System.out.println("수정오류발생");
				} else {
					Personalinformation updatePerson = updatePerson(per);
					updateReturnteValue = dbCon.update(updatePerson);
				}
				if (updateReturnteValue == 1) {
					System.out.println("update 성공");
				} else {
					System.out.println("update 실패");
				}
				break;

			case SORT:
				System.out.println("1.키순 | 2.몸무게순 | 3.나이순");
				System.out.println("번호를 선택해주세요");
				int no1 = Integer.parseInt(sc.nextLine());
				switch (no1) {
				case 1:
					ArrayList<Personalinformation> sortList1 = dbCon.sortHeight();
					if (sortList1 == null) {
						System.out.println("정렬 실패");
					} else {
						sortHeight(sortList1);
					}
					break;
				case 2:
					ArrayList<Personalinformation> sortList2 = dbCon.sortWeight();
					if (sortList2 == null) {
						System.out.println("정렬 실패");
					} else {
						sortWeight(sortList2);
					}
					break;
				case 3:
					ArrayList<Personalinformation> sortList3 = dbCon.sortAge();
					if (sortList3== null) {
						System.out.println("정렬 실패");
					} else {
						sortAge(sortList3);
					}
					break;
				}
				break;
			case DELETE:
				int deleteid = inputId();
				int deleteReturnValue = dbCon.delete(deleteid);
				if (deleteReturnValue == 1) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
				break;
			case EXIT:
				run = false;
				break;
			}
		} // end of while
		System.out.println("종료");
	} // end of main

	private static void sortHeight(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "이름" + "\t" + "나이" + "\t" + "성별" + "\t" + "키" + "\t" + "몸무게" + "\t" + "지역"
				+ "\t" + "혈액형" + "\t" + "전화번호" + "\t" + "        주민등록번호");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void sortWeight(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "이름" + "\t" + "나이" + "\t" + "성별" + "\t" + "키" + "\t" + "몸무게" + "\t" + "지역"
				+ "\t" + "혈액형" + "\t" + "전화번호" + "\t" + "        주민등록번호");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	private static void sortAge(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "이름" + "\t" + "나이" + "\t" + "성별" + "\t" + "키" + "\t" + "몸무게" + "\t" + "지역"
				+ "\t" + "혈액형" + "\t" + "전화번호" + "\t" + "        주민등록번호");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static Personalinformation updatePerson(Personalinformation per) {
		int age = updateData(per.getName(), "나이", per.getAge());
		per.setAge(age);
		int height = updateData(per.getName(), "키", per.getHeight());
		per.setHeight(height);
		int weight = updateData(per.getName(), "몸무게", per.getWeight());
		per.setWeight(weight);
		System.out.println(per);
		return per;
	}

	private static int updateData(String persondata, String name, int score) {
		boolean run = true;
		int data = 0;
		while (run) {
			System.out.println(name + " " + persondata + " " + score + ">>");
			try {
				data = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(score));
				if (matcher.find() && data <= 200 && data >= 0) {
					run = false;
				} else {
					System.out.println("정보를 잘못입력하였습니다. 재입력요청");
				}
			} catch (NumberFormatException e) {
				System.out.println("정보 입력에 오류 발생");
				data = 0;
			}
		}
		return data;
	}

	private static void printScoreSort(ArrayList<Personalinformation> list) {
		Collections.sort(list, Collections.reverseOrder());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + "등 " + list.get(i).toString());
		}

	}

	private static int inputId() {
		boolean run = true;
		int id = 0;
		while (run) {
			try {
				System.out.println("ID 입력(number): ");
				id = Integer.parseInt(sc.nextLine());
				if (id > 0 && id < Integer.MAX_VALUE) {
					run = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("id 입력오류");
			}
		}
		return id;
	}

	private static String searchPerson() {
		String name = null;
		name = matchingNamePattern();
		return name;
	}

	private static String matchingNamePattern() {
		String name = null;
		while (true) {
			try {
				System.out.print("검색할 이름: ");
				name = sc.nextLine();
				Pattern pattern = Pattern.compile("^[가-힣]{2,4}$");
				Matcher matcher = pattern.matcher(name);
				if (!matcher.find()) {
					System.out.println("이름입력오류발생 다시재입력요청합니다.");
				} else {
					break;
				}

			} catch (Exception e) {
				System.out.println("입력에서 오류가 발생했습니다.");
				break;
			}
		}
		return name;
	}

	private static void printAnalyze(ArrayList<Personalinformation> list3) {
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("id" + "\t" + "이름" + "\t" + "나이" + "\t" + "성별" + "\t" + "키" + "\t" + "몸무게" + "\t" + "혈액형"
				+ "\t" + "전화번호" + "\t");
		for (Personalinformation data : list3) {
			System.out.println(data.getId() + "\t" + data.getName() + "\t" + data.getAge() + "\t" + data.getGender()
					+ "\t" + data.getHeight() + "\t" + data.getWeight() + "\t" + data.getBloodtype() + "\t"
					+ data.getPhoneNumber());
		}
		System.out.println("+---------------------------------------------------------------------------------------------+");
	}

	private static void printperson(ArrayList<Personalinformation> list2) {
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("id" + "\t" + "이름" + "\t" + "나이" + "\t" + "성별" + "\t" + "키" + "\t" + "몸무게" + "\t" + "지역"
				+ "\t" + "혈액형" + "\t" + "전화번호" + "\t" + "        주민등록번호");
		for (Personalinformation data : list2) {
			System.out.println(data);
		}
		System.out.println("+---------------------------------------------------------------------------------------------+");
	}

	private static Personalinformation inputDataperson() {
		String name = inputName();
		int age = inputAge();
		String gender = inputGender();
		int height = inputHeight();
		int weight = inputWeight();
		String city = inputAddress();
		String bloodtype = inputType();
		String phoneNumber = inputNumber();
		String birth = inputBirth();
		Personalinformation person = new Personalinformation(0, name, age, gender, height, weight, city, bloodtype,
				phoneNumber, birth);

		return person;
	}

	private static String inputBirth() {
		System.out.println("주민등록번호 앞 6자리:");
		int s = Integer.parseInt(sc.nextLine());
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		Matcher matcher = pattern.matcher(String.valueOf(s));
		if (!matcher.find()) {
			System.out.println("잘못됨");
		}

		System.out.println("주민등록번호 뒤 7자리 :");
		int b = Integer.parseInt(sc.nextLine());
		Pattern pattern1 = Pattern.compile("^[0-9]{7}$");
		Matcher matcher1 = pattern1.matcher(String.valueOf(b));
		if (!matcher1.find()) {
			System.out.println("잘못됨");
		}
		return s + "-" + b;
	}

	private static String inputNumber() {
		int s = (int) (Math.random() * (9999 - 1111 + 1) + 1111);
		int t = (int) (Math.random() * (9999 - 1111 + 1) + 1111);
		String number = ("010-" + s + "-" + t);
		Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
		Matcher matcher = pattern.matcher(number);
		if (!matcher.find()) {
			System.out.println("잘못됨");
		}
		return number;
	}

	private static String inputType() {
		String type = null;
		while (true) {
			System.out.print("혈액형 :");
			type = sc.nextLine();
			if (type.equals("A") || type.equals("B") || type.equals("AB") || type.equals("O")) {
				break;
			} else {
				System.out.println("혈액형입력오류 다시입력해주세요.");
				continue;
			}
		}
		return type;
	}

	private static String inputAddress() {
		String address = null;
		System.out.print("주소입력 :");
		address = sc.nextLine();
		return address;
	}

	private static String inputName() {
		String name = null;
		while (true) {
			System.out.print("이름 :");
			name = sc.nextLine();
			Pattern pattern = Pattern.compile("^[가-힣]{2,4}$");
			Matcher matcher = pattern.matcher(name);
			if (!matcher.find()) {
				System.out.println("이름입력오류 다시입력해주세요.");
			} else {
				break;
			}
		}
		return name;
	}

	private static String inputGender() {
		String gender = null;
		boolean run = true;
		while (run) {
			System.out.print("성별입력(남자/여자) :");
			gender = sc.nextLine();
			if (gender.equals("남자") || gender.equals("여자")) {
				run = false;
				break;
			} else {
				System.out.println("성별 입력오류 ");
			}
		}
		return gender;
	}

	private static int inputWeight() {
		int weight = 0;
		while (true) {
			try {
				System.out.print("몸무게 :");
				weight = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(weight));
				if (matcher.find() && weight <= 200) {
					break;
				} else {
					System.out.println("몸무게를 잘못입력하였습니다. 재입력요청");
				}

			} catch (NumberFormatException e) {
				System.out.println("몸무게입력 오류 발생");
				weight = 0;
				break;
			}
		}
		return weight;
	}

	private static int inputHeight() {
		int height = 0;
		while (true) {
			try {
				System.out.print("키 :");
				height = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(height));
				if (matcher.find() && height <= 200) {
					break;
				} else {
					System.out.println("키를 잘못입력하였습니다. 재입력요청");
				}

			} catch (NumberFormatException e) {
				System.out.println("키입력 오류 발생");
				height = 0;
				break;
			}
		}
		return height;
	}

	private static int inputAge() {

		int age = 0;
		while (true) {
			try {
				System.out.print("나이 :");
				age = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(age));
				if (matcher.find() && age <= 100) {
					break;
				} else {
					System.out.println("나이를 잘못입력하였습니다. 재입력요청");
				}

			} catch (NumberFormatException e) {
				System.out.println("나이입력 오류 발생");
				age = 0;
				break;
			}
		}
		return age;

	}

}
