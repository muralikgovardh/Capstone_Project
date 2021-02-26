package com.tavant.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tavant.fileupload.model.FileDB;

public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
