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
			System.out.println("=====================|���νŻ����� ���α׷�|=========================");
			System.out.println("1.�Է�| 2.���| 3.�м�| 4.�˻�| 5.����| 6.����| 7.����| 8.����");
			System.out.println("==============================================================");
			System.out.print(">>");
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case INPUT:
				Personalinformation person = inputDataperson();
				// ����Ÿ���̽��Է�
				int rValue = dbCon.insert(person);
				if (rValue == 1) {
					System.out.println("���Լ���");
				} else {
					System.out.println("���Խ���");
				}
				break;

			case PRINT:
				ArrayList<Personalinformation> list2 = dbCon.select();
				if (list2 == null) {
					System.out.println("���ý���");
				} else {
					printperson(list2);
				}
				break;
			case ANLYZE:
				ArrayList<Personalinformation> list3 = dbCon.analizeSelect();
				if (list3 == null) {
					System.out.println("�м����ý���");
				}
				printAnalyze(list3);
				break;
			case SEARCH:
				String dataName = searchPerson();
				ArrayList<Personalinformation> list4 = dbCon.nameSearchSelect(dataName);
				if (list4.size() >= 1) {
					printperson(list4);
				} else {
					System.out.println("�̸� �˻�����");
				}
				break;
			case UPDATE:

				int updateReturnteValue = 0;
				int id = inputId();
				Personalinformation per = dbCon.selectId(id);
				if (per == null) {
					System.out.println("���������߻�");
				} else {
					Personalinformation updatePerson = updatePerson(per);
					updateReturnteValue = dbCon.update(updatePerson);
				}
				if (updateReturnteValue == 1) {
					System.out.println("update ����");
				} else {
					System.out.println("update ����");
				}
				break;

			case SORT:
				System.out.println("1.Ű�� | 2.�����Լ� | 3.���̼�");
				System.out.println("��ȣ�� �������ּ���");
				int no1 = Integer.parseInt(sc.nextLine());
				switch (no1) {
				case 1:
					ArrayList<Personalinformation> sortList1 = dbCon.sortHeight();
					if (sortList1 == null) {
						System.out.println("���� ����");
					} else {
						sortHeight(sortList1);
					}
					break;
				case 2:
					ArrayList<Personalinformation> sortList2 = dbCon.sortWeight();
					if (sortList2 == null) {
						System.out.println("���� ����");
					} else {
						sortWeight(sortList2);
					}
					break;
				case 3:
					ArrayList<Personalinformation> sortList3 = dbCon.sortAge();
					if (sortList3== null) {
						System.out.println("���� ����");
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
					System.out.println("���� ����");
				} else {
					System.out.println("���� ����");
				}
				break;
			case EXIT:
				run = false;
				break;
			}
		} // end of while
		System.out.println("����");
	} // end of main

	private static void sortHeight(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "�̸�" + "\t" + "����" + "\t" + "����" + "\t" + "Ű" + "\t" + "������" + "\t" + "����"
				+ "\t" + "������" + "\t" + "��ȭ��ȣ" + "\t" + "        �ֹε�Ϲ�ȣ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void sortWeight(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "�̸�" + "\t" + "����" + "\t" + "����" + "\t" + "Ű" + "\t" + "������" + "\t" + "����"
				+ "\t" + "������" + "\t" + "��ȭ��ȣ" + "\t" + "        �ֹε�Ϲ�ȣ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	private static void sortAge(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "�̸�" + "\t" + "����" + "\t" + "����" + "\t" + "Ű" + "\t" + "������" + "\t" + "����"
				+ "\t" + "������" + "\t" + "��ȭ��ȣ" + "\t" + "        �ֹε�Ϲ�ȣ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static Personalinformation updatePerson(Personalinformation per) {
		int age = updateData(per.getName(), "����", per.getAge());
		per.setAge(age);
		int height = updateData(per.getName(), "Ű", per.getHeight());
		per.setHeight(height);
		int weight = updateData(per.getName(), "������", per.getWeight());
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
					System.out.println("������ �߸��Է��Ͽ����ϴ�. ���Է¿�û");
				}
			} catch (NumberFormatException e) {
				System.out.println("���� �Է¿� ���� �߻�");
				data = 0;
			}
		}
		return data;
	}

	private static void printScoreSort(ArrayList<Personalinformation> list) {
		Collections.sort(list, Collections.reverseOrder());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + "�� " + list.get(i).toString());
		}

	}

	private static int inputId() {
		boolean run = true;
		int id = 0;
		while (run) {
			try {
				System.out.println("ID �Է�(number): ");
				id = Integer.parseInt(sc.nextLine());
				if (id > 0 && id < Integer.MAX_VALUE) {
					run = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("id �Է¿���");
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
				System.out.print("�˻��� �̸�: ");
				name = sc.nextLine();
				Pattern pattern = Pattern.compile("^[��-�R]{2,4}$");
				Matcher matcher = pattern.matcher(name);
				if (!matcher.find()) {
					System.out.println("�̸��Է¿����߻� �ٽ����Է¿�û�մϴ�.");
				} else {
					break;
				}

			} catch (Exception e) {
				System.out.println("�Է¿��� ������ �߻��߽��ϴ�.");
				break;
			}
		}
		return name;
	}

	private static void printAnalyze(ArrayList<Personalinformation> list3) {
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("id" + "\t" + "�̸�" + "\t" + "����" + "\t" + "����" + "\t" + "Ű" + "\t" + "������" + "\t" + "������"
				+ "\t" + "��ȭ��ȣ" + "\t");
		for (Personalinformation data : list3) {
			System.out.println(data.getId() + "\t" + data.getName() + "\t" + data.getAge() + "\t" + data.getGender()
					+ "\t" + data.getHeight() + "\t" + data.getWeight() + "\t" + data.getBloodtype() + "\t"
					+ data.getPhoneNumber());
		}
		System.out.println("+---------------------------------------------------------------------------------------------+");
	}

	private static void printperson(ArrayList<Personalinformation> list2) {
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("id" + "\t" + "�̸�" + "\t" + "����" + "\t" + "����" + "\t" + "Ű" + "\t" + "������" + "\t" + "����"
				+ "\t" + "������" + "\t" + "��ȭ��ȣ" + "\t" + "        �ֹε�Ϲ�ȣ");
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
		System.out.println("�ֹε�Ϲ�ȣ �� 6�ڸ�:");
		int s = Integer.parseInt(sc.nextLine());
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		Matcher matcher = pattern.matcher(String.valueOf(s));
		if (!matcher.find()) {
			System.out.println("�߸���");
		}

		System.out.println("�ֹε�Ϲ�ȣ �� 7�ڸ� :");
		int b = Integer.parseInt(sc.nextLine());
		Pattern pattern1 = Pattern.compile("^[0-9]{7}$");
		Matcher matcher1 = pattern1.matcher(String.valueOf(b));
		if (!matcher1.find()) {
			System.out.println("�߸���");
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
			System.out.println("�߸���");
		}
		return number;
	}

	private static String inputType() {
		String type = null;
		while (true) {
			System.out.print("������ :");
			type = sc.nextLine();
			if (type.equals("A") || type.equals("B") || type.equals("AB") || type.equals("O")) {
				break;
			} else {
				System.out.println("�������Է¿��� �ٽ��Է����ּ���.");
				continue;
			}
		}
		return type;
	}

	private static String inputAddress() {
		String address = null;
		System.out.print("�ּ��Է� :");
		address = sc.nextLine();
		return address;
	}

	private static String inputName() {
		String name = null;
		while (true) {
			System.out.print("�̸� :");
			name = sc.nextLine();
			Pattern pattern = Pattern.compile("^[��-�R]{2,4}$");
			Matcher matcher = pattern.matcher(name);
			if (!matcher.find()) {
				System.out.println("�̸��Է¿��� �ٽ��Է����ּ���.");
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
			System.out.print("�����Է�(����/����) :");
			gender = sc.nextLine();
			if (gender.equals("����") || gender.equals("����")) {
				run = false;
				break;
			} else {
				System.out.println("���� �Է¿��� ");
			}
		}
		return gender;
	}

	private static int inputWeight() {
		int weight = 0;
		while (true) {
			try {
				System.out.print("������ :");
				weight = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(weight));
				if (matcher.find() && weight <= 200) {
					break;
				} else {
					System.out.println("�����Ը� �߸��Է��Ͽ����ϴ�. ���Է¿�û");
				}

			} catch (NumberFormatException e) {
				System.out.println("�������Է� ���� �߻�");
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
				System.out.print("Ű :");
				height = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(height));
				if (matcher.find() && height <= 200) {
					break;
				} else {
					System.out.println("Ű�� �߸��Է��Ͽ����ϴ�. ���Է¿�û");
				}

			} catch (NumberFormatException e) {
				System.out.println("Ű�Է� ���� �߻�");
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
				System.out.print("���� :");
				age = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(age));
				if (matcher.find() && age <= 100) {
					break;
				} else {
					System.out.println("���̸� �߸��Է��Ͽ����ϴ�. ���Է¿�û");
				}

			} catch (NumberFormatException e) {
				System.out.println("�����Է� ���� �߻�");
				age = 0;
				break;
			}
		}
		return age;

	}

}
