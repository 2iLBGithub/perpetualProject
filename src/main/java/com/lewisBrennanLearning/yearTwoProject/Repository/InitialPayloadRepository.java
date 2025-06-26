package com.lewisBrennanLearning.yearTwoProject.Repository;

import com.lewisBrennanLearning.yearTwoProject.Model.InitialPayload;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InitialPayloadRepository extends MongoRepository<InitialPayload, ObjectId> {

}
