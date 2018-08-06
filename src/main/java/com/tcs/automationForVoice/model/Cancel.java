package com.tcs.automationForVoice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cancel {
	
	private String orderNo;
	private String orderNo_type;
	//private String amendableState;
	private String postCancellationState;
	private String postCancellationState_type;
	
	
	@JsonProperty("order.orderNo.type")
	public String getOrderNo_type() {
		return orderNo_type;
	}
	public void setOrderNo_type(String orderNo_type) {
		this.orderNo_type = orderNo_type;
	}
	private String error;
	//private String amendableState_type;
	
	private String error_type;
	@JsonProperty("order.error")
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	//public String getAmendableState_type() {
		//return amendableState_type;
	//}
	//public void setAmendableState_type(String amendableState_type) {
		//this.amendableState_type = amendableState_type;
	//}
	@JsonProperty("order.postCancellationState.type")
	public String getPostCancellationState_type() {
		return postCancellationState_type;
	}
	public void setPostCancellationState_type(String postCancellationState_type) {
		this.postCancellationState_type = postCancellationState_type;
	}
	@JsonProperty("order.error.type")
	public String getError_type() {
		return error_type;
	}
	public void setError_type(String error_type) {
		this.error_type = error_type;
	}

	
	@JsonProperty("order.orderNo")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/*@JsonProperty("cancel.amendableState")
	public String getAmendableState() {
		return amendableState;
	}
	public void setAmendableState(String amendableState) {
		this.amendableState = amendableState;
	}*/
	@JsonProperty("order.postCancellationState")
	public String getPostCancellationState() {
		return postCancellationState;
	}
	public void setPostCancellationState(String postCancellationState) {
		this.postCancellationState = postCancellationState;
	}

}
