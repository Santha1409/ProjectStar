package com.tesco.tps.model;
import java.util.List;

import com.tesco.tps.dto.CostAmendRequestDto;

public class CostAmendInput {
	private List<CostAmendRequestDto> inputList;//=new ArrayList<CostAmendRequestDto>();

	public List<CostAmendRequestDto> getInputList() {
		return inputList;
	}

	public void setInputList(List<CostAmendRequestDto> inputList) {
		this.inputList = inputList;
	}
	

}
