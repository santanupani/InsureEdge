package za.co.reverside.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.reverside.Service;
import za.co.reverside.domain.ClaimRequest;
import za.co.reverside.repository.ClaimRequestRepository;

@RestController
public class AttachmentService {
    
    private static final Logger log = LoggerFactory.getLogger(Service.class);
    
    @Autowired
    private ClaimRequestRepository claimRequestRepository;
    
     @RequestMapping(value = "api/claim/{claimNumber}/investigationReport", method = RequestMethod.GET, produces = "application/*")
    public byte[] getInvestigationReport(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        log.info("byte " + claimRequest.getComfirmationAmount().toString());
        return claimRequest.getInvestigationReport();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/affidavit", method = RequestMethod.GET, produces = "application/*")
    public byte[] getAffidavit(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
       
        return claimRequest.getAffidavit();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/proofOfPickup", method = RequestMethod.GET, produces = "application/*")
    public byte[] getProofOfPickup(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getProofOfPickup();

    }

    @RequestMapping(value = "api/claim/{claimNumber}/comfirmationAmount", method = RequestMethod.GET, produces = "application/*")
    public byte[] getComfirmationAmount(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getComfirmationAmount();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/amountBanked", method = RequestMethod.GET, produces = "application/*")
    public byte[] getAmountBanked(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getAmountBanked();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/transTrackDocument", method = RequestMethod.GET, produces = "application/*")
    public byte[] getTransTrackDocument(@PathVariable("claimNumber") String claimNumber) throws  IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getTransTrackDocument();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/quote", method = RequestMethod.GET, produces = "application/*")
    public byte[] getQuote(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getQuote();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/report", method = RequestMethod.GET, produces = "application/*")
    public byte[] getReport(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getReport();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/photo1", method = RequestMethod.GET, produces = "image/*")
    public byte[] getPhoto1(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getPhoto1();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/photo2", method = RequestMethod.GET, produces = "image/*")
    public byte[] getPhoto2(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getPhoto2();

    }
    @RequestMapping(value = "api/claim/{claimNumber}/photo3", method = RequestMethod.GET, produces = "image/*")
    public byte[] getPhoto3(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getPhoto3();

    }
    
      @RequestMapping(value = "api/claim/{claimNumber}/photo4", method = RequestMethod.GET, produces = "image/*")
    public byte[] getPhoto4(@PathVariable("claimNumber") String claimNumber) throws IOException {
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
    
        return claimRequest.getPhoto4();

    }
    

}
