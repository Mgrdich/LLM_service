package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(
        callSuper = true,
        exclude = {"discussions"})
@Entity
@Table(name = "conversation")
@Data
public class ConversationEntity extends BaseEntity {
    @OneToMany(mappedBy = "conversation", orphanRemoval = true)
    private List<DiscussionEntity> discussions;

    @Column(name = "title")
    private String title;

    @Override
    public String toString() {
        return "ConversationEntity{" + "id=" + this.getId() + "}";
    }
}
