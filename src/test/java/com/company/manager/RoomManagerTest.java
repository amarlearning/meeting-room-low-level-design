package com.company.manager;

import com.company.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomManagerTest {

  RoomManager roomManager;

  @BeforeEach
  void setup() {
    roomManager = new RoomManager();
  }

  @Test
  void test_throwNullExceptionWhenRoomIsNUll() {
    Assertions.assertThrows(
        NullPointerException.class,
        () -> {
          roomManager.createRoom(null);
        });
  }

  @Test
  void test_createRoom() {

    // Given
    Room room1 = new Room("Nexus");
    Room room2 = new Room("Picador");

    // When, Then.
    assertTrue(roomManager.createRoom(room1));
    assertTrue(roomManager.createRoom(room2));
    assertFalse(roomManager.createRoom(room2));
  }

  @Test
  void test_getRooms() {

    // Given.
    Room room1 = new Room("Nexus");
    Room room2 = new Room("Picador");

    roomManager.createRoom(room1);
    roomManager.createRoom(room2);
    roomManager.createRoom(room2);

    assertEquals(2, this.roomManager.getRooms().size());
  }
}
