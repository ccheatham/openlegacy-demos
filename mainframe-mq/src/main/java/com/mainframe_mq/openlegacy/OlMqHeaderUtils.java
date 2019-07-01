package com.mainframe_mq.openlegacy;



import org.openlegacy.providers.mq.utils.MqHeaderUtils;
import org.openlegacy.utils.ByteUtils;

public class OlMqHeaderUtils implements MqHeaderUtils {

	@Override
    public byte[] compose(byte[] message, byte[] path) {
            byte[] path2 = ByteUtils.hexStringToByteArray("4040404040404040");
            for (int i = 0; i < path.length; i++) {
                path2[i] = path[i];
            }
            return ByteUtils.concat(path2, message);
    }
}