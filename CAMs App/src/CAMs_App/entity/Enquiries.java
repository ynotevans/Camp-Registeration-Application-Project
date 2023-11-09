package CAMs_App.entity;

public class Enquiries {
    private String enquiry;
    private String answer;
    private String inquirer; 
    private String answerer; 
    private Boolean processed;
    
    public Enquiries(String enquiry , String inquirer){
        this.enquiry = enquiry;
        this.inquirer = inquirer;
        this.answer = "Not processed yet";
        this.answerer = "NULL";
        this.processed = false;
        
    };

    public void viewEnquiries(){
        System.out.println("Inquirer:" + this.inquirer);
        System.out.println("Enquiry: " + this.enquiry);

        System.out.println("Answerer: " + this.answerer);
        System.out.println("Answer: " + this.answer);
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public void setAnswer(String answer , String answerer) {
        this.answer = answer;
        this.answerer = answerer;
        this.processed = true;
    }

    public void setInquirer(String inquirer) {
        this.inquirer = inquirer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public String getAnswer() {
        return answer;
    }

    public String getInquirer() {
        return inquirer;
    }

    public String getAnswerer() {
        return answerer;
    }

    public Boolean getProcessed() {
        return processed;
    }
}
