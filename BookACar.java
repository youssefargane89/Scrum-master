package ma.ac.emi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import ma.ac.emi.dto.BookACarDto;
import ma.ac.emi.enums.BookCarStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data

public class BookACar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fromDate;
    private Date toDate;
    private long days;
    private long price;
    private BookCarStatus bookCarStatus;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;
    public BookACarDto getBookACarDTO(){
      BookACarDto bookACarDto = new BookACarDto();
      bookACarDto.setId(id);
      bookACarDto.setDays(days);
      bookACarDto.setPrice(price);
      bookACarDto.setBookCarStatus(bookCarStatus);
      bookACarDto.setToDate(toDate);
      bookACarDto.setFromDate(fromDate);
      bookACarDto.setEmail(user.getEmail());
      bookACarDto.setUsername(user.getName());
      bookACarDto.setUserId(user.getId());
      bookACarDto.setCarId(car.getId());
      return bookACarDto;

    }
}
