package com.kingleadsw.ysm.sequence;
import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.utils.Dates;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @Auther: zhoujie
 * @Description:
 */
public class Sequence
{

  private final long twepoch = 1288834974657L;
  private final long workerIdBits = 5L;
  private final long datacenterIdBits = 5L;
  private final long maxWorkerId = 31L;
  private final long maxDatacenterId = 31L;
  private final long sequenceBits = 12L;
  private final long workerIdShift = 12L;
  private final long datacenterIdShift = 17L;
  private final long timestampLeftShift = 22L;
  private final long sequenceMask = 4095L;
  private long workerId;
  private long datacenterId;
  private long sequence = 0L;
  private long lastTimestamp = -1L;
  
  public Sequence()
  {
    this.datacenterId = getDatacenterId(31L);
    this.workerId = getMaxWorkerId(this.datacenterId, 31L);
  }
  
  public Sequence(long workerId, long datacenterId)
  {
    if ((workerId > 31L) || (workerId < 0L)) {
      throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", new Object[] {
        Long.valueOf(31L) }));
    }
    if ((datacenterId > 31L) || (datacenterId < 0L)) {
      throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", new Object[] {
        Long.valueOf(31L) }));
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
  }
  
  protected static long getMaxWorkerId(long datacenterId, long maxWorkerId)
  {
    StringBuilder mpid = new StringBuilder();
    mpid.append(datacenterId);
    String name = ManagementFactory.getRuntimeMXBean().getName();
    if (name != null) {
      mpid.append(name.split("@")[0]);
    }
    return (mpid.toString().hashCode() & 0xFFFF) % (maxWorkerId + 1L);
  }
  
  protected static long getDatacenterId(long maxDatacenterId)
  {
    long id = 0L;
    try
    {
      InetAddress ip = InetAddress.getLocalHost();
      NetworkInterface network = NetworkInterface.getByInetAddress(ip);
      if (network == null)
      {
        id = 1L;
      }
      else
      {
        byte[] mac = network.getHardwareAddress();
        if (null != mac)
        {
          id = (0xFF & mac[(mac.length - 1)] | 0xFF00 & mac[(mac.length - 2)] << 8) >> 6;
          
          id %= (maxDatacenterId + 1L);
        }
      }
    }
    catch (Exception e)
    {
      //todo 记录错误日志

    }
    return id;
  }
  
  public synchronized long nextId()
  {
    long timestamp = timeGen();
    if (timestamp < this.lastTimestamp)
    {
      throw new UnknowException(Integer.valueOf(500001));
    }
    if (this.lastTimestamp == timestamp)
    {
      this.sequence = (this.sequence + 1L & 0xFFF);
      if (this.sequence == 0L) {
        timestamp = tilNextMillis(this.lastTimestamp);
      }
    }
    else
    {
      this.sequence = 0L;
    }
    this.lastTimestamp = timestamp;
    
    return timestamp - 1288834974657L << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
  }
  
  protected long tilNextMillis(long lastTimestamp)
  {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }
  
  protected long timeGen()
  {
    return Dates.now();
  }
}
