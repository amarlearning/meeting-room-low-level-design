package com.company.model;

import lombok.Getter;

import java.util.UUID;

public class Meeting {

  private final String id;

  @Getter private int startTime;
  @Getter private int endTime;

  @Getter private Room room;

  /**
   * Constructor to create a meeting for the given start, end time and room.
   *
   * @param startTime Integer
   * @param endTime Integer
   * @param room Room
   */
  public Meeting(int startTime, int endTime, Room room) {
    this.id = UUID.randomUUID().toString();
    this.startTime = startTime;
    this.endTime = endTime;
    this.room = room;
  }
}
