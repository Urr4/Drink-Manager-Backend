package de.urr4.drinkmanager.exceptions;

public class DrinkManagerException extends RuntimeException {

	private ExceptionType exceptionType;


	public DrinkManagerException(ExceptionType type) {
		this.exceptionType = type;
	}


	public ExceptionType getExceptionType() {
		return exceptionType;
	}


	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}
}
