package com.pap.crm_project.team;

import com.pap.crm_project.applicationuser.ApplicationUser;
import com.pap.crm_project.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "teamLeader_id"
    )
    private ApplicationUser teamLeader;

    @ManyToMany()
    @JoinTable(
            name = "teams_users",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "application_user_id")
    )
    private List<ApplicationUser> teamMembers;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "team"
    )
    private List<Task> teamTasks;

    public Team(String name, ApplicationUser teamLeader) {
        this.name = name;
        this.teamLeader = teamLeader;
    }

    @Override
    public String toString() {
        return name;
    }
}