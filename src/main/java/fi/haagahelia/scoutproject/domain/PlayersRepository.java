package fi.haagahelia.scoutproject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayersRepository extends CrudRepository<Player, Long> {

	List<Player> findByLastName(String lastName);
}
