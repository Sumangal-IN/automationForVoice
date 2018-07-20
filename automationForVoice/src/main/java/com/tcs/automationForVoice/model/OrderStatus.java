package com.tcs.automationForVoice.model;

public class OrderStatus {
	private String orderNumber;
	private String creationDate;
	private String submittedDate;
	private String lastModifiedDate;
	private String state;
	private String orderOriginSource;
	private String totalToPay;
	private String paymentGroups;
	private String jurisdiction;
	private String stateDetail;

	public OrderStatus(String orderNumber) {
		this.orderNumber = orderNumber;
		this.creationDate = "";
		this.submittedDate = "";
		this.lastModifiedDate = "";
		this.state = "";
		this.orderOriginSource = "";
		this.totalToPay = "";
		this.paymentGroups = "";
		this.jurisdiction = "";
		this.stateDetail = "";
	}

	public OrderStatus(String orderNumber, String creationDate, String submittedDate, String lastModifiedDate, String state, String orderOriginSource, String totalToPay, String paymentGroups, String jurisdiction, String stateDetail) {
		this.orderNumber = orderNumber;
		this.creationDate = creationDate;
		this.submittedDate = submittedDate;
		this.lastModifiedDate = lastModifiedDate;
		this.state = state;
		this.orderOriginSource = orderOriginSource;
		this.totalToPay = totalToPay;
		this.paymentGroups = paymentGroups;
		this.jurisdiction = jurisdiction;
		this.stateDetail = stateDetail;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderOriginSource() {
		return orderOriginSource;
	}

	public void setOrderOriginSource(String orderOriginSource) {
		this.orderOriginSource = orderOriginSource;
	}

	public String getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(String totalToPay) {
		this.totalToPay = totalToPay;
	}

	public String getPaymentGroups() {
		return paymentGroups;
	}

	public void setPaymentGroups(String paymentGroups) {
		this.paymentGroups = paymentGroups;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getStateDetail() {
		return stateDetail;
	}

	public void setStateDetail(String stateDetail) {
		this.stateDetail = stateDetail;
	}

}
