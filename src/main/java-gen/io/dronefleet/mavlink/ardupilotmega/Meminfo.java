package io.dronefleet.mavlink.ardupilotmega;

import io.dronefleet.mavlink.annotations.MavlinkMessage;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageField;
import java.lang.Override;
import java.lang.String;

/**
 * state of APM memory 
 */
@MavlinkMessage(
    id = 152,
    crc = 208
)
public final class Meminfo {
  /**
   * heap top 
   */
  private final int brkval;

  /**
   * free memory 
   */
  private final int freemem;

  /**
   * free memory (32 bit) 
   */
  private final long freemem32;

  private Meminfo(int brkval, int freemem, long freemem32) {
    this.brkval = brkval;
    this.freemem = freemem;
    this.freemem32 = freemem32;
  }

  @MavlinkMessageBuilder
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "Meminfo{brkval=" + brkval
         + ", freemem=" + freemem
         + ", freemem32=" + freemem32 + "}";
  }

  /**
   * heap top 
   */
  @MavlinkMessageField(
      position = 1,
      unitSize = 2
  )
  public final int brkval() {
    return brkval;
  }

  /**
   * free memory 
   */
  @MavlinkMessageField(
      position = 2,
      unitSize = 2
  )
  public final int freemem() {
    return freemem;
  }

  /**
   * free memory (32 bit) 
   */
  @MavlinkMessageField(
      position = 4,
      unitSize = 4,
      extension = true
  )
  public final long freemem32() {
    return freemem32;
  }

  public static class Builder {
    private int brkval;

    private int freemem;

    private long freemem32;

    private Builder() {
    }

    /**
     * heap top 
     */
    @MavlinkMessageField(
        position = 1,
        unitSize = 2
    )
    public final Builder brkval(int brkval) {
      this.brkval = brkval;
      return this;
    }

    /**
     * free memory 
     */
    @MavlinkMessageField(
        position = 2,
        unitSize = 2
    )
    public final Builder freemem(int freemem) {
      this.freemem = freemem;
      return this;
    }

    /**
     * free memory (32 bit) 
     */
    @MavlinkMessageField(
        position = 4,
        unitSize = 4,
        extension = true
    )
    public final Builder freemem32(long freemem32) {
      this.freemem32 = freemem32;
      return this;
    }

    public final Meminfo build() {
      return new Meminfo(brkval, freemem, freemem32);
    }
  }
}