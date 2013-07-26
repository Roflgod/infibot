package com.infibot.client.api.util;

/**
 * @author Roflgod
 */
public class Timer {

	private final long period;

	private long start;
	private long end;

	private boolean isPaused = false;
	private long pauseTime;

	public Timer(final long period) {
		this.period = period;
		this.start = System.currentTimeMillis();
		this.end = start + period;
	}

	private long getCurrent() {
		return isPaused ? pauseTime : System.currentTimeMillis();
	}

	public long getElapsed() {
		return getCurrent() - start;
	}

	public long getRemaining() {
		return isRunning() ? end - getCurrent() : 0;
	}

	public boolean isRunning() {
		return getCurrent() < end;
	}

	public void pause() {
		if (!isPaused) {
			pauseTime = System.currentTimeMillis();
			isPaused = true;
		}
	}

	public void resume() {
		if (isPaused) {
			start = System.currentTimeMillis();
			long timeLeft = end - pauseTime;
			end = start + timeLeft;
			isPaused = false;
		}
	}

	public void reset() {
		start = System.currentTimeMillis();
		end = start + period;
	}

	public long setEndIn(long millis) {
		end = System.currentTimeMillis() + millis;
		return end;
	}

	public String toElapsedString() {
		return format(getElapsed());
	}

	public String toRemainingString() {
		return format(getRemaining());
	}

	private static final int MILLIS_PER_SEC = 1000;
	private static final int MILLIS_PER_MIN = MILLIS_PER_SEC * 60;
	private static final int MILLIS_PER_HOUR = MILLIS_PER_MIN * 60;
	private static final int MILLIS_PER_DAY = MILLIS_PER_HOUR * 24;

	public static String format(long time) {
		final StringBuilder s = new StringBuilder();
		final long days = time / MILLIS_PER_DAY;
		time -= days * MILLIS_PER_DAY;
		final long hours = time / MILLIS_PER_HOUR;
		time -= hours * MILLIS_PER_HOUR;
		final long mins = time / MILLIS_PER_MIN;
		time -= mins * MILLIS_PER_MIN;
		final long secs = time / MILLIS_PER_SEC;
		time -= secs * MILLIS_PER_SEC;

		if (days > 0) {
			s.append(days);
			s.append(':');
		}
		if (hours < 10) {
			s.append(0);
		}
		s.append(hours);
		s.append(':');
		if (mins < 10) {
			s.append(0);
		}
		s.append(mins);
		s.append(':');
		if (secs < 10) {
			s.append(0);
		}
		s.append(secs);

		return s.toString();
	}

}