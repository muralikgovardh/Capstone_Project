package com.tavant.fileupload.service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.tavant.fileupload.message.ResponseMessage;
import com.tavant.fileupload.model.Address;
import com.tavant.fileupload.model.FileDB;
import com.tavant.fileupload.model.Root;
import com.tavant.fileupload.repository.AddressRepository;
import com.tavant.fileupload.repository.FileDBRepository;





@Service
public class FileStorageService {

  @Autowired
  private FileDBRepository fileDBRepository;
  
  @Autowired
  AddressRepository addressRepository;
  Integer count=0;

  public Integer getCount() {
	return count;
}

public void setCount(Integer count) {
	this.count = count;
}

public boolean store(MultipartFile file) throws IOException {
	
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB fileDb = new FileDB(fileName, file.getContentType(), file.getBytes());
    String s = new String(fileDb.getData(), StandardCharsets.UTF_8);
	 // To split the string line by line and storing in String array 
	  String [] line=s.split("\\n");
	  // Iterating through the line array
	  for(int i=0;i<line.length-1;i++) {
	  // split each line by "," operator and storing in lines array 
	  String[] lines=line[i].split(",");
	   
	  // Setting values to add object from each line 
	  Address add= new Address();
	  	 add.setCity(lines[0]);
		 add.setConfidenceScore(lines[1]);
		 add.setDistrict(lines[2]);
		 add.setELoc(lines[3]);
		 add.setFormattedAddress(lines[4]);  
		 add.setGeocodeLevel(lines[5]);
		 add.setHouseName(lines[6]);
		 add.setHouseNumber(lines[7]);
		 add.setLocality(lines[8]);
		 add.setPincode(lines[9]);
		 add.setPoi(lines[10]);
		 add.setState(lines[11]);
		 add.setStreet(lines[12]);
		 add.setSubDistrict(lines[13]);
		 add.setSubLocality(lines[14]);
		 add.setSubSubLocality(lines[15]);
		 add.setVillage(lines[16]);
	  
	  // eloc value 
	  System.out.println(lines[3]);
	  // calling the demo method on add 
	  demo(add);   
	 
	 
	 }
	  System.out.println(count);
	 
    return true;
    
  }
   //
  public ResponseEntity<?> demo(Address addr)
  {
	  System.out.println(addr.getELoc());
	  String value=addr.getELoc().toString();
	  // Trimming the extra spaces in eloc string 
	  value=value.replaceAll("\\s", "");
	  try {
		  // eloc api link completion using eloc value from input 
		  String var=String.format("http://apis.mapmyindia.com/advancedmaps/v1/kvtbcblhvw653wgqocxj95pja34rl2yh/place_detail?place_id=%s",value);
		  RestTemplate rest=new RestTemplate();
	  	  Root root=rest.getForObject(var, Root.class);
	  	  // To validate the input addresses by checking response and size 
	  	  if(root.getResponseCode()==200 && root.getResults().size()>0)
		  {
			  addressRepository.save(addr);
			  count++;
			  //response for success 
			  return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Location is Valid, Address Added"));
		  }
	  }
	  catch(Exception e) 
	  {
		  // response for failure 
		  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("No location found"));
	  }
	  return null;
  }  

 
}
