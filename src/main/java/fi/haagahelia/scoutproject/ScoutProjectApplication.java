package fi.haagahelia.scoutproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.scoutproject.domain.AppUser;
import fi.haagahelia.scoutproject.domain.AppUserRepository;
import fi.haagahelia.scoutproject.domain.Player;
import fi.haagahelia.scoutproject.domain.PlayersRepository;
import fi.haagahelia.scoutproject.domain.Series;
import fi.haagahelia.scoutproject.domain.SeriesRepository;
import fi.haagahelia.scoutproject.domain.Team;
import fi.haagahelia.scoutproject.domain.TeamRepository;

@SpringBootApplication
public class ScoutProjectApplication {
	private static final Logger log = LoggerFactory.getLogger(ScoutProjectApplication.class);
			
	public static void main(String[] args) {
		SpringApplication.run(ScoutProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner playerDemo (PlayersRepository prepository, SeriesRepository srepository, TeamRepository trepository, AppUserRepository urepository) {
		return (args) -> {

			log.info("Tallennetaan muutama pelaaja kova koodaamanlla.");

			//tallennetaan suomen sarjatasot.
			srepository.save(new Series("Liiga"));
			srepository.save(new Series("Ykkönen"));
			srepository.save(new Series("Kakkonen"));
			srepository.save(new Series("Kolmonen"));
			srepository.save(new Series("Nelonen"));
			srepository.save(new Series("Vitonen"));
			srepository.save(new Series("Kutonen"));
			
			
			//tallenna muutamia joukkuetta
			trepository.save(new Team("Kups", srepository.findByLevel("Liiga").get(0)));trepository.save(new Team("Fc Inter", srepository.findByLevel("Liiga").get(0)));trepository.save(new Team("Fc Haka", srepository.findByLevel("Liiga").get(0)));trepository.save(new Team("Fc Kapina", srepository.findByLevel("Vitonen").get(0)));trepository.save(new Team("Halku", srepository.findByLevel("Nelonen").get(0)));trepository.save(new Team("Rips", srepository.findByLevel("Kolmonen").get(0)));
			trepository.save(new Team("Tips", srepository.findByLevel("Kakkonen").get(0)));trepository.save(new Team("JJK", srepository.findByLevel("Ykkönen").get(0)));trepository.save(new Team("KTP", srepository.findByLevel("Liiga").get(0)));trepository.save(new Team("Jaro", srepository.findByLevel("Liiga").get(0)));trepository.save(new Team("HIFK", srepository.findByLevel("Liiga").get(0)));trepository.save(new Team("Fc Lahti", srepository.findByLevel("Liiga").get(0)));
			
			
			
			
			//lisätään muutama pelaaja tietokantaan
			//pelaajalle lisätään myös joukkue ja sarjataso
			prepository.save(new Player("Niko", "Tikkanen", "Alempi keskikenttä", "23", "Hyvä ensimmäinen kosketus. Taitava kuljettaja. Fyysinen", "Nopeus. Puolustaminen", trepository.findByName("Tips").get(0)));
			prepository.save(new Player("Henri", "Toivomäki", "Keskuspuolustaja", "32", "Sijoittuminen. Oikea aikaisuus. Ilmapallot", "Nopeus. Fyysisyys. Pelinavaaminen", trepository.findByName("Kups").get(0)));
			prepository.save(new Player("Ville", "Hovi", "Laitapuolustaja", "32", "Nopeus. Vasenjalkainen. Pallollinen puolustaja", "Fyysisyys. Erikoistilanne puolustaminen", trepository.findByName("Fc Kapina").get(0)));
			prepository.save(new Player("Phondo", "Wälläri", "Ylempi keskikenttä", "25", "Kuljettaminen. 1vs1 pelaaminen. Näkee kentän todella hyvin. Ei menetä palloja", "Ajoittain pitkiä aikoja pelin ulkopuolella. Pallottomana hieman laiska", trepository.findByName("Rips").get(0)));
			prepository.save(new Player("Robin", "Saastamoinen", "Hyökkääjä", "29", "Hyvä bokisssa. Nopea. Hyvä kuljettamaan. Ykkösen parhaita viimeistelijöitä", "Vahva kaikilla osa-alueilla.", trepository.findByName("JJK").get(0)));
			
			
			
			// luodaan uudet käyttäjät: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@mail.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@mail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("listaa kaikki pelaajat");
			for (Player player : prepository.findAll()) {
				log.info(player.toString());
			}
		};
	}
}