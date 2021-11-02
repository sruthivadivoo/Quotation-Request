package com.cts.quotation.icm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.property.Properties;
import com.filenet.api.util.Id;
import com.ibm.casemgmt.api.Case;
import com.ibm.casemgmt.api.CaseType;
import com.ibm.casemgmt.api.constants.ModificationIntent;
import com.ibm.casemgmt.api.objectref.ObjectStoreReference;
@Component
public class CreateCase {

	@Autowired
	CaseManagerConnection connection;
	
	public String createCaseCM(String contractName, String companyName,String contractType,String companyType,double amountUSD,String category) {
		ObjectStore targetOs = connection.getConnection();
		ObjectStoreReference targetOsRef = new ObjectStoreReference(targetOs);
		CaseType caseType = CaseType.fetchInstance(targetOsRef, "CM_Contract_Management_CT");
		Case pendingCase = Case.createPendingInstance(caseType);
		pendingCase.getProperties().putObjectValue("CM_ContractName", contractName);
		pendingCase.getProperties().putObjectValue("CM_CompanyName", companyName);
		pendingCase.getProperties().putObjectValue("CM_ContractType", contractType);
		pendingCase.getProperties().putObjectValue("CM_CompanyType", companyType);
		pendingCase.getProperties().putObjectValue("CM_AmountUSD", amountUSD);
		pendingCase.getProperties().putObjectValue("CM_Category", category);
		pendingCase.getProperties().putObjectValue("CM_Source", "ExternalUI");
		//pendingCase.getProperties().putObjectValue("DEMO_BrokerName", brokerName);
		//pendingCase.getProperties().putObjectValue("DEMO_BrokerEmail", brokerEmail);
		pendingCase.save(RefreshMode.REFRESH, null, ModificationIntent.MODIFY);
		String caseId = pendingCase.getId().toString();
		//System.out.println(caseId);
		return caseId;
	}
	public String createCaseWithDocument(byte[] byteArr, String contractName, String companyName,String fileName,double amountUSD,String category) {
		String METHOD_NAME = "createCaseWithDocument";
		System.out.println(METHOD_NAME);
		
		try {
			ObjectStore targetOs = connection.getConnection();
			ObjectStoreReference targetOsRef = new ObjectStoreReference(targetOs);
			CaseType caseType = CaseType.fetchInstance(targetOsRef, "CM_Contract_Management_CT");
			Case pendingCase = Case.createPendingInstance(caseType);
			pendingCase.getProperties().putObjectValue("CM_AmountUSD", amountUSD);
			pendingCase.getProperties().putObjectValue("CM_Category", category);
			pendingCase.getProperties().putObjectValue("CM_Source", "ExternalDocument");
			pendingCase.save(RefreshMode.REFRESH, null, ModificationIntent.MODIFY);
			String caseId = pendingCase.getId().toString();
			Document document = Factory.Document.createInstance(targetOs, "CM_ContractDocument");
			ContentElementList contentList = Factory.ContentElement.createList();
			ContentTransfer content = Factory.ContentTransfer.createInstance();
			InputStream is = new ByteArrayInputStream(byteArr);
			content.setCaptureSource(is);
			content.set_ContentType("application/pdf");
			content.set_RetrievalName("fileName");
			contentList.add(content);
			document.set_ContentElements(contentList);
			document.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
			Properties properties = document.getProperties();
			properties.putValue("DocumentTitle", fileName);
			properties.putValue("CM_CompanyName", companyName);
			properties.putValue("CM_ContractName", contractName);
			document.set_MimeType("application/pdf");
			document.save(RefreshMode.REFRESH);
			System.out.println("Filing PDF file to : " + caseId);
			
			Folder folder = Factory.Folder.fetchInstance(targetOs, new Id(caseId), null);
			//Folder folder = Factory.Folder.fetchInstance(objectStore, folderPath, null);
			ReferentialContainmentRelationship ref = folder.file(document, AutoUniqueName.AUTO_UNIQUE, null, DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
			ref.save(RefreshMode.REFRESH);
			System.out.println("PDF filed in CaseFolder.");
			JSONObject jsonObject = new JSONObject();
			
			System.out.println("Path Extension: "+folder.get_Name()+"\t"+folder.get_DateCreated());
			String pathWithDate = folder.get_FolderName();
			System.out.println(pathWithDate);
			
			String docID=document.get_Id().toString();

			jsonObject.put("DocumentId", docID);
			jsonObject.put("CaseId",caseId);
			System.out.println("ExecutableClass.fileToCaseFolder()"+jsonObject.get("DocumentId")+"\t"+jsonObject.get("CaseId"));
			return jsonObject.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ExecutableClass.fileToCaseFolder().Exception in case folder");
			return null;
		}
	}
}

