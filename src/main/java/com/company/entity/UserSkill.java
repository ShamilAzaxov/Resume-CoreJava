package com.company.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSkill {

    private Integer id;
    private User user;
    private Skill skill;
    private int power;
}
