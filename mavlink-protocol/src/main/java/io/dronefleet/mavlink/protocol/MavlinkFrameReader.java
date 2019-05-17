package io.dronefleet.mavlink.protocol;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * Reads Mavlink protocol frames.
 * <p>
 * A frame is a collection of bytes that appear to be a complete packet. A frame begins with
 * a valid protocol version marker (STX), and includes the rest of the expected packet bytes
 * according to the specified length, protocol version, and incompatibility flags (signature,
 * for instance).
 * <p>
 * The parsed frames are unreliable in the sense that the only actual test is that the frame
 * began with a valid STX. Any long enough sequence of bytes which begins a valid STX will be
 * returned as a frame by this reader. Therefore, users of this class are required to understand
 * and CRC check the returned frames, issuing calls to {@link #drop()} when validation fails.
 */
public class MavlinkFrameReader {
//    private final TransactionalInputStream in;

    private final CacheInputStream cacheInputStream;
    /**
     * Creates a mavlink frame reader for the specified input stream.
     *
     * @param in The input stream to read mavlink frames from.
     */
    public MavlinkFrameReader(InputStream in) {
        cacheInputStream = new CacheInputStream(in,280);
    }

    /**
     * Reads the next frame in the stream. If this a call to this method returns {@code true},
     * then the bytes of the read frame can be retrieved by calling {@link #frame()}.
     *
     * @return {@code true} if the next frame was read, or {@code false} if the stream
     * has ended.
     * @throws IOException if an IO error occurs.
     */
    public boolean next() throws IOException {
        int versionMarker;
        int payloadLength;
        int incompatibleFlags;

        cacheInputStream.commit();
        while ((versionMarker = cacheInputStream.read()) != -1) {
            if ((payloadLength = cacheInputStream.read()) == -1) {
                return false;
            }
            switch (versionMarker) {
                case MavlinkPacket.MAGIC_V1:
                    int m1Len = 6 + payloadLength;
                    return cacheInputStream.read(m1Len) == m1Len;
                case MavlinkPacket.MAGIC_V2:
                    incompatibleFlags = cacheInputStream.read();
                    int m2Len = 9 + payloadLength + (incompatibleFlags & 1) * 13;
                    return incompatibleFlags != -1 && cacheInputStream.read(m2Len) == m2Len;
                default:
                    cacheInputStream.commit();
                    Log.i("MavlinkFrameReader","error frame");
                    break;
            }
        }
        return false;
    }

    /**
     * Returns the frame read by the previous call to {@link #next()}.
     *
     * @return The read frame's bytes, or an empty byte array if no frame was read yet.
     */
    public byte[] frame() {
        return cacheInputStream.getBuffer();
    }

    /**
     * Drops the last frame, returning its bytes to the stream, skipping its first byte.
     *
     * @throws IOException if an IO error occurs.
     */
    public void drop() throws IOException {
//        in.rollback();
//        in.skip(1);
//        in.commit();
    }
}
