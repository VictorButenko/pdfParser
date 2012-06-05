package com.ibm.iic.formparser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

/**
 * @author Butenko_V
 *
 * Java Class for parsing PDF form for IBM Innovation center.
 */
public class PdfParsing {

    public static void main(String[] args) {

        //Create the main object (which is mapping to PDF form)
        String fileName = "IIC_Form.PDF";
        DataFromPdf storageClass = new DataFromPdf(); //The class for storing our data
        HashMap<String, String> hashMap = new HashMap<String, String>(); //HashMap for recieving and storing data

        //parse pdf and save the data in the our DatFromPdf class.
        try {
            parse(hashMap, fileName);
            storageClass.save(hashMap);  //It's not necessary I think, but ...
     

        } catch (IOException e) {
            System.err.println("The file wasn't found");
            e.printStackTrace();
        }



    }

    private static void parse(HashMap hashMap, String fileName) throws IOException {

        PDDocument pdf = PDDocument.load(fileName);
        PDDocumentCatalog docCatalog = pdf.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List<PDField> list = acroForm.getFields();
        Iterator<PDField> it = list.iterator();
        while (it.hasNext()) {
            PDField field = it.next();
            hashMap.put(field.getFullyQualifiedName(), field.getValue()); //FIXME: I think it's enough and we don't need the class "DataFromPDF". But I'll do it anyway
            //Print just for TEST FIXEME:
            System.out.println("The name of the field: " + field.getFullyQualifiedName());
            System.out.println("The value of the field: " + field.getValue());
        }

    }
}

//POJO class for mapping the data
class DataFromPdf {

    void save(HashMap<String, String> hashMap) {
        setCompanyName(hashMap.get("CompanyName"));
        setContactPerson(hashMap.get("ContactPerson"));
        setContactInfo(hashMap.get("ContactInfo"));
        setCompanyDescription(hashMap.get("CompanyDescription"));
        setLocationID(hashMap.get("LocationID"));
        setIBMcontact(hashMap.get("IBMContact"));
        setApplicationName(hashMap.get("ApplicationName"));
        setApplicationVersion(hashMap.get("ApplicationVersion"));
        setIndustry(hashMap.get("Industry"));
        setApplicationDescription(hashMap.get("ApplicationDescription"));
        setCurrentEnv(hashMap.get("CurrentEnv"));
        setRequiredStartDate(hashMap.get("RequiredStartDate"));
        setProjectDuration(hashMap.get("ProjectDuration"));
        setHWRequirements(hashMap.get("HWRequirements"));
        setSWRequirements(hashMap.get("SWRequirements"));
    }

    public String getHWRequirements() {
        return HWRequirements;
    }

    public void setHWRequirements(String HWRequirements) {
        this.HWRequirements = HWRequirements;
    }

    public String getIBMcontact() {
        return IBMcontact;
    }

    public void setIBMcontact(String IBMcontact) {
        this.IBMcontact = IBMcontact;
    }

    public String getSWRequirements() {
        return SWRequirements;
    }

    public void setSWRequirements(String SWRequirements) {
        this.SWRequirements = SWRequirements;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCurrentEnv() {
        return currentEnv;
    }

    public void setCurrentEnv(String currentEnv) {
        this.currentEnv = currentEnv;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(String projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getRequiredStartDate() {
        return requiredStartDate;
    }

    public void setRequiredStartDate(String requiredStartDate) {
        this.requiredStartDate = requiredStartDate;
    }


    //Fields from PDF Form which should be filled. 
    private String companyName;
    private String contactPerson;
    private String contactInfo;
    private String companyDescription;
    private String locationID;
    private String IBMcontact;
    private String applicationName;
    private String applicationVersion;
    private String industry;
    private String applicationDescription;
    private String currentEnv;
    private String requiredStartDate;
    private String projectDuration;
    private String HWRequirements;
    private String SWRequirements;
}