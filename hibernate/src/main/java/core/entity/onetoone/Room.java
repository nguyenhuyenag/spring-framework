package core.entity.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Room {
 
    @Id
    @GeneratedValue
    private Integer id;
 
    @Column
    private String name;
 
    @Column
    private String hotel;
 
    @OneToOne(mappedBy = "room")
    private Tourist tourist;
 
}
