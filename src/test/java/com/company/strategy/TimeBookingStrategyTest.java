package com.company.strategy;

import com.company.model.Meeting;
import com.company.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TimeBookingStrategyTest {

  BookingStrategy bookingStrategy;

  @BeforeEach
  void setup() {
    bookingStrategy = new TimeBookingStrategy();
  }

  @Test
  void test_bookValidCase() {

    // Given.
    Room room = new Room("Albert");
    List<Meeting> meetings =
        List.of(new Meeting(1, 2, room),
                new Meeting(2, 3, room),
                new Meeting(3, 4, room));

    // When.
    Meeting meeting = bookingStrategy.book(4, 6, room, meetings);
    Meeting meeting2 = bookingStrategy.book(9, 12, room, meetings);

    // Then.
    assertNotNull(meeting);
    assertNotNull(meeting2);
  }

  @Test
  void test_bookInvalidTestCase() {

    // Given.
    Room room = new Room("Albert");
    List<Meeting> meetings =
        List.of(new Meeting(2, 4, room),
                new Meeting(7, 10, room),
                new Meeting(14, 18, room));

    // When.
    Meeting meeting = bookingStrategy.book(1, 10, room, meetings); // collide with meeting 1 & 2
    Meeting meeting2 = bookingStrategy.book(1, 3, room, meetings); // collide with meeting 1
    Meeting meeting3 = bookingStrategy.book(3, 5, room, meetings); // collide with meeting 1
    Meeting meeting4 = bookingStrategy.book(7, 12, room, meetings); // collide with meeting 2
    Meeting meeting5 = bookingStrategy.book(11, 15, room, meetings); // collide with meeting 3
    Meeting meeting6 = bookingStrategy.book(14, 18, room, meetings); // Same as meeting 3

    // Then.
    assertNull(meeting);
    assertNull(meeting2);
    assertNull(meeting3);
    assertNull(meeting4);
    assertNull(meeting5);
    assertNull(meeting6);
  }
}
