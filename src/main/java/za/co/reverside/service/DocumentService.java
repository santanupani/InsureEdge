/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.reverside.service;

import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.FileResolver;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import za.co.reverside.domain.*;
import za.co.reverside.repository.JapsperImageRepository;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.*;

@Service
public class DocumentService {

    private String quotationWording;
    NumberFormat nfm = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
    private List<JasperImage> logos = null;

    @Autowired
    private JapsperImageRepository japsperImageRepository;


    FileResolver fileResolver = new FileResolver() {

        public File resolveFile(String fileName) {
            URI uri;
            try {
                uri = new URI(this.getClass().getResource(fileName).getPath());
                return new File(uri.getPath());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    public byte[] generateReleaseFormPDF(ReleaseForm releaseForm) throws JRException, IOException {
        Map<String, Object> reportData = new HashMap<String, Object>();
        logos = japsperImageRepository.findAll();
        reportData.put("polygon-logo", logos.get(0).getImage());
        reportData.put("polygon-footer", logos.get(1).getImage());
        reportData.put("releaseForm", releaseForm);        
        
        List<ReleaseForm> releaseFormList=new ArrayList<ReleaseForm>();
        releaseFormList.add(releaseForm);
        
       JRBeanCollectionDataSource indemnityOptionsDS = new JRBeanCollectionDataSource(releaseFormList);
        InputStream jasperIS = getClass().getResourceAsStream("/reports/claim-release-form.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData,indemnityOptionsDS);

        return JasperExportManager.exportReportToPdf(jasperPrint);

    }


    public byte[] generateQuotationPDF(Quotation quotation) throws JRException {

        Map<String, Object> reportData = new HashMap<String, Object>();
        logos = japsperImageRepository.findAll();        
        
        for (int i = 0; i < quotation.getQuotationOptions().size(); i++) {

            quotation.getQuotationOptions().get(i).setLimit(nfm.format(Double.parseDouble(quotation.getQuotationOptions().get(i).getLimit())).replace(",", ".").replace("$", ""));
            quotation.getQuotationOptions().get(i).setPavements(nfm.format(Double.parseDouble(quotation.getQuotationOptions().get(i).getPavements())).replace(",", ".").replace("$", ""));
            quotation.getQuotationOptions().get(i).setStaticLimit(nfm.format(Double.parseDouble(quotation.getQuotationOptions().get(i).getStaticLimit())).replace(",", ".").replace("$", ""));

            for (Answer answer : quotation.getQuotationRequest().getAnswers()) {
                if (answer.getAnswer() != null && answer.getAnswer().contains("On-going, paid monthly")) {
                    quotation.getQuotationOptions().get(i).setPremium(nfm
                            .format(Double.parseDouble(quotation.getQuotationOptions()
                                    .get(i).getPremium())).replace(",", ".").replace("$", "") + " (per monthly excluding 14% VAT)");
                } else if (answer.getAnswer() != null && answer.getAnswer().contains("On-going, paid annually")) {
                    quotation.getQuotationOptions().get(i).setPremium(nfm
                            .format(Double.parseDouble(quotation.getQuotationOptions()
                                    .get(i).getPremium())).replace(",", ".").replace("$", "") + " (per annually excluding 14% VAT)");
                } else if (answer.getAnswer() != null && answer.getAnswer().contains("Specific Period (Once-Off)")) {
                    quotation.getQuotationOptions().get(i).setPremium(nfm
                            .format(Double.parseDouble(quotation.getQuotationOptions()
                                    .get(i).getPremium())).replace(",", ".").replace("$", "") + " (per once-off excluding 14% VAT)");
                }

            }
        }

        for (Answer answer : quotation.getQuotationRequest().getAnswers()) {
            if (answer.getAnswer() != null) {
                if (answer.getAnswer().equals("Specific Period (Once-Off)")) {
                    this.setQuotationWording("Quote only valid for 7 days "
                            + "and acceptance thereof by underwriters subject to a fully completed risk assessment, proposal form and signed "
                            + "debit order form. Underwriters reserve the right to refuse acceptance of the risk as declared to us.\n");

                } else if (answer.getAnswer().equals("On-going, paid monthly") || answer.getAnswer().equals("On-going, paid annually")) {
                    this.setQuotationWording("Quote is valid for 15 days and acceptance thereof by underwriters subject to a fully completed risk assessment, proposal form and signed "
                            + "debit order form. Underwriters reserve the right to refuse acceptance of the risk as declared to us.\n");
                }
            }
        }

        reportData.put("quotationWording", getQuotationWording());
        reportData.put("quotation", quotation);
        reportData.put("polygon-logo", logos.get(0).getImage());
        reportData.put("polygon-footer", logos.get(1).getImage());

        JRBeanCollectionDataSource quotationOptions = new JRBeanCollectionDataSource(quotation.getQuotationOptions());
        InputStream jasperIS = getClass().getResourceAsStream("/reports/quotationReport.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, quotationOptions);

        return JasperExportManager.exportReportToPdf(jasperPrint);

    }
    
    public byte[] generateQuotationPDFAuto(Quotation quotation) throws JRException {

        Map<String, Object> reportData = new HashMap<String, Object>();
        logos = japsperImageRepository.findAll();         
       
        setQuotationWording("Quote is valid for 15 days and acceptance thereof by underwriters subject to a fully completed risk assessment, proposal form and signed "
                            + "debit order form. Underwriters reserve the right to refuse acceptance of the risk as declared to us.\n");
       
        reportData.put("quotationWording", getQuotationWording());
        reportData.put("quotation", quotation);
        reportData.put("polygon-logo", logos.get(0).getImage());
        reportData.put("polygon-footer", logos.get(1).getImage());

        List<Quotation> quotationList = new ArrayList<Quotation>();
        quotationList.add(quotation);
       
        JRBeanCollectionDataSource quotationRequest1 = new JRBeanCollectionDataSource(quotationList);
        InputStream jasperIS = getClass().getResourceAsStream("/reports/quotationAuto.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, quotationRequest1);

        return JasperExportManager.exportReportToPdf(jasperPrint);

    }
    
   

    public byte[] policyScheduleReportPDF(Policy policy) throws JRException, IOException {
        Map<String, Object> reportData = new HashMap<String, Object>();
        logos = japsperImageRepository.findAll();
        reportData.put("polygon-logo", logos.get(0).getImage());
        reportData.put("genric-logo", logos.get(2).getImage());
        reportData.put("polygon-sched", logos.get(3).getImage());
        reportData.put("policy", policy);

        JRBeanCollectionDataSource indemnityOptionsDS = new JRBeanCollectionDataSource(policy.getIndemnityOptions());
        InputStream jasperIS = getClass().getResourceAsStream("/reports/policyReport.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, indemnityOptionsDS);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    
    public byte[] autoPolicyScheduleReportPDF(Policy policy) throws JRException, IOException {
        Map<String, Object> reportData = new HashMap<String, Object>();
        logos = japsperImageRepository.findAll();
        reportData.put("polygon-logo", logos.get(0).getImage());
        reportData.put("polygon-footer", logos.get(1).getImage());        
        reportData.put("policy", policy);

        JRBeanCollectionDataSource indemnityOptionsDS = new JRBeanCollectionDataSource(policy.getIndemnityOptions());
        InputStream jasperIS = getClass().getResourceAsStream("/reports/policyReportAuto.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperIS);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportData, indemnityOptionsDS);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public String getQuotationWording() {
        return quotationWording;
    }


    public void setQuotationWording(String quotationWording) {
        this.quotationWording = quotationWording;
    }

    public List<PolicyRequestType> generateCancellationRequestsFromCIB(MultipartFile file) throws IOException {
        List<PolicyRequestType> requestByCIB = new ArrayList<PolicyRequestType>();
        ArrayList<Object> arList = null;
        ArrayList<Object> al = null;
        String thisLine;
        FileInputStream fis = new FileInputStream(new File("src/main/java/reversideCIB.csv"));
        DataInputStream myInput = new DataInputStream(fis);
        int i = 0;
        arList = new ArrayList<Object>();
        while ((thisLine = myInput.readLine()) != null) {
            al = new ArrayList<Object>();
            String strar[] = thisLine.split(",");
            for (int j = 0; j < strar.length; j++) {
                al.add(strar[j]);
            }
            arList.add(al);
            System.out.println();
            i++;
        }

        try {
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");
            for (int k = 0; k < arList.size(); k++) {
                ArrayList<Object> ardata = (ArrayList) arList.get(k);
                HSSFRow row = sheet.createRow((short) 0 + k);
                for (int p = 0; p < ardata.size(); p++) {
                    HSSFCell cell = row.createCell((short) p);
                    String data = ardata.get(p).toString();
                    if (data.startsWith("=")) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        data = data.replaceAll("\"", "");
                        data = data.replaceAll("=", "");
                        cell.setCellValue(data);
                    } else if (data.startsWith("\"")) {
                        data = data.replaceAll("\"", "");
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(data);
                    } else {
                        data = data.replaceAll("\"", "");
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(data);
                    }
                }
                System.out.println();
            }
            FileOutputStream fileOut = new FileOutputStream("test.xls");
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated");
        } catch (Exception ex) {
            ex.printStackTrace();
        } //main method ends
        finally {
            myInput.close();
        }
        return requestByCIB;
    }

}
