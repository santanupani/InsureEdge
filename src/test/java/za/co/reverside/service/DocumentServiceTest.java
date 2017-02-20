package za.co.reverside.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//import org.dom4j.DocumentException;
import org.junit.Test;

//import com.itextpdf.text.DocumentException;


import net.sf.jasperreports.engine.JRException;
import za.co.reverside.domain.Product;
import za.co.reverside.domain.Quotation;
import za.co.reverside.domain.QuotationOption;
import za.co.reverside.domain.QuotationRequest;

public class DocumentServiceTest {

//    @Test
//    public void test() throws DocumentException, IOException, JRException {
//        DocumentService service = new DocumentService();
//
//        Quotation quotation = new Quotation();
//        QuotationRequest quotationRequest = new QuotationRequest();
//        quotationRequest.setApplicantName("Thabo");
//        quotationRequest.setCompanyName("Reverside Software Solutions");
//
//        quotationRequest.setCreateDate(new Date());
//        quotationRequest.setReference("fe0cf511-11cf-4a90-b8c1-661fedd10016");
//        
//        Product product = new Product();
//        product.setName("Cash and Valuables in Transinsit");
//        product.setId(1l);
//
//        QuotationOption quotationOption = new QuotationOption();
//        quotationOption.setCommodity("Gold");
//        quotationOption.setLocation("Midrand");
//        quotationOption.setExcess("1231");
//        quotationOption.setLimit("100044");
//        quotationOption.setPremium("12334");
//        quotationOption.setDuration("Annually");
//        quotationOption.setCover("Static cover");
//        quotationOption.setPavements("1200.90");
//        quotationOption.setQuotation(quotation);
//
//        QuotationOption quotationOption2 = new QuotationOption();
//        quotationOption2.setCommodity("Cash");
//        quotationOption2.setLocation("Sandton");
//        quotationOption2.setExcess("12000");
//        quotationOption2.setLimit("998211");
//        quotationOption2.setPremium("1999");
//        quotationOption2.setDuration("Weekly");
//        quotationOption2.setPavements("0.00");
//        quotationOption2.setCover("Cash In Transit");
//        quotationOption2.setQuotation(quotation);
//
//        
//        QuotationOption quotationOption3 = new QuotationOption();
//        quotationOption3.setCommodity("Cash");
//        quotationOption3.setLocation("Sandton");
//        quotationOption3.setExcess("12000");
//        quotationOption3.setLimit("1998223");
//        quotationOption3.setPremium("1999");
//        quotationOption3.setPavements("2190.88");
//        quotationOption3.setCover("Cash In Transit");
//        quotationOption3.setDuration("Monthly");
//        quotationOption3.setQuotation(quotation);
//
//        quotationRequest.setProduct(product);
//
//        quotation.setQuotationRequest(quotationRequest);
//
//        List<QuotationOption> quotationOptions = new ArrayList<QuotationOption>();
//        
//        quotationOptions.add(quotationOption);
//        quotationOptions.add(quotationOption2);
//        quotationOptions.add(quotationOption3);
//
//        quotation.setQuotationOptions(quotationOptions);
//
//        byte[] data = service.generateQuotationPDF(quotation);
//        FileOutputStream out = new FileOutputStream("target/test.pdf");
//        out.write(data);
//        out.close();
//    }

}
