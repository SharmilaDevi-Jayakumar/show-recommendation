package com.kyro.showrecommendation.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kyro.showrecommendation.Converter.ListToStringConverter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Data
@Getter
@Builder
@Setter
@Entity
@Table(name = "user_history")
@DynamicUpdate
public class UserHistoryEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "USER_HISTORY_SEQ", sequenceName = "USER_HISTORY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_HISTORY_SEQ")
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "show_history")
    @Convert(converter = ListToStringConverter.class)
    private List<String> showHistory = new ArrayList<>();

    public UserHistoryEntity() {
    }

    public UserHistoryEntity (Long historyId, Long userId, List<String> showHistory) {

        this.historyId = historyId;
        this.userId = userId;
        this.showHistory = showHistory;
    }
}
