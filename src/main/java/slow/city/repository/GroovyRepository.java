package slow.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import slow.city.model.Groovy;

@Repository
public interface GroovyRepository extends JpaRepository<Groovy, Long>,JpaSpecificationExecutor<Groovy> {

	Groovy findOneById(Integer id);

}
