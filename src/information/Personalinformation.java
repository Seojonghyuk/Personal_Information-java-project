package information;

import java.util.Objects;

public class Personalinformation implements Comparable<Personalinformation> {
	private int id;
	private String name;
	private int age;
	private String gender;
	private int height;
	private int weight;
	private String city;
	private String bloodtype;
	private String phoneNumber;
	private String birth;

	public Personalinformation(int id, String name, int age, int height, int weight) {
		this(id, name, age, null, height, weight, null, null, null, null);
	}
	public Personalinformation( String name, int age, String gender, int height, int weight, String city,
			String bloodtype, String phoneNumber, String birth) {
		this(0,name,age,gender,height,weight,city,bloodtype,phoneNumber,birth);
	}
	

	public Personalinformation(int id, String name, int age, String gender, int height, int weight, String city,
			String bloodtype, String phoneNumber, String birth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.city = city;
		this.bloodtype = bloodtype;
		this.phoneNumber = phoneNumber;
		this.birth = birth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personalinformation other = (Personalinformation) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return id + "\t" + name + "\t" + age + "\t" + gender + "\t" + height + "\t" + weight + "\t" + city + "\t"
				+ bloodtype + "\t" + phoneNumber + "\t" + birth;
	}

	public int compareTo(Personalinformation o) {
		if ((this.height - o.height) == 0) {
			return 0;
		} else if ((this.height - o.height) > 0) {
			return 1;
		} else {
			return -1;
		}

	}

}
