package com.tavant.fileupload.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tavant.fileupload.message.ResponseFile;
import com.tavant.fileupload.message.ResponseMessage;
import com.tavant.fileupload.model.FileDB;
import com.tavant.fileupload.service.FileStorageService;

@Controller
@CrossOrigin("http://localhost:3000")
public class FileController {

  @Autowired
  private FileStorageService storageService;
  // variable to get successfully uploaded records 
  Integer count;
  // To store the file data 
  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.store(file);
      count=storageService.getCount();
      message = "Uploaded "+count+" address successfully: " + file.getOriginalFilename();
      storageService.setCount(0);
      // response on success 
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      // response on failure 
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("could not upload"));
    }
  }

 
}
