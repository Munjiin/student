package dao;

import java.util.ArrayList;
import java.util.List;

import domain.BoardVO;
import domain.PageDTO;

public class BoardDAO {
    private static  final String LIST = "select *\n" +
            "from (select\n" +
            "             /*+ INDEX_DESC (tbl_board pk_board) */\n" +
            "          ROWNUM rn, bno, title, content, WRITER, REGDATE, UPDATEDATE, viewcnt \n" +
            "      from TBL_BOARD\n" +
            "      where bno > 0\n" +
            "        and ROWNUM <= (? * ?))\n" +
            "where rn > (?-1) * ?";
	
	public List<BoardVO> getList(PageDTO pageDTO)throws Exception{
        List<BoardVO> list = new ArrayList<>();

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(LIST);
                int i = 1;
                stmt.setInt(i++,pageDTO.getPage());
                stmt.setInt(i++,pageDTO.getSize());
                stmt.setInt(i++,pageDTO.getPage());
                stmt.setInt(i++,pageDTO.getSize());
                rs = stmt.executeQuery();

                while(rs.next()){
                    BoardVO vo = new BoardVO();
                    int idx = 2;
          
                    vo.setMno(rs.getInt(idx++));
                    vo.setBno(rs.getInt(idx++));
                    vo.setName(rs.getString(idx++));
                    vo.setId(rs.getString(idx++));
                    vo.setTitle(rs.getString(idx++));
                    vo.setCnt(rs.getString(idx++));
                    vo.setAnswer(rs.getString(idx++));
                    vo.setAddfile(rs.getString(idx++));
                    vo.setRegdate(rs.getDate(idx++));
                    vo.setUpdatedate(rs.getDate(idx++));
                  
                    list.add(vo);
                }

            }
        }.executeAll();

        return list;
    }

}
