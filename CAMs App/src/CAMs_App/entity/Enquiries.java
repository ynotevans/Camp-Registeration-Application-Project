package CAMs_App.entity;

public class Enquiries {
    private String enquiry;
    private String answer;
    private int inquirer; //use student id better?
    private int answerer; //staff id
    private Boolean processed;
    
    public Enquiries(String enquiry , int inquirer){
        this.enquiry = enquiry;
        this.inquirer = inquirer;
        this.answer = "Not processed yet";
        this.answerer = -1;
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

    public void setAnswer(String answer , int answerer) {
        this.answer = answer;
        this.answerer = answerer;
        this.processed = true;
    }

    public void setInquirer(int inquirer) {
        this.inquirer = inquirer;
    }

    public void setAnswerer(int answerer) {
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

    public int getInquirer() {
        return inquirer;
    }

    public int getAnswerer() {
        return answerer;
    }

    public Boolean getProcessed() {
        return processed;
    }
}
