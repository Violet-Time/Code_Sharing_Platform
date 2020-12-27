package platform.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonPropertyOrder({ "id", "date", "code" })
@Entity
public class Code {
    @Id
    UUID id;
    String code;
    LocalDateTime date;
    long time;
    int views;
    @Transient
    final DateTimeFormatter formatter;

    public Code() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = UUID.randomUUID();
    }

    public Code(String code, long time, int views) {
        this();
        this.code = code;
        this.time = time;
        this.views = views;
        date = LocalDateTime.now();
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public String getStringId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    @JsonGetter(value = "date")
    public String getStringDate() {
        return this.date.format(formatter);
    }

    @JsonIgnore
    public LocalDateTime getDate() {
        return this.date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDateTime data) {
        this.date = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String toString() {
        return "Code(code=" + this.getCode() + ", data=" + this.getStringDate() + ")";
    }
}
