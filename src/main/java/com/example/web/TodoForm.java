package com.example.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TodoForm {
  @NotNull
  @Size(min = 1, max = 127)
  private String description;

  @NotNull
  @Size(min = 1, max = 127)
  private String category;

  private Boolean finish = false;
}
