package com.project.teamup.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Data
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class Translation {
    private Long entityId;
    private UserLanguage language;
    private String name;
}
