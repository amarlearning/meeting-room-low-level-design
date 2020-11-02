package com.company.manager;

import com.company.exception.InvalidTimeException;
import com.company.exception.NoRoomAvailableException;
import com.company.model.Meeting;
import com.company.model.Room;
import com.company.strategy.BookingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingManager {

  private RoomManager roomManager;
  private BookingStrategy bookingStrategy;

  private Map<String, List<Meeting>> bookings;

  /**
   * Constructor for BookingManager class.
   *
   * @param roomManager RoomManager
   * @param bookingStrategy BookingStrategy
   */
  public BookingManager(RoomManager roomManager, BookingStrategy bookingStrategy) {
    this.bookings = new HashMap<>();
    this.roomManager = roomManager;
    this.bookingStrategy = bookingStrategy;
  }

  /**
   * Method to book room for the given start and end time.
   *
   * @param startTime Integer
   * @param endTime Integer
   * @return
   */
  public Meeting bookRoom(int startTime, int endTime) {

    Meeting meeting = null;

    if (startTime >= endTime) {
      throw new InvalidTimeException("Start time should be less than endTime.");
    }

    List<Room> rooms = roomManager.getRooms();

    for (Room room : rooms) {

      List<Meeting> meetings = this.bookings.getOrDefault(room.getId(), new ArrayList<>());

      meeting = bookingStrategy.book(startTime, endTime, room, meetings);

      if (meeting != null) {
        break;
      }
    }

    if (meeting == null) {
      throw new NoRoomAvailableException("No room is available for the given time.");
    }

    if (!this.bookings.containsKey(meeting.getRoom().getId())) {
      this.bookings.put(meeting.getRoom().getId(), new ArrayList<>());
    }

    this.bookings.get(meeting.getRoom().getId()).add(meeting);

    return meeting;
  }

  /**
   * Fetch all meetings associated to a particular room.
   *
   * @param room Room
   * @return
   */
  public List<Meeting> getMeetingForRoom(final Room room) {
    return this.bookings.getOrDefault(room.getId(), new ArrayList<>());
  }
}
