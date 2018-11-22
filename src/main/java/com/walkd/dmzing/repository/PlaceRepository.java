package com.walkd.dmzing.repository;

import com.walkd.dmzing.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
