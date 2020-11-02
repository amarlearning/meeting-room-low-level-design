package com.company.strategy;

import com.company.model.Meeting;
import com.company.model.Room;

import java.util.List;

/** Book meeting based on time comparison. */
public class TimeBookingStrategy implements BookingStrategy {

  @Override
  public Meeting book(
      final int startTime, final int endTime, final Room room, final List<Meeting> meetings) {

    boolean canBook = true;
    for (Meeting m : meetings) {
      if (!(startTime >= m.getEndTime() || endTime <= m.getStartTime())) {
        canBook = false;
      }
    }

    return canBook ? new Meeting(startTime, endTime, room) : null;
  }
}
