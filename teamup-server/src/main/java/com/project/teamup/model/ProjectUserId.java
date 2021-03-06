package com.project.teamup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUserId implements Serializable {
    @Column(name = "project_id")
    private Long projectId;
    @Column(name = "user_id")
    private Long userId;
}
