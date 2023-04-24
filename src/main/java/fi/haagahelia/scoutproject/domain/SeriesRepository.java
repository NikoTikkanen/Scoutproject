package fi.haagahelia.scoutproject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SeriesRepository extends CrudRepository <Series, Long>{
	
	List<Series> findByLevel(String level);

}
