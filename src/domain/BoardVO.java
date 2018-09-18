package domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private int mno, bno;
	private String name, id, title, cnt, answer, addfile;
	private Date regdate, updatedate;

}
