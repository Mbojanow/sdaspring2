package pl.sdacademy.springdemo.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import pl.sdacademy.springdemo.domain.Person;

@Service
public class PersonSearchService {

  private final EntityManager entityManager;

  public PersonSearchService(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Person> findBy(final String username, final Integer age, final Long pesel,
                             final boolean isOrConjuction) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
    final Root<Person> root = query.from(Person.class);
    final List<Predicate> predicates = new ArrayList<>();
    if (username != null) {
      predicates.add(criteriaBuilder.equal(root.get("username"), username));
    }

    if (age != null) {
      predicates.add(criteriaBuilder.equal(root.get("age"), age));
    }

    if (pesel != null) {
      predicates.add(criteriaBuilder.equal(root.get("pesel"), pesel));
    }
    Predicate predicate = null;
    if (isOrConjuction) {
        predicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    } else {
        predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    final CriteriaQuery<Person> buildQuery = query.select(root).where(predicate);
    return entityManager.createQuery(buildQuery).getResultList();
  }
}
