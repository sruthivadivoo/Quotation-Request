package com.cts.quotation.icm.util;
import org.springframework.web.multipart.MultipartFile;

public class MultipartModel {

    private String firstName;
    private String lastName;
	private MultipartFile uploadDocument;
    
	public MultipartModel() {
		super();
	}
	public MultipartModel(MultipartFile uploadDocument, String firstName, String lastName) {
		super();
		this.uploadDocument = uploadDocument;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public MultipartFile getDocumentToUpload() {
		return uploadDocument;
	}
	public void setDocumentToUpload(MultipartFile uploadDocument) {
		this.uploadDocument = uploadDocument;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
