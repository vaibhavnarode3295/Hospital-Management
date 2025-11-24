package com.hospitalManagement.Proj1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Entity
@Table(name="Hospital_Users")
@Getter
@Setter
@SQLDelete(sql="UPDATE HOSPITAL_USERS SET STATUS='deleted' WHERE ID=?")
@SQLRestriction("STATUS <> 'deleted'")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    private String status="active";
}
