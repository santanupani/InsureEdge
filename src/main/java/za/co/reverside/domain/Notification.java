package za.co.reverside.domain;

import java.io.Serializable;

public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    private String to;
    private String cc;
    private String subject;
    private String message;
    private byte[] attachment;
    private String pdfname;

    public Notification(String to, /*String cc,*/String subject, String message) {
        this.to = to;
//        this.cc = cc;
        this.subject = subject;
        this.message = message;
    }

    public Notification(String to, /*String cc,*/ String subject, String message, byte[] attachment, String pdfname) {
        this.to = to;
        /*this.cc = cc;*/
        this.subject = subject;
        this.message = message;
        this.attachment = attachment;
        this.pdfname = pdfname;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /*public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }*/

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }

}
