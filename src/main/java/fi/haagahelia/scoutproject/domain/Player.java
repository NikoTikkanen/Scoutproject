package fi.haagahelia.scoutproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {

	//luodaan pelaaja jolla on generoituva id numero, etunimi, sukunimi, syntymäpäivä ja ikä
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String position;
	private String age;
	private String str;
	private String weakness;
	
	//Toisesta taulusta tietoja. Sarjataso ja seurajoukkue muunmuassa.
	
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="tid")
	private Team team;
	
	public Player () {}
	
	//konstruktorit ja setterit, getterit sekä to string
	public Player(String firstName, String lastName, String position, String age, String str, String weakness, Team team) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.age = age;
		this.str = str;
		this.weakness = weakness;
		
		
		this.team = team;
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	
	
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	



	@Override
	public String toString() {
		if (this.team != null)
			return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position + ", age="
			+ age + ", strenght" + str + ", weaknsess" + weakness + ", team=" + this.getTeam() +"]";
		else
			return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position=" + position + ", age="
				+ age + ", strenght" + str + ", weaknsess" + weakness +"]";
	}
	
	
	
}
