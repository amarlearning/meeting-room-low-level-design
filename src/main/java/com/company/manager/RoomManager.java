package com.company.manager;

import com.company.model.Room;
import lombok.NonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoomManager {

  private Set<Room> rooms;

  public RoomManager() {
    this.rooms = new HashSet<>();
  }

  /**
   * Create a new room for hotel.
   *
   * @param room Room
   * @return
   */
  public boolean createRoom(@NonNull final Room room) {
    return this.rooms.add(room);
  }

  /**
   * Helper method to return room in the natural sorting order by name.
   *
   * @return
   */
  public List<Room> getRooms() {
    return this.rooms.stream()
        .sorted(Comparator.comparing(Room::getName))
        .collect(Collectors.toUnmodifiableList());
  }
}
