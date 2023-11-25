package ra.academy.model;

import javax.persistence.*;

@Entity // thực thể map với bảng
@Table(name = "catalog") // tên bảng
public class Catalog {
    @Id // khóa chính
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public Catalog() {
    }

    public Catalog(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public  void copy(Catalog cat){
        this.name = cat.name;
        this.description= cat.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
