package me.gayoungkim.base.error.exception;

import me.gayoungkim.base.error.ErrorCode;

public class NotFoundException extends CustomBaseException {

  public NotFoundException(ErrorCode errorCode) {
    super(errorCode.getMessage(), errorCode);
  }

  public NotFoundException() {
    super(ErrorCode.NOT_FOUND);
  }
}
