package com.tesco.tps.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tesco.tps.core.domain.CouchbaseDomain;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)

public class TestDetails implements CouchbaseDomain {
	private static final long serialVersionUID = 1L;
public TestDetails(String uniqueKey) {
		this.uniqueKey = uniqueKey;
		
	}

	public TestDetails() {
	
	}

	
		@JsonProperty("uniqueKey")
	private String uniqueKey;
		
		@JsonProperty("name")
		private String name;
		@JsonProperty("organization")
		private String organization;
		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return uniqueKey;
		}

		public String getUniqueKey() {
			return uniqueKey;
		}

		public void setUniqueKey(String uniqueKey) {
			this.uniqueKey = uniqueKey;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getOrganization() {
			return organization;
		}

		public void setOrganization(String organization) {
			this.organization = organization;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
	
}

