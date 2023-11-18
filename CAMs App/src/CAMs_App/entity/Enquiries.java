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

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setInquirer(String inquirer) {
        this.inquirer = inquirer;
    }

    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }

    public void setProcessed() {
        this.processed = true;
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
