package com.pikia.component.base;

import java.io.Serializable;

public class BaseDomain implements Serializable {
	private Long id;
	private boolean delflag = false;
	private boolean modified;
	private boolean initialized;

	public BaseDomain(Long id) {
		this.id = id;
	}

	public BaseDomain() {

	}

	public boolean isDelflag() {
		return delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

}
