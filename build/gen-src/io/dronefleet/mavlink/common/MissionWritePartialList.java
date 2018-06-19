package io.dronefleet.mavlink.common;

import io.dronefleet.mavlink.annotations.MavlinkMessage;
import io.dronefleet.mavlink.annotations.MavlinkMessageField;

/**
 * This message is sent to the MAV to write a partial list. If start index == end index, only one item 
 * will be transmitted / updated. If the start index is NOT 0 and above the current list size, this 
 * request should be REJECTED! 
 */
@MavlinkMessage(
    id = 38,
    crc = 9
)
public final class MissionWritePartialList {
  private final int targetSystem;

  private final int targetComponent;

  private final int startIndex;

  private final int endIndex;

  private final MavMissionType missionType;

  private MissionWritePartialList(int targetSystem, int targetComponent, int startIndex,
      int endIndex, MavMissionType missionType) {
    this.targetSystem = targetSystem;
    this.targetComponent = targetComponent;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
    this.missionType = missionType;
  }

  public static Builder builder() {
    return new Builder();
  }

  /**
   * System ID 
   */
  @MavlinkMessageField(
      position = 1,
      length = 1
  )
  public final int targetSystem() {
    return targetSystem;
  }

  /**
   * Component ID 
   */
  @MavlinkMessageField(
      position = 2,
      length = 1
  )
  public final int targetComponent() {
    return targetComponent;
  }

  /**
   * Start index, 0 by default and smaller / equal to the largest index of the current onboard list. 
   */
  @MavlinkMessageField(
      position = 3,
      length = 2
  )
  public final int startIndex() {
    return startIndex;
  }

  /**
   * End index, equal or greater than start index. 
   */
  @MavlinkMessageField(
      position = 4,
      length = 2
  )
  public final int endIndex() {
    return endIndex;
  }

  /**
   * Mission type, see MAV_MISSION_TYPE 
   */
  @MavlinkMessageField(
      position = 6,
      length = 1,
      extension = true
  )
  public final MavMissionType missionType() {
    return missionType;
  }

  public class Builder {
    private int targetSystem;

    private int targetComponent;

    private int startIndex;

    private int endIndex;

    private MavMissionType missionType;

    private Builder() {
    }

    /**
     * System ID 
     */
    @MavlinkMessageField(
        position = 1,
        length = 1
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
        length = 1
    )
    public final Builder targetComponent(int targetComponent) {
      this.targetComponent = targetComponent;
      return this;
    }

    /**
     * Start index, 0 by default and smaller / equal to the largest index of the current onboard list. 
     */
    @MavlinkMessageField(
        position = 3,
        length = 2
    )
    public final Builder startIndex(int startIndex) {
      this.startIndex = startIndex;
      return this;
    }

    /**
     * End index, equal or greater than start index. 
     */
    @MavlinkMessageField(
        position = 4,
        length = 2
    )
    public final Builder endIndex(int endIndex) {
      this.endIndex = endIndex;
      return this;
    }

    /**
     * Mission type, see MAV_MISSION_TYPE 
     */
    @MavlinkMessageField(
        position = 6,
        length = 1,
        extension = true
    )
    public final Builder missionType(MavMissionType missionType) {
      this.missionType = missionType;
      return this;
    }

    public final MissionWritePartialList build() {
      return new MissionWritePartialList(targetSystem, targetComponent, startIndex, endIndex, missionType);
    }
  }
}