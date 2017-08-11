package com.pikia.component.ddd.message;

public class DomainMessage {
	public static final long DEFAULT_WAIT_FOR_TIMEOUT = 10000L;
	protected Object eventSource;
	// protected volatile ResultEvent resultEvent;
	private long waitForTimeOut = 10000L;

	public DomainMessage(Object eventSource) {
		this.eventSource = eventSource;
	}

	public DomainMessage(Object eventSource, long waitForTimeOut) {
		this(eventSource);
		setWaitForTimeOut(waitForTimeOut);
	}

	public long getWaitForTimeOut() {
		return this.waitForTimeOut;
	}

	public void setWaitForTimeOut(long waitForTimeOut) {
		this.waitForTimeOut = waitForTimeOut;
	}

	public Object getEventSource() {
		return this.eventSource;
	}

	public void setEventSource(Object eventSource) {
		this.eventSource = eventSource;
	}

}
