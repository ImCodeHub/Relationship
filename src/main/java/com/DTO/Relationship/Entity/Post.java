package com.DTO.Relationship.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

//  (Many post can belong to One User)
    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

//  (one post can have multiple comments)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        this.dateTime = LocalDateTime.now();
    }
}
