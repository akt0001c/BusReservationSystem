package com.masaischool.exceptions;

public class NonCancelableTicketException extends Exception {
	NonCancelableTicketException(String msg)
	{
		super(msg);
	}
}
