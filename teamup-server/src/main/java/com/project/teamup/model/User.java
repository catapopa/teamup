package com.project.teamup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    @JsonIgnore
    private String password;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    @Column
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;
    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts;
    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "supervisor")
    private User supervisor;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column
    @Enumerated(EnumType.STRING)
    private UserSeniority seniority;
    @Column
    @Enumerated(EnumType.STRING)
    private UserLanguage language;
    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProjectUserExperience> projectExperiences;
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserSkill> skills;

    public void setProjectExperiences(List<ProjectUserExperience> projectExperiences) {
        if (this.projectExperiences != null){
            this.projectExperiences.clear();
        }
        if (projectExperiences != null){
            if (this.projectExperiences != null){
                this.projectExperiences.addAll(projectExperiences);
            } else {
                this.projectExperiences = new ArrayList<>(projectExperiences);
            }
        }
    }

    public void setSkills(List<UserSkill> skills) {
        if (this.skills != null){
            this.skills.clear();
        }
        if(skills != null){
            if (this.skills != null){
                this.skills.addAll(skills);
            } else {
                this.skills = new ArrayList<>(skills);
            }
        }
    }
}
