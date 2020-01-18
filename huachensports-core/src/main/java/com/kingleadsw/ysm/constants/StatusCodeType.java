package com.kingleadsw.ysm.constants;

public enum StatusCodeType
{
  OK(Integer.valueOf(200)),  BAD_REQUEST(Integer.valueOf(400)),  UNAUTHORIZED(Integer.valueOf(401)),  FORBIDDEN(Integer.valueOf(403)),  NOT_FOUND(Integer.valueOf(404)),  REQUEST_TIMEOUT(Integer.valueOf(408)),  CONFLICT(Integer.valueOf(409)),  GONE(Integer.valueOf(410)),  LOCKED(Integer.valueOf(423)),  SERVER_ERROR(Integer.valueOf(500));
  
  private final Integer value;
  
  private StatusCodeType(Integer value)
  {
    this.value = value;
  }
  
  public Integer value()
  {
    return this.value;
  }
  
  public String toString()
  {
    return this.value.toString();
  }
}
