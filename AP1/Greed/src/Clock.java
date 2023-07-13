import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Clock {
	enum Time{
		seconds,minutes,houre;
	}
	static long seconds = 0,minutes = 0,houres = 0,timeElapsed = 0;
	static Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timeElapsed += 1000;
			seconds = (timeElapsed/1000)%60;
			minutes =((timeElapsed/1000)%3600)/60;
			houres = (timeElapsed/1000)/3600;
			JLabel temp = Game.label2;
			temp.setText("Time : "+getTime());
		}
	});
	private static String convertTime(long time,Time t) {
		String ans = "00";
		if(t == Time.seconds) {
			ans = String.format("%02d", time) ;
		}
		else if(t == Time.minutes) {
			ans = String.format("%02d", time) ;
		}
		else if (t == Time.houre) {
			ans = String.format("%d", time) ;
		}
		return ans;
	}
	private static String getTime() {
		return (convertTime(houres, Time.houre) + ":" + convertTime(minutes, Time.minutes) + ":" + convertTime(seconds, Time.seconds));
	}
	public static void stop() {
		seconds = 0;
		minutes = 0;
		houres = 0;
		timeElapsed = 0;
		timer.stop();
		
		
	}
	public static void start() {
		seconds = 0;
		minutes = 0;
		houres = 0;
		timeElapsed = 0;
		timer.start();
		
	}
	
}
