package ru.javawebinar.graduation.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
}
