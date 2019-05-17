package io.dronefleet.mavlink.protocol;

import android.util.Log;
import android.view.animation.PathInterpolator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author huangwanjie
 * @date 2019/5/15
 */
public class CacheInputStream{

  private InputStream inputStream;
  private byte[] frame;
  private int pos = 0;
  private int datatotal = 0;
  private int size;
  public CacheInputStream(InputStream inputStream,int size){
    this.inputStream = inputStream;
    this.size = size;
  }

  public int read() throws IOException {
    int data = inputStream.read();
    if (data != -1){
      datatotal++;
      frame[pos++] = (byte) data;
    }
    return data;
  }

  public int read(int targetLen) throws IOException {
    int len = 0;
    int total = 0;
    while (total != targetLen){
      if ((len = inputStream.read(frame,total + 3,targetLen - total)) != -1){
        total = len + total;
      }
    }
    datatotal += total;
    Log.i("CacheInputStream","datatotal:" + datatotal);
    return total;
  }

  public byte[] getBuffer(){
    return frame;
  }
  public void commit(){
    frame = new byte[size];
    pos = 0;
  }
}
