package io.dronefleet.mavlink.common;

import io.dronefleet.mavlink.annotations.MavlinkMessage;
import io.dronefleet.mavlink.annotations.MavlinkMessageBuilder;
import io.dronefleet.mavlink.annotations.MavlinkMessageField;
import java.lang.Override;
import java.lang.String;

/**
 * Delete all mission items at once. 
 */
@MavlinkMessage(
    id = 45,
    crc = 232
)
public final class MissionClearAll {
  /**
   * System ID 
   */
  private final int targetSystem;

  /**
   * Component ID 
   */
  private final int targetComponent;

  /**
   * Mission type, see {@link io.dronefleet.mavlink.common.MavMissionType MavMissionType} 
   */
  private final MavMissionType missionType;

  private MissionClearAll(int targetSystem, int targetComponent, MavMissionType missionType) {
    this.targetSystem = targetSystem;
    this.targetComponent = targetComponent;
    this.missionType = missionType;
  }

  @MavlinkMessageBuilder
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "MissionClearAll{targetSystem=" + targetSystem
         + ", targetComponent=" + targetComponent
         + ", missionType=" + missionType + "}";
  }

  /**
   * System ID 
   */
  @MavlinkMessageField(
      position = 1,
      unitSize = 1
  )
  public final int targetSystem() {
    return targetSystem;
  }

  /**
   * Component ID 
   */
  @MavlinkMessageField(
      position = 2,
      unitSize = 1
  )
  public final int targetComponent() {
    return targetComponent;
  }

  /**
   * Mission type, see {@link io.dronefleet.mavlink.common.MavMissionType MavMissionType} 
   */
  @MavlinkMessageField(
      position = 4,
      unitSize = 1,
      extension = true
  )
  public final MavMissionType missionType() {
    return missionType;
  }

  public static class Builder {
    private int targetSystem;

    private int targetComponent;

    private MavMissionType missionType;

    private Builder() {
    }

    /**
     * System ID 
     */
    @MavlinkMessageField(
        position = 1,
        unitSize = 1
    )
    public final Builder targetSystem(int targetSystem) {
      this.targetSystem = targetSystem;
      return this;
    }

    /**
     * Component ID 
     */
    @MavlinkMessageField(
        position = 2,
        unitSize = 1
    )
    public final Builder targetComponent(int targetComponent) {
      this.targetComponent = targetComponent;
      return this;
    }

    /**
     * Mission type, see {@link io.dronefleet.mavlink.common.MavMissionType MavMissionType} 
     */
    @MavlinkMessageField(
        position = 4,
        unitSize = 1,
        extension = true
    )
    public final Builder missionType(MavMissionType missionType) {
      this.missionType = missionType;
      return this;
    }

    public final MissionClearAll build() {
      return new MissionClearAll(targetSystem, targetComponent, missionType);
    }
  }
}