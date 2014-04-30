package br.com.unisul.bean;

public class JsonResponse {
	 
	  private String status;
	  private String errorMessage;
	  private String data;
	
	public JsonResponse(String status, String errorMessage,String data) {
		    this.status = status;
		    this.errorMessage = errorMessage;
		    this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	  	 
}
