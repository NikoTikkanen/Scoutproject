package fi.haagahelia.scoutproject.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Series {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long sid;
	private String level;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "series")
	private List<Team> teams;
	
	
	public Series () {}

	public Series(String level) {
		super();
		this.level = level;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
	
	

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Series [sid=" + sid + ", level=" + level + "]";
	}
	
	
	
	
}
