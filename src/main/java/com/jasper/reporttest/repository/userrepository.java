package com.jasper.reporttest.repository;

import com.jasper.reporttest.domain.user;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface userrepository extends MongoRepository<user,String> {


}
