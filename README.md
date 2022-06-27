# 2022-06-27 (월)
## 1. Entity

정의 - DB에 쓰일 필드와 여러 엔티티간 연관관계를 정의한다.

테이블 - 엔티티
열 - Column
행 - Entity 객체

필드 - Entity의 각 Column을 의미한다.

```java
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;
  
  private String title;
  
  private String content;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Member writer;
  
  public void changeTitle(String title) { this.title = title; }
```

### 1.1 어노테이션

**@Entity**
클래스 위에 선언하여 이 클래스가 엔티티임을 알려준다.
JPA에서 정의된 필드들을 바탕으로 데이터베이스에 테이블을 만들어준다.

**@Builder**
해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다.
이렇게 선언해놓으면 나중에 다른 곳에서 Board.builder(). 
{여러가지 필드의 초기값 선언 }. build() 형태로 객체를 만들 수 있다.

**@AllArgsConstructor**
선언된 모든 필드를 파라미터로 갖는 생성자를 자동으로 만들어준다.
**@NoArgsConstructor**
파라미터가 아예없는 기본생성자를 자동으로 만들어준다.

**@Getter**
각 필드값을 조회할 수 있는 getter를 자동으로 생성해준다.
변수는 보통 직접 접근 및 변경이 안되도록 private 선언자를 통해 지정한다. 

**@ToString**
해당 클래스에 선언된 필드들을 모두 출력할 수 있는 toString 메서드를 자동으로 생성할 수 있도록 해준다.

**@Id, @GeneratedValue**
해당 엔티티의 주요 키(Primary Key, PK)가 될 값을 지정해주는 것이 @Id 이다. 
@GeneratedValue는 이 PK가 자동으로 1씩 증가하는 형태로 생성될지 등을 결정해주는 어노테이션이다.

**@ManyToOne**
해당 엔티티와 다른 엔티티를 관계짓고 싶을 때 쓰는 어노테이션이다. 
ManyToOne이라고 부르는 이유는 Writer 입장에서 Board는 여러 개가 될 수 있기 때문에 Writer : Board = 1 : N 관계가 되기 때문이다.
