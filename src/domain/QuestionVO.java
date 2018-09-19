package domain;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionVO {
	private int qno, limittime, mno, reply;
	private String question, cmt, name, id;
	private Date regdate, replydate;
	private Double time;
	
}
