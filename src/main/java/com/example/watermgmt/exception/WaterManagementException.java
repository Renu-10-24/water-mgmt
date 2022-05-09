package com.example.watermgmt.exception;

public class WaterManagementException extends RuntimeException {
	String msg;

	public WaterManagementException(String msg) {
		this.msg = msg;
	}
	
}
