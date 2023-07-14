package me.gayoungkim.base.error.exception;

import me.gayoungkim.base.error.ErrorCode;

public class MenuNotFoundException extends NotFoundException {

  public MenuNotFoundException() {
    super(ErrorCode.MENU_NOT_FOUND);
  }
}
