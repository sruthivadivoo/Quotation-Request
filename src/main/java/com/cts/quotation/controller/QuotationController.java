package com.cts.quotation.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.cts.quotation.icm.util.MultipartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.*;
import javax.mail.search.*;
import com.cts.quotation.icm.util.CreateCase;
import org.apache.commons.*;
import org.apache.commons.io.IOUtils;
@Controller
public class QuotationController {

	@Autowired
	CreateCase cs;
	
	@RequestMapping("/home")
	public ModelAndView welcome() {
		ModelAndView view = new ModelAndView("index");
		
		return view;
	}
	@RequestMapping("/creatingcasethroughmail")
	public ModelAndView loadCaseMailForm(){
		ModelAndView view = new ModelAndView("CaseMailDetailForm");
		return view;
	}

	// load case details form

	@RequestMapping("/createCaseForm")
	public ModelAndView loadQuotationForm() {
		ModelAndView view = new ModelAndView("caseDetailForm");
		return view;
	}
	
	@RequestMapping("/createCaseDocumentForm")
	public ModelAndView loadDocumentQuotationForm() {
		ModelAndView view = new ModelAndView("caseDocumentDetailForm");
		return view;
	}

	// create new quote
	@RequestMapping("/generateQuote")
	public ModelAndView generateQuote(@RequestParam("contractName") String contractName, @RequestParam("companyName") String companyName,
			@RequestParam("contractType") String contractType, @RequestParam("amountUSD") double amountUSD,@RequestParam("companyType") String companyType,@RequestParam("category") String category) {
		//CreateCase cs = new CreateCase();
		String caseID=cs.createCaseCM(contractName,companyName,contractType,companyType,amountUSD,category);
		ModelAndView model =new ModelAndView();
		model.setViewName("success");
		model.addObject("msg", "Contract has been created successfully");
		
		
		//ps.saveProduct(prod);
		return model;
	}
	@PostMapping(path = "/generateDocumentQuote",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ModelAndView generateDocumentQuote(@RequestParam("contractName") String contractName, @RequestParam("companyName") String companyName,
			 @RequestParam("uploadDocument") MultipartFile multipartModel,@RequestParam("amountUSD") double amountUSD,@RequestParam("category") String category) throws IOException {
		//CreateCase cs = new CreateCase();
		String fileName=multipartModel.getOriginalFilename();
		byte [] byteArr=multipartModel.getBytes();
		String caseID=cs.createCaseWithDocument(byteArr,contractName,companyName,fileName,amountUSD,category);
		ModelAndView modelView =new ModelAndView();
		modelView.setViewName("success");
		modelView.addObject("msg", "Contract has been created successfully. ");
		
		
		//ps.saveProduct(prod);
		return modelView;
	}
      
}
