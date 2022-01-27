package org.launchcode.bookworm.data.repository;

import org.launchcode.bookworm.data.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository <Authority,Integer> {
}
