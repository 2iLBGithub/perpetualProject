package com.lewisBrennanLearning.yearTwoProject.Repository;

import com.lewisBrennanLearning.yearTwoProject.Model.ParsedPayload;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParsedPayloadRepository extends MongoRepository<ParsedPayload, ObjectId> {
}
