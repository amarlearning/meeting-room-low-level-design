package com.company.strategy;

import com.company.model.Meeting;
import com.company.model.Room;

import java.util.List;

/** Interface to define strategy to book a room based on the given time. */
public interface BookingStrategy {
  Meeting book(int startTime, int endTime, Room room, List<Meeting> meetings);
}
