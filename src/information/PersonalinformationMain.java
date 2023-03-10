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
			System.out.println("=====================|°³ÀÎ½Å»óÁ¤º¸ ÇÁ·Î±×·¥|=========================");
			System.out.println("1.ÀÔ·Â| 2.Ãâ·Â| 3.ºĞ¼®| 4.°Ë»ö| 5.¼öÁ¤| 6.Á¤·Ä| 7.»èÁ¦| 8.Á¾·á");
			System.out.println("==============================================================");
			System.out.print(">>");
			no = Integer.parseInt(sc.nextLine());
			switch (no) {
			case INPUT:
				Personalinformation person = inputDataperson();
				// µ¥ÀÌÅ¸º£ÀÌ½ºÀÔ·Â
				int rValue = dbCon.insert(person);
				if (rValue == 1) {
					System.out.println("»ğÀÔ¼º°ø");
				} else {
					System.out.println("»ğÀÔ½ÇÆĞ");
				}
				break;

			case PRINT:
				ArrayList<Personalinformation> list2 = dbCon.select();
				if (list2 == null) {
					System.out.println("¼±ÅÃ½ÇÆĞ");
				} else {
					printperson(list2);
				}
				break;
			case ANLYZE:
				ArrayList<Personalinformation> list3 = dbCon.analizeSelect();
				if (list3 == null) {
					System.out.println("ºĞ¼®¼±ÅÃ½ÇÆĞ");
				}
				printAnalyze(list3);
				break;
			case SEARCH:
				String dataName = searchPerson();
				ArrayList<Personalinformation> list4 = dbCon.nameSearchSelect(dataName);
				if (list4.size() >= 1) {
					printperson(list4);
				} else {
					System.out.println("ÀÌ¸§ °Ë»ö¿À·ù");
				}
				break;
			case UPDATE:

				int updateReturnteValue = 0;
				int id = inputId();
				Personalinformation per = dbCon.selectId(id);
				if (per == null) {
					System.out.println("¼öÁ¤¿À·ù¹ß»ı");
				} else {
					Personalinformation updatePerson = updatePerson(per);
					updateReturnteValue = dbCon.update(updatePerson);
				}
				if (updateReturnteValue == 1) {
					System.out.println("update ¼º°ø");
				} else {
					System.out.println("update ½ÇÆĞ");
				}
				break;

			case SORT:
				System.out.println("1.Å°¼ø | 2.¸ö¹«°Ô¼ø | 3.³ªÀÌ¼ø");
				System.out.println("¹øÈ£¸¦ ¼±ÅÃÇØÁÖ¼¼¿ä");
				int no1 = Integer.parseInt(sc.nextLine());
				switch (no1) {
				case 1:
					ArrayList<Personalinformation> sortList1 = dbCon.sortHeight();
					if (sortList1 == null) {
						System.out.println("Á¤·Ä ½ÇÆĞ");
					} else {
						sortHeight(sortList1);
					}
					break;
				case 2:
					ArrayList<Personalinformation> sortList2 = dbCon.sortWeight();
					if (sortList2 == null) {
						System.out.println("Á¤·Ä ½ÇÆĞ");
					} else {
						sortWeight(sortList2);
					}
					break;
				case 3:
					ArrayList<Personalinformation> sortList3 = dbCon.sortAge();
					if (sortList3== null) {
						System.out.println("Á¤·Ä ½ÇÆĞ");
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
					System.out.println("»èÁ¦ ¼º°ø");
				} else {
					System.out.println("»èÁ¦ ½ÇÆĞ");
				}
				break;
			case EXIT:
				run = false;
				break;
			}
		} // end of while
		System.out.println("Á¾·á");
	} // end of main

	private static void sortHeight(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "ÀÌ¸§" + "\t" + "³ªÀÌ" + "\t" + "¼ºº°" + "\t" + "Å°" + "\t" + "¸ö¹«°Ô" + "\t" + "Áö¿ª"
				+ "\t" + "Ç÷¾×Çü" + "\t" + "ÀüÈ­¹øÈ£" + "\t" + "        ÁÖ¹Îµî·Ï¹øÈ£");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static void sortWeight(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "ÀÌ¸§" + "\t" + "³ªÀÌ" + "\t" + "¼ºº°" + "\t" + "Å°" + "\t" + "¸ö¹«°Ô" + "\t" + "Áö¿ª"
				+ "\t" + "Ç÷¾×Çü" + "\t" + "ÀüÈ­¹øÈ£" + "\t" + "        ÁÖ¹Îµî·Ï¹øÈ£");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	private static void sortAge(ArrayList<Personalinformation> list) {
		System.out.println("id" + "\t" + "ÀÌ¸§" + "\t" + "³ªÀÌ" + "\t" + "¼ºº°" + "\t" + "Å°" + "\t" + "¸ö¹«°Ô" + "\t" + "Áö¿ª"
				+ "\t" + "Ç÷¾×Çü" + "\t" + "ÀüÈ­¹øÈ£" + "\t" + "        ÁÖ¹Îµî·Ï¹øÈ£");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private static Personalinformation updatePerson(Personalinformation per) {
		int age = updateData(per.getName(), "³ªÀÌ", per.getAge());
		per.setAge(age);
		int height = updateData(per.getName(), "Å°", per.getHeight());
		per.setHeight(height);
		int weight = updateData(per.getName(), "¸ö¹«°Ô", per.getWeight());
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
					System.out.println("Á¤º¸¸¦ Àß¸øÀÔ·ÂÇÏ¿´½À´Ï´Ù. ÀçÀÔ·Â¿äÃ»");
				}
			} catch (NumberFormatException e) {
				System.out.println("Á¤º¸ ÀÔ·Â¿¡ ¿À·ù ¹ß»ı");
				data = 0;
			}
		}
		return data;
	}

	private static void printScoreSort(ArrayList<Personalinformation> list) {
		Collections.sort(list, Collections.reverseOrder());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + 1 + "µî " + list.get(i).toString());
		}

	}

	private static int inputId() {
		boolean run = true;
		int id = 0;
		while (run) {
			try {
				System.out.println("ID ÀÔ·Â(number): ");
				id = Integer.parseInt(sc.nextLine());
				if (id > 0 && id < Integer.MAX_VALUE) {
					run = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("id ÀÔ·Â¿À·ù");
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
				System.out.print("°Ë»öÇÒ ÀÌ¸§: ");
				name = sc.nextLine();
				Pattern pattern = Pattern.compile("^[°¡-ÆR]{2,4}$");
				Matcher matcher = pattern.matcher(name);
				if (!matcher.find()) {
					System.out.println("ÀÌ¸§ÀÔ·Â¿À·ù¹ß»ı ´Ù½ÃÀçÀÔ·Â¿äÃ»ÇÕ´Ï´Ù.");
				} else {
					break;
				}

			} catch (Exception e) {
				System.out.println("ÀÔ·Â¿¡¼­ ¿À·ù°¡ ¹ß»ıÇß½À´Ï´Ù.");
				break;
			}
		}
		return name;
	}

	private static void printAnalyze(ArrayList<Personalinformation> list3) {
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("id" + "\t" + "ÀÌ¸§" + "\t" + "³ªÀÌ" + "\t" + "¼ºº°" + "\t" + "Å°" + "\t" + "¸ö¹«°Ô" + "\t" + "Ç÷¾×Çü"
				+ "\t" + "ÀüÈ­¹øÈ£" + "\t");
		for (Personalinformation data : list3) {
			System.out.println(data.getId() + "\t" + data.getName() + "\t" + data.getAge() + "\t" + data.getGender()
					+ "\t" + data.getHeight() + "\t" + data.getWeight() + "\t" + data.getBloodtype() + "\t"
					+ data.getPhoneNumber());
		}
		System.out.println("+---------------------------------------------------------------------------------------------+");
	}

	private static void printperson(ArrayList<Personalinformation> list2) {
		System.out.println("+---------------------------------------------------------------------------------------------+");
		System.out.println("id" + "\t" + "ÀÌ¸§" + "\t" + "³ªÀÌ" + "\t" + "¼ºº°" + "\t" + "Å°" + "\t" + "¸ö¹«°Ô" + "\t" + "Áö¿ª"
				+ "\t" + "Ç÷¾×Çü" + "\t" + "ÀüÈ­¹øÈ£" + "\t" + "        ÁÖ¹Îµî·Ï¹øÈ£");
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
		System.out.println("ÁÖ¹Îµî·Ï¹øÈ£ ¾Õ 6ÀÚ¸®:");
		int s = Integer.parseInt(sc.nextLine());
		Pattern pattern = Pattern.compile("^[0-9]{6}$");
		Matcher matcher = pattern.matcher(String.valueOf(s));
		if (!matcher.find()) {
			System.out.println("Àß¸øµÊ");
		}

		System.out.println("ÁÖ¹Îµî·Ï¹øÈ£ µÚ 7ÀÚ¸® :");
		int b = Integer.parseInt(sc.nextLine());
		Pattern pattern1 = Pattern.compile("^[0-9]{7}$");
		Matcher matcher1 = pattern1.matcher(String.valueOf(b));
		if (!matcher1.find()) {
			System.out.println("Àß¸øµÊ");
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
			System.out.println("Àß¸øµÊ");
		}
		return number;
	}

	private static String inputType() {
		String type = null;
		while (true) {
			System.out.print("Ç÷¾×Çü :");
			type = sc.nextLine();
			if (type.equals("A") || type.equals("B") || type.equals("AB") || type.equals("O")) {
				break;
			} else {
				System.out.println("Ç÷¾×ÇüÀÔ·Â¿À·ù ´Ù½ÃÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
		}
		return type;
	}

	private static String inputAddress() {
		String address = null;
		System.out.print("ÁÖ¼ÒÀÔ·Â :");
		address = sc.nextLine();
		return address;
	}

	private static String inputName() {
		String name = null;
		while (true) {
			System.out.print("ÀÌ¸§ :");
			name = sc.nextLine();
			Pattern pattern = Pattern.compile("^[°¡-ÆR]{2,4}$");
			Matcher matcher = pattern.matcher(name);
			if (!matcher.find()) {
				System.out.println("ÀÌ¸§ÀÔ·Â¿À·ù ´Ù½ÃÀÔ·ÂÇØÁÖ¼¼¿ä.");
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
			System.out.print("¼ºº°ÀÔ·Â(³²ÀÚ/¿©ÀÚ) :");
			gender = sc.nextLine();
			if (gender.equals("³²ÀÚ") || gender.equals("¿©ÀÚ")) {
				run = false;
				break;
			} else {
				System.out.println("¼ºº° ÀÔ·Â¿À·ù ");
			}
		}
		return gender;
	}

	private static int inputWeight() {
		int weight = 0;
		while (true) {
			try {
				System.out.print("¸ö¹«°Ô :");
				weight = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(weight));
				if (matcher.find() && weight <= 200) {
					break;
				} else {
					System.out.println("¸ö¹«°Ô¸¦ Àß¸øÀÔ·ÂÇÏ¿´½À´Ï´Ù. ÀçÀÔ·Â¿äÃ»");
				}

			} catch (NumberFormatException e) {
				System.out.println("¸ö¹«°ÔÀÔ·Â ¿À·ù ¹ß»ı");
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
				System.out.print("Å° :");
				height = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(height));
				if (matcher.find() && height <= 200) {
					break;
				} else {
					System.out.println("Å°¸¦ Àß¸øÀÔ·ÂÇÏ¿´½À´Ï´Ù. ÀçÀÔ·Â¿äÃ»");
				}

			} catch (NumberFormatException e) {
				System.out.println("Å°ÀÔ·Â ¿À·ù ¹ß»ı");
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
				System.out.print("³ªÀÌ :");
				age = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(age));
				if (matcher.find() && age <= 100) {
					break;
				} else {
					System.out.println("³ªÀÌ¸¦ Àß¸øÀÔ·ÂÇÏ¿´½À´Ï´Ù. ÀçÀÔ·Â¿äÃ»");
				}

			} catch (NumberFormatException e) {
				System.out.println("³ªÀÌÀÔ·Â ¿À·ù ¹ß»ı");
				age = 0;
				break;
			}
		}
		return age;

	}

}
