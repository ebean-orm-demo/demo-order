package app.data;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.avaje.ebean.annotation.CreatedTimestamp;

@MappedSuperclass
public class Domain {

    @Id
    private Integer id;

    @CreatedTimestamp
    private Timestamp cretime;

    @Version
    private Timestamp updtime;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCretime() {
		return cretime;
	}

	public void setCretime(Timestamp cretime) {
		this.cretime = cretime;
	}

	public Timestamp getUpdtime() {
		return updtime;
	}

	public void setUpdtime(Timestamp updtime) {
		this.updtime = updtime;
	}
    
}
