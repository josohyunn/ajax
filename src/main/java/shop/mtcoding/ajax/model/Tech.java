package shop.mtcoding.ajax.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // DB 조회 -> PC에 Category 객체 생성. 없으면 터짐
@Setter
@Getter
@Table(name = "tech_tb")
@Entity
public class Tech {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name; // Spring, Java, Javascript, React, HTML

    // @JsonIgnore // 직렬화 안됨 -> messageconverter가 발동하지 않기 때문에 json직렬화가 되지 않는다. 그래서
    // getter가 때려지지 않기
    // 때문에 lazy로딩이 일어나지 않는다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
