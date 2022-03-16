package org.mongo.api;

import org.mongo.entity.Garage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GarageRepository extends MongoRepository<Garage, String> {
}
