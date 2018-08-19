package com.tcs.automationForVoice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class Order{
	
	
	private String submitDate;
	private String creationDate;
	private String lastModifiedDate;
	private String orderState;
	private String orderOriginSource;
	private String totalToPay;
	private String paymentGroups;
	private String jurisdiction;
	private String stateDetail;
    private String submitDate_type;
	private String creationDate_type;
	private String lastModifiedDate_type;
	private String orderState_type;
	private String orderOriginSource_type;
	private String totalToPay_type;
	private String paymentGroups_type;
	private String jurisdiction_type;
	private String stateDetail_type;
	private String error;
	private String error_type;
	private String cust_rel_order;
	private String cust_rel_order_type;
	private String orderSuceessStatus;
	private String orderSuceessStatus_type;
	
		
	
	
	
	@JsonProperty("order.submitDate")
	public String getSubmitDate() {
		return submitDate;
	}
	
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	@JsonProperty("order.creationDate")
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	@JsonProperty("order.lastModifiedDate")
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@JsonProperty("order.orderState")
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	@JsonProperty("order.orderOriginSource")
	public String getOrderOriginSource() {
		return orderOriginSource;
	}
	public void setOrderOriginSource(String orderOriginSource) {
		this.orderOriginSource = orderOriginSource;
	}
	@JsonProperty("order.totalToPay")
	public String getTotalToPay() {
		return totalToPay;
	}
	public void setTotalToPay(String totalToPay) {
		this.totalToPay = totalToPay;
	}
	@JsonProperty("order.paymentGroups")
	public String getPaymentGroups() {
		return paymentGroups;
	}
	public void setPaymentGroups(String paymentGroups) {
		this.paymentGroups = paymentGroups;
	}
	@JsonProperty("order.jurisdiction")
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	@JsonProperty("order.stateDetail")
	public String getStateDetail() {
		return stateDetail;
	}
	public void setStateDetail(String stateDetail) {
		this.stateDetail = stateDetail;
	}
	@JsonProperty("order.submitDate.type")
	public String getSubmitDate_type() {
		return submitDate_type;
	}
	public void setSubmitDate_type(String submitDate_type) {
		this.submitDate_type = submitDate_type;
	}
	@JsonProperty("order.creationDate.type")
	public String getCreationDate_type() {
		return creationDate_type;
	}
	public void setCreationDate_type(String creationDate_type) {
		this.creationDate_type = creationDate_type;
	}
	@JsonProperty("order.lastModifiedDate.type")
	public String getLastModifiedDate_type() {
		return lastModifiedDate_type;
	}
	public void setLastModifiedDate_type(String lastModifiedDate_type) {
		this.lastModifiedDate_type = lastModifiedDate_type;
	}
	@JsonProperty("order.orderState.type")
	public String getOrderState_type() {
		return orderState_type;
	}
	public void setOrderState_type(String orderState_type) {
		this.orderState_type = orderState_type;
	}
	@JsonProperty("order.orderOriginSource.type")
	public String getOrderOriginSource_type() {
		return orderOriginSource_type;
	}
	public void setOrderOriginSource_type(String orderOriginSource_type) {
		this.orderOriginSource_type = orderOriginSource_type;
	}
	@JsonProperty("order.totalToPay.type")
	public String getTotalToPay_type() {
		return totalToPay_type;
	}
	public void setTotalToPay_type(String totalToPay_type) {
		this.totalToPay_type = totalToPay_type;
	}
	@JsonProperty("order.paymentGroups.type")
	public String getPaymentGroups_type() {
		return paymentGroups_type;
	}
	public void setPaymentGroups_type(String paymentGroups_type) {
		this.paymentGroups_type = paymentGroups_type;
	}
	@JsonProperty("order.jurisdiction.type")
	public String getJurisdiction_type() {
		return jurisdiction_type;
	}
	public void setJurisdiction_type(String jurisdiction_type) {
		this.jurisdiction_type = jurisdiction_type;
	}
	@JsonProperty("order.stateDetail.type")
	public String getStateDetail_type() {
		return stateDetail_type;
	}
	public void setStateDetail_type(String stateDetail_type) {
		this.stateDetail_type = stateDetail_type;
	}
	@JsonProperty("order.error")
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@JsonProperty("order.error.type")
	public String getError_type() {
		return error_type;
	}
	public void setError_type(String error_type) {
		this.error_type = error_type;
	}

	public String getCust_rel_order() {
		return cust_rel_order;
	}

	public void setCust_rel_order(String cust_rel_order) {
		this.cust_rel_order = cust_rel_order;
	}

	public String getCust_rel_order_type() {
		return cust_rel_order_type;
	}

	public void setCust_rel_order_type(String cust_rel_order_type) {
		this.cust_rel_order_type = cust_rel_order_type;
	}

	public String getOrderSuceessStatus() {
		return orderSuceessStatus;
	}

	public void setOrderSuceessStatus(String orderSuceessStatus) {
		this.orderSuceessStatus = orderSuceessStatus;
	}

	public String getOrderSuceessStatus_type() {
		return orderSuceessStatus_type;
	}

	public void setOrderSuceessStatus_type(String orderSuceessStatus_type) {
		this.orderSuceessStatus_type = orderSuceessStatus_type;
	}

	

}
