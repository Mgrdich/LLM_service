package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@EqualsAndHashCode(
        callSuper = true,
        exclude = {"discussions"})
@Entity
@Table(name = "conversation")
@Data
public class ConversationEntity extends BaseEntity {
    @OneToMany(mappedBy = "conversation", orphanRemoval = true)
    private List<DiscussionEntity> discussions;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "title")
    private String title;

    @Override
    public String toString() {
        return "ConversationEntity{" + "id=" + this.getId() + "}";
    }
}
