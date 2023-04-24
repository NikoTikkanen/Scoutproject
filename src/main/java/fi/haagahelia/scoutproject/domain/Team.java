package fi.haagahelia.scoutproject.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tid;
	private String name;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="sid")
	private Series series;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	private List<Player> players;
	
	
	
	public Team () {}
	//konstruktorit
	public Team(String name, Series series) {
		super();
		this.name = name;
		this.series = series;
		
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Override
	public String toString() {
		if ( this.series != null)
		return "Team [tid=" + tid + ", team=" + name + ", series=" + this.getSeries() +  "]";
		else
			return "Team [tid=" + tid + ", team=" + name +  "]";
	}
	
	
}
