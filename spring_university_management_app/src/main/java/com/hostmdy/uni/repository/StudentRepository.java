package com.hostmdy.uni.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.uni.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
