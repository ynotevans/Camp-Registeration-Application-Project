package CAMs_App.entity;

/**
 * The {@link Enquiries} class represents an inquiry submitted to a camp
 * by a student.
 * 
 *  @author Wan Ismail
 *  @version 1.0
 *  @since 2023-10-25
 */
public class Enquiries {
	
	/**
	 * The message of the inquiry.
	 */
    private String enquiry;
    /**
     * The answer to the inquiry.
     */
    private String answer;
    /**
     * The userID of the inquirer
     */
    private String inquirer; 
    /**
     * The userID of the answerer
     */
    private String answerer; 
    /**
     * The status of the inquiry.
     */
    private Boolean processed;
    
    /**
     * Constructs an {@link Enquiries} object with the given message and sender.
     * 
     * @param enquiry	The message of the inquiry. 
     * @param inquirer	The userID of the inquirer.
     */
    public Enquiries(String enquiry , String inquirer){
        this.enquiry = enquiry;
        this.inquirer = inquirer;
        this.answer = "(Not processed yet)";
        this.answerer = "NULL";
        this.processed = false;
        
    };
    
    /**
     * Sets the message of the inquiry.
     * 
     * @param enquiry The message to be set.
     */
    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }
    
    /**
     * Sets the answer to the in inquiry.
     * 
     * @param answer The answer to be set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    /**
     * Sets the name of the inquirer.
     * 
     * @param inquirer The name to be set.
     */
    public void setInquirer(String inquirer) {
        this.inquirer = inquirer;
    }

    /**
     * Sets the name of the answerer.
     * 
     * @param answerer The name to be set.
     */
    public void setAnswerer(String answerer) {
        this.answerer = answerer;
    }
    
    /**
     * Sets the processed status of the inquiry to true.
     */
    public void setProcessed() {
        this.processed = true;
    }
    
    /**
     * Gets the message of the inquiry.
     * 
     * @return The text in string form.
     */
    public String getEnquiry() {
        return enquiry;
    }
    
    /**
     * Gets the answer of the inquiry.
     * 
     * @return The text in string form.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Gets the inquirer.
     * 
     * @return The name of the inquirer.
     */
    public String getInquirer() {
        return inquirer;
    }
    
    /**
     * Gets the answerer.
     * 
     * @return The name of the answerer.
     */
    public String getAnswerer() {
        return answerer;
    }
    
    /**
     * Gets the status of the inquiry.
     * 
     * @return The processed status.
     */
    public Boolean getProcessed() {
        return processed;
    }


}
