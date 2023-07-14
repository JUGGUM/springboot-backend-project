package me.gayoungkim.base.error.exception;

import me.gayoungkim.base.error.ErrorCode;

public class MenuNotTopMenuException extends BadRequestException {

  public MenuNotTopMenuException() {
    super(ErrorCode.NOT_TOP_MENU);
  }
}
