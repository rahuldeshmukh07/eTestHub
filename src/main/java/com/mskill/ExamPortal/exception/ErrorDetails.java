/**
 * 
 */
package com.mskill.ExamPortal.exception;

/**
 * @author 1838177
 *
 */
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
//    private String PrettyStackTrace = " "; 
    private String e;
   
    public ErrorDetails(Date timestamp,String message,  String details, Throwable e) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
        this.e = ExceptionUtils.getStackTrace(e);
//        this.PrettyStackTrace = ExceptionUtils.getStackTrace(e);
   }

    
    






	public String getE() {
		return e;
	}









	public void setE(String e) {
		this.e = e;
	}









	public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }


	
    
    
}