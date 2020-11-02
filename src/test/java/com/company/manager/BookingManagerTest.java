package com.company.manager;

import com.company.exception.InvalidTimeException;
import com.company.exception.NoRoomAvailableException;
import com.company.model.Meeting;
import com.company.model.Room;
import com.company.strategy.BookingStrategy;
import com.company.strategy.TimeBookingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingManagerTest {

  BookingManager bookingManager;

  @BeforeEach
  void setUp() {
    Room room1 = new Room("Picasso");
    Room room2 = new Room("Albert");
    Room room3 = new Room("Pamela");

    RoomManager roomManager = new RoomManager();
    roomManager.createRoom(room1);
    roomManager.createRoom(room2);
    roomManager.createRoom(room3);

    BookingStrategy bookingStrategy = new TimeBookingStrategy();

    bookingManager = new BookingManager(roomManager, bookingStrategy);
  }

  @Test
  void test_bookRoomWithInvalidTime() {

    // Then.
    assertThrows(
        InvalidTimeException.class,
        () -> {
          // When.
          bookingManager.bookRoom(20, 5);
        });

    // Then.
    assertThrows(
        InvalidTimeException.class,
        () -> {
          // When.
          bookingManager.bookRoom(3, 2);
        });
  }

  @Test
  void test_bookRoomWhenRoomIsAvailable() {

    // When.
    Meeting meeting1 = bookingManager.bookRoom(3, 7);
    // Then.
    assertNotNull(meeting1);

    // When.
    Meeting meeting2 = bookingManager.bookRoom(1, 5);
    // Then.
    assertNotNull(meeting2);

    // When.
    Meeting meeting3 = bookingManager.bookRoom(2, 5);
    // Then.
    assertNotNull(meeting3);
  }

  @Test
  void test_bookWhenWhenNoRoomIsAvailable() {

    // Given.
    Meeting meeting1 = bookingManager.bookRoom(1, 9);
    Meeting meeting2 = bookingManager.bookRoom(7, 12);
    Meeting meeting3 = bookingManager.bookRoom(3, 9);

    // At this point, no room is available for the same time,
    // so an exception is thrown that no room is available for that time.

    // Then.
    assertThrows(
        NoRoomAvailableException.class,
        () -> {
          // When.
          Meeting meeting4 = bookingManager.bookRoom(2, 12);
        });
  }

  @Test
  void test_bookingHistoryForRoom() {

    // Given.
    Meeting meeting1 = bookingManager.bookRoom(1, 3); // Room1 is booked.
    Meeting meeting2 =
        bookingManager.bookRoom(3, 5); // Room1 is booked since it's free for given time.
    Meeting meeting3 =
        bookingManager.bookRoom(5, 9); // Room1 is booked since it's free for given time.

    // Then.
    assertEquals(
        3,
        // When.
        bookingManager.getMeetingForRoom(meeting1.getRoom()).size());
  }
}
