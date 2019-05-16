package io.dronefleet.mavlink.protocol.util;

import im.helmsman.mavlink.helmsman.HelmsmanDialect;
import im.helmsman.mavlink.helmsman.SonarEchoData;
import io.dronefleet.mavlink.protocol.MavlinkPacket;

/**
 * @author huangwanjie
 * @date 2019/5/16
 */
public class MavLinkUnpackUtil {

  public static Object unpack(MavlinkPacket packet){
    int msgId = packet.getMessageId();
    switch (msgId){
      case HelmsmanDialect.MAVLINK_SONAR_ECHO_DATA:
        return SonarEchoData.unpack(packet.getPayload());
      default:
        return null;
    }
  }
}
