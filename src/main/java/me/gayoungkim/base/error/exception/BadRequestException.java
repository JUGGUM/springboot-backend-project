package me.gayoungkim.base.error.exception;

import me.gayoungkim.base.error.ErrorCode;

public class BadRequestException extends CustomBaseException {

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode.getMessage(), errorCode);
  }

  public BadRequestException() {
    super(ErrorCode.INVALID_INPUT_VALUE);
  }

}
