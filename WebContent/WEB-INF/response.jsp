<%--
  Created by IntelliJ IDEA.
  User: 5CLASS-184
  Date: 2018-09-12
  Time: 오후 7:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>

<!-- 제목 -->

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h2 class="page-header">이해했니?</h2>
		</div>
	</div>


	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default panel-primary">
				<div class="panel-heading">
					<h3>${question.get(0).qno}.${question.get(0).question}</h3></div>

				<!-- /.panel-heading -->
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<div class="panel-body">
						<c:if test="${response.size()!=0}">
							<thead>
								<tr>
									<th>내가 쓴 답</th>
									<th>총인원</th>
									<th>이해했어요!</th>
									<th>이해못했어요ㅠ</th>
									<th>무응답</th>
									<th>퍼센트(이해했어요/총인원)</th>
								</tr>
							</thead>
							<tbody>
								<tr class="odd gradeX">
									<td>${response.get(0).reply==1?"이해했어요":"이해못했어요"}</td>
									<td>${result.total}</td>
									<td>${result.res_yes}</td>
									<td>${result.res_no}</td>
									<td>${result.noresponse}</td>
									<td>${result.percent}%</td>
								</tr>
							</tbody>
						</c:if>
						<c:if test="${response.size()==0}">
							<br> 해당 질문에 응답하지 않아 결과를 볼 수 없습니다.
                      </c:if>
					</div>
				</table>
				
				
				
				
				<div class="text-center">
					<div class="well">
						<a href="qlist?page=${page}">
							<button class="btn btn-primary btn-lg btn-block">목록보기</button>
						</a>
					</div>
				</div>
				<!-- /.table-responsive -->
			</div>
		</div>
	</div>
	<!-- /.panel-body -->
	<!-- /.panel -->
</div>
<!-- /.col-lg-12 -->
<!-- /.row -->


<%@ include file="includes/footer.jsp"%>









































<%-- 
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div style="height: auto; max-width: 1080px; text-align: left;">

							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="panel panel-default">
							<div style="height: 350px; max-width: 1080px; text-align: left">
								<div class="text-center">
									<c:if test="${response.size()!=0}">
										<c:forEach var="response" items="${response}">
											<br>내가 쓴 답:
                      ${response.reply==1?"이해했어요":"이해못했어요"}
                     
                     <br>결과 :
                      ${response.percent}%
                      </c:forEach>

									</c:if>
								</div>
								<div class="text-center">
									<c:if test="${response.size()==0}">
										<br> 해당 질문에 응답하지 않아 결과를 볼 수 없습니다.
                      </c:if>
								</div>
							</div>
						</div>
					</div>
					<a href="qlist?page=${page}">
						<button type="button" class="btn btn-outline btn-primary btn-sm">목록보기</button>
					</a>
				</div>
			</div>
 --%>





