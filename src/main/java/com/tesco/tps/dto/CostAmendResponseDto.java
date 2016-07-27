package com.tesco.tps.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CostAmendResponseDto {
	@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
	
	private String message;

	private List<MissingCostAmends> missingSet;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MissingCostAmends> getMissingSet() {
		return missingSet;
	}

	public void setMissingSet(List<MissingCostAmends> missingSet) {
		this.missingSet = missingSet;
	}

	public class MissingCostAmends {
		@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

		private String tradedUnitUUID;

		private String tradingPartnerUUID;

		private String costEffectiveDate;

		private String currentCostPrice;

		private String currentOffInvoiceDiscount;

		private String currentFinalInvoiceCost;

		private String currencyCurrentPriceIn;

		private String newCostPrice;

		private String newOffInvoiceDiscount;

		private String newOffInvoiceStartDate;

		private String newOffInvoiceEndDate;

		private String newFinalInvoiceCost;

		private String currencyNewPriceIn;

		private String costChangeReasonCode;

		private String deadLine;

		private String action;

		private String workFlowStatus;

		private String status;

		private String currentAssignedTo;

		private String ReasonForFailure;

		public String getTradedUnitUUID() {
			return tradedUnitUUID;
		}

		public void setTradedUnitUUID(String tradedUnitUUID) {
			this.tradedUnitUUID = tradedUnitUUID;
		}

		public String getTradingPartnerUUID() {
			return tradingPartnerUUID;
		}

		public void setTradingPartnerUUID(String tradingPartnerUUID) {
			this.tradingPartnerUUID = tradingPartnerUUID;
		}

		public String getCostEffectiveDate() {
			return costEffectiveDate;
		}

		public void setCostEffectiveDate(String costEffectiveDate) {
			this.costEffectiveDate = costEffectiveDate;
		}

		public String getCurrentCostPrice() {
			return currentCostPrice;
		}

		public void setCurrentCostPrice(String currentCostPrice) {
			this.currentCostPrice = currentCostPrice;
		}

		public String getCurrentOffInvoiceDiscount() {
			return currentOffInvoiceDiscount;
		}

		public void setCurrentOffInvoiceDiscount(String currentOffInvoiceDiscount) {
			this.currentOffInvoiceDiscount = currentOffInvoiceDiscount;
		}

		public String getCurrentFinalInvoiceCost() {
			return currentFinalInvoiceCost;
		}

		public void setCurrentFinalInvoiceCost(String currentFinalInvoiceCost) {
			this.currentFinalInvoiceCost = currentFinalInvoiceCost;
		}

		public String getCurrencyCurrentPriceIn() {
			return currencyCurrentPriceIn;
		}

		public void setCurrencyCurrentPriceIn(String currencyCurrentPriceIn) {
			this.currencyCurrentPriceIn = currencyCurrentPriceIn;
		}

		public String getNewCostPrice() {
			return newCostPrice;
		}

		public void setNewCostPrice(String newCostPrice) {
			this.newCostPrice = newCostPrice;
		}

		public String getNewOffInvoiceDiscount() {
			return newOffInvoiceDiscount;
		}

		public void setNewOffInvoiceDiscount(String newOffInvoiceDiscount) {
			this.newOffInvoiceDiscount = newOffInvoiceDiscount;
		}

		public String getNewOffInvoiceStartDate() {
			return newOffInvoiceStartDate;
		}

		public void setNewOffInvoiceStartDate(String newOffInvoiceStartDate) {
			this.newOffInvoiceStartDate = newOffInvoiceStartDate;
		}

		public String getNewOffInvoiceEndDate() {
			return newOffInvoiceEndDate;
		}

		public void setNewOffInvoiceEndDate(String newOffInvoiceEndDate) {
			this.newOffInvoiceEndDate = newOffInvoiceEndDate;
		}

		public String getNewFinalInvoiceCost() {
			return newFinalInvoiceCost;
		}

		public void setNewFinalInvoiceCost(String newFinalInvoiceCost) {
			this.newFinalInvoiceCost = newFinalInvoiceCost;
		}

		public String getCurrencyNewPriceIn() {
			return currencyNewPriceIn;
		}

		public void setCurrencyNewPriceIn(String currencyNewPriceIn) {
			this.currencyNewPriceIn = currencyNewPriceIn;
		}

		public String getCostChangeReasonCode() {
			return costChangeReasonCode;
		}

		public void setCostChangeReasonCode(String costChangeReasonCode) {
			this.costChangeReasonCode = costChangeReasonCode;
		}

		public String getDeadLine() {
			return deadLine;
		}

		public void setDeadLine(String deadLine) {
			this.deadLine = deadLine;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getWorkFlowStatus() {
			return workFlowStatus;
		}

		public void setWorkFlowStatus(String workFlowStatus) {
			this.workFlowStatus = workFlowStatus;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCurrentAssignedTo() {
			return currentAssignedTo;
		}

		public void setCurrentAssignedTo(String currentAssignedTo) {
			this.currentAssignedTo = currentAssignedTo;
		}

		public String getReasonForFailure() {
			return ReasonForFailure;
		}

		public void setReasonForFailure(String reasonForFailure) {
			ReasonForFailure = reasonForFailure;
		}

	}
}
