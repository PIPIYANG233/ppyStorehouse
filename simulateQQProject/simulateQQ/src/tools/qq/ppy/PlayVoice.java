package tools.qq.ppy;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class PlayVoice {
	public static void playVoiceFile(String yy) {
		
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(yy));
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();
			int count;
			byte tempBuffer[] = new byte[1024];
			while ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
				if (count > 0) {
					sourceDataLine.write(tempBuffer, 0, count);
				}
			}
			sourceDataLine.drain();
			sourceDataLine.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
