package fi.haagahelia.scoutproject.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.scoutproject.domain.Player;
import fi.haagahelia.scoutproject.domain.PlayersRepository;
import fi.haagahelia.scoutproject.domain.Series;
import fi.haagahelia.scoutproject.domain.SeriesRepository;
import fi.haagahelia.scoutproject.domain.Team;
import fi.haagahelia.scoutproject.domain.TeamRepository;

@Controller
public class PController {

	@Autowired
	private PlayersRepository repository;
	
	@Autowired
	private SeriesRepository srepository;
	
	@Autowired
	private TeamRepository trepository;
	
	
	
	// Ohjataan login sivulle.
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    
    //kirjautumisen jälkeen home sivulle.
    @RequestMapping(value="/home")
    public String home() {	
        return "home";
    }
    
    
	//näytä kaikki pelaajat
	@RequestMapping(value={"/playerlist"})
	public String playerList(Model model) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println("JUKKA: " + username);
    	model.addAttribute("name", username);
		model.addAttribute("players", repository.findAll());
		return "playerlist";
	}
	
	//näytä kaikki sarjat
		@RequestMapping(value={"/serieslist"})
		public String seriesList(Model model) {
			List<Team> teams = (List<Team>) trepository.findAll();
			model.addAttribute("teams", teams);
			List<Player> players = (List<Player>) repository.findAll();
			model.addAttribute("players", players);
			model.addAttribute("seriess", srepository.findAll());
			return "serieslist";
		}
		
		//näytä kaikki joukkueet
				@RequestMapping(value={"/teamlist"})
				public String teamList(Model model) {
					List<Player> players = (List<Player>) repository.findAll();
					model.addAttribute("players", players);
					model.addAttribute("teams", trepository.findAll());
					model.addAttribute("seriess", srepository.findAll());
					return "teamlist";
				}
	
	
		
		//hakee kaikki Sarjat Json muodossa
				@RequestMapping(value="/series", method = RequestMethod.GET)
				public @ResponseBody List<Series> seriesListRest(){
					return (List<Series>) srepository.findAll();
				}
				
				//hakee kaikki Joukkueet Json muodossa
				@RequestMapping(value="/team", method = RequestMethod.GET)
				public @ResponseBody List<Team> teamListRest(){
					return (List<Team>) trepository.findAll();
				}
				
				//hakee kaikki Pelaajat json muodossa
				@RequestMapping(value="/players", method = RequestMethod.GET)
				public @ResponseBody List<Player> playerListRest(){
					return (List<Player>) repository.findAll();
				}
		
		// RESTful service to get player by id
	    @RequestMapping(value="/player/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Player> findPlayerRest(@PathVariable("id") Long playerId) {	
	    	return repository.findById(playerId);
	    }
	    
	 // RESTful service to get series by id
	    @RequestMapping(value="/series/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Series> findSeriesRest(@PathVariable("id") Long seriesId) {	
	    	return srepository.findById(seriesId);
	    }
	    
	    // RESTful service to get team by id
	    @RequestMapping(value="/team/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Team> findTeamRest(@PathVariable("id") Long teamId) {	
	    	return trepository.findById(teamId);
	    }
	    
	    
	 
	    
	//lisää pelaaja jo valmiina olevaan joukkueeseen
	@RequestMapping(value = "/add")
	public String addPlayer(Model model){
		model.addAttribute("player", new Player());
		model.addAttribute("series", srepository.findAll());
		model.addAttribute("team", trepository.findAll());
		
		
		return "addplayer";
	}   
	
	//lisää joukkue, jos ei ole jo valmiina
	@RequestMapping(value = "/addteam")
	public String addTeam(Model model){
		
		model.addAttribute("team", new Team());
		model.addAttribute("series", srepository.findAll());
		return "addteam";
	}  
	
	//joukkueen tallennus tietokantaan
	@RequestMapping(value = "/tsave", method = RequestMethod.POST)
    public String savet(Team team){
        trepository.save(team);
        return "redirect:teamlist";
    } 
	
	// pelaajan tallennus tietokantaan. h2 eli ei ole pysyvä. tallennus istunnon ajan.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Player player){
        repository.save(player);
        return "redirect:playerlist";
    }  
    
    // pelaajan poisto.
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePlayer(@PathVariable("id") Long playerId, Model model) {
    	repository.deleteById(playerId);
        return "redirect:../playerlist";
    }  
    
    //pelaajan editointi
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPlayer(@PathVariable("id") Long playerId, Model model) {
    	model.addAttribute("player", repository.findById(playerId));
    	model.addAttribute("series", srepository.findAll());
    	model.addAttribute("team", trepository.findAll());
    	
    	return "editplayer";
    } 
    
    //Joukkueen editointi
    @RequestMapping(value = "/tedit/{tid}", method = RequestMethod.GET)
    public String editTeam(@PathVariable("tid") Long teamId, Model model) {
    	model.addAttribute("team", trepository.findById(teamId));
    	model.addAttribute("series", srepository.findAll());
    	
    	return "editteam";
    } 
}

