package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Type;
import com.walkd.dmzing.domain.UserPickedMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPickedMapRepository extends JpaRepository<UserPickedMap, Long> {
    Long countByCourse_Type(Type type);
}
