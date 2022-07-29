package com.example.mmproject.domain.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mentor;

    @Column
    private String mentee;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Message> messages = new ArrayList<>();

    public Room(String mentor, String mentee){
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
