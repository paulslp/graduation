package ru.javawebinar.graduation.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {
}
