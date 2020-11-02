package com.company.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Room {

  private final String id;
  private final String name;

  public Room(final String name) {
    this.name = name;
    this.id = UUID.randomUUID().toString();
  }
}
