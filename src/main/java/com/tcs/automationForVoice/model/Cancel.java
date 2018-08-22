package com.tcs.automationForVoice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cancel {
	
	private String orderNo;
	private String orderNo_type;
	//private String amendableState;
	private String postCancellationState;
	private String postCancellationState_type;
	
	private String error;
	//private String amendableState_type;
	
	private String error_type;
	private String cancellable;
	private String cancellable_type;
	private String reasonOrderNotCancellable;
	private String reasonOrderNotCancellable_type;
	private String failureReason;
	private String failureReason_type;
	private String success;
	private String success_type;
	
	@JsonProperty("order.orderNo.type")
	public String getOrderNo_type() {
		return orderNo_type;
	}
	public void setOrderNo_type(String orderNo_type) {
		this.orderNo_type = orderNo_type;
	}
	
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
	public String getCancellable() {
		return cancellable;
	}
	public void setCancellable(String cancellable) {
		this.cancellable = cancellable;
	}
	public String getCancellable_type() {
		return cancellable_type;
	}
	public void setCancellable_type(String cancellable_type) {
		this.cancellable_type = cancellable_type;
	}
	public String getReasonOrderNotCancellable() {
		return reasonOrderNotCancellable;
	}
	public void setReasonOrderNotCancellable(String reasonOrderNotCancellable) {
		this.reasonOrderNotCancellable = reasonOrderNotCancellable;
	}
	public String getReasonOrderNotCancellable_type() {
		return reasonOrderNotCancellable_type;
	}
	public void setReasonOrderNotCancellable_type(String reasonOrderNotCancellable_type) {
		this.reasonOrderNotCancellable_type = reasonOrderNotCancellable_type;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	public String getFailureReason_type() {
		return failureReason_type;
	}
	public void setFailureReason_type(String failureReason_type) {
		this.failureReason_type = failureReason_type;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getSuccess_type() {
		return success_type;
	}
	public void setSuccess_type(String success_type) {
		this.success_type = success_type;
	}

}
