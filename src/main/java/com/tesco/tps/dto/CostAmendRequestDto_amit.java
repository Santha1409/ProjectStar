package com.tesco.tps.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tesco.tps.core.annotation.Id;
import com.tesco.tps.core.dto.AuditableCouchbaseDto;
import com.tesco.tps.core.interfaces.OnCreateValidate;
import com.tesco.tps.core.interfaces.OnUpdateValidate;
import com.tesco.tps.validator.NullOrNotBlank;

@JsonInclude(value = Include.NON_NULL)
public class CostAmendRequestDto_amit extends AuditableCouchbaseDto {
	private static final long serialVersionUID = -1058695177341631229L;

	@NotNull(groups = OnUpdateValidate.class)
	@NotBlank(groups = OnUpdateValidate.class)
	@Null(groups = OnCreateValidate.class)
	@Id
	private String id;
	@NotBlank(groups = { OnCreateValidate.class })
	@NullOrNotBlank(groups = { OnUpdateValidate.class })
	private String moduleId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId
	 *            the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}
